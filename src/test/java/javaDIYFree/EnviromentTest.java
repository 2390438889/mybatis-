package javaDIYFree;

import com.alibaba.druid.pool.DruidDataSource;
import javaDIYFree.config.MybatisConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * @author Hearts
 * @date 2019/4/19
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class) //运行spring虚拟环境
@ContextConfiguration(classes = MybatisConfig.class) //加载自定义配置类,@ContextConfiguration(name = "classpath:applicationContext.xml") 是加载xml文件配置的

public class EnviromentTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DruidDataSource dataSource;

    @Autowired
    private MapperScannerConfigurer mapperScannerConfigurer;

    /**
     * 测试bean是否注入成功
     */
    @Test
    public void BeanTest(){

        Assert.notNull(sqlSessionFactory, "sqlSessionFactoryBean 配置失败");

        Assert.notNull(dataSource, "DruidDataSource 配置失败");

        Assert.notNull(mapperScannerConfigurer, "MapperScannerConfigurer 配置失败");

    }
}
