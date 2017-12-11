<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
    *{font-family: "Microsoft YaHei UI";font-size:14px;}
    .bold{
        font-weight:bold;}
    .red{color:#f00;}
    .inline{display:inline-block;}
</style>
<title>学习springMVC</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
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
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>