package com.allen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class DefaultHandlerInterceptor implements HandlerInterceptor {

	// handle处理前 简单打印HandlerMethod 执行(Method#invoke)之前处理的操作 比如打印方法
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		System.out.printf("handler object : %s \n", handler.toString());
		return true;
	}
}
