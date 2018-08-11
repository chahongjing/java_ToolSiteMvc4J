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
        <li><ul>
            <li>array --&gt; list: List&lt;String&gt; resultList = new ArrayList&lt;&gt;(Arrays.asList(array));</li>
            <li>list --&gt; array: String[] dest = list.toArray(new String[0]);</li>
            <li>set --&gt; list: List&lt;String&gt; list_1 = new ArrayList&lt;&gt;(set);</li>
            <li>list --&gt; set: Set&lt;String&gt; set = new HashSet&lt;&gt;(list);</li>
        </ul></li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>