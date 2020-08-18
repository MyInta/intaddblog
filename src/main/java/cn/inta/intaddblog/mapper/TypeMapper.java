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



//    List<Type> findTop();
}
