package com.liugw.learn.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BatchTest {

	private static JdbcTemplate jdbcTemplate;

	@BeforeClass
	public static void setUpClass() {
		String url = "jdbc:hsqldb:mem:test";
		String username = "sa";
		String password = "";

		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("BeforeClass ----- create dataSource");
	}

	@Test
	public void test() {
		// 1.声明SQL
		String sql = "select * from INFORMATION_SCHEMA.SYSTEM_TABLES";
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// 2.处理结果集
				String value = rs.getString("TABLE_NAME");
				System.out.println("Column TABLENAME:" + value);
			}
		});
	}

	// 在每个测试方法之前执行
	@Before
	public void setUp() {
		String createTableSql = "create memory table test" + "(id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, "
				+ "name varchar(100))";
		System.out.println(createTableSql);
		jdbcTemplate.update(createTableSql);

		// 创建函数
		String createHsqldbFunctionSql = "CREATE FUNCTION FUNCTION_TEST(str CHAR(100)) " +
				"returns INT begin atomic return length(str);end";
		jdbcTemplate.update(createHsqldbFunctionSql);

		// 创建存储过程
		String createHsqldbProcedureSql = "CREATE PROCEDURE PROCEDURE_TEST" +
				"(INOUT inOutName VARCHAR(100), OUT outId INT) " +
				"MODIFIES SQL DATA " +
				"BEGIN ATOMIC " +
				"  insert into test(name) values (inOutName); " +
				"  SET outId = IDENTITY(); " +
				"  SET inOutName = 'Hello,' + inOutName; " +
				"END";
		jdbcTemplate.execute(createHsqldbProcedureSql);
	}

	// 在每个测试方法之后执行
	@After
	public void tearDown() {
		jdbcTemplate.execute("DROP FUNCTION FUNCTION_TEST");
		jdbcTemplate.execute("DROP PROCEDURE PROCEDURE_TEST");

		String dropTableSql = "drop table test";
		System.out.println(dropTableSql);
		jdbcTemplate.execute(dropTableSql);
	}

	/**
	 * 获取mysql 数据源
	 * 
	 * @return
	 */
	public DataSource getMysqlDataSource() {
		String url = "jdbc:mysql://localhost:3306/taotao?serverTimezone=UTC";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, "root", "root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

	/**
	 * JdbcTemplate 批处理： 支持普通的批处理及占位符批处理；
	 */
	@Test
	public void testBatchUpdate1() {
		String insertSql = "insert into test(name) values('name5')";
		String[] batchSql = new String[] { insertSql, insertSql };
		jdbcTemplate.batchUpdate(batchSql);
		System.out.println(jdbcTemplate.queryForInt("select count(*) from test"));
	}

	/**
	 * 直接调用batchUpdate方法执行需要批处理的语句即可。
	 */
	@Test
	public void testBatchUpdate2() {
		String insertSql = "insert into test(name) values(?)";
		final String[] batchValues = new String[] { "name5", "name6" };
		jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, batchValues[i]);
			}

			@Override
			public int getBatchSize() {
				return batchValues.length;
			}
		});
		System.out.println(jdbcTemplate.queryForInt("select count(*) from test"));
	}

	/**
	 * NamedParameterJdbcTemplate 批处理： 支持命名参数批处理；
	 */
	@Test
	public void testBatchUpdate3() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		String insertSql = "insert into test(name) values(:myName)";
		UserModel[] models = new UserModel[10];
		for (int i = 0; i < 10; i++) {
			UserModel model = new UserModel();
			model.setMyName("name" + i);
			models[i] = model;
			System.out.println(model.getMyName());
		}
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(models);
		namedParameterJdbcTemplate.batchUpdate(insertSql, params);
		System.out.println(jdbcTemplate.queryForInt("select count(*) from test"));
	}

	/**
	 * SimpleJdbcTemplate 批处理： 已更简单的方式进行批处理；
	 */
	@Test
	public void testBatchUpdate4() {
		SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
		String insertSql = "insert into test(name) values(?)";
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] { "name5" });
		params.add(new Object[] { "name6" });
		simpleJdbcTemplate.batchUpdate(insertSql, params);
		System.out.println(jdbcTemplate.queryForInt("select count(*) from test"));
	}

	@Test
	public void testBatchUpdate5() {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("test");
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("name", "name5");
		insert.executeBatch(new Map[] { valueMap, valueMap });
		System.out.println(jdbcTemplate.queryForInt("select count(*) from test"));
	}
}
