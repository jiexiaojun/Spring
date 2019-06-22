package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
* 项目名称：SSM_Activiti_mssql_1        
* 类名称：BaseDao    
* 类描述：
* 创建人：Jie.xiaojun
* 创建时间：2018年7月6日 下午4:06:46
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
//抽象类  加了注解其实是无效的@Repository 所以才新建了DataMangerDao继承BaseDao，在子类上注解
public abstract class BaseDao extends SqlSessionDaoSupport {

	private static final String ORDER_BY = "orderBy";

	private static final String CONVERTED_ORDER_BY = "convertedOrderBy";
	

	protected JdbcTemplate jt;
	protected JdbcTemplate cacheJt;

	public void setCacheJdbcTemplate(JdbcTemplate jt) {
		this.cacheJt = jt;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jt = jdbcTemplate;
	}

	/**
	 * @Description: 查询获取list
	 * @param sqlDef
	 * @param param
	 * @return List
	 * @throws Exception   
	 * @author Jie.xiaojun
	 * @date 2018年7月5日  上午10:13:27
	 */
	public List queryForList(String sqlDef, Object param) throws Exception {
		if (param instanceof Map)
			convertOrderBy((Map) param);
		return getSqlSession().selectList(sqlDef, param);
	}

	/**
	 * @Description: 获取list 分页
	 * @param sqlDef
	 * @param param
	 * @param starNum
	 * @param num
	 * @return List
	 * @throws Exception
	 * @author Jie.xiaojun
	 * @date 2018年7月5日 上午10:12:44
	 */
	public List queryForPageList(String sqlDef, Object param, int starNum,
			int num) throws Exception {
		return getSqlSession().selectList(sqlDef, param,
				new RowBounds(starNum, num));
	}

	/**
	 * @Description: 查询出对象
	 * @param sqlDef
	 * @param param
	 * @return Object
	 * @throws Exception   
	 * @author Jie.xiaojun
	 * @date 2018年7月5日  上午10:15:41
	 */
	public Object queryToObject(String sqlDef, Object param) throws Exception {
		return (Object) getSqlSession().selectOne(sqlDef, param);
	}

	/**
	 * @Description: 根据参数更新表
	 * @param sqlDef
	 * @param param
	 * @return int
	 * @throws Exception   
	 * @author Jie.xiaojun
	 * @date 2018年7月5日  上午10:15:53
	 */
	public int update(String sqlDef, Object param) throws Exception {
		return getSqlSession().update(sqlDef, param);
	}

	/**
	 * @Description: 根据参数删除
	 * @param sqlDef
	 * @param param
	 * @return int
	 * @throws Exception   
	 * @author Jie.xiaojun
	 * @date 2018年7月5日  上午10:16:06
	 */
	public int delete(String sqlDef, Object param) throws Exception {
		return getSqlSession().delete(sqlDef, param);
	}

	/**
	 * @Description: 根据参数插入
	 * @param sqlDef
	 * @param param
	 * @return int
	 * @throws Exception   
	 * @author Jie.xiaojun
	 * @date 2018年7月5日  上午10:16:50
	 */
	public int insert(String sqlDef, Object param) throws Exception {
		return getSqlSession().insert(sqlDef, param);
	}

	private Map convertOrderBy(Map param) {
		if (param.containsKey(ORDER_BY)) {
			Iterator entryIterator;
			Entry<String, String> entry;
			List<Map<String, String>> mapList = (List<Map<String, String>>) param
					.get(ORDER_BY);
			List<String> orderByList = new ArrayList<String>();
			for (Map<String, String> orderByElement : mapList) {
				entryIterator = orderByElement.entrySet().iterator();
				while (entryIterator.hasNext()) {
					entry = (Entry<String, String>) entryIterator.next();
					orderByList.add(entry.getKey()
							+ entry.getValue().substring(0, 1).toUpperCase()
							+ entry.getValue().substring(1).toLowerCase());
				}
			}
			param.put(CONVERTED_ORDER_BY, orderByList);
		}
		return param;
	}
}
