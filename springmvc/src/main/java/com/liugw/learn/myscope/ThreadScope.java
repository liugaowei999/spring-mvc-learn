package com.liugw.learn.myscope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/***
 * 自定义线程级别的作用域
 * 
 * @author liugaowei
 *
 */
public class ThreadScope implements Scope {

	private final ThreadLocal<Map<String, Object>> THREAD_SCOPE = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
			// 用于存放线程相关的bean
			return new HashMap<String, Object>();
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		// 如果当前线程已经绑定了相应的bean， 则直接返回
		if (THREAD_SCOPE.get().containsKey(name)) {
			return THREAD_SCOPE.get().get(name);
		}

		// 使用ObjectFactory创建bean，并绑定到当前的线程
		THREAD_SCOPE.get().put(name, objectFactory.getObject());
		return THREAD_SCOPE.get().get(name);
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub Bean 对象销毁逻辑
		// 此处不实现就代表类似proytotype，容器返回给用户后就不管了

	}

	@Override
	public Object remove(String name) {
		// TODO Auto-generated method stub
		return THREAD_SCOPE.get().remove(name);
	}

	@Override
	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
