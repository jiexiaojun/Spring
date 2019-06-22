package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.utils.ApiResult;

import com.service.DeploymentService;

@Controller
@RequestMapping(value="/deployment")
public class DeploymentController {

	@Autowired
	DeploymentService deploymentService;
	
	
	/**
	 * @Description: 部署流程定义
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午8:50:20
	 */
	@ResponseBody
	@RequestMapping("/deploymentFlow")
	public ApiResult deploymentFlow(String params) throws Exception{
		return deploymentService.deploymentFlow(params);
	}
	
	
	
	/**
	 * @Description: 部署流程定义并立即启动流程
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午9:45:18
	 */
	@ResponseBody
	@RequestMapping("/deploymentAndStartProcess")
	public ApiResult deploymentAndStartProcess() throws Exception{
		return deploymentService.deploymentAndStartProcess();
	}
	
	
	
	/**
	 * @Description:查询流程定义
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午8:58:35
	 */
	@ResponseBody
	@RequestMapping("/queryDeployment")
	public ApiResult queryDeployment() throws Exception{
		return deploymentService.queryDeployment();
	}
	
	/**
	 * @Description: 删除流程定义
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午11:56:50
	 */
	@ResponseBody
	@RequestMapping("/deleteDeployment")
	public ApiResult deleteDeployment(String params) throws Exception{
		return deploymentService.deleteDeployment(params);
	}
	
	
	/**
	 * @Description: 启动流程
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午9:45:18
	 */
	@ResponseBody
	@RequestMapping("/startProcess")
	public ApiResult startProcess(String params) throws Exception{
		return deploymentService.startProcess(params);
	}
	
	
	/**
	 * @Description: 查询任务
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午10:19:16
	 */
	@ResponseBody
	@RequestMapping("/queryTask")
	public ApiResult queryTask() throws Exception{
		return deploymentService.queryTask();
	}
	
	
	/**
	 * @Description: 办理任务
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  上午10:19:45
	 */
	@ResponseBody
	@RequestMapping("/doTask")
	public ApiResult doTask(String params) throws Exception{
		return deploymentService.doTask(params);
	}
	
	/**
	 * @Description: 根据实例编号查找下一个任务节点
	 * @param taskId
	 * @return ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  下午3:45:57
	 */
	@RequestMapping("/backTaskTab")
	@ResponseBody
	public ApiResult backTaskTab(String params) throws Exception
	{
		return deploymentService.backTaskTab(params);
	}
	
	/**
	 * @Description: 根据流程定义ID获取流程所有节点信息
	 * @param params
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月7日  下午4:24:11
	 */
	@RequestMapping("/getAllAct")
	@ResponseBody
	public ApiResult getAllAct(String params) throws Exception
	{
		return deploymentService.getAllAct(params);
	}
	
	/**
	 * @Description: 根据任务ID 查询几个人共同所有  或者几个组共同所有
	 * @param params
	 * @return
	 * @throws Exception ApiResult  
	 * @author Jie.xiaojun
	 * @date 2018年7月9日  上午11:20:43
	 */
	@RequestMapping("/getTaskList")
	@ResponseBody
	public ApiResult getTaskList(String params) throws Exception
	{
		return deploymentService.getTaskList(params);
	}
	
}
