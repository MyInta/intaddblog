package cn.inta.intaddblog.po;

import lombok.Data;

import java.util.Date;

/**
 * @author inta
 * @date 2020/8/5
 * @describe 评论实体类
 *      评论id 评论用户名称 邮箱地址 博客ID 发布时间 是否是管理员 评论内容 父评论ID
 */
@Data
public class Comment {
    private Integer id;
    private String username;
    private String email;
    private Integer blogID;
    private Date createTime;
    private Boolean adminRight;
    private String content;
    private Integer parentID;
}
