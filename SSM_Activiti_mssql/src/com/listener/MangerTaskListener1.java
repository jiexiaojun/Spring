package com.listener;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MangerTaskListener1 implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("进入MangerTaskHandlerCandidateUsers=========");
		String[] empLoyees = { "冯小刚经纪人", "范冰冰经纪人", "冯小刚" };
		delegateTask.addCandidateUsers(Arrays.asList(empLoyees));// 完成多处理人的指定
		System.out.println("节点任务人========冯小刚经纪人,范冰冰经纪人,冯小刚");
	}
}
