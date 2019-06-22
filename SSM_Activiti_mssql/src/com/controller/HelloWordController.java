package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.utils.ApiResult;

import com.service.HelloWordService;


@Controller
@RequestMapping(value="/helloword")
public class HelloWordController {
	
	@Autowired
	HelloWordService helloWordService;
	
	
	@ResponseBody
	@RequestMapping(value="/getLists")
	public ApiResult getLists() throws Exception 
	{
		return helloWordService.getLists();
	}
	
	
	@RequestMapping("/errorPage")
	public String addPage() throws Exception{
		return "/error/404";
	}	
	
	
	@RequestMapping("/helloPage")
	public String helloPage() throws Exception{
		return "/jsp/helloword";
	}
	
}
