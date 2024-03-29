package com.example.config;

import java.util.Properties;

import com.example.common.Constants;
import com.example.util.CommonUtil;

public class EnvironmentConfig {

	private static Properties properties = null;
	public static String TOPIC_NAME;

	static {
		properties = CommonUtil.getConfigPropertiesFn();
		loadEnvironmentVariable();
	}

	private EnvironmentConfig() {

	}

	public static void loadEnvironmentVariable() {
		TOPIC_NAME = properties.getProperty(Constants.TOPIC_NAME);
	}
}
