package com.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MangerTaskListener2 implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {

		delegateTask.addCandidateGroup("1");;// 完成多处理人的指定
		System.out.println("节点任务人=======属于超级管理员组的人");
	}
}
