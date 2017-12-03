package com.liugw.learn.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.liugw.learn.resourcetest.ResourceBean;
import com.liugw.learn.resourcetest.ResourceBean3;

import junit.framework.Assert;

public class ResourceLoaderAwareTest {

	@Test
	public void test() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");

		ResourceBean resourceBean = context.getBean(ResourceBean.class);

		ResourceLoader loader = resourceBean.getResourceLoader();

		dumpStream(loader.getResource("classpath:com/liugw/learn/resourcetest/test1.properties"));

		Assert.assertTrue(loader instanceof ApplicationContext);
		if (loader instanceof ApplicationContext) {
			System.out.println("loader instanceof ApplicationContext");
		}

		System.out.println(loader.getClass().getName());
		System.out.println(loader.toString());
	}

	@Test
	public void testIject() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-applicationcontext.xml");

		ResourceBean3 resourceBean1 = context.getBean("resourceBean1", ResourceBean3.class);
		ResourceBean3 resourceBean2 = context.getBean("resourceBean2", ResourceBean3.class);

		dumpStream(resourceBean1.getResource());
		if (resourceBean1.getResource() instanceof ClassPathResource) {
			System.out.println("resourceBean1.getResource() instanceof ClassPathResource");
		}

		if (resourceBean2.getResource() instanceof ClassPathResource) {
			System.out.println("resourceBean2.getResource() instanceof ClassPathResource");
		}

	}

	@Test
	public void testClasspathPrefix() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		// 只加载一个绝对匹配的Resource，且通过ResourceLoader.getResource进行加载
		Resource[] resources = resolver.getResources("classpath:META-INF/INDEX.LIST");
		// System.out.println(resources[0].getFile().getAbsolutePath());
		System.out.println(resources.length);
		// dumpStream(resources[0]);

		// 使用通配符，仍然用ResourceLoader.getResource
		resources = resolver.getResources("classpath:META-INF/*.LIST");
		System.out.println(resources.length);

	}

	@Test
	// classpath*:
	public void testClasspathAsteriskPrefix() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		// 加载多个绝对匹配的所有Resource
		// 首先通过ClassLoader.getResources("META-INF") 加载非模式路径部分
		// 然后进行便利模式匹配
		Resource[] resources = resolver.getResources("classpath*:META-INF/INDEX.LIST");
		System.out.println("count=" + resources.length);

		// 加载多个模式匹配的Resource
		resources = resolver.getResources("classpath*:META-INF/*.LIST");
		System.out.println(resources.length);

	}

	/////////////////////////
	private void dumpStream(Resource resource) throws IOException {

		if (!resource.exists()) {
			System.out.println("resource is not existes!!");
		}
		System.out.println(resource.getFile().getAbsolutePath());

		// 获取文件资源
		try (InputStream is = resource.getInputStream()) {

			// 读取资源
			byte[] descBytes = new byte[is.available()];
			is.read(descBytes);
			System.out.println(new String(descBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
