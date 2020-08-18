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
}
