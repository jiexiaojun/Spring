package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.base.BaseMapper;
import net.chenlin.dp.modules.sys.entity.SysMacroEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 通用字典
 * @author zcl<yczclcn@163.com>
 */
@MapperScan
public interface SysMacroMapper extends BaseMapper<SysMacroEntity> {

	/**
	 * 选择菜单上级数据源
	 * @return
	 */
	List<SysMacroEntity> listNotMacro();

	/**
	 * 统计子菜单数量
	 * @param typeId
	 * @return
	 */
	int countMacroChildren(Long typeId);

	/**
	 * 指定类型的所有参数列表
	 * @param type
	 * @return
	 */
	List<SysMacroEntity> listMacroValue(String type);
	
}
