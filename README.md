# README

项目结构：springboot+thymeleaf+semanticUI+mysql +mybatis

插件：

>  lombok  简化po层
>
> pagehelper 分页
>
> commonmark markdown转换插件

开发过程中遇到的问题：

使用了mybatis，并且没有将xml写在resource下，而是选择放在mapper接口同一个包下，所以需要配置一下，以防文件没有被扫描加载

```xml
<!--因为我的项目结构xml文件和接口放一起了（在java目录下）,
编译的时候这个xml文件并没有被自动拉到target里面，必须要修改pom文件(配置mapper-locations都没用)，添加下列内容
或者改结构，将xml放到resource下，配置xml地址（mapper-locations: classpath:mapper/*.xml）即可-->
<resources>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
            <include>**/*.yml</include>
            <include>**/*.properties</include>
        </includes>
    </resource>
    <resource>
        <directory>src/main/resources</directory>
        <includes>
            <include>**/*.*</include>
        </includes>
    </resource>
</resources>
```

* 前端问题

> 1、后台包装的map进入到页面使用th:each遍历时候，iter“乱序”，导致归档处年份错乱，修改成list或者前端有别的方式解决，待询问前端大佬解决之
>
> 2、thymeleaf模板替换文档，需要在后台启动之后，执行控制器后进行替换，静态页面打开时不执行模板引擎
>
> 3、semanticUI使用了静态资源，资源放置于resource下，js加载时候指定资源地址即可，注意一些资源加载中需要考虑前置依赖，比如semantic需要先加载jquery.js
>
> 4、页面如blog包含了多个表内字段信息，单独将表信息返回给页面比较繁琐，故使用了一个包装类BLogHtml，将字段内容都包含进去，再返回给页面，得以解决。期间多表关联过程注意到，修改底层数据库结构后，在mapper.xml中查询字段内容需要对应进行跟进，不然会易出现查询出该字段为空的情况。
>
> 5、博客属性中，有一个整型property：1原创、2转载、3翻译 ，使用了th:switch解决，页面展示时候的选择

