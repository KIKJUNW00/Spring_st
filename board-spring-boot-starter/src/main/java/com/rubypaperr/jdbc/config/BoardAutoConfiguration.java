package com.rubypaperr.jdbc.config;

import org.springframework.context.annotation.Bean;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

public class BoardAutoConfiguration {

	@Bean
	JDBCConnectionManager getJDBCConnectionManager() {
	JDBCConnectionManager manager = new JDBCConnectionManager();
	manager.setDriverClass("com.mysql.cj.jdbc.Drvier");
	manager.setUrl("jdbc:mysql://localhost:3306/bootmission");
	manager.setUsername("root");
	manager.setPassword("1234");
	return manager;
	}
}
