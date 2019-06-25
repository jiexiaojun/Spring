package com.allen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Demo Controller
 *
 * @author mercyblitz
 * @date 2017-10-11
 **/
@RestController // = @Controller + @ResponseBody
public class RestDemoController {

	@RequestMapping("/index")
	// @PostMapping // Post 请求 @RequestMapping(method = RequestMethod.POST) Create(C)
	// @GetMapping // GET 请求 @RequestMapping(method = RequestMethod.GET) Read(R)
	// @PutMapping // Put 请求的 @RequestMapping(method = RequestMethod.PUT) Update(U)
	// @DeleteMapping // Post 请求 @RequestMapping(method = RequestMethod.DELETE) Delete(D)
	// Windows 通过 PostMan 来测试
	// curl -X POST
	public String index() {
		return "Hello,World";
	}

	@RequestMapping("/exception")
	public String exception() throws Exception {
		List<String> list = new ArrayList<>();
		return list.get(0);// 模拟空指针异常
	}

	// 返回json格式数据
	@RequestMapping("/data")
	public Map<String, Object> data() {
		Map<String, Object> data = new HashMap<>();
		data.put("username", "小马哥");
		data.put("password", "123456");
		return data;
	}

}
