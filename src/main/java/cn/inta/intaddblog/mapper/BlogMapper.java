package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.Blog;
import cn.inta.intaddblog.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/8/5
 * @describe
 */
@Repository
public interface BlogMapper {

    /**
     * 分页查找
     * @param map
     * @return
     */
    List<Blog> findTop(Map<String, Object> map);

    /**
     * 数量统计
     * @return
     */
    Integer count();

    /**
     * 依据搜索名称，模糊查询博客，返回结果分页
     * @param query
     * @param map
     * @return
     */
    List<Blog> findByQuery(String query, Map<String, Object> map);


    /**
     * 依据map或json数据添加博客
     * @param map
     * @return
     */
    Integer insertBlog(Map map);

    /**
     * 添加博客
     * @param blog
     * @return
     */
    Blog addBlog(Blog blog);

    /**
     * 依据博客id删除该博客
     * @param id
     * @return
     */
    Integer deleteBlogById(Integer id);

    /**
     * 依据博客id更新博客游览数量+1
     * @param id
     * @return
     */
    Integer updateViews(Integer id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 依据博客id查询博客
     * @param id
     * @return
     */
    Blog findBlogById(Integer id);

    /**
     * 查询出所有博客
     * @return
     */
    List<Blog> findBlogAll();

    /**
     * 分页查询博客
     * @param begin
     * @param size
     * @return
     */
    List<Blog> findBlogByPage(Integer begin, Integer size);

    /**
     * 依据自定义条件查询博客
     * @param blog
     * @return
     */
    List<Blog> findBlogByCondition(Blog blog);

    /**
     * 根据名称查询博客id
     * @param title
     * @return
     */
    Integer findBlogIDByName(String title);

    /**
     * 查询所有可见（已发布）的博客
     * @return
     */
    List<Blog> findBlogAllVisible();

    /**
     * 查询出最近被回复的size条blog
     * @param num
     * @return
     */
    List<Blog> findRecommendTop(Integer num);

    /**
     * 获取归档所需的年份列表
     * @return
     */
    List<String> findGroupYear();

    /**
     * 依据年份查询归档中的博客
     * @param year
     * @return
     */
    List<Blog> findByYear(String year);
}
