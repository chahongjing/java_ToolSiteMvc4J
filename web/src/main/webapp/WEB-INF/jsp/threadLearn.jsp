<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习thread</title>
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
    <h2>多线程</h2>
    <ul>
        <li>继承Thread类, 它继承Runnable接口</li>
        <li>实现Runnable接口，实现run方法，没有返回值不能抛出受检查的异常</li>
        <li>实现Callable接口，实现call方法，他有返回值，可以抛出受检查的异常</li>
        <li>
            <ul>
                <li>1</li>
            </ul>
        </li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>