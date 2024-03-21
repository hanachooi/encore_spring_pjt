package com.example.encore_spring_pjt.config;



// ibatis : Mybatis의 이전버전
import org.springframework.context.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

/*
 * @Component : 객체생성과 라이프사이클 관리를 
 * Spring Container에게 위임해서 하겠다.
 * Inversion of Control (IOC) : 객체의 생성을 개발자가 아니라 프레임워크에서 하는 것
 * -> 제어의 역행
 * - IOC(Dependenct Injection(DI) : 서비스가 DAO를 주입받는 것, 
 * Dependency Lookup(DL))
 * - @Controller
 * - @Service
 * - @Repository
 * - @Bean
 * 
 * 환경설정에 관련된 객체
 * - @Configuration
 * 
 * Dependency Injection 관련 어노테이션으로
 * spring container(ApplicationContext)가 관리하는 객체를 가져다 쓸 수 있다.
 * - Autowired
 * - Inject
 * - Resource
 * - Qulified
 * - 
 */

@Configuration
@PropertySource("classpath:/application.properties")
// classpath에 있는 properties내용을 가져오겠다.
public class MariadbConfig {

    // DI 구현
    @Autowired
    private ApplicationContext context;

    // 개발자가 따로 지정을 하지 않았기에 hikariConfig 라는 이름으로 관리된다.
    // 개발자가 객체를 생성했지만 Spring Container가 관리하는 객체로 바꾸겠다.
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig(){
        // DB정보를 담는 객체
        return new HikariConfig();
    }
    // hikari : Spring Boot에서 내장하고 있는 CP를 관리하는 객체
    
    // import할 때 javax.sql의 DataSource로
    @Bean
    public DataSource dataSource(){
        // DataSource에 넘겨서 Connection Pool? 생성,
        // DataSource : Conneciton Pool 생성 
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        // Connection pool과 다른 객체들을 관리하는 factory
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        // factory에 cp를 넣기
        factory.setDataSource(dataSource());

        // Mapper xml등록, 자바경로가 아닐경우 classPath
        factory.setMapperLocations(context.getResources("classpath:/mapper/**/*Mapper.xml"));
        // ** : 모든 파일과 디렉토리, 디렉토리일 경우 /를 타고 다음으로
        
        factory.setConfiguration(myConfiguration());
        
        return factory.getObject();

    }

    // hikari엥서 만든 CP를 spring container의 Factory에 등록

    // factory에서 encore라는 이름으로
    // cp에서 connection을 꺼내는 작업
    @Bean("encore")
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration myConfiguration(){
        return new org.apache.ibatis.session.Configuration();
        // 카멜과 스네이크를 매핑시키는 작업
    }
    
}
