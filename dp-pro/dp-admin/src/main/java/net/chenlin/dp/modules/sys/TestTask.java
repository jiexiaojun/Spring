package net.chenlin.dp.modules.sys;

import org.springframework.stereotype.Component;

/**
 * 定时任务测试
 * @author zcl<yczclcn@163.com>
 */
@Component("taskDemo")
public class TestTask {

    public void execute() {
        System.out.println("当前时间为："+System.currentTimeMillis());
    }

}
