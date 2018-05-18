<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习反射</title>
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

        .green {
            color: #008000;
        }

        .inline {
            display: inline-block;
        }

        p {
            text-indent: 10px;
        }
    </style>
</head>
<body>
<div>
    <div>
        <p>反射相关的类</p>
        <ul>
            <li>Method，方法，getDeclaredMethods()</li>
            <li>Field，属性，getDeclaredFields()</li>
            <li>Constructor，构造器，getDeclaredConstructors()</li>
            <li>getAnnotations()</li>
            <li>newInstance(args)</li>
            <li>Method.getParameterTypes()</li>
            <li>Field.getType()</li>
        </ul>
    </div>
    <hr>
    <div>
        <p>相关方法</p>
        <ul>
            <li>Class.forName()</li>
            <li>instance.getClass()</li>
            <li>Person.class</li>
        </ul>
    </div>
</div>
<jsSection>

</jsSection>
</body>
</html>