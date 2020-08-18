package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author inta
 * @date 2020/8/8
 * @describe
 */
@Repository
public interface UserMapper {

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findUserById(Integer user_id);
}
