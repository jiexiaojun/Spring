package com.allen.service;

import org.springframework.transaction.annotation.Transactional;
import com.allen.model.Student;
import com.allen.utils.Result;

@Transactional(rollbackFor = Exception.class)
public interface StudentService {

	Result addStudent(Student s) throws Exception;

	Result delStudent(Student s) throws Exception;

	Result findStudent(Student s) throws Exception;

	Result testTx(Student s) throws Exception;



}
