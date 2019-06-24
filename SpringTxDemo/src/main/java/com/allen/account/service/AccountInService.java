package com.allen.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.allen.account.dao.AccountDao;

@Service
public class AccountInService {

	@Autowired
	AccountDao accountDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int transferForIn(final String in, Double money) throws Exception {
		int inCount = accountDao.upateForIn(in, money);
		return inCount;
	}
}
