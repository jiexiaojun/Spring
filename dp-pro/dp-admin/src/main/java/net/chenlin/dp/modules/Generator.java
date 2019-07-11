package net.chenlin.dp.modules;

import net.chenlin.dp.common.support.gen.JdbcGenUtils;

/**
 * 代码生成器
 * @author zcl<yczclcn@163.com>
 */
public class Generator {

	public static void main(String[] args) throws Exception {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl =
				"jdbc:mysql://127.0.0.1:3306/dp-lte-boot?useUnicode=true&characterEncoding=utf-8";
		String jdbcUsername = "root";
		String jdbcPassword = "slpcb456852.";

		String tablePrefix = "gen_";

		String javaProject = "dp-admin";
		String javaModule = "generator";
		String webModule = "base";

		JdbcGenUtils.generatorCode(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, tablePrefix,
				javaProject, javaModule, webModule);
	}

}
