package cn.inta.intaddblog.po;

import lombok.Data;

import java.util.Date;

/**
 * @author inta
 * @date 2020/8/5
 * @describe 分类 专栏表
 */
@Data
public class Type {
    private Integer id;
    private String name;
    private String introduction;
    private Integer number;
    private Date updateTime;
}
