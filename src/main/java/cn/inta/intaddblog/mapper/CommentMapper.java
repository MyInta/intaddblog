package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/7
 * @describe
 */
@Repository
public interface CommentMapper {
   int saveComment(Comment comment);

   List<Integer> findIdsByBlogId(String bid);

   int deleteById(Integer id);

   int deleteByPid(Integer ParentID);

   int updatePid(Integer parentID);

   List<Comment> findByBlogId(Integer bid);

   List<Comment> findByPid(Integer parentID);

   List<Comment> list();
}
