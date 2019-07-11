package net.chenlin.dp.modules.sys.controller;

import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.chenlin.dp.common.util.ShiroUtils;

/**
 * Controller公共组件
 * @author zcl<yczclcn@163.com>
 */
public abstract class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
	
}
