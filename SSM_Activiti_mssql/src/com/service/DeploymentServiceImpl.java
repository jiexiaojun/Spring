package com.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.utils.ApiResult;
import cn.utils.ApiStatus;

@Service
public class DeploymentServiceImpl implements DeploymentService {


	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;
	
	/**
	 * 流程部署动作 是向3个表插入数据
	 * ACT_RE_DEPLOYMENT  部署信息表    其ID值的来源是表ACT_GE_PROPERTY的next.dbid的值
	 * ACT_RE_PROCDEF     流程定义数据表
	 * ACT_GE_BYTEARRAY   二进制数据表  用二级制形式存部署流程的文件
	 */
	public ApiResult deploymentFlow(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		
		// 创建一个部署构建器对象，用于加载流程定义文件(bpmn文件和png文件)
        DeploymentBuilder deploymentBuilder =repositoryService.createDeployment();
        //1、classpath方式
        deploymentBuilder.addClasspathResource("cn/diagrams/"+params+".bpmn");
        deploymentBuilder.addClasspathResource("cn/diagrams/"+params+".png");
        //2、InputStream方式
        //获取资源相对路径
//		String bpmnPath = "E:/wj_work/activiti_01/src/qjlc.bpmn";
//		String pngPath = "E:/wj_work/activiti_01/src/qjlc.png";		
		//读取资源作为一个输入流
//		FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
//		FileInputStream pngfileInputStream = new FileInputStream(pngPath);
//      deploymentBuilder.addInputStream("qjlc.bpmn", bpmnfileInputStream);
//      deploymentBuilder.addInputStream("qjlc.png", pngfileInputStream);
        //3、字符串方式方式
//      String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions>...</definitions>";
//      deploymentBuilder.addString("qjlc.bpmn", text);
        
        // 部署，并返回一个部署对象(其实Deployment是一个接口)
        deploymentBuilder.name("部署包");
        deploymentBuilder.category("A");
        deploymentBuilder.tenantId("汪俊");
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println(deployment.getName());
		return result;
	}

	/**
	 * 主要就是查ACT_RE_PROCDEF     流程定义数据表
	 */
	public ApiResult queryDeployment() throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		// 流程定义查询对象，用于查询流程定义表（act_re_procdef）
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        // 以下查询的是所有的流程定义
        List<ProcessDefinition> list = query.list();
        System.out.println(list.size());
        for (ProcessDefinition pd : list) {
        	System.out.println(pd.getId() + "———" + pd.getName() + "———" + 
        			pd.getVersion()+ "———" +pd.getKey()+ "———" +pd.getDeploymentId());
        } 
        ProcessDefinitionQuery query1 = repositoryService.createProcessDefinitionQuery();
        // 根据流程定义的Version来过滤
        query1.processDefinitionVersion(1);
        // 添加排序条件
        query1.orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> Definitionlist = query1.list();
        System.out.println(Definitionlist.size());
        for (ProcessDefinition pd : Definitionlist) {
        	System.out.println(pd.getId() + "———" + pd.getName() + "———" + 
        			pd.getVersion()+ "———" +pd.getKey()+ "———" +pd.getDeploymentId());
        }
        return result;
	}


	/**
	 * 部署并启动流程，就是部署流程和启动流程的一个合体操作
	 * 要注意一点 部署后，怎么获取当前部署流程的Key或者ID并启动 我看到的方法是获取所有定义然后找最后一条
	 */
	public ApiResult deploymentAndStartProcess() throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		// 创建一个部署构建器对象，用于加载流程定义文件(bpmn文件和png文件)
        DeploymentBuilder deploymentBuilder =repositoryService.createDeployment();
        //1、classpath方式
        deploymentBuilder.addClasspathResource("cn/diagrams/qjlc.bpmn");
        deploymentBuilder.addClasspathResource("cn/diagrams/qjlc.png");
        // 开启流程，myLeaveProcess是流程的key
        runtimeService.startProcessInstanceByKey("myLeaveProcess");
        // 开启流程，myLeaveProcess:1:5004是流程的ID,如果同一个流程被部署很多次，他们的key相同，但是version不同 但是只想启动一个就用Id启动
        runtimeService.startProcessInstanceById("myLeaveProcess:1:5004");
		return result;
	}


	/**
	 * 启动流程操作 设计到2个主表 和几个历史表
	 * ACT_RU_EXECUTION  运行时流程执行实例
	 * ACT_RU_TASK       任务表
	 * 
	 * ACT_HI_ACTINST  流程执行历史记录表 记录整个过程
	 * ACT_HI_PROCINST 流程实例历史表
	 * ACT_HI_TASKINST 任务历史表 所有人完成过的任务记录
	 */
	public ApiResult startProcess(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
//		String processDefinitionId = "myLeaveProcess:1:5004"; // 流程定义表ACT_RE_PROCDE的ID
//		String processDefinitionId = "myLeaveProcess:2:22504"; // 流程定义表ACT_RE_PROCDE的ID
//		runtimeService.startProcessInstanceById(params,"dd"); // 根据请假流程定义来具体地请一次假，即启动流程实例
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyer", "迪丽热巴");
//        variables.put("projectManager", "陈桂林");
//        variables.put("divisionManager", "刘才林");
		runtimeService.startProcessInstanceById(params, variables);
		return result;
	}

	
	
	/**
	 * 查询代表任务  其实就是查询 ACT_RU_TASK 任务表
	 */
	public ApiResult queryTask() throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		// 任务查询对象，对应操作的数据库表是任务表(act_ru_task)
        TaskQuery query = taskService.createTaskQuery();
//        query.taskAssignee("汪俊").orderByTaskCreateTime().desc();
        query.taskCandidateUser("冯小刚").orderByTaskCreateTime().desc();
//        query.taskCandidateUser("冯小刚").orderByTaskCreateTime().desc();
        List<Task> list = query.list();
        for (Task task : list) {
            System.out.println(task.getId() + "\t" + task.getName());
        }
		return result;
	}


	/**
	 * 完成自己的任务 简单的一条线的逻辑的话  
	 * 更新ACT_RU_EXECUTION  运行时流程执行实例 当前所在节点ACT_ID
	 * 更新ACT_RU_TASK       任务表   删除上个人的任务，插入新的任务
	 * 插入ACT_HI_ACTINST    流程执行历史记录表 记录整个过程
	 * 插入ACT_HI_TASKINST   任务历史表 所有人完成过的任务记录
	 * 
	 * 
	 * 如果执行实例结束了 则ACT_RU_EXECUTION和ACT_RU_TASK均无该实例任何数据
	 * 更新ACT_HI_PROCINST 流程实例历史表
	 */
	public ApiResult doTask(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("message", "通过");
//		variables.put("divisionManager", "刘才林");
		taskService.complete(params, variables);
		return result;
	}


	/**
	 * 删除流程部署表和流程定义表信息
	 * 如果流程已经启动  还在进行中 不级联删除是无法删除的
	 * 但是级联删 会删除 所有表 与该实例有关的信息
	 */
	public ApiResult deleteDeployment(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		//流程部署表ACT_RE_DEPLOYMENT的ID值  true是级联删除
		repositoryService.deleteDeployment(params, true);
		return  result;
	}

	/**
	 * 获取流程当前执行实例 当前节点的下一个节点的待办人
	 */
	public ApiResult backTaskTab(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		// 创建任务查询  根据任务id查询
		Task task = taskService.createTaskQuery().taskId(params).singleResult();

        String procInstId = task.getProcessInstanceId();
        System.out.println(procInstId);//27505
        
//        String processDefinitionId = task.getProcessDefinitionId();
        // 流程标示
        String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId)
                .singleResult().getProcessDefinitionId();
        System.out.println(processDefinitionId);//myLeaveProcess:2:27504

        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinitionId);
        // 执行实例
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery()
                .processInstanceId(procInstId).singleResult();
        // 当前实例的执行到哪个节点
        String activitiId = execution.getActivityId();
        System.out.println(activitiId);
        // 获得当前任务的所有节点
        List<ActivityImpl> activitiList = def.getActivities();
        ActivityImpl activityImpl=null;
        for(int i=0;i< activitiList.size();i++)
        {
            String flag=activitiList.get(i).getId();
            if(flag.equals(activitiId))
            {
                activityImpl=activitiList.get(i);
            }
        }
        int num=activitiList.indexOf(activityImpl);
        ActivityImpl activityImpl_=activitiList.get(num+1);
        TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl_.getActivityBehavior())
                .getTaskDefinition();
        // 获取下一节点的代办人
        System.out.println(taskDefinition.getAssigneeExpression());
        result.setData(taskDefinition.getAssigneeExpression());
		return result;
	}

	/**
	 * 获取所有节点
	 */
	public ApiResult getAllAct(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		BpmnModel model = repositoryService.getBpmnModel(params);
        if (model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for (FlowElement e : flowElements) {
                System.out.println("flowelement id:" + e.getId() + "\t"+ "name:" + e.getName());
            }
        }
		return result;
	}

	
	public ApiResult getTaskList(String params) throws Exception {
		ApiResult result = new ApiResult(ApiStatus.success, "操作成功");
		List<IdentityLink> list = taskService.getIdentityLinksForTask(params); 
		if(list!=null && list.size()>0){ 
			for (IdentityLink identityLink : list) 
			{
				System.out.println("任务ID："+identityLink.getTaskId()); 
		        System.out.println("用户ID："+identityLink.getUserId()); 
		        System.out.println("组ID："+identityLink.getGroupId()); 
		        System.out.println("-----------------------------------"); 	
			} 	
		}
		return result;
	}
	
}
