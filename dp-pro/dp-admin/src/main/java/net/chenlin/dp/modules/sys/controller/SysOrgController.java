package net.chenlin.dp.modules.sys.controller;

import java.util.List;

import net.chenlin.dp.modules.sys.entity.SysOrgEntity;
import net.chenlin.dp.modules.sys.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;

/**
 * 组织机构
 * @author zcl<yczclcn@163.com>
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends AbstractController {

	@Autowired
	private SysOrgService sysOrgService;
	
	/**
	 * 机构列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysOrgEntity> list() {
		return sysOrgService.listOrg();
	}
	
	/**
	 * 树形机构列表，机构新增、编辑
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysOrgEntity> select() {
		return sysOrgService.listOrgTree();
	}
	
	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	@SysLog("新增机构")
	@RequestMapping("/save")
	public R save(@RequestBody SysOrgEntity org) {
		return sysOrgService.saveOrg(org);
	}
	
	/**
	 * 根据id查询机构详情
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long orgId) {
		return sysOrgService.getOrg(orgId);
	}
	
	/**
	 * 修改机构
	 * @param org
	 * @return
	 */
	@SysLog("修改机构")
	@RequestMapping("/update")
	public R update(@RequestBody SysOrgEntity org) {
		return sysOrgService.updateOrg(org);
	}

	/**
	 * 禁用机构
	 * @param orgId
	 * @return
	 */
	@SysLog("禁用机构")
	@RequestMapping("/disable")
	public R disableOrg(@RequestBody Long orgId) {
		SysOrgEntity orgEntity = new SysOrgEntity();
		orgEntity.setOrgId(orgId);
		orgEntity.setStatus(0);
		return sysOrgService.updateOrg(orgEntity);
	}

	/**
	 * 启用机构
	 * @param orgId
	 * @return
	 */
	@SysLog("启用机构")
	@RequestMapping("/enable")
	public R enableOrg(@RequestBody Long orgId) {
		SysOrgEntity orgEntity = new SysOrgEntity();
		orgEntity.setOrgId(orgId);
		orgEntity.setStatus(1);
		return sysOrgService.updateOrg(orgEntity);
	}
	
	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	@SysLog("删除机构")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysOrgService.bactchRemoveOrg(id);
	}
	
}
