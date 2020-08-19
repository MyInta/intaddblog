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
>
> 6、评论区，使用了包装类型包装了评论和其子评论，但是第三四级回复评论如何操作，目前不知道怎么解决，待查看其他人优秀的见解来进行修改。所以预测这边肯定存在一个bug，那就是多级回复信息会被吞掉
>
> 7、thyemeleaf模板报错：Caused by: org.springframework.expression.spel.SpelEvaluationException: EL1007E: Property or field 'blogHtmlCommentParts' cannot be found on null，原因在于字段引用时发现为空，解决方法：第一种自欺欺人法，在yml配置文件添加
>
> ```yml
> mybatis:
>   configuration:
>     call-setters-on-nulls: true #设置返回字段不为空，前端不报错
> ```
>
> 第二种方法，我的项目中实施的方式：修改前端页面，进行th:if判断字段不为空，包裹评论区
>
> ```html
> <th:block th:if="${blogHtml != null}">
> <div class="comment" th:each="blogHtmlCommentPart:${blogHtml.blogHtmlCommentParts}">...
> ```
>
> 8、使用themleaf替换尾部【最新博客】部分片段，使用了js提前加载控制器的请求地址以实现，实现之后才意识到一个新的问题，最新博客标题过长，页面效果不好看，解决办法，修改标题长度限制或者用省略号替代一定长度，该前端功能如何实现？后端截取一定长度容易，但是会增加不必要的代码数量
>
> 9、[TODO] 从一个页面按搜索键查询模糊字段到搜索页面，底部最新博客栏为空白,此处问题一直没找明白原因，其他页面下，显示正常，搜索页面下不正常。
>
> 10、使用的PageHelper插件进行分页，规定的一页3条记录，却还是整个都显示出来。 原因可能是pageHelper只对紧跟之后的第一条查询语句进行分页导致。但是排查后发现并非如此，而是版本依赖问题，我使用的是springboot而非springmvc，依赖的pagehelper版本应该用
>
> ```xml
> <dependency>
>     <groupId>com.github.pagehelper</groupId>
>     <artifactId>pagehelper-spring-boot-starter</artifactId>
>     <version>1.2.12</version>
> </dependency>
> ```
>
> 之前我用了
>
> <!--pagehelper分页工具-->
> <dependency>
>     <groupId>com.github.pagehelper</groupId>
>     <artifactId>pagehelper</artifactId>
>     <version>5.1.10</version>
> </dependency>
>
> 需要添加一个拦截器，来使得pagehelper的拦截生效，从而修改sql语句
>
> 11、搜索页面search分页之后，上下页进行跳转失败，原因在于控制器一开始限制请求方式为POST，而我前端页面上下页跳转使用的链接拼接方式，放开GET请求，并把query查询字段以及页码pageNum传递即能正常跳转了。



分页用法

> 依赖
>
> ```xml
> <!--pagehelper分页工具-->
> <dependency>
>     <groupId>com.github.pagehelper</groupId>
>     <artifactId>pagehelper-spring-boot-starter</artifactId>
>     <version>1.2.12</version>
> </dependency>
> ```



> 配置文件
>
> ```yml
> pagehelper:
>   helperDialect: mysql
>   reasonable: true
>   supportMethodsArguments: true
>   params: count=countSql
> ```



> 业务层
>
> ```java
> public PageInfo findPage(int page,int pageSize){
>   PageHelper.startPage(page,pageSize);
>   List<Company> List=companyDao.selectAll();
>   PageInfo pageInfo = new PageInfo(list);
>   return pageInfo;
>  }
> ```



> 插件实体类 内部属性一览
>
> ```java
> public class PageInfo<T> implements Serializable {
> private static final long serialVersionUID = 1L;
> //当前页
> private int pageNum;
> //每页的数量
> private int pageSize;
> //当前页的数量
> private int size;
> //由于startRow 和endRow 不常用，这里说个具体的用法
> //可以在页面中"显示startRow 到endRow 共size 条数据"
> //当前页面第一个元素在数据库中的行号
> private int startRow;
> //当前页面最后一个元素在数据库中的行号
> private int endRow;
> //总记录数
> private long total;
> //总页数
> private int pages;
> //结果集
> private List<T> list;
> //前一页
> private int prePage;
> //下一页
> private int nextPage;
> //是否为第一页
> private boolean isFirstPage = false;
> //是否为最后一页
> private boolean isLastPage = false;
> //是否有前一页
> private boolean hasPreviousPage = false;
> //是否有下一页
> private boolean hasNextPage = false;
> //导航页码数
> private int navigatePages;
> //所有导航页号
> private int[] navigatepageNums;
> //导航条上的第一页
> private int navigateFirstPage;
> //导航条上的最后一页
> private int navigateLastPage;
> }
> ```
>
> 

