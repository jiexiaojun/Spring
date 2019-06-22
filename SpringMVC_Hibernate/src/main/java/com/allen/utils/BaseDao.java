package com.allen.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/** 
 * BaseDao 定义DAO的通用操作 
 *  
 * @author Allen 
 */
public interface BaseDao<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(Serializable id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public List<T> findBySql(String sql);

	public List<T> findBySql(String sql, Map<String, Object> params);

	public List<T> findBySql(String sql, int page, int rows);

	public List<T> findBySql(String sql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> params);

	public int executeSql(String sql);

	public int executeSql(String sql, Map<String, Object> params);

	public Integer countBySql(String sql);

	public Integer countBySql(String sql, Map<String, Object> params);

	List<T> findExtOpenSession(String hql);

	void updateExtOpenSession(T o);

	List<T> findList(Serializable[] ids);

	List<Map<String, Object>> getQueryResultToListMap(String sql, Map<String, Object> params);

	List<Map<String, Object>> getHqlQueryResultToListMap(String hql, Map<String, Object> params);

	List<Map<String, Object>> getSqlQueryResultToListMap(String sql, Map<String, Object> params,
			int page, int rows);

	T getByFetchProperty(String fetchHql, String pName, Object pValue);
}
