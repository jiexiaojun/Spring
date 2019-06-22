package com.allen.service;

import org.springframework.transaction.annotation.Transactional;
import com.allen.model.District;
import com.allen.utils.Result;

@Transactional(rollbackFor = Exception.class)
public interface DistrictService {

	Result create(District test) throws Exception;

	Result selectByPrimaryKey(District test) throws Exception;

	Result testTx(District test) throws Exception;



}
