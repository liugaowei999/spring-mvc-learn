package com.liugw.learn.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELTest {

	@Test
	public void testParser() {
		// 1. ����������
		ExpressionParser parser = new SpelExpressionParser();

		// 2. �������ʽ�����ַ������ʽ����ΪExpression����
		Expression expression = parser.parseExpression("  ('Hello' + ' World').concat(#end)  ");

		// 3. ���������ģ�׼�������������ȵȱ��ʽ��Ҫ������������
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!!!!");

		// 4. ��ֵ��ͨ��Expression�ӿڵ�getValue�������������Ļ�ȡ���ʽֵ
		Object value = expression.getValue(context);

		System.out.println(value.toString());
	}

	@Test
	// ģ�巽ʽ
	public void testParserContext() {
		// 1. ����������
		ExpressionParser parser = new SpelExpressionParser();

		// ����ģ��
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

		// ģ����ʽ
		String template = "#{'Hello '}#{'World!!!!*****'}";

		// 2. ����ģ����ʽ
		Expression expression = parser.parseExpression(template, parserContext);

		// 3. ��ȡ���
		Object vlaue = expression.getValue();

		System.out.println(vlaue.toString());

	}

	@Test
	public void testClassTypeExression() {

		ExpressionParser parser = new SpelExpressionParser();

		// java.lang�������
		Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
		System.out.println(result1.getName());

		// �����������
		String expression2 = "T(com.liugw.learn.test.SpELTest)";
		Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
		System.out.println(result2.getName());

		// �ྲ̬�ֶη���
		int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(Integer.class);
		System.out.println(result3);

		// �ྲ̬��������
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

		// #root ������
		context = new StandardEvaluationContext("helloworld_val2");
		String result2 = parser.parseExpression("#root").getValue(context, String.class);
		System.out.println(result2);

		// #this ��ǰ�����Ķ���
		String result3 = parser.parseExpression("#this").getValue(context, String.class);
		System.out.println(result3);

	}

}
