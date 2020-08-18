package cn.inta.intaddblog.po;

import lombok.Data;

import java.util.Date;

/**
 * @author inta
 * @date 2020/8/5
 * @describe 标签表
 */
@Data
public class Tag {
    private Integer id;
    private String name;
    private Integer number;
    private Date updateTime;
}
