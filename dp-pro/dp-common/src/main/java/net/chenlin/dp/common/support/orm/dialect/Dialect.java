package net.chenlin.dp.common.support.orm.dialect;

/**
 * 数据库方言抽象类
 * @author zcl<yczclcn@163.com>
 */
public abstract class Dialect {

    /**
     * 得到分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getLimitString(String sql, int offset, int limit);

    /**
     * 得到分页sql
     * @param sql
     * @return
     */
    public abstract String getCountString(String sql);

}
