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
 * AOP - 注解方式
 * 
 * @author liugaowei
 *
 */
@Aspect
@Order(1)
public class ZJHelloAspect {

	// 引入新的接口
	/**
	 * @DeclareParents(value="AspectJ语法类型表达式", defaultImpl=引入接口的默认实现类)
	 * 
	 * 
	 * --- 注意： 此处value 是AspectJ语法类型表达式。不是切入点表达式
	 */
	@DeclareParents(
			value = "com.liugw..*.IZJHelloService+",
			defaultImpl = com.liugw.learn.impl.IntroductiondService.class)
	private IIntroductionService introductionService;

	////// ===========================================================================================
	/**
	 * 命名切入点：定义切入点, 切入点名称即为方法的名字：“beforePointcut” , 匹配目标方法的参数类型为String；
	 * 
	 * @param param
	 */
	@Pointcut(value = "execution(* com.liugw..*.zjsayBefore(..)) && args(param)", argNames = "param")
	public void beforePointcut(String param) {
	}
	////// ===========================================================================================

	///////////////////////////////////////////////////////////////////////////////
	// 前置通知
	/**
	 * @Before(value = "切入点表达式或命名切入点", argNames = "参数列表参数名")
	 */
	@Before(value = "execution(* com.liugw..*.zjsayHello(..))")
	public void beforeAdvice() {
		System.out.println("===========before advice");
	}

	/**
	 * 可以使用前面定义的切入点， 也可以在value中直接定义表达式
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

	// 后置最终通知
	/**
	 * @After( value="切入点表达式或命名切入点", argNames="参数列表参数名" )
	 */
	@After(value = "execution(* com.liugw..*.zjsayAfterFinally(..))")
	public void afterFinallyAdvice() {
		System.out.println("===========after finally advice");
	}

	/**
	 * value 和 pointcut 二选一， 同时出现时， 使用pointcut覆盖value的配置。
	 * 
	 * @AfterReturning( value="切入点表达式或命名切入点", pointcut="切入点表达式或命名切入点",
	 * argNames="参数列表参数名", returning="返回值对应参数名")
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
	 * @AfterThrowing ( value="切入点表达式或命名切入点", pointcut="切入点表达式或命名切入点",
	 *                argNames="参数列表参数名", throwing="异常对应参数名")
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
	 * @Around ( value="切入点表达式或命名切入点", argNames="参数列表参数名")
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
