package lujie.dao;

import lujie.config.MybatisConfig;
import lujie.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Hearts
 * @date 2019/4/17
 * @desc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertUser(){
//        final SqlSession session = sqlSessionFactory.openSession();
//        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        userMapper.insert(user);
//        session.close();
    }

    @Test
    public void selectUser(){


    }
}
