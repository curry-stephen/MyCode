package com.yy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {

    //添加分组
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("黎治跃");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("李帅");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("刘超琪");
    }

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment) {

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //获取项目环境
        boolean b = environment.acceptsProfiles(profiles);
        System.out.println(b);

        return new Docket(DocumentationType.SWAGGER_2).groupName("YY")
                .apiInfo(apiInfo())
                .enable(b)  //是否启动Swagger，false，则浏览器无法访问Swagger
                .select()
                //RequestHandlerSelectors,配置要扫描的接口方式
                //basePackage指定要扫描的包
                //any : 扫描全部
                //none :不扫描
                //withClassAnnotation : 扫描类上的注解
                .apis(RequestHandlerSelectors.basePackage("com.yy.controller"))
                //path :过滤路径
                //.paths(PathSelectors.ant("/yy/**"))
                .build();
    }

//配置Swagger信息=apiinfo

    private ApiInfo apiInfo() {
        Contact contact = new Contact("YY", "https://www.4399.com", "2463252763@qq.com");
        return new ApiInfo("YY的SwaggerAPI文档",
                "黎治跃失恋了，2022/2/19",
                "1.0v",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
