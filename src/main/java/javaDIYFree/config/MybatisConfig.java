package javaDIYFree.config;

import com.alibaba.druid.pool.DruidDataSource;
import javaDIYFree.typeHandler.VarcharTypeHandler;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Configurator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


/**
 * @author Hearts
 * @date 2019/4/16
 * @desc
 */
@Configuration
@ComponentScan(basePackages = "javaDIYFree")
//@MapperScan(basePackages = "javaDIYFree.dao")
public class MybatisConfig {


    /**
     * 配置数据源
     * 我这里使用的是阿里的连接池，里面还有很多连接池的属性，大家可以去配置
     * @return
     */
    @Bean(name = "test")
    public DruidDataSource createTestDataSource(){

        DruidDataSource druidDataSource = new DruidDataSource();
        //配置jdbc驱动类全限定类名
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //配置数据库url
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");

        //配置用户名
        druidDataSource.setUsername("root");

        //配置密码
        druidDataSource.setPassword("root");
        return druidDataSource;
    }

    /**
     * 配置mybatis SqlSessionFactoryBean 会话工厂，每一次连接都是一次会话，会话工厂就是用来生产会话的
     * @param druidDataSource
     * @return
     */
    @Bean
    public SqlSessionFactory createSqlSessionFactoryBean(DruidDataSource druidDataSource){
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        SqlSessionFactory sqlSessionFactory = null;
        try{
            //配置数据源
            sqlSessionFactoryBean.setDataSource(druidDataSource);

            //配置mapper文件所在的位置
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

            //配置自定义TypeHandler,TypeHandler的扩展后面会写
            //sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new VarcharTypeHandler()});

            //设置类型别名的类数组
            sqlSessionFactoryBean.setTypeAliases(new Class[]{VarcharTypeHandler.class});

            //设置类型别名的包
            sqlSessionFactoryBean.setTypeAliasesPackage("javaDIYFree");

            sqlSessionFactory = sqlSessionFactoryBean.getObject();

            sqlSessionFactory.getConfiguration().setLogImpl(Log4jImpl.class);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    /**
     * 配置Mapper接口扫描配置，将扫描到的Mapper接口实例化后交给spring容器管理，
     * 如果不加这个配置的话，无法注入Mapper,无法实例化，也可以直接用@MapperScan(basePackages = "javaDIYFree.dao")
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactory sqlSessionFactory){
        final MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        //Mapper文件所在的包
        mapperScannerConfigurer.setBasePackage("javaDIYFree.dao");
        try {

            //设置会话工厂，注入sqlSessionFactory,
            //（可以不用配置）只有当配置多数据源的时候，这时会有多个sqlSessionFactory，
            // 可以通过改属性来指定哪一个sqlSessionFactory（综合网上的总结）
            //来自https://blog.csdn.net/s740556472/article/details/54862476#commentBox
            mapperScannerConfigurer.setSqlSessionFactory(sqlSessionFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapperScannerConfigurer;

    }

}
