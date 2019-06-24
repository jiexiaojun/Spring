package com.allen.transcation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.allen.transcation.dao.MemberDao;
import com.allen.transcation.entity.Member;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;

	public List<Member> query() throws Exception {
		return memberDao.select();
	}


	public boolean remove(long id) throws Exception {
		return memberDao.delete(id) > 0;
	}


	public boolean modify(long id, String name) throws Exception {
		return memberDao.update(id, name) > 0;
	}


	public boolean add(String id, String name) throws Exception {
		boolean r = memberDao.insert(id, name) > 0;
		return r;
	}

//	第一层   login本身是一个事务
	@Transactional
	public void testTx(long id, String name) throws Exception {
		// 更新肯定成功
		memberDao.update(id, name);

		// 插入会失败 id在数据库中是数字 将3改成字符串 比如 哈哈哈 就会导致更新也失败 回滚
		memberDao.insert("3", name);
	}

}
