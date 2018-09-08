<%--
  Created by IntelliJ IDEA.
  User: chahongjing
  Date: 2018/9/6
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mybatis学习</title>
    <style>

    </style>
</head>
<body>
<div>
    <p>
    <h2>mybatis缓存</h2>
    <ul>
        <li>一级缓存，存储在sqlsession中，相同的读取会直接使用缓存，不能跨sqlsession，默认开户</li>
        <li>二级缓存，针对mapper进行缓存，即不同的sqlsession,对于同一mapper共用缓存，默认关闭</li>
        <li>参数，如果指定的@Param,则用指定的名称，否则，使用索引作为名称</li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>