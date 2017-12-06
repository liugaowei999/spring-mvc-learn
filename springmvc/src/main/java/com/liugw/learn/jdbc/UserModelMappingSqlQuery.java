package com.liugw.learn.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class UserModelMappingSqlQuery extends MappingSqlQuery<UserModel> {

	public UserModelMappingSqlQuery(JdbcTemplate jdbcTempate) {
		super.setDataSource(jdbcTempate.getDataSource());
		super.setSql("select * from test where name=:name");
		super.declareParameter(new SqlParameter("name", Types.VARCHAR));
		compile();
	}
	@Override
	protected UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserModel model = new UserModel();
		model.setId(rs.getInt("id"));
		model.setMyName(rs.getString("name"));
		return model;
	}

}
