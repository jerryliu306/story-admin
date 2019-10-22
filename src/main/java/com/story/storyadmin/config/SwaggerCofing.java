package com.story.storyadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerCofing extends WebMvcConfigurationSupport {
    @Bean
    public Docket apiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.story.storyadmin.web")).paths(PathSelectors.any()).build()//过滤的接口
                .groupName("STORY-ADMIN 后端接口文档") //定义分组
                .apiInfo(apiInfo()) //展示在文档页面信息内容
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("STORY-ADMIN API")
                .description("STORY-ADMIN's REST API")//详细描述
                .version("1.0")
                .contact(new Contact("sunningjun", "http://www.sundayfine.com", "sunnj87@163.com"))//作者
//                .license("The Apache License, Version 2.0")//许可证信息
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//许可证地址
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}