package com.allen.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.allen.http.messsage.PropertiesPersonHttpMessageConverter;

/**
 * Web MVC 配置
 *
 * @author mercyblitz
 * @date 2017-10-14
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.add(new PropertiesPersonHttpMessageConverter());
	}

}
