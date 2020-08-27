package com.buzz.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@PropertySource({ "classpath:application.yml" })
@Configuration
@EnableTransactionManagement
//@MapperScan("com.buzz.app.mapper.maindb")
@MapperScan(value ="com.buzz.app.mapper.maindb",sqlSessionFactoryRef = "mainSqlSessionFactory")
public class MainDataSourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(MainDataSourceConfig.class);
	
	@Autowired
	private ApplicationContext _applicationContext;
	
	public MainDataSourceConfig() {
		logger.info("start >>> MainDataSourceConfig");
	}
	
	@Primary
	@Bean(name = "mainDataSource")    
	@ConfigurationProperties(prefix="spring.maindb.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	/******************************************************************************************************************
     *     DB Transaction 설정
     ******************************************************************************************************************/
	@Primary
	@Bean(name = "mainPlatformTransactionManager")    
    public PlatformTransactionManager  transactionManager(){
   	    	
    	//default 설정
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(mainDataSource());
        
        //HikariCP 사용시 아래 코드 사용
        //DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
    
    /******************************************************************************************************************
     *     MyBatis 설정파일을 바탕으로 SqlSessionFactory를 생성
     ******************************************************************************************************************/
	@Primary
    @Bean(name = "mainSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) throws Exception {
    	
    	logger.info("start >>> mainSqlSessionFactory");
    	
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
            
        //1. 매퍼 설정 (실제 xml 파일이 없으면 실행시 오류)       
        sqlSessionFactoryBean.setMapperLocations(_applicationContext.getResources("classpath*:/mapper/maindb/**/*.xml"));
        
        //2. Config 설정
        //mybatis-config.xml 에서는 typeAlias 와 그외 설정은 각 프로젝트에서 개별설정
        sqlSessionFactoryBean.setConfigLocation(_applicationContext.getResource("classpath:/mybatis-config.xml"));
        
                
        //3. alias 설정
        //alias 의 경우 config 파일이 합쳐지는 경우 중복이슈가 있어 공통에서는 가급적 별칭을 사용하지 않는다
        //사용하는 경우 @Alias 어노테이션과 패키지 지정 방식을 사용 한다
        //sqlSessionFactoryBean.setTypeAliasesPackage("kr.org.grac.common.vo");
                
        return sqlSessionFactoryBean.getObject();
    }
    
    /******************************************************************************************************************
     * Mybatis SqlSessionTemplate 설정
     * 핵심적인 역할을 하는 클래스로서 SQL 실행이나 트랜잭션 관리를 한다.
     * SqlSession 인터페이스를 구현해야 하며, Thread-safe 하다.
     ******************************************************************************************************************/
	@Primary
    @Bean(name = "mainSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	   	
    	
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
}
