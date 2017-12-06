package com.liugw.learn.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.liugw.learn.intf.IIntroductionService;

/**
 * AOP - ע�ⷽʽ
 * 
 * @author liugaowei
 *
 */
@Aspect
@Order(1)
public class ZJHelloAspect {

	// �����µĽӿ�
	/**
	 * @DeclareParents(value="AspectJ�﷨���ͱ��ʽ", defaultImpl=����ӿڵ�Ĭ��ʵ����)
	 * 
	 * 
	 * --- ע�⣺ �˴�value ��AspectJ�﷨���ͱ��ʽ�������������ʽ
	 */
	@DeclareParents(
			value = "com.liugw..*.IZJHelloService+",
			defaultImpl = com.liugw.learn.impl.IntroductiondService.class)
	private IIntroductionService introductionService;

	////// ===========================================================================================
	/**
	 * ��������㣺���������, ��������Ƽ�Ϊ���������֣���beforePointcut�� , ƥ��Ŀ�귽���Ĳ�������ΪString��
	 * 
	 * @param param
	 */
	@Pointcut(value = "execution(* com.liugw..*.zjsayBefore(..)) && args(param)", argNames = "param")
	public void beforePointcut(String param) {
	}
	////// ===========================================================================================

	///////////////////////////////////////////////////////////////////////////////
	// ǰ��֪ͨ
	/**
	 * @Before(value = "�������ʽ�����������", argNames = "�����б������")
	 */
	@Before(value = "execution(* com.liugw..*.zjsayHello(..))")
	public void beforeAdvice() {
		System.out.println("===========before advice");
	}

	/**
	 * ����ʹ��ǰ�涨�������㣬 Ҳ������value��ֱ�Ӷ�����ʽ
	 * 
	 * @param param
	 */
	@Before(value = "execution(* com.liugw..*.zjsayBefore(..)) && args(param,aaa)", argNames = "jp,param,aaa")
	// @Before(value = "beforePointcut(param)", argNames = "jp,param, age")
	public void beforeAdvice(JoinPoint jp, String param, int age) {
		System.out.println("===========before advice param:" + param + " , aaa=" + age);

		System.out.println(jp.toLongString());
		System.out.println(jp.toShortString());
		System.out.println(jp.toString());
		System.out.println("getThis()  =" + jp.getThis());
		System.out.println("getTarget()=" + jp.getTarget());
		System.out.println("getKind()  =" + jp.getKind());
		System.out.println("getArgs()  =" + jp.getArgs());
		System.out.println("getArgs().length  =" + jp.getArgs().length);
		System.out.println("getArgs()  =" + jp.getArgs()[0]);
		System.out.println("getClass()=" + jp.getClass().getName());
		System.out.println("getSignature=" + jp.getSignature());
		System.out.println("getSourceLocation=" + jp.getSourceLocation());
		System.out.println("getStaticPart=" + jp.getStaticPart());
	}

	// ��������֪ͨ
	/**
	 * @After( value="�������ʽ�����������", argNames="�����б������" )
	 */
	@After(value = "execution(* com.liugw..*.zjsayAfterFinally(..))")
	public void afterFinallyAdvice() {
		System.out.println("===========after finally advice");
	}

	/**
	 * value �� pointcut ��ѡһ�� ͬʱ����ʱ�� ʹ��pointcut����value�����á�
	 * 
	 * @AfterReturning( value="�������ʽ�����������", pointcut="�������ʽ�����������",
	 * argNames="�����б������", returning="����ֵ��Ӧ������")
	 * 
	 * @param returnval
	 */
	@AfterReturning(
			value = "execution(* com.liugw..*.zjsayBefore(..))",
			pointcut = "execution(* com.liugw..*.zjsayAfterReturning(..))",
			argNames = "retVal",
			returning = "retVal")
	public void afterReturningAdvice(Object returnval) {
		System.out.println("===========after returning advice retVal:" + returnval);
	}

	/**
	 * @AfterThrowing ( value="�������ʽ�����������", pointcut="�������ʽ�����������",
	 *                argNames="�����б������", throwing="�쳣��Ӧ������")
	 * @param exception
	 */
	@AfterThrowing(
			value = "execution(* com.liugw..*.zjsayAfterThrowing(..))",
			argNames = "exception",
			throwing = "exception")
	public void afterThrowingAdvice(Exception exception) {
		System.out.println("===========after throwing advice exception:" + exception);
	}

	/**
	 * @Around ( value="�������ʽ�����������", argNames="�����б������")
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "execution(* com.liugw..*.zjsayAround(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println("===========around after advice");
		return retVal;
	}

}
