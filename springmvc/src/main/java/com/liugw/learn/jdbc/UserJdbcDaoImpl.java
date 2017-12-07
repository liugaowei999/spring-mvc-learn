package com.liugw.learn.jdbc;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class UserJdbcDaoImpl extends SimpleJdbcDaoSupport implements IUserDao {
	private static final String INSERT_SQL = "insert into test(name) values(:myName)";
	private static final String COUNT_ALL_SQL = "select count(*) from test";

	@Override
	public void save(UserModel model) {
		// TODO Auto-generated method stub
		getSimpleJdbcTemplate().update(INSERT_SQL, new BeanPropertySqlParameterSource(model));
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return getSimpleJdbcTemplate().queryForInt(COUNT_ALL_SQL);
	}

}
