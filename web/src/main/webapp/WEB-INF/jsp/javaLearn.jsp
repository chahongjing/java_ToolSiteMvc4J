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

    <p>include指令和标签区别
    <ul>
        <li>
            &lt;%@ include file="list.jsp"%&gt;:静态包含，只会生成一个servlet
        </li>
        <li>
            &lt;/jsp:include&gt;:动态包含，会生成两个servlet
        </li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>