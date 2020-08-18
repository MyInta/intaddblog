package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.Tag;
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

    /*找到最热门的标签*/
    List<Tag> findTop();

    /*通用标签id获取标签信息*/
    Tag findById(Integer id);

}
