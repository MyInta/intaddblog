package cn.inta.intaddblog.vo;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.po.User;
import lombok.Data;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/17
 * @describe 用于返回到blog.html的封装类
 */
@Data
public class BlogHtml {

    private User user;
    private Blog blog;
    private List<Tag> tags;
    private List<BlogHtmlCommentPart> blogHtmlCommentParts;

}
