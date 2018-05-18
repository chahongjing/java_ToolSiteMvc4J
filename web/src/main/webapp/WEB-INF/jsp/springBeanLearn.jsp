<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习spring Bean</title>
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
</head>
<body>
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
        </p>
        <ul>
            <li>xml和注解</li>
            <li>bean是通过反射的方式创建，因此必须要有一个无参的构造函数</li>
            <li>若xml配置中没有指定id,则以类名作为bean的名字，id可以指定多个，以逗号，分号或空格分隔</li>
            <li>在bean的xml配置中可以使用autowire="byName"表示实体属性通过名称自动装配，byType则是根据类型进行自动装配，但同一类型的bean只能有一个</li>
        </ul>
        <p>bean之间的关系</p>
        <ul>
            <li>继承，可以使用parent来继承同类型(或父类?)的bean，若只想设置为模板，则可设置abstract属性为true，
                并不是实例中的所有属性都会继承，如abstract，autowired；若一个bean没有指定类型，则必须为abstract
            </li>
            <li>依赖，使用depends-on属性来创建依赖，属性值为bean的id，依赖多个bean，值用逗号或空格隔开</li>
        </ul>
        <p>bean作用域， 可以用scope属性指定</p>
        <ul>
            <li>单例(singleton)，默认都是单例的，容器初始化时创建bean实例，整个容器生命周期内都只会创建一个实例</li>
            <li>请求(request)，不同的请求之间的bean不同，同一请求之间是共享的bean</li>
            <li>会话(session)，不同的会话之间的bean不同，同一会话之间是共享的bean</li>
            <li>原型(prototype)，容器初始化时不创建bean，每次请求时才会创建新的bean</li>
        </ul>
        <p>使用外部属性文件</p>
        <ul>
            <li>属性值在properties中，在bean中使用${driverClass}来配置</li>
        </ul>
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
    <div>
        <p>
            bean后置处理事件，在bean初始化前后进行相关操作，如检查属性是否正确要实现BeanPostProcessor接口
        </p>
        <p>创建bean的工厂方法，在bean节点上指定factory-method</p>
    </div>
    <div>
        <p>创建bean方式</p>
        <ul>
            <li>静态工厂方法。要创建的bean, class指定为工厂类型，通过factory-method属性指定工厂方法，通过constructor-arg子节点指定参数值</li>
            <li>实例工厂方法。在创建的bean上，指定factory-bean为工厂实例bean(即要先配置工厂bean)，factory-method指定工厂方法，constructor-arg子节点传递参数</li>
            <li>通过spring框架的FactoryBean泛型接口的实现类来创建bean</li>
        </ul>
    </div>
    <div>
        <p>注解配置bean，需要在配置文件中配置&lt;context:component-scan&gt;&lt;/context:component-scan&gt;</p>
        <ul>
            <li>
                @Component，基本注解，标识b一个受spring管理的组件
            </li>
            <li>
                @Respository，持久层组件
            </li>
            <li>
                @Service，服务层（业务层）的组件
            </li>
            <li>
                @Controller，表现层的组件
            </li>
        </ul>
        <hr>
        <span>context:component-scan属性</span>
        <ul>
            <li>base-package指定要扫描的包，包括子包</li>
            <li>user-default-filters=false；默认为true, 要设置为不使用默认过滤，context:include-filter和context:exclude-filter才会生效</li>
        </ul>
    </div>
    <div>
        <p>context:component-scan子节点</p>
        <ul>
            <li>
                resource-pattern；resuorce-pattern="repositor/*.class"，指定扫描的资源
            </li>
            <li>
                context:include-filter；包含类型
            </li>
            <li>
                context:exclude-filter；排除类型
            </li>
            <li>
                <p>context:include-filter和context:exclude-filter可指定包含或排除方式，可以设置多个</p>
                <ul>
                    <li>type=annotation expression=xxx.xxx.注解名</li>
                    <li>assignable；指定class或interface</li>
                    <li>aspectj；aspectJ；语法</li>
                    <li>regex；正则表达式</li>
                    <li>custom</li>
                </ul>
            </li>
        </ul>
    </div>
    <div>
        <p>bean自动装配，可以使用@Qualifier("beanName")指定bean名称</p>
        <ul>
            <li>@Autowired，默认按类型进行装配，可以自动装配类型兼容的属性，表示容器中必须要有兼容的bean实例，否则会报错，如果不想报错可以使用@Autowired(required=false)，
                如果属性为数组，会把所有匹配的bean进行装配，如果属性为集合，会把所有兼容bean进行装配，如果为map，键为String，会把类型兼容的bean进行填充，
                键为bean的名称
            </li>
            <li>
                @Resource，和Autowired类似，但它默认按名称进行装配，需要Bean名称
            </li>
            <li>
                @Inject，和Autowired类似，但没有required属性
            </li>
        </ul>
    </div>
</div>
<jsSection>

</jsSection>
</body>
</html>