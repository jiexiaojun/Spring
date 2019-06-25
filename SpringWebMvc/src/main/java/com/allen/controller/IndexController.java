package com.allen.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// 这里只使用@Controller注解 可以映射到对应的页面
@Controller
public class IndexController {

	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}

	// 直接跳转到页面
	@RequestMapping("/404")
	public String pageNotFound(HttpServletRequest request) {
		// 这里想讲数据返回给前端html页面 暂时没时间实现
		request.setAttribute("statusCode", request.getAttribute("javax.servlet.error.status_code"));
		request.setAttribute("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
		return "404";
	}

	/**
	 * 处理页面找不到的情况
	 * @param status
	 * @param request
	 * @param throwable
	 * @return
	 */

	// 返回的是json文本信息
	@ResponseBody
	@GetMapping("/505")
	public Map<String, Object> handlePageNotFound(HttpStatus status, HttpServletRequest request,
			Throwable throwable) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("statusCode", request.getAttribute("javax.servlet.error.status_code"));
		errors.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
		return errors;
	}
}
