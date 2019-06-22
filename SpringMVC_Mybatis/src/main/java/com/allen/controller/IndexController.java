/**
 * @项目名称：SpringMVC
 * @文件名称：DemoController.java
 * @所属包名：com.allen.controller
 * @创建时间：2019年5月9日下午2:17:59
 * @Copyright (c) 2019 SLPCB All Rights Reserved.
 */
package com.allen.controller;

/**
 * @类名称：DemoController
 * @类描述：TODO
 * @创建人：改成自己名字
 * @创建时间：2019年5月9日 下午2:17:59
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	// 视图解析 跳转到WEB-INF/view下面的hello.jsp
	@RequestMapping("/hello")
	public String demo() {
		return "hello";
	}


}

