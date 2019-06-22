package com.allen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allen.model.Student;
import com.allen.service.StudentService;
import com.allen.utils.Result;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;


	@ResponseBody
	@RequestMapping("/addStudent")
	public Result addStudent(Student s) throws Exception {
		return studentService.addStudent(s);
	}

	@ResponseBody
	@RequestMapping("/delStudent")
	public Result delStudent(Student s) throws Exception {
		return studentService.delStudent(s);
	}

	@ResponseBody
	@RequestMapping("/findStudent")
	public Result findStudent(Student s) throws Exception {
		return studentService.findStudent(s);
	}

	@ResponseBody
	@RequestMapping("/testTx")
	public Result testTx(Student s) throws Exception {
		return studentService.testTx(s);
	}

}
