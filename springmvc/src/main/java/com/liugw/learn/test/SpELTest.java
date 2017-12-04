package com.liugw.learn.test;

import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.liugw.learn.pojo.SpELBean;

public class SpELTest {

	@Test
	public void testParser() {
		// 1. 创建解析器
		ExpressionParser parser = new SpelExpressionParser();

		// 2. 解析表达式：将字符串表达式解析为Expression对象
		Expression expression = parser.parseExpression("  ('Hello' + ' World').concat(#end)  ");

		// 3. 构造上下文：准备比如变量定义等等表达式需要的上下文数据
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!!!!");

		// 4. 求值：通过Expression接口的getValue方法根据上下文获取表达式值
		Object value = expression.getValue(context);

		System.out.println(value.toString());
	}

	@Test
	// 模板方式
	public void testParserContext() {
		// 1. 创建解析器
		ExpressionParser parser = new SpelExpressionParser();

		// 定义模板
		ParserContext parserContext = new ParserContext() {

			@Override
			public String getExpressionPrefix() {
				// TODO Auto-generated method stub
				return "#{";
			}

			@Override
			public String getExpressionSuffix() {
				// TODO Auto-generated method stub
				return "}";
			}

			@Override
			public boolean isTemplate() {
				// TODO Auto-generated method stub
				return true;
			}

		};

		// 模板表达式
		String template = "#{'Hello '}#{'World!!!!*****'}";

		// 2. 解析模板表达式
		Expression expression = parser.parseExpression(template, parserContext);

		// 3. 获取结果
		Object vlaue = expression.getValue();

		System.out.println(vlaue.toString());

	}

	@Test
	public void testClassTypeExression() {

		ExpressionParser parser = new SpelExpressionParser();

		// java.lang包类访问
		Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
		System.out.println(result1.getName());

		// 其他包类访问
		String expression2 = "T(com.liugw.learn.test.SpELTest)";
		Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
		System.out.println(result2.getName());

		// 类静态字段访问
		int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(Integer.class);
		System.out.println(result3);

		// 类静态方法调用
		int result4 = parser.parseExpression("T(Integer).parseInt('100')").getValue(Integer.class);
		System.out.println(result4);
	}

	@Test
	public void testConstructorExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		String result1 = parser.parseExpression("new String('hahahfdaf')").getValue(String.class);
		System.out.println(result1);

		Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
		System.out.println(result2);
	}

	@Test
	public void testInstanceOf() {
		ExpressionParser parser = new SpelExpressionParser();
		Boolean result = parser.parseExpression("'haha'   instanceof  T(String)").getValue(Boolean.class);
		System.out.println(result);
	}

	@Test
	public void testVariableExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("val1", "hello @@@@@");
		String result = parser.parseExpression("#val1").getValue(context, String.class);
		System.out.println(result);

		// #root 根对象
		context = new StandardEvaluationContext("helloworld_val2");
		String result2 = parser.parseExpression("#root").getValue(context, String.class);
		System.out.println(result2);

		// #this 当前上下文对象
		String result3 = parser.parseExpression("#this").getValue(context, String.class);
		System.out.println(result3);

	}

	@Test
	/**
	 * 自定义函数 registerFunction 和 setVariable都可以注册自定义函数，推荐使用registerFunction
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public void testFunctionExpression() throws NoSuchMethodException, SecurityException {
		// 1. 创建解析器
		ExpressionParser parser = new SpelExpressionParser();

		// 2. 创建上下文
		StandardEvaluationContext context = new StandardEvaluationContext();

		// 3. 定义函数，并注册
		Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
		context.registerFunction("parseInt", parseInt);
		context.setVariable("parseInt2", parseInt);

		// 4. 编写表达式，生成Expression对象
		String expressionStr = "#parseInt('3') == #parseInt2('3')";
		Expression expression = parser.parseExpression(expressionStr);

		// 5. 获取结果
		String result = expression.getValue(context, String.class);
		System.out.println("result=" + result);
	}

	@Test
	/**
	 * "#varibaleName=value" ----->即可赋值
	 */
	public void testAssignExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		// 1. 给root对象赋值
		EvaluationContext context = new StandardEvaluationContext("aaaaaaaa");
		String result = parser.parseExpression("#root='bbbbb'").getValue(context, String.class);
		System.out.println(result);

		String result2 = parser.parseExpression("#this='ccccc'").getValue(context, String.class);
		System.out.println(result2);

		// 2. 给自定义变量赋值
		context.setVariable("sum", "10");
		String result3 = parser.parseExpression("#sum").getValue(context, String.class);
		System.out.println(result3); // 10

		result3 = parser.parseExpression("#sum=#root").getValue(context, String.class);
		System.out.println(result3); // aaaaaaaa

		result3 = parser.parseExpression("#sum=#this").getValue(context, String.class);
		System.out.println(result3); // aaaaaaaa
	}

	@Test
	public void testFiledAccessExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		Date date = new Date();
		StandardEvaluationContext context = new StandardEvaluationContext(date);
		int result1 = parser.parseExpression("Year").getValue(context, Integer.class);
		int result2 = parser.parseExpression("year").getValue(context, Integer.class);
		System.out.println("result1=" + result1 + ", date.getYear()=" + date.getYear());
		System.out.println("result2=" + result2 + ", date.getYear()=" + date.getYear());

		// context.setRootObject(null);
		String result3 = parser.parseExpression("#root?.year").getValue(context, String.class);
		System.out.println(result3);

		String result4 = parser.parseExpression("getYear()").getValue(context, String.class);
		System.out.println(result4);

	}

	/**
	 * 自定义SpEL语法的前缀、后缀测试
	 */
	@Test
	public void testPrefixExpression() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");
		SpELBean spELBean1 = context.getBean("helloBean1", SpELBean.class);
		SpELBean spELBean2 = context.getBean("helloBean2", SpELBean.class);
		System.out.println("spELBean1=" + spELBean1.getValue());
		System.out.println("spELBean2=" + spELBean2.getValue());
	}
}
