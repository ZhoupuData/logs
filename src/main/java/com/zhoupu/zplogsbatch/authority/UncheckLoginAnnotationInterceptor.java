package com.zhoupu.zplogsbatch.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.zhoupu.zplogsbatch.annotation.UncheckLogin;
import com.zhoupu.zplogsbatch.domain.User;

/**
 * 
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年3月2日 下午1:36:33
 * @version 1.0 *
 * @since
 */
public class UncheckLoginAnnotationInterceptor extends HandlerInterceptorAdapter {

	public UncheckLoginAnnotationInterceptor() {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod)
			throws Exception {

		int status = response.getStatus();
		// 交给错误页面处理
		if (400 <= status && status < 600) {
			return true;
		}
		HandlerMethod handler = null;
		UncheckLogin ucheckLogin = null;
		if (handlerMethod instanceof HandlerMethod) {
			handler = (HandlerMethod) handlerMethod;
			ucheckLogin = handler.getMethodAnnotation(UncheckLogin.class);
		} else if (handlerMethod instanceof ParameterizableViewController) {
			return true;
		} else {
			return false;
		}

		// 声明不检查权限,放行
		if (null != ucheckLogin) {
			return true;
		}
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();

		User user = (User) session.getAttribute("user");

		if (null == user) {
			response.sendRedirect(contextPath + "/reload");
			return false;
		}

		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}

}
