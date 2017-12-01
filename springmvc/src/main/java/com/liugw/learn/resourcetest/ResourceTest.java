package com.liugw.learn.resourcetest;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class ResourceTest {

	@Test
	public void testByteArrayResource() {

		Resource resource = new ByteArrayResource("hello world".getBytes());
		if (resource.exists()) {
			System.out.println("===========================");
			dumpStream(resource);
		}
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
