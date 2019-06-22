package com.allen.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.allen.mapper.BaseDao;
import com.allen.mapper.DistrictMapper;
import com.allen.model.District;
import com.allen.utils.Result;


@Service
public class DistrictServiceImpl implements DistrictService {


	@Autowired
	private BaseDao baseDao;

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.allen.service.DistrictService#create(com.allen.model.District)
	 */
	public Result create(District district) throws Exception {
		Result r = new Result();
		district.setDistrictname("钻孔3");
		baseDao.insert("com.allen.mapper.DistrictMapper.insertSelective", district);

		SqlSession session = baseDao.getSqlSession();
		DistrictMapper mapper = session.getMapper(DistrictMapper.class);
		district.setDistrictname("钻孔4");
		mapper.insertSelective(district);
		r.setMsg("操作成功");
		return r;
	}


	public Result selectByPrimaryKey(District district) throws Exception {
		Result r = new Result();
		int id = district.getId();

		List<District> list = new ArrayList<>();
		District a = (District) baseDao
				.queryToObject("com.allen.mapper.DistrictMapper.selectByPrimaryKey", id);
		SqlSession session = baseDao.getSqlSession();
		DistrictMapper mapper = session.getMapper(DistrictMapper.class);
		District b = mapper.selectByPrimaryKey(id);
		list.add(a);
		list.add(b);
		r.setRows(list);
		return r;
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.allen.service.DistrictService#testTx(com.allen.model.District)
	 */
	public Result testTx(District district) throws Exception {
		district.setId(1);
		district.setDistrictname("测试事物");
		baseDao.update("com.allen.mapper.DistrictMapper.updateByPrimaryKeySelective", district);// 修改数据
		// 插入数据，不允许插入自增主键会报错 导致修改一起回滚
		baseDao.insert("com.allen.mapper.DistrictMapper.insert", district);
		return null;
	}



}
