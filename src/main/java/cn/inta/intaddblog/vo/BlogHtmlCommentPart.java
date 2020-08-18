package cn.inta.intaddblog.vo;

import cn.inta.intaddblog.po.Comment;
import lombok.Data;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/17
 * @describe 博客内容中的评论区域实体包装类
 */
@Data
public class BlogHtmlCommentPart {

    Comment curComment;

    List<Comment> children;
}
