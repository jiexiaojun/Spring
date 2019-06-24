package com.allen.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.allen.account.service.AccountService;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

	@Autowired
	AccountService accountService;

	@Test
	public void testTransfer() {
		try {
			accountService.transfer("汪俊", "刘丽", 9981d);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
