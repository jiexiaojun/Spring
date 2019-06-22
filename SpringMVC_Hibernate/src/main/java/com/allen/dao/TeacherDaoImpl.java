package com.allen.dao;

import org.springframework.stereotype.Repository;
import com.allen.model.Teacher;
import com.allen.utils.BaseDaoImpl;


/** 
 * TeacherDao的实现类
 * 继承BaseDaoImpl 显示TeacherDao接口 
 * @author Allen 
 */
@Repository(value = "TeacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao {

	/** 
	 * 若TeacherDao 定义了BaseDao没有的方法，则可以在这里实现 
	 */

}
