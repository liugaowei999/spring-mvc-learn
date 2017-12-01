package com.liugw.learn.myscope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/***
 * �Զ����̼߳����������
 * 
 * @author liugaowei
 *
 */
public class ThreadScope implements Scope {

	private final ThreadLocal<Map<String, Object>> THREAD_SCOPE = new ThreadLocal<Map<String, Object>>() {
		@Override
		protected Map<String, Object> initialValue() {
			// ���ڴ���߳���ص�bean
			return new HashMap<String, Object>();
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		// �����ǰ�߳��Ѿ�������Ӧ��bean�� ��ֱ�ӷ���
		if (THREAD_SCOPE.get().containsKey(name)) {
			return THREAD_SCOPE.get().get(name);
		}

		// ʹ��ObjectFactory����bean�����󶨵���ǰ���߳�
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
		// TODO Auto-generated method stub Bean ���������߼�
		// �˴���ʵ�־ʹ�������proytotype���������ظ��û���Ͳ�����

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
