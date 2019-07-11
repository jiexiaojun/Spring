package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 系统日志
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	/**
	 * 清空日志
	 * @return
	 */
	int batchRemoveAll();
	
}
