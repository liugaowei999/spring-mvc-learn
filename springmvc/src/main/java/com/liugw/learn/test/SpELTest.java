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

}
