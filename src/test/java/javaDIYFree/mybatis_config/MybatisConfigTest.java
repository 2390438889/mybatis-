package javaDIYFree.mybatis_config;

import javaDIYFree.dao.UserMapper;
import javaDIYFree.model.User;
import junit.framework.Assert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @author Hearts
 * @date 2019/4/22
 * @desc
 */
public class MybatisConfigTest {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @Before
    public void init(){
        String resource = "config/MapperConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
       sqlSessionFactory = builder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void selectAllTest(){
        userMapper.selectAll().forEach(new Consumer<User>() {
            public void accept(User user) {
                System.out.println(String.format("%s ==========> %s",user.getUsername(),user.getPassword()));
            }
        });

    }

    @Test
    /**
     * 测试insert方法
     */
    public void insertUser(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        Assert.assertEquals(userMapper.insert(user),1);
    }

    @After
    public void close(){
        sqlSession.close();
    }
}
