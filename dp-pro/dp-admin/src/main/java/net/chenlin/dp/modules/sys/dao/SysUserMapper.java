package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 系统用户dao
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	SysUserEntity getByUserName(String username);

	/**
	 * 查询用户所有菜单id
	 * @param userId
	 * @return
	 */
	List<Long> listAllMenuId(Long userId);

	/**
	 * 查询用户所有机构id
	 * @param userId
	 * @return
	 */
	List<Long> listAllOrgId(Long userId);

	/**
	 * 用户修改密码
	 * @param query
	 * @return
	 */
	int updatePswdByUser(Query query);

	/**
	 * 修改用户状态
	 * @param query
	 * @return
	 */
	int updateUserStatus(Query query);

	/**
	 * 重置用户密码
	 * @param user
	 * @return
	 */
	int updatePswd(SysUserEntity user);
	
}
