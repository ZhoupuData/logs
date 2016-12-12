/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhoupu.zplogsbatch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhoupu.zplogsbatch.annotation.UncheckLogin;
import com.zhoupu.zplogsbatch.commons.Result;
import com.zhoupu.zplogsbatch.commons.SendMsg;
import com.zhoupu.zplogsbatch.commons.Utils;
import com.zhoupu.zplogsbatch.domain.User;
import com.zhoupu.zplogsbatch.service.UserService;

@Controller
public class MainController extends BaseController {
	
	
	@Value("${server.init.sendmsg:false}")
	private boolean sendmsg;
	
	@Value("${server.init.msg:123456123}")
	private String msg;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaffeineCache caffeineCache;
	
	@Autowired
	private SendMsg taobaoSendMsg;

	@UncheckLogin
	@GetMapping("/tologin")
	public String toLogin(HttpServletRequest request) {
		if (null != request.getSession().getAttribute("user")) {
            return "autologin";
        }
		return "login";
	}

	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@UncheckLogin
	@PostMapping("/login")
	public @ResponseBody Result login(HttpServletRequest request,String username, String password) {

		User user = userService.findUserByUsername(username);

		if (user == null) {
			return Result.error("该用户不存在");
		}

		ValueWrapper vm = caffeineCache.get(username);
		if(null == vm){
			return Result.error("登录验证失败");
		}
		
		user = (User)vm.get();
		if(null != user && user.getPassword().equals(password) && user.getUsername().equals(username)){
			request.getSession().setAttribute("user", user);
			
			caffeineCache.evict(username);
			
			return Result.ok();
		
		}else{
			return Result.error("登录验证失败");
		}
		
	}
	
	
	@UncheckLogin
	@PostMapping(value = "/sendPhonecode")
	public @ResponseBody Result sendPhonecode(HttpServletRequest request, String phone) {
		if(StringUtils.isEmpty(phone)){
			return Result.error("该用户不存在");
		}

		phone = phone.trim();
		
		User user = userService.findUserByUsername(phone);

		if (user == null) {
			return Result.error("该用户不存在");
		}
		
		
		ValueWrapper vm = caffeineCache.get(phone);
		if(null == vm){
			if (sendmsg) {
				String vcode = Utils.getVerCode();
				user.setPassword(vcode);
				String res = taobaoSendMsg.sendMsg(new String[] { phone, vcode, "2" });
				if (StringUtils.isNotEmpty(res) && res.contains("SUCCESS")) {
					caffeineCache.put(phone, user);
					return Result.ok();
				} else {
					return Result.error("短信发送失败");
				}
			}else{
				user.setPassword(msg);
				caffeineCache.put(phone, user);
				return Result.ok();
			}
			
		}else{
			return Result.error("请勿频繁发送短信");
		}
		
		
		
		
	}

	@UncheckLogin
	@RequestMapping("/reload")
	public String reload() {

		return "reload";
	}

	/**
	 * 
	 * @return
	 */
	@UncheckLogin
	@PostMapping("/logout")
	public @ResponseBody Result logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		return Result.ok();
	}
}
