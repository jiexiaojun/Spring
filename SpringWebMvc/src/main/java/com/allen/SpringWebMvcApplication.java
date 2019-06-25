package com.allen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.allen.interceptor.DefaultHandlerInterceptor;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class SpringWebMvcApplication extends WebMvcConfigurerAdapter implements ErrorPageRegistrar {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebMvcApplication.class, args);
	}

	// 全局处理异常
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DefaultHandlerInterceptor());
	}

	// Spring Boot 错误处理页面 映射到访问404的路由
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
//		registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/505"));//返回json文本信息
		registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));// 返回页面
	}
}
