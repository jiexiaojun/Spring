package com.allen.transcation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.allen.transcation.entity.Member;

@Repository
public class MemberDao {

	private JdbcTemplate template;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<Member> select() {

		return template.query("select * from t_member", new RowMapper() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member m = new Member();
				m.setId(rs.getLong("id"));
				m.setName(rs.getString("name"));
				return m;
			}
		});
	}


	public int insert(String id, String name) throws Exception {
		return template.update("insert into t_member(id,name) values(?,?)", id, name);
	}


	public int delete(long id) throws Exception {
		return template.update("delete from t_member where id = ?", id);
	}


	public int update(long id, String name) throws Exception {
		return template.update("update t_member set name = ? where id = ?", name, id);
	}

}
