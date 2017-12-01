package com.liugw.learn.resourcetest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import junit.framework.Assert;

public class ResourceTest {

	@Test
	public void testByteArrayResource() {

		Resource resource = new ByteArrayResource("hello world".getBytes());
		if (resource.exists()) {
			System.out.println("===========================");
			dumpStream(resource);
		}
	}

	@Test
	public void testInputStreamResource() {
		ByteArrayInputStream bis = new ByteArrayInputStream("Hello world InputStreamResource".getBytes());
		Resource resource = new InputStreamResource(bis);
		if (resource.exists()) {
			dumpStream(resource);
		}
	}

	@Test
	public void testFileResource() {
		File file = new File("D:/test.txt");
		Resource resource = new FileSystemResource(file);
		if (resource.exists()) {
			dumpStream(resource);
		}
		junit.framework.Assert.assertEquals(false, resource.isOpen());
	}

	///////////////////////////// ClassPathResource
	///////////////////////////// //////////////////////////////////////////
	/**
	 * ClassPathResource 代表classpath路径的资源， 将使用ClassLoader进行加载资源。 classpath资源
	 * 存在于类路径的文件系统中或jar包中， 且 isOpen 永远返回false，表示可多次读取资源。
	 * ClassPathResource加载资源替代了Class类和ClassLoader类的“getResource(String name)
	 * " 和 ”getResourceAsStream(String name)"两个加载类路径资源的方法。提供一致的访问方式。
	 * ClassPathResource 提供了三个构造函数：
	 * （1）public ClassPathResource(String path) : 使用默认的ClassLoader加载path类路径资源；
	 * （2）public ClassPathResource(String path, ClassLoader classloader) :
	 * 使用指定的ClassLoader加载path类路径资源
	 * （3）public ClassPathResource(String path, Class<?> clazz):
	 * 使用指定的类加载path类路径资源， 将加载相对于当前类的路径的资源。
	 * 
	 * (1),(2) --->是绝对路径
	 * (3) ----> 相对路径。 相对于Class文件的路径。
	 * 
	 * @param resource
	 * @throws IOException
	 */
	/**
	 * 使用默认的ClassLoader加载
	 * 
	 * @throws IOException
	 */
	@Test
	public void testClassPathResourceByDefaultClassLoader() throws IOException {
		Resource resource = new ClassPathResource("com/liugw/learn/resourcetest/test1.properties");
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path:" + resource.getFile().getAbsolutePath());
		System.out.println("file_name:" + resource.getFilename());
		Assert.assertEquals(false, resource.isOpen());
	}

	/**
	 * 使用指定的ClassLoader进行加载资源， 将加载指定的ClassLoader类路径上相对于根路径的资源
	 * 
	 * @param resource
	 * @throws IOException
	 */
	@Test
	public void testClasspathResourceByClassLoader() throws IOException {
		ClassLoader c1 = this.getClass().getClassLoader();

		Resource resource = new ClassPathResource("com/liugw/learn/resourcetest/test1.properties", c1);
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path:" + resource.getFile().getAbsolutePath());
		System.out.println("file_name:" + resource.getFilename());
		Assert.assertEquals(false, resource.isOpen());
	}

	/**
	 * 使用指定的类， 将尝试加载相对于当前类的路径下的资源
	 * 
	 * @param resource
	 * @throws IOException
	 */
	@Test
	public void testClasspathResourceClass() throws IOException {
		Class clazz = this.getClass();
		Resource resource = new ClassPathResource("test1.properties", clazz);
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("file path:" + resource.getFile().getAbsolutePath());
		Assert.assertEquals(false, resource.isOpen());
	}

	/**
	 * 加载jar包里的资源，首先在当前类路径下找，如果找不到，最后到jar里找，返回第一个jar包找到的结果
	 * 
	 * path:
	 * file:/C:/Users/liugw/.m2/repository/org/springframework/spring-context-
	 * support/4.0.0.RELEASE/spring-context-support-4.0.0.RELEASE.jar!/overview.
	 * html
	 * 
	 * @param resource
	 * @throws IOException
	 */
	@Test
	public void testClasspathResourceFromJar() throws IOException {
		Resource resource = new ClassPathResource("overview.html");
		if (resource.exists()) {
			dumpStream(resource);
		}

		/**
		 * 不能用resource.getFile() 应该用 resource.getURL(). 因为资源不存在于文件系统而是存在于jar包中
		 */
		System.out.println("path: " + resource.getURL().getPath());
		Assert.assertEquals(false, resource.isOpen());
	}

	// ======================================================
	/**
	 * URLResource 代表URL资源， 用于简化URL资源访问。isOpen永远返回false，表示可多次读取资源
	 * URLResource支持以下方式访问：
	 * （1）http ：通过标准的http协议访问web资源 new URLResource("http://...");
	 * （2）ftp : 通过ftp访问资源， 如：new URLResource("ftp://...")
	 * （3）file：通过file协议访问本地文件系统资源 ，new URLResource("file:d:/test.txt");
	 * 
	 * @throws IOException
	 */
	@Test
	public void testURLResource() throws IOException {
		Resource resource = new UrlResource("file:d:/test.txt");
		if (resource.exists()) {
			dumpStream(resource);
		}
		System.out.println("path=" + resource.getFile().getAbsolutePath());
	}

	private void dumpStream(Resource resource) {

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
