package com.allen.dao;

import org.springframework.stereotype.Repository;
import com.allen.model.Student;
import com.allen.utils.BaseDaoImpl;

/** 
 * StudentDao的实现类
 * 继承BaseDaoImpl 显示StudentDao接口 
 * @author Allen 
 */
@Repository(value = "StudentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	/** 
	 * 若StudentDao 定义了BaseDao没有的方法，则可以在这里实现 
	 */

}
