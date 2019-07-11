package net.chenlin.dp.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.chenlin.dp.modules.sys.dao.SysOrgMapper;
import net.chenlin.dp.modules.sys.dao.SysRoleOrgMapper;
import net.chenlin.dp.modules.sys.entity.SysOrgEntity;
import net.chenlin.dp.modules.sys.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.constant.MsgConstant;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;

/**
 * 组织机构
 * @author zcl<yczclcn@163.com>
 */
@Service("sysOrgService")
public class SysOrgServiceImpl implements SysOrgService {

	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Autowired
	private SysRoleOrgMapper sysRoleOrgMapper;

	/**
	 * 查询机构列表
	 * @return
	 */
	@Override
	public List<SysOrgEntity> listOrg() {
		return sysOrgMapper.list();
	}

	/**
	 * 查询机构列表：ztree数据源
	 * @return
	 */
	@Override
	public List<SysOrgEntity> listOrgTree() {
		List<SysOrgEntity> orgList = sysOrgMapper.list();
		SysOrgEntity org = new SysOrgEntity();
		org.setOrgId(0L);
		org.setName("一级机构");
		org.setParentId(-1L);
		org.setOpen(true);
		orgList.add(org);
		return orgList;
	}

	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@Override
	public R saveOrg(SysOrgEntity org) {
		int count = sysOrgMapper.save(org);
		return CommonUtils.msg(count);
	}

	/**
	 * 根据id查询机构
	 * @param orgId
	 * @return
	 */
	@Override
	public R getOrg(Long orgId) {
		SysOrgEntity org = sysOrgMapper.getObjectById(orgId);
		return CommonUtils.msg(org);
	}

	/**
	 * 更新机构
	 * @param org
	 * @return
	 */
	@Override
	public R updateOrg(SysOrgEntity org) {
		int count = sysOrgMapper.update(org);
		return CommonUtils.msg(count);
	}

	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@Override
	public R bactchRemoveOrg(Long[] id) {
		boolean children = this.hasChildren(id);
		if(children) {
			return R.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysOrgMapper.batchRemove(id);
		sysRoleOrgMapper.batchRemoveByOrgId(id);
		return CommonUtils.msg(id, count);
	}

	/**
	 * 是否含有子机构
	 * @param id
	 * @return
	 */
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = sysOrgMapper.countOrgChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查询所有直接子机构集合
	 * @param parentId
	 * @return
	 */
	@Override
	public List<Long> listOrgChildren(Long parentId) {
		return sysOrgMapper.listOrgChildren(parentId);
	}

	/**
	 * 递归查询所有子机构集合
	 * @param parentId
	 * @return
	 */
	@Override
	public List<Long> getAllOrgChildren(Long parentId) {
		List<Long> orgIds = new ArrayList<>();
		List<Long> parentIds = listOrgChildren(parentId);
		recursionOrgChildren(parentIds, orgIds);
		return orgIds;
	}

	/**
	 * 递归查询子机构
	 * @param parentIds
	 * @param result
	 */
	public void recursionOrgChildren(List<Long> parentIds, List<Long> result) {
		for (Long parentId : parentIds) {
			List<Long> ids = listOrgChildren(parentId);
			if (ids.size() > 0) {
				recursionOrgChildren(ids, result);
			}
			result.add(parentId);
		}
	}

}
