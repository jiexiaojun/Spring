package com.allen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.allen.aop.dao.MemberDao;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberDaoTest {

	@Autowired
	MemberDao memberDao;

	// 这里执行不管怎么样都不会执行切面
	// 因为aop.xml文件中配置aop:pointcut是expression="execution(* com.allen.aop.service..*(..))"
	// service包里面的方法

	@Test
	public void testInsert() {
		memberDao.insert();
	}

}
