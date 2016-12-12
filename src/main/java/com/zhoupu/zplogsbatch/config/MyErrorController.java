package com.zhoupu.zplogsbatch.config;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	
	@RequestMapping(value = ERROR_PATH)//如果没有开启ErrorPageRegistrar。错误的页面都走这里，不区分40X，50X
	public String handleError() {
		return ERROR_PATH;
	}
	
	@Override
	public String getErrorPath() {//定义统一的错误页面
		return ERROR_PATH;
	}

	@RequestMapping("/400")
	public String error400() { //开启ErrorPageRegistrar或，详细错误页面调整拦截
		return "404";
	}

	@RequestMapping("/404")
	public String error404() {//开启ErrorPageRegistrar或，详细错误页面调整拦截
		return "404";
	}

	@RequestMapping("/500")
	public String error500() {//开启ErrorPageRegistrar或，详细错误页面调整拦截
		return "500";
	}

}
