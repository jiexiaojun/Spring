package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.utils.ApiResult;
import cn.utils.ApiStatus;

import com.dao.DataMangerDao;
import com.entity.HelloWord;


@Service
@SuppressWarnings("unchecked")
public class HelloWordServiceImpl  implements HelloWordService{

	@Autowired
	private DataMangerDao dataMangerDao;
	
	
	public ApiResult getLists() throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");	
		List<HelloWord> list = dataMangerDao.queryForList("helloword.helloword_getAllName", params);
		result.setData(list);
		return result; 
	}

}
