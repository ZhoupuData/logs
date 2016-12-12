package com.zhoupu.zplogsbatch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhoupu.zplogsbatch.authority.UncheckLoginAnnotationInterceptor;

/**
 * [简要描述]:WebMvcConfigurationSupport代替 WebMvcConfigurerAdapter 可以进行更多类型的定制。
 * <br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年3月2日 下午1:38:03
 * @version 1.0 *
 * @since
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UncheckLoginAnnotationInterceptor()).addPathPatterns("/**");
	}

}
