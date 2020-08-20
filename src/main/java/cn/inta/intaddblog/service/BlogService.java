package cn.inta.intaddblog.service;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.Tag;
import cn.inta.intaddblog.vo.SearchHtml;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/8/5
 * @describe
 */
public interface BlogService {


    /**
     * 根据主键查询博客
     * @param blogId
     * @return
     */
    Blog findBlogById(Integer blogId);

    /**
     * 依据id查询出blog，并将其转化为markdown网页
     * @param id
     * @return
     */
    Blog getAndConvert(Integer id);

    /**
     * 依据复杂条件查询出blog
     * @param blog
     * @return
     */
    List<Blog> findBlogByCondition(Blog blog);

    /**
     * 分页查询博客
     * @param begin
     * @param size
     * @return
     */
    List<Blog> findBlogByPage(Integer begin, Integer size);

    /**
     * 查询出最新更新的被推荐的size条blog
     * @param num
     * @return
     */
    List<Blog> findRecommendTop(Integer num);

    /**
     * 查询出最近更新的size条blog 其id和标题信息即可
     * @param num
     * @return
     */
    List<Blog> findUpTimeTop(Integer num);

    /**
     * 依据查询条件返回博客分页
     * @param query
     * @return
     */
    List<SearchHtml> findByQuery(String query);

    /**
     * 获取归档页中所有博客信息
     * @return
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 获取博客总数量
     * @return
     */
    Integer blogNum();

    /**
     * 添加博客
     * @param blog
     * @return
     */
    Blog addBlog(Blog blog);

    /**
     * 依据id更新博客
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Integer id, Blog blog);

    /**
     * 依据id删除博客
     * @param id
     */
    void deleteBlog(Integer id);

    /**
     * 将标签列表转化为间隔逗号','的字符串
     * @param tags
     * @return
     */
    String tagsToIds(List<Tag> tags);

    /**
     * 通过标签id查询出所有博客
     * @param id
     * @return
     */
    List<Blog> findBlogByTagId(Integer id);

    /**
     * 通过类型id查询出所有博客
     * @param id
     * @return
     */
    List<Blog> findBlogByTypeId(Integer id);

    /**
     * 查询出所有博客
     * @return
     */
    List<Blog> findAll();
}
