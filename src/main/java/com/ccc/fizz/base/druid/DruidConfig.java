package com.ccc.fizz.base.druid;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DruidConfig {

	@Value("${druid.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${druid.datasource.url}")
	private String url;

	@Value("${druid.datasource.username}")
	private String username;

	@Value("${druid.datasource.password}")
	private String password;

	@Bean
	@Primary
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

}
