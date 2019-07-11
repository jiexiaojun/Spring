package net.chenlin.dp.common.support.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * session监听器
 * @author zcl<yczclcn@163.com>
 */
public class UserSessionListener implements SessionListener {

    private static final Logger LOG = LoggerFactory.getLogger(UserSessionListener.class);

    @Override
    public void onStart(Session session) {
        LOG.debug("会话创建：{}", session.getId());
    }

    @Override
    public void onStop(Session session) {
        LOG.debug("会话停止：{}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        LOG.debug("会话过期：{}", session.getId());
    }
}
