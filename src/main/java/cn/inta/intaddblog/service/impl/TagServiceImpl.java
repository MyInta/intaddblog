package cn.inta.intaddblog.service.impl;

import cn.inta.intaddblog.mapper.TagMapper;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/8/17
 * @describe
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.findById(id);
    }

    @Override
    public List<Tag> getTagsByIds(String tagIds) {
        List<Tag> tags = new ArrayList<>();
        String[] splits_tags = tagIds.split(",");
        for (String tag : splits_tags) {
            Integer tag_value = Integer.valueOf(tag);
            tags.add(getTagById(tag_value));
        }
        return tags;
    }

    @Override
    public List<Tag> findAll() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> findByBlogId(Integer id) {
        return tagMapper.findByBlogId(id);
    }

    @Override
    public List<Tag> findTop(Integer num) {
        return tagMapper.findTop(num);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.findByName(name);
    }

    @Override
    public Integer saveTag(Tag tag) {
        return tagMapper.save(tag);
    }

    @Override
    public Integer updateTag(Tag tag) {
        return tagMapper.update(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        tagMapper.deleteById(id);
    }

    @Override
    public Integer getNumById(Integer id) {
        return tagMapper.getNumById(id);
    }

}
