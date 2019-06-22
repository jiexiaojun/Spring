package com.allen.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.allen.dao.StudentDao;
import com.allen.model.Student;
import com.allen.model.Teacher;
import com.allen.utils.Result;

@Service
public class StudentServiceImpl implements StudentService {


	@Autowired
	private StudentDao studentDao;


	public Result addStudent(Student s) {
		Result r = new Result();
		Student student = new Student();
		student.setAge(18);
		student.setName("刘丽");
		student.setPassword("1314520");
		studentDao.save(student);
		r.setError(false);
		r.setMsg("添加");
		return r;
	}

	public Result delStudent(Student s) throws Exception {
		Result r = new Result();
		Student student = (Student) studentDao.get(1);
		System.out.println(student.getName());
		studentDao.delete(student);
		r.setError(false);
		r.setMsg("删除");
		return r;
	}


	public Result findStudent(Student s) throws Exception {
		Result r = new Result();
		String hql = "from Student";
//		String sql="select id,age,name,password from student";
		List<Student> list = studentDao.find(hql);
//		List<Student> listA = studentDao.findBySql(sql);
		for (Student student : list) {
			System.out.println(
					student.getAge() + "=" + student.getName() + "=" + student.getPassword());
			Set<Teacher> teachers = student.getTeachers();
			for (Teacher teacher : teachers) {
				System.out.println(teacher.getAge() + "=" + teacher.getName());
			}
		}
		r.setRows(list);
		r.setError(false);
		r.setMsg("查询");
		return r;
	}


	// 测试事物 更新失败 导致插入也不成功 事物回滚
	public Result testTx(Student s) throws Exception {
		Result r = new Result();
		Student student = new Student();
		student.setId(1);
		student.setAge(18);
		student.setName("刘丽");
		student.setPassword("1314520");
		studentDao.save(student);// 插入成功

		Student student1 = (Student) studentDao.get(3);
		student1.setId(5);
		student1.setName("你真的好美呀");
		studentDao.update(student1);// 更新失败

		r.setError(false);
		return r;
	}

}
