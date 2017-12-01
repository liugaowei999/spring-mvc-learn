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
