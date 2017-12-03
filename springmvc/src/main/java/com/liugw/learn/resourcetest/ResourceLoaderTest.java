package com.liugw.learn.resourcetest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import junit.framework.Assert;

public class ResourceLoaderTest {

	@Test
	public void testResourceLoad() throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("classpath:com/liugw/learn/resourcetest/test1.properties");
		dumpStream(resource);
		
		// ��֤���ص�ClassPathResource
		Assert.assertEquals(ClassPathResource.class, resource.getClass());
		Resource resource2 = loader.getResource(
				"file:E:\\dev\\workspace\\spring-mvc-learn\\springmvc\\target\\classes\\com\\liugw\\learn\\resourcetest\\test1.properties");
		dumpStream(resource2);

		// ��֤���ص�ClassPathResource
		Assert.assertEquals(UrlResource.class, resource2.getClass());

		Resource resource3 = loader.getResource("com/liugw/learn/resourcetest/test1.properties");
		dumpStream(resource3);
		Assert.assertTrue(resource3 instanceof ClassPathResource);
		if (resource3 instanceof ClassPathResource) {
			System.out.println("resource3 instanceof ClassPathResource");
		}

		if (resource instanceof ClassPathResource) {
			System.out.println("resource instanceof ClassPathResource");
		}

		if (resource2 instanceof ClassPathResource) {
			System.out.println("resource2 instanceof ClassPathResource");
		}

		if (resource2 instanceof UrlResource) {
			System.out.println("resource2 instanceof UrlResource");
		}
	}

	/////////////////////////
	private void dumpStream(Resource resource) throws IOException {
		
		if(!resource.exists()) {
			System.out.println("resource is not existes!!");
		}
		System.out.println(resource.getFile().getAbsolutePath());

		// ��ȡ�ļ���Դ
		try (InputStream is = resource.getInputStream()) {

			// ��ȡ��Դ
			byte[] descBytes = new byte[is.available()];
			is.read(descBytes);
			System.out.println(new String(descBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
