package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.QuartzJobLogEntity;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 定时任务日志
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

	/**
	 * 清空日志
	 * @return
	 */
	int batchRemoveAll();
	
}
