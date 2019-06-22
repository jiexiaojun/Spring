package com.allen.mapper;

import com.allen.model.District;

public interface DistrictMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(District record);

	int insertSelective(District record);

	District selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(District record);

	int updateByPrimaryKey(District record);
}
