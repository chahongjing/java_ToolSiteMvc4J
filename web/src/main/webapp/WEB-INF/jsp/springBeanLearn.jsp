<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
    * {
        font-family: "Microsoft YaHei UI";
        font-size: 14px;
    }

    .bold {
        font-weight: bold;
    }

    .red {
        color: #f00;
    }

    .inline {
        display: inline-block;
    }
</style>
<title>学习spring Bean</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>
            spring 提供两种容器实现,BeanFactory和ApplicationContext
        <ul>
            <li>BeanFactory是spring框架的基础设施，面向spring本身</li>
            <li>ApplicationContext面向开发者，几乎所有场合都用ApplicationContext，而不用BeanFactory，主要有三种实现类
                <ul>
                    <li>
                        ClassPathXmlApplicationContext
                    </li>
                    <li>
                        FileSystemXmlApplicationContext
                    </li>
                    <li>
                        WebApplicationContext，在web系统中常用
                    </li>
                </ul>
            </li>
        </ul>
        </p>
        <p>从配置文件中获取bean， ApplicationContext ctx = new ClassPathXmlApplicationContext("bean
            xml地址，如applicationContext.xml");<br>
            ctx.getBean("id");
        </p>
        <p>配置bean
        <ul>
            <li>xml和注解</li>
            <li>bean是通过反射的方式创建，因此必须要有一个无参的构造函数</li>
            <li>若xml配置中没有指定id,则以类名作为bean的名字，id可以指定多个，以逗号，分号或空格分隔</li>
            <li></li>
        </ul>
        </p>
    </div>
    <div>
        <p>spring 依赖注入</p>
        <ul>
            <li>构造函数注入, &lt;constructor-arg value=""&gt;&lt;/constructor-arg&gt;,添加多个，按参数顺序来配置，还可以添加index属性指定参数顺序<br>
            还可以添加type属性指定类型，以区别重载构造函数
            </li>
            <li>属性注入， &lt;property name="" value=""&gt;&lt;/property&gt;</li>
            <li>工厂注入，一般不用</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>