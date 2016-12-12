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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhoupu.zplogsbatch.commons.Result;
import com.zhoupu.zplogsbatch.domain.Actions;
import com.zhoupu.zplogsbatch.service.ActionsService;

@Controller
@RequestMapping("/actions")
public class ActionsController extends BaseController {

	@Autowired
	private ActionsService actionsService;

	@GetMapping("/getactions")
	public String getActions() {

		return "saas/actions";
	}
	
	
	@GetMapping("/geteditactions/{id}")
	public String getEditactions(ModelMap modelMap,@PathVariable("id") Long id) {
		Actions actions = null;
        if(0==id){
        	actions = new Actions();
        }else{
        	actions = actionsService.findOne(id);
        }
        modelMap.put("actions", actions);
		return "saas/editactions";
	}
	
	
	@PostMapping(value = "/save")
	public @ResponseBody Result save(Actions actions) {
		actionsService.save(actions);
		return Result.ok();
	}
	
	@DeleteMapping(value = "/delete/{ids}")
	public @ResponseBody Result delete(@PathVariable("ids") String ids) {
		actionsService.delete(ids);
		return Result.ok();
	}

	@GetMapping(value = "")
	public @ResponseBody Map<String, Object> getAll(@RequestParam MultiValueMap<String, Object> param) {

		Map<String, Object> map = getCondition(param);

		return actionsService.getInfoListMap(map);

	}

}
