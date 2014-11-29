package org.voip.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.voip.config.Constants;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//check session
		String username = (String)request.getSession().getAttribute(Constants.USERNAME);
		String path = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		if(username==null && !path.equals("/login")){
			//redirect to login
			response.sendRedirect(request.getContextPath()+"/login");
		}
		return super.preHandle(request, response, handler);
	}
	
}

