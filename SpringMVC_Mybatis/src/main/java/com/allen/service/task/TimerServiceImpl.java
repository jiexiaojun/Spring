package com.allen.service.task;

import org.springframework.stereotype.Service;


@Service
public class TimerServiceImpl {

	/*
	 * 每周六早上10点半 如果有待安总审核的单推送消息提醒安总审核请假单
	 */
	public void actionEveryFiveMinutes() throws Exception {
		System.out.println("定时器每隔10分钟执行一次");

	}
}
