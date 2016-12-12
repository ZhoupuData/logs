package com.zhoupu.zplogsbatch.config;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

//@Configuration
public class MyErrorViewConfig {

	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return new MyErrorPageRegistrar();
	}

	private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

		/**
		 * 定义详细的错误页面
		 */
		@Override
		public void registerErrorPages(ErrorPageRegistry registry) {
			registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/404"));
			registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
			registry.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/404"));
			registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
		}

	}
}
