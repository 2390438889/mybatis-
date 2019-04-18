package lujie.config;

import com.alibaba.druid.pool.DruidDataSource;
import lujie.typeHandler.VarcharTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;


/**
 * @author Hearts
 * @date 2019/4/16
 * @desc
 */
@Configuration
@ComponentScan(basePackages = "lujie")
@PropertySource("classpath:jdbc.properties")
public class MybatisConfig {



    @Bean(name = "test")
    public DruidDataSource createTestDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }


    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean(DruidDataSource druidDataSource){
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        try{
            sqlSessionFactoryBean.setDataSource(druidDataSource);
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
            sqlSessionFactoryBean.setEnvironment("druid");
            sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new VarcharTypeHandler()});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactoryBean;
    }

//    @Bean
//    public SqlSessionFactory createSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean){
//        try {
//            final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//            return sqlSessionFactory;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactory sqlSessionFactory){
        final MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("lujie.dao");
        try {
            mapperScannerConfigurer.setSqlSessionFactory(sqlSessionFactory);
            mapperScannerConfigurer.setAddToConfig(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapperScannerConfigurer;

    }

}
