<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习springMVC</title>
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
    spring 2.5之前用@controller，之后用注解：
    <ul>
        <li>@RequestParam</li>
        <li>@ModelAttribute</li>
        <li>@SessionAttributes</li>
        <li>@InitBinder</li>
    </ul>
    <br>
    spring3.0引入restful风格，如:
    <ul>
        <li>@PathVariable(rest @PathParam)</li>
        <li>@CookieValue</li>
        <li>@RequestHandler</li>
        <li>@RequestBody</li>
        <li>@ResponseBody</li>
        <li>@ResponseStatus</li>
        <li>@RequestPart</li>
        <li>@ExceptionHandler</li>
    </ul>
    <br>
    还有@NumberFormat 和 @DateTimeFormat支持，还引入mvc XML的命名空间用于支持mvc配置，如&lt;mvc:annotation-driven&gt;

</div>
<jsSection>

</jsSection>
</body>
</html>