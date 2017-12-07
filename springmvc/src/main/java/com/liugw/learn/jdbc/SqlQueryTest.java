package com.liugw.learn.jdbc;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;

/**
 * SimpleJdbcTemplate类也是基于JdbcTemplate类，但利用Java5+的可变参数列表和自动装箱和拆箱从而获取更简洁的代码。
 * SimpleJdbcTemplate主要提供两类方法：query及queryForXXX方法、update及batchUpdate方法
 * 
 * @author liugaowei
 *
 */
public class SqlQueryTest {

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

	@Test
	public void testSqlQuery() {
		SqlQuery query = new UserModelSqlQuery(jdbcTemplate);
		List<UserModel> result = query.execute("jack");
		System.out.println(result.size());
	}

	/**
	 * MappingSqlQuery 与SqlQuery的唯一不同是： MappingSqlQuery可以将mapRow每行数据转换成需要的格式
	 */
	@Test
	public void testMappingSqlQuery() {
		jdbcTemplate.update("insert into test(name) values('mark')");

		SqlQuery<UserModel> query = new UserModelMappingSqlQuery(jdbcTemplate);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", "mark");
		UserModel result = query.findObjectByNamedParam(paramMap);
		System.out.println(result.getMyName());
	}

	/**
	 * SqlFunction：SQL“函数”包装器，用于支持那些返回单行结果集的查询。该类主要用于返回单行单列结果集。
	 * 
	 * @param args
	 */
	@Test
	public void testSqlFunction() {
		jdbcTemplate.update("insert into test(name) values('name5')");
		jdbcTemplate.update("insert into test(name) values('name6')");
		String countSql = "select count(*) from test";
		SqlFunction<Integer> sqlFunction1 = new SqlFunction<>(jdbcTemplate.getDataSource(), countSql);
		int result = sqlFunction1.run();

		System.out.println(result);

		String selectSql = "select name from test where name = ?";
		SqlFunction<String> sqlFunction2 = new SqlFunction<>(jdbcTemplate.getDataSource(), selectSql);
		sqlFunction2.declareParameter(new SqlParameter(Types.VARCHAR));
		String reString = (String) sqlFunction2.runGeneric(new Object[] { "name5" });
		System.out.println(reString);

	}

	public static void main(String[] args) {
		SqlQueryTest queryTest = new SqlQueryTest();
		SqlQueryTest.setUpClass();
		queryTest.setUp();
		// ================================
		queryTest.testSqlQuery();
		// ================================
		queryTest.tearDown();

	}
}
