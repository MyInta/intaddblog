package cn.inta.intaddblog.service;

import cn.inta.intaddblog.po.User;

/**
 * @author inta
 * @date 2020/8/8
 * @describe
 */
public interface UserService {

    /*核对账户密码*/
    User checkUser(String username, String password);

    /**
     * 通过用户id获取到它属于的用户信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);
}
