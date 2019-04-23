package javaDIYFree.dao;

import javaDIYFree.config.MybatisConfig;
import javaDIYFree.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    /**
     * 测试insert方法
     */
    public void insertUser(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        userMapper.insert(user);
    }

    public List<User> generatorUserList(int count){
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setUsername("lisi");
            user.setPassword("123456");
            list.add(user);
        }
        return list;
    }

 /*   @Test
    public void insertEmptyUserListTest(){
        userMapper.insertList(generatorUserList(0));
    }

    *//**
     * 测试insertList方法
     *//*
    @Test
    public void insertUserList(){
        userMapper.insertList(generatorUserList(10));
    }
*/


    @Test
    /**
     * 测试selectAll方法
     */
    public void selectAllUser(){

        userMapper.selectAll().forEach(new Consumer<User>() {
            public void accept(User i) {
                //打印用户名和密码
                System.out.println(i.getUsername() +" ======> "+i.getPassword());
            }
        });
    }
}
