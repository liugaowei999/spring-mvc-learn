package com.liugw.learn.test;

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

}
