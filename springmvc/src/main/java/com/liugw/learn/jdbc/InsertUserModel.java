package com.liugw.learn.jdbc;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertUserModel extends SqlUpdate {

	public InsertUserModel(JdbcTemplate jdbcTemplate) {
		super.setDataSource(jdbcTemplate.getDataSource());
		super.setSql("insert into test(name) values(?)");
		super.declareParameter(new SqlParameter(Types.VARCHAR));
		compile();
	}
}
