package cn.inta.intaddblog.service;

import cn.inta.intaddblog.po.Tag;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/17
 * @describe
 */
public interface TagService {

    Tag getTagById(Integer id);

    List<Tag> getTagsByIds(String tags);
}
