package cn.inta.intaddblog.service.impl;

import cn.inta.intaddblog.mapper.UserMapper;
import cn.inta.intaddblog.po.User;
import cn.inta.intaddblog.service.UserService;
import cn.inta.intaddblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author inta
 * @date 2020/8/8
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        //将密码转化成MD5格式再进行校验
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }


    @Override
    public User getUserById(Integer id) {
        User user = userMapper.findUserById(id);
        if (user == null) {
            user = new User();
            user.setNickname("匿名");
            user.setEmail("000000@??.??");
        }
        return user;
    }
}
