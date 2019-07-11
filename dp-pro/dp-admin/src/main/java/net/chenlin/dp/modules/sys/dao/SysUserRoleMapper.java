package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.SysUserRoleEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 户与角色关系
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

	/**
	 * 查询用户角色id集合
	 * @param userId
	 * @return
	 */
	List<Long> listUserRoleId(Long userId);

	/**
	 * 根据用户id删除用户与角色的关系
	 * @param id
	 * @return
	 */
	int batchRemoveByUserId(Long[] id);

	/**
	 * 根据角色id删除用户与角色的关系
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);
	
}
