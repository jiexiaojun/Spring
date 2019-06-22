package com.allen.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;


/**
 * BaseDaoImpl 定义DAO的通用操作的实现
 * 
 * @author Allen
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseDaoImpl<T> implements BaseDao<T> {

	protected Class<?> entityClass;
	protected String entityName;

	public BaseDaoImpl() {
		this.entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.entityName = entityClass.getName();
	}

	/** 
	 * 获取当前工作的Session 
	 */
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}



	public Serializable save(T o) {
		if (o != null) {
			return this.getSession().save(o);
		}
		return null;
	}

	public void delete(T o) {
		if (o != null) {
			this.getSession().delete(o);
		}
	}



	public void update(T o) {
		if (o != null) {
			this.getSession().update(o);
		}
	}


	public void saveOrUpdate(T o) {
		if (o != null) {
			this.getSession().saveOrUpdate(o);
		}
	}

	public T get(Serializable id) {
		return (T) this.getSession().get(entityClass, id);
	}


	public T get(String hql) {
		Query q = this.getSession().createQuery(hql);
		List<T> l = q.list();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}


	public T get(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}


	public List<T> find(String hql) {
		Query q = this.getSession().createQuery(hql);
		return q.list();
	}

	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}


	public List<T> find(String hql, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key).getClass().isArray()) {
					q.setParameterList(key, (Object[]) params.get(key));
				}
				else if (params.get(key) instanceof Collection) {
					q.setParameterList(key, (Collection) params.get(key));
				}
				else {
					q.setParameter(key, params.get(key));
				}
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


//  此处说明一下createQuery(hql)和createSQLQuery(sql)的区别
//	hibernate 中createQuery与createSQLQuery两者区别是：
//	前者用的hql语句进行查询，后者可以用sql语句查询
//	前者以hibernate生成的Bean为对象装入list返回
//	后者则是以对象数组进行存储
//	所以使用createSQLQuery有时候也想以hibernate生成的Bean为对象装入list返回，就不是很方便
//	突然发现createSQLQuery有这样一个方法可以直接转换对象
//	Query query = session.createSQLQuery(sql).addEntity(XXXXXXX.class);
//	XXXXXXX 代表以hibernate生成的Bean的对象，也就是数据表映射出的Bean。


	public List<T> findBySql(String sql) {
		SQLQuery q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		return q.list();
	}

	public List<T> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	public List<T> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = this.getSession().createSQLQuery(sql).addEntity(entityClass);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}


	public Long count(String hql) {
		Query q = this.getSession().createQuery(hql);
		Object obj = q.uniqueResult();
		return (Long) (obj == null ? 0l : obj);
	}


	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		Object obj = q.uniqueResult();
		return (Long) (obj == null ? 0l : obj);
	}


	public int executeHql(String hql) {
		Query q = this.getSession().createQuery(hql);
		return q.executeUpdate();
	}


	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}


	public int executeSql(String sql) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		return q.executeUpdate();
	}


	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}


	public Integer countBySql(String sql) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		Object obj = q.uniqueResult();
		return (Integer) (obj == null ? 0l : obj);
	}


	public Integer countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		Object obj = q.uniqueResult();
		return (Integer) (obj == null ? 0l : obj);
	}


	public List<T> findExtOpenSession(String hql) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery(hql);
		List<T> list = q.list();
		s.close();
		return list;
	}


	public void updateExtOpenSession(T o) {
		if (o != null) {
			Session s = this.sessionFactory.openSession();
			s.update(o);
			s.close();
		}
	}

	public List<T> findList(Serializable[] ids) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from ").append(entityName).append(" t").append(" where t.id in (:ids)");
		Query q = this.getSession().createQuery(hql.toString());
		q.setParameterList("ids", ids);
		return q.list();
	}



	public List<Map<String, Object>> getQueryResultToListMap(final String sql,
			final Map<String, Object> params) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key).getClass().isArray()) {
					q.setParameterList(key, Arrays.asList(params.get(key)));
				}
				else {
					q.setParameter(key, params.get(key));
				}
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}


	public List<Map<String, Object>> getHqlQueryResultToListMap(final String hql,
			final Map<String, Object> params) {
		Query q = this.getSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key).getClass().isArray()) {
					q.setParameterList(key, (Object[]) params.get(key));
				}
				else if (params.get(key) instanceof Collection) {
					q.setParameterList(key, (Collection) params.get(key));
				}
				else {
					q.setParameter(key, params.get(key));
				}
			}
		}
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}


	public List<Map<String, Object>> getSqlQueryResultToListMap(final String sql,
			final Map<String, Object> params, int page, int rows) {
		SQLQuery q = this.getSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (params.get(key).getClass().isArray()) {
					q.setParameterList(key, (Object[]) params.get(key));
				}
				else if (params.get(key) instanceof Collection) {
					q.setParameterList(key, (Collection) params.get(key));
				}
				else {
					q.setParameter(key, params.get(key));
				}
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}



	public T getByFetchProperty(String fetchHql, String pName, Object pValue) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(entityName).append(" t");
		hql.append(fetchHql).append(" where t.").append(pName).append("= :").append(pName);
		params.put(pName, pValue);
		return get(hql.toString(), params);
	}
}
