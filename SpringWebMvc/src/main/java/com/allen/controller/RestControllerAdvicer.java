package com.allen.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 注解异常全局处理 
@RestControllerAdvice(basePackages = "com.allen.controller")
public class RestControllerAdvicer {


	// 指定哪些异常 将异常信息返回给客户端
	@ExceptionHandler(value = {NullPointerException.class, IllegalAccessException.class,
			IllegalStateException.class, IndexOutOfBoundsException.class})
	public Object handleNPE(Throwable throwable) {
		Map<String, Object> data = new HashMap<>();
		data.put("Message", throwable.getMessage());
		data.put("StackTrace", throwable.getStackTrace());
		throwable.printStackTrace();
		return data;
	}


}
