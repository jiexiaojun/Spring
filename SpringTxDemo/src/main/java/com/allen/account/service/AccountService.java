package com.allen.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.allen.account.dao.AccountDao;

@Service
public class AccountService {

	@Autowired
	AccountDao accountDao;
	@Autowired
	AccountOutService outService;
	@Autowired
	AccountInService inService;

//	public void transferForOut(final String out,Double money) throws Exception{
//		int outCount = accountDao.upateForOut(out, money);
//		if(outCount == 0){
//			throw new Exception("转出失败");
//		}
//	}

//	public void transferForIn(final String in,Double money) throws Exception{
//		int inCount = accountDao.upateForIn(in, money);
//		
//		if(inCount == 0){
//			throw new Exception("转入失败");
//		}
//	}


	/**
	 * 转账逻辑
	 * @param out 由谁转出
	 * @param in 转给谁
	 * @param money 转多少钱
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void transfer(final String out, final String in, final Double money) throws Exception {

		if (out.equals(in)) {
			throw new Exception("自己给自己转账，没有意义");
		}

		inService.transferForIn(in, money);
		outService.transferForOut(out, money);

	}
}
