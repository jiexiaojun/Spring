package com.service;

import cn.utils.ApiResult;

public interface DeploymentService {

	ApiResult deploymentFlow(String params) throws Exception;

	ApiResult queryDeployment()throws Exception;

	ApiResult deploymentAndStartProcess()throws Exception;

	ApiResult startProcess(String params)throws Exception;

	ApiResult queryTask()throws Exception;

	ApiResult doTask(String params)throws Exception;

	ApiResult deleteDeployment(String params)throws Exception;

	ApiResult backTaskTab(String params)throws Exception;

	ApiResult getAllAct(String params)throws Exception;

	ApiResult getTaskList(String params)throws Exception;


}
