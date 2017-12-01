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
	 * ClassPathResource ����classpath·������Դ�� ��ʹ��ClassLoader���м�����Դ�� classpath��Դ
	 * ��������·�����ļ�ϵͳ�л�jar���У� �� isOpen ��Զ����false����ʾ�ɶ�ζ�ȡ��Դ��
	 * ClassPathResource������Դ�����Class���ClassLoader��ġ�getResource(String name)
	 * " �� ��getResourceAsStream(String name)"����������·����Դ�ķ������ṩһ�µķ��ʷ�ʽ��
	 * ClassPathResource �ṩ���������캯����
	 * ��1��public ClassPathResource(String path) : ʹ��Ĭ�ϵ�ClassLoader����path��·����Դ��
	 * ��2��public ClassPathResource(String path, ClassLoader classloader) :
	 * ʹ��ָ����ClassLoader����path��·����Դ
	 * ��3��public ClassPathResource(String path, Class<?> clazz):
	 * ʹ��ָ���������path��·����Դ�� ����������ڵ�ǰ���·������Դ��
	 * 
	 * (1),(2) --->�Ǿ���·��
	 * (3) ----> ���·���� �����Class�ļ���·����
	 * 
	 * @param resource
	 * @throws IOException
	 */
	/**
	 * ʹ��Ĭ�ϵ�ClassLoader����
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
	 * ʹ��ָ����ClassLoader���м�����Դ�� ������ָ����ClassLoader��·��������ڸ�·������Դ
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
	 * ʹ��ָ�����࣬ �����Լ�������ڵ�ǰ���·���µ���Դ
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
	 * ����jar�������Դ�������ڵ�ǰ��·�����ң�����Ҳ��������jar���ң����ص�һ��jar���ҵ��Ľ��
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
		 * ������resource.getFile() Ӧ���� resource.getURL(). ��Ϊ��Դ���������ļ�ϵͳ���Ǵ�����jar����
		 */
		System.out.println("path: " + resource.getURL().getPath());
		Assert.assertEquals(false, resource.isOpen());
	}

	// ======================================================
	/**
	 * URLResource ����URL��Դ�� ���ڼ�URL��Դ���ʡ�isOpen��Զ����false����ʾ�ɶ�ζ�ȡ��Դ
	 * URLResource֧�����·�ʽ���ʣ�
	 * ��1��http ��ͨ����׼��httpЭ�����web��Դ new URLResource("http://...");
	 * ��2��ftp : ͨ��ftp������Դ�� �磺new URLResource("ftp://...")
	 * ��3��file��ͨ��fileЭ����ʱ����ļ�ϵͳ��Դ ��new URLResource("file:d:/test.txt");
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
