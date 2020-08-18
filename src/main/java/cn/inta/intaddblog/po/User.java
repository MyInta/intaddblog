package cn.inta.intaddblog.po;

import lombok.Data;

import java.util.Date;

/**
 * @author inta
 * @date 2020/8/5
 * @describe
 */
@Data
public class User {

    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String avatar;

    private Date createTime;
    private Date updateTime;
}
