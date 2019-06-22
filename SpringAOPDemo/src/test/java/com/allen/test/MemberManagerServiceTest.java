package com.allen.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.allen.aop.service.MemberManagerService;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberManagerServiceTest {

	@Autowired
	MemberManagerService memberManagerService;


	// 单元测试过程 加载application-context.xml文件
	// 然后依次加载application-beans.xml和application-common.xml文件
	// 本项目中beans是空的，common中扫描包 以便测试中将MemberManagerService注入进来
	// 当application-context.xml文件文件中aop文件被注释掉的时候 此时没有采用AOP 只执行了add方法
	// 当application-context.xml文件文件中aop文件没有被注释掉的时候 此时AOP生效了 ，在调用方法前后执行了LogAspect中的方法
	@Test
	@Ignore
	public void testAdd() {
		memberManagerService.add(null);
	}


	// 做事务代理的时候
	// TracationManage来管理事务操作（切面）
	// DataSource ，SessionFactory(DataSource)
	// DataSource 包含了连接信息，事物的提交或者回滚一些基础功能
	// 通过连接点是可以获取到方法（切点）具体操作哪个DataSource
	// 通过切面通知类型，去执行DataSource的功能方法

	// 完全裸露，一丝不挂
	@Test
//	@Ignore
	public void testRemove() {
		try {
			memberManagerService.remove(0);
		} catch (Exception e) {
		}
	}

	@Test
	@Ignore
	public void testModify() {
		memberManagerService.modify(null);
	}

	@Test
	@Ignore
	public void testQuery() {
		memberManagerService.query("");
	}

}
