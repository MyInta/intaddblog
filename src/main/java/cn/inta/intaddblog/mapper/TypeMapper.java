package cn.inta.intaddblog.mapper;

import cn.inta.intaddblog.po.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author inta
 * @date 2020/8/8
 * @describe
 */
@Repository
public interface TypeMapper {

    Type findByName(String name);

    Type findById(Integer id);

    List<Type> findAll();

    List<Type> findTop(Integer num);

    String findIntroductionById(Integer id);

    Integer save(Type type);

    Integer findNumberById(Integer id);

    Integer update(Type type);

    void deleteById(Integer id);
}
