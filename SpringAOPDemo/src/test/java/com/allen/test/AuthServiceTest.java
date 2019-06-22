package com.allen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.allen.aop.service.AuthService;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceTest {

	@Autowired
	AuthService authService;

	@Test
	public void testLogin() {
		authService.login("", "");
	}

	public void testLogout() {
		authService.logout("");
	}

}
