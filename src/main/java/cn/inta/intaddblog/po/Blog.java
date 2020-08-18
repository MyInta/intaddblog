package cn.inta.intaddblog.po;

import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author inta
 * @date 2020/8/5
 * @describe 博客实体类
 *
 *      id 标题 摘要 内容 发布时间 所属专栏 游览量 标签 评论 首图地址
 *     是否开启推荐 是否开启转载声明 是否开启赞赏 是否开启评论 属性（1原创、2转载、3翻译） 状态（0草稿、1已发布）
 *
 *     使用了lombok插件，不设置setget方法
 */
@Component
@Data
public class Blog {

    //博客id
    @NonNull
    private Integer id;
    //标题
    @NonNull
    private String title;
    //摘要
    private String summary;
    //内容
    private String content;
    //发布时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //所属专栏
    private Integer type;
    //游览量
    private Integer views;
    //标签
    private String tags;
    //评论
    private String comments;
    //首图地址
    private String pictureUrl;
    //是否开启推荐
    private Boolean recommendRight;
    //是否开启转载声明
    private Boolean reprintRight;
    //是否开启赞赏
    private Boolean appreciationRight;
    //是否开启评论
    private Boolean commentRight;
    //属性（1原创、2转载、3翻译）
    private Integer property;
    //状态（0草稿、1已发布）
    private Integer state;
    //博客隶属的用户id信息
    private Integer userId;

    public Blog() {
    }
}
