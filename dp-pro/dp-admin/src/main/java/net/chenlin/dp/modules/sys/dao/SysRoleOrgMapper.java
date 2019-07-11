package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.SysRoleOrgEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 角色与机构的关系
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	/**
	 * 查询角色的机构集合
	 * @param roleId
	 * @return
	 */
	List<Long> listOrgId(Long roleId);

	/**
	 * 根据机构id删除角色与机构的关系
	 * @param id
	 * @return
	 */
	int batchRemoveByOrgId(Long[] id);

	/**
	 * 根据角色id删除角色与机构的关系
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);
	
}
