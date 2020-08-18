package cn.inta.intaddblog.service;

import cn.inta.intaddblog.po.Comment;
import cn.inta.intaddblog.vo.BlogHtmlCommentPart;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/17
 * @describe
 */
public interface CommentService {

    List<BlogHtmlCommentPart> listCommentByBlogId(Integer blogId);

    Comment saveComment(Comment comment);
}
