package com.allen.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.allen.domain.Person;


@RestController
public class PersonRestController {

	@GetMapping("/person/{id}")
	public Person person(@PathVariable Long id, @RequestParam(required = false) String name) {
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		return person;
	}


	// consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	// produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

	// @RequestBody 的内容是 JSON
	// 响应的内容是 Properties
	@PostMapping(value = "/person/json/to/properties", consumes = "application/json;charset=UTF-8", // 请求类型Content-Type
			produces = "application/properties+person") // 响应类型Accept
	public Person personJsonToProperties(@RequestBody Person person) {
		return person;
	}

	// @RequestBody 的内容是 Properties
	// 响应的内容是 JSON
	@PostMapping(value = "/person/properties/to/json", consumes = "application/properties+person", // 请求类型Content-Type
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // 响应类型 Accept
	public Person personPropertiesToJson(@RequestBody Person person) {
		return person;
	}

}
