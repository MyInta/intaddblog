package cn.inta.intaddblog.service.impl;

import cn.inta.intaddblog.exception.NotFoundException;
import cn.inta.intaddblog.mapper.BlogMapper;
import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.service.BlogService;
import cn.inta.intaddblog.util.MarkdownUtils;
import cn.inta.intaddblog.vo.SearchHtml;
import cn.inta.intaddblog.vo.admin.AdminBlogsHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/8/5
 * @describe
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    public BlogMapper blogMapper;

    @Override
    public Blog findBlogById(Integer blogId) {
        return blogMapper.findBlogById(blogId);
    }

    @Override
    public Blog getAndConvert(Integer id) {
        Blog blog;
        try {
            blog = blogMapper.findBlogById(id);
        } catch (Exception e) {
            throw new NotFoundException("该博客不存在");
        }
//        Blog b = new Blog();
//        BeanUtils.copyProperties(blog,b);
//        String content = b.getContent();
//        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        blogMapper.updateViews(id);
        return blog;
    }

    @Override
    public List<Blog> findBlogByCondition(Blog blog) {
        return blogMapper.findBlogByCondition(blog);
    }

    @Override
    public List<Blog> findBlogByPage(Integer begin, Integer size) {
        return null;
    }

    @Override
    public List<Blog> findRecommendTop(Integer num) {
        return blogMapper.findRecommendTop(num);
    }

    @Override
    public List<Blog> findUpTimeTop(Integer num) {
        return blogMapper.findUpTimeTop(num);
    }

    @Override
    public List<SearchHtml> findByQuery(String query) {
        return blogMapper.findByQuery(query);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public Integer blogNum() {
        return blogMapper.count();
    }

    @Override
    public Integer updateBlog(Integer id, Blog blog) {
        Blog b = blogMapper.findBlogById(id);
        //如果没有该id的博客，就直接返回0表示失败
        if (b == null) return 0;
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Integer id) {

    }

    @Override
    public String tagsToIds(List<Tag> tags) {
        StringBuffer ids = new StringBuffer();
        boolean flag = false;
        for (Tag tag : tags) {
            if (flag) {
                ids.append(",");
            } else {
                flag = true;
            }
            ids.append(tag.getId());
        }
        return ids.toString();
    }

    @Override
    public List<Blog> findBlogByTagId(Integer id) {
        return blogMapper.findBlogByTagId(id);
    }

    @Override
    public List<Blog> findBlogByTypeId(Integer id) {
        return blogMapper.findBlogByTypeId(id);
    }

    @Override
    public List<Blog> findAll() {
        return blogMapper.findAll();
    }

    @Override
    public List<AdminBlogsHtml> adminBlog() {
        return blogMapper.findAdminBlog();
    }

    @Override
    public Integer saveBlog(Blog blog) {
        return blogMapper.save(blog);
    }

}
