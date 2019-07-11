package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.sys.entity.ColumnEntity;
import net.chenlin.dp.modules.sys.entity.TableEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 代码生成器
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysGeneratorMapper {

	/**
	 * 查询数据库表
	 * @param page
	 * @param query
	 * @return
	 */
	List<TableEntity> listTable(Page<TableEntity> page, Query query);

	/**
	 * 根据表名称查询表信息
	 * @param tableName
	 * @return
	 */
	TableEntity getTableByName(String tableName);

	/**
	 * 查询数据表所有列
	 * @param tableName
	 * @return
	 */
	List<ColumnEntity> listColumn(String tableName);
	
}
