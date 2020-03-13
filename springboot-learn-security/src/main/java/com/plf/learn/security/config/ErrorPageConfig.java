package com.plf.learn.security.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 定义错误页面
 * @author Panlf
 *
 */
@Configuration
public class ErrorPageConfig {
	
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		/*return new WebServerFactoryCustomizer<ConfigurableWebServerFactory> () {

			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
			}
		};*/
		return factory -> factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
	}
}
