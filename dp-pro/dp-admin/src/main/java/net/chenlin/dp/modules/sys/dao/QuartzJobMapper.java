package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.QuartzJobEntity;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 定时任务
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

}
