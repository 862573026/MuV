package com.newx.muv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.newx.muv.netty.TCPServer;
import com.newx.muv.support.XssSqlStringJsonSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.WebApplicationInitializer;


@SpringBootApplication
@MapperScan("com.newx.muv.dao")
@EnableCaching
@ServletComponentScan
public class MuvApplication extends SpringBootServletInitializer
		implements WebApplicationInitializer,CommandLineRunner{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MuvApplication.class);
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(MuvApplication.class, args);
		TCPServer tcpServer = context.getBean(TCPServer.class);
		tcpServer.start();
	}




	@Override
	public void run(String... args) throws Exception {

	}
}
