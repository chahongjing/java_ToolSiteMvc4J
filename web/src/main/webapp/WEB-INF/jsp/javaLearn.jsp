<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习jsp</title>
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
    <p>
    <h2>bean属性操作</h2>
    可使用beanutils工具类
    </p>

    <p>
    <h2>stream</h2>
    <ul>
        <li>repeat: List&lt;Integer&gt; collect = IntStream.range(1, 10).boxed().collect(Collectors.toList());</li>
        <li>sorted: Arrays.stream(s).sorted(Comparator.naturalOrder()).sorted(Comparator.reverseOrder());</li>
        <li>转map: Arrays.stream(s).collect(Collectors.toMap(item -> item, item ->item));</li>
        <li>
            <ul>
                <li>array --&gt; list: List&lt;String&gt; resultList = new ArrayList&lt;&gt;(Arrays.asList(array));</li>
                <li>list --&gt; array: String[] dest = list.toArray(new String[0]);</li>
                <li>set --&gt; list: List&lt;String&gt; list_1 = new ArrayList&lt;&gt;(set);</li>
                <li>list --&gt; set: Set&lt;String&gt; set = new HashSet&lt;&gt;(list);</li>
            </ul>
        </li>
    </ul>
    </p>

    <p>
    <h2>hashmap,hashtable</h2>
    <ul>
        <li>hashmap底层由数组和链表组成，键值均可为空, 默认容量为16*2,可指定大小，abstractmap</li>
        <li>hashtable键值都不能为空，每个方法都加了Synchronize,容量为11,2n+1，可指定大小，实际大小为2的幂次方,dictionary</li>
    </ul>
    </p>

    <p>
    <h2>apache commons</h2>
    <ul>
        <li>org.apache.commons.io.IOUtils</li>
        <li>org.apache.commons.io.FileUtils</li>
        <li>org.apache.commons.io.FilenameUtils</li>
        <li>org.springframework.util.StringUtils</li>
        <li>org.apache.commons.lang3.StringUtils</li>
        <li>org.apache.commons.lang3.StringEscapeUtils</li>
        <li>org.apache.commons.lang3.ArrayUtils</li>
        <li>org.apache.commons.collections.CollectionUtils</li>
        <li>org.apache.http.client.utils.URLEncodedUtils</li>
        <li>org.apache.commons.codec.digest.DigestUtils</li>
        <li>org.apache.commons.beanutils.PropertyUtils</li>
        <li>org.apache.commons.beanutils.BeanUtils</li>
        <li>org.apache.http.util.EntityUtils</li>
    </ul>
    </p>

    <p>
    <h2>java虚拟机</h2>
    <ul>
        <li>程序计数器，执行下一条指令的地址</li>
        <li>java栈，存储栈桢，如每调用一个方法，都会创建一个桢，存储局部变量，返回值等</li>
        <li>本地方法栈，和java栈类似，执行native method服务</li>
        <li>堆</li>
        <li>方法区：存储类信息，名称， 方法，字段等，及静态变量，常量，常量池</li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>