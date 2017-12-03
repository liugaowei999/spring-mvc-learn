package com.liugw.learn.resourcetest;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class ResourceBean implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

}
