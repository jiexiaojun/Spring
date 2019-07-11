package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.sys.entity.SysAreaEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 行政区域
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {

	/**
	 * 根据上级id查询子节点集合
	 * @param query
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(Query query);

	/**
	 * 统计子节点数量
	 * @param areaId
	 * @return
	 */
	int countAreaChildren(Long areaId);
	
}
