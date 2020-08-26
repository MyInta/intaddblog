package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.vo.TagsHtml;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/7
 * @describe 标签数据库操控层
 */
@Repository
public interface TagMapper {

    /*通过标签名获取标签*/
    Tag findByName(String name);

    /*找到所有标签*/
    List<Tag> findAll();

    /*通用标签id获取标签信息*/
    Tag findById(Integer id);

    /*通过博客id获得该博客下的所有标签*/
    List<Tag> findByBlogId(Integer id);

    /*查询出num数量的最新更新的标签*/
    List<Tag> findTop(Integer num);

    /*保存tag信息*/
    Integer save(Tag tag);

    /*通过id删除标签*/
    void deleteById(Integer id);

    /*通过id更新标签*/
    Integer update(Tag tag);

    Integer getNumById(Integer id);
}
