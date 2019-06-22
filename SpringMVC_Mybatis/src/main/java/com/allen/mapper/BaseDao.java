package com.allen.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @类名称：BaseDao
 * @类描述：通用dao
 * @创建人：allen
 * @创建时间：2019年5月10日 上午10:13:09
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Repository
public class BaseDao extends SqlSessionDaoSupport {

	private static final String ORDER_BY = "orderBy";
	private static final String CONVERTED_ORDER_BY = "convertedOrderBy";


	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}


	/**
	 * @方法描述：查询获取list
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:13:26
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:13:26
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public List queryForList(String sqlDef, Object param) throws Exception {
		if (param instanceof Map)
			convertOrderBy((Map) param);
		return getSqlSession().selectList(sqlDef, param);
	}

	/**
	 * @方法描述：获取list 分页
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:13:38
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:13:38
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @param starNum
	 * @param num
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public List queryForPageList(String sqlDef, Object param, int starNum, int num)
			throws Exception {
		return getSqlSession().selectList(sqlDef, param, new RowBounds(starNum, num));
	}

	/**
	 * @方法描述：查询出对象
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:14:01
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:14:01
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public Object queryToObject(String sqlDef, Object param) {
		return getSqlSession().selectOne(sqlDef, param);
	}

	/**
	 * @方法描述：根据参数更新表
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:14:10
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:14:10
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public int update(String sqlDef, Object param) throws Exception {
		return getSqlSession().update(sqlDef, param);
	}

	/**
	 * @方法描述：根据参数删除
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:14:27
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:14:27
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public int delete(String sqlDef, Object param) throws Exception {
		return getSqlSession().delete(sqlDef, param);
	}

	/**
	 * @方法描述：根据参数插入
	 * @创建人：allen
	 * @创建时间：2019年5月10日 上午10:14:36
	 * @修改人：allen
	 * @修改时间：2019年5月10日 上午10:14:36
	 * @修改内容：
	 * @param sqlDef
	 * @param param
	 * @return
	 * @throws Exception	
	 * @version 1.0
	 */
	public int insert(String sqlDef, Object param) throws Exception {
		return getSqlSession().insert(sqlDef, param);
	}

	public long queryForLong(String sqlDef, Object param) {
		return getSqlSession().selectOne(sqlDef, param);
	}

	public int queryForInt(String sqlDef, Object param) {
		return getSqlSession().selectOne(sqlDef, param);
	}

	public Object queryForExtObject(String sqlDef, Object param) throws Exception {
		return getSqlSession().selectOne(sqlDef, param);
	}

	private Map convertOrderBy(Map param) {
		if (param.containsKey(ORDER_BY)) {
			Iterator entryIterator;
			Entry<String, String> entry;
			List<Map<String, String>> mapList = (List<Map<String, String>>) param.get(ORDER_BY);
			List<String> orderByList = new ArrayList<String>();
			for (Map<String, String> orderByElement : mapList) {
				entryIterator = orderByElement.entrySet().iterator();
				while (entryIterator.hasNext()) {
					entry = (Entry<String, String>) entryIterator.next();
					orderByList.add(entry.getKey() + entry.getValue().substring(0, 1).toUpperCase()
							+ entry.getValue().substring(1).toLowerCase());
				}
			}
			param.put(CONVERTED_ORDER_BY, orderByList);
		}
		return param;
	}
}
