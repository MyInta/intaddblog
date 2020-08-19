package cn.inta.intaddblog.service.impl;

import cn.inta.intaddblog.mapper.CommentMapper;
import cn.inta.intaddblog.po.Comment;
import cn.inta.intaddblog.service.CommentService;
import cn.inta.intaddblog.vo.BlogHtmlCommentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author inta
 * @date 2020/8/17
 * @describe 评论区内容业务层
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<BlogHtmlCommentPart> listCommentByBlogId(Integer blogId) {
        List<Comment> commentList = commentMapper.findByBlogId(blogId);
        return changeCommentToBlogHtmlCommentPart(commentList);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }

    /*将comment类型转化成blog页面所需类型*/
    private List<BlogHtmlCommentPart> changeCommentToBlogHtmlCommentPart(List<Comment> commentList) {
        List<BlogHtmlCommentPart> blogHtmlCommentParts = new ArrayList<>();
        Queue<Comment> queue = new LinkedList<>();
        for (Comment c : commentList) {
            //如果是父评论，就进行添加
            if (c.getParentID().equals(-1)) queue.add(c);
        }
        while (!queue.isEmpty()) {
            Comment temp = queue.poll();
            BlogHtmlCommentPart bhcp = new BlogHtmlCommentPart();
            List<Comment> children = new ArrayList<>();
            //遍历评论集合，找到父评论id为当前评论id的子评论，进行添加
            for (Comment c : commentList) {
                if (c.getParentID().equals(temp.getId())) {
                    children.add(c);
                }
            }
            bhcp.setCurComment(temp);
            bhcp.setChildren(children);
            //前面将当前评论和其子评论都已经添加ok了，那就加入到结果集中
            blogHtmlCommentParts.add(bhcp);
        }
        return blogHtmlCommentParts;
    }
}
