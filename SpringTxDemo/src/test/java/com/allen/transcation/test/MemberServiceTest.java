package com.allen.transcation.test;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import com.allen.transcation.entity.Member;
import com.allen.transcation.service.MemberService;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Test
	@Ignore
	public void testQuery() {
		try {
			List<Member> list = memberService.query();
			System.out.println(JSON.toJSONString(list, true));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
//	@Ignore
	public void testAdd() {
		try {
			boolean r = memberService.add("4", "迪丽热巴");
			System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	@Ignore
	public void testRemove() {
		try {
			boolean r = memberService.remove(3L);
			System.out.println(r);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	@Ignore
	public void testTx() {
		try {
			memberService.testTx(1, "测试事物");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
