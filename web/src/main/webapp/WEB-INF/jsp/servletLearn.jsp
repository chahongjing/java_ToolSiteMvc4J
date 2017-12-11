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

    .green {
        color: #008000;
    }

    .inline {
        display: inline-block;
    }
    p{text-indent: 10px;}
</style>
<title>学习servlet</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <p>1. 一般继承GenericServlet的子类HttpServlet, HttpServlet继承了GenericServlet,
        GenericServlet继承了servlet和servletconfig</p>
    <p>2. servlet生命周期函数:
        <ul>
            <li>constructor: 创建时，只调用一次</li>
            <li>init(ServletConfig)：初始化时，只调用一次</li>
            <li>service：可被调用<span class="red">多次</span>，用于处理用户请求</li>
            <li>destory：关闭时，只调用一次</li>
        </ul>
    </p>
    <p>3. load-on-startup，值越小越先启动；若小于0或<span class="red">没有填写值</span>，则在调用时才会加载并init</p>
    <p>4. 一个servlet可以有多个servlet-mapping进行映射，并且只能以<span class="red">*或/</span>开头</p>
    <p>5. servlet对象（<span class="red">一般是单例的</span>）：
        <ul>
            <li>ServletConfig:封装了servlet信息，可以通过this.getServletConfig()获取（GenericServlet或HttpServlet才能调用），并可以获取ServletContext对象，还包含在配置文件中添加的init-param（必须放在load-on-startup之前）信息.
                servletConfig.getInitParameter("paramName")
            </li>
            <li>ServletContext:当前应用。即application对象（<span class="red">servlet运行环境</span>）。
                <ul>
                    <li>可获取当前web应用的初始化参数（非servlet初始化参数）servletContext.getInitParameter("paramName")</li>
                    <li>获取服务器上文件路径：getRealPath("/note.txt");如: <span class="green">E:\ToolSiteMvc4J\note.txt</span></li>
                    <li>获取应用名称: getContextPath();如：<span class="green">/ToolSiteMvc4J</span></li>
                    <li>getResourceAsStream(String path);path是从web的根目录开始寻找
                        <ul>
                            <li>相比较:getClass().getClassLoader().getResourceAsString("");在classes中寻找</li>
                        </ul>
                    </li>
                    <li>spring mvc中从controller中获取servletconfig，可以让controller实现ServletConfigAware接口，其后实现setServletConfig方法即可，
                    普通servlet直接通过this.getServletConfig()即可获取</li>
                </ul>
            </li>
            <li>
                PageContext:页面上下文,可以获取到其它8个内置对象
            </li>
            <li>
                exception:当page指令中isErrorPage=true时才可以使用
            </li>
        </ul>
    </p>
    <p>6. ServletRequest
    <ul>
        <li>request.getParameter("key")来获取请求参数，<span class="red">get, post</span>请求参数均可获取</li>
        <li>request.getParameterValues("key")用来获取多个值，如checkbox多选，getParameter只能获取第一个值</li>
        <li>request.getParameterNames获取所有参数名</li>
        <li>request.getParameterMap()获取键值对(Map)，key为参数名，value为参数值，类型为String[]</li>
    </ul>
    </p>
    <p>7. HttpServletRequest,继承ServletRequest
    <ul>
        <li>request.getRequestURL()获取请求地址，不包括querystring, 如http://localhost:8080/ToolSiteMvc4J/userinfo/login</li>
        <li>request.getRequestURI()来获取请求uri,包括context，不包括参数, 如/ToolSiteMvc4J/userinfo/login</li>
        <li>request.getQueryString()来获取查询参数，如username=zjy&password=1</li>
        <li>request.getServletPath(),获取servlet请求方路径，不包括context, 参数之前, 如/userinfo/login</li>
        <li>request.getMethod(),获取请求方式，如POST</li>
        <li>request.getSession(),获取session,可以传参，当传true时表示，若存在，则直接返回，不存在，则创建，为false时，当存在则返回，不存在返回null</li>
        <li>RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + servletName);
            <br/>requestDispatcher.<span class="red">forward</span>(request, response);
            <br>
            ServletRequest和ServletContext均可获取requestDispatcher,但路径参数不一样，request可以是相对路径(third?name=jay)也可以是绝对路径（/third?name=jay,从项目根目录开始），而context必须是绝对路径（/third?name=jay）
            <br>
            只能转发到当前应用的页面
        </li>
        <li>HttpServletRequestWrapper,包装类，如重写getPrameter()方法</li>
    </ul>
    </p>
    <p>8. ServletResponse
    <ul>
        <li>response.getWriter()获取响应的PrintWriter, 调用该writer.print()将信息返回到浏览器页面上</li>
        <li>response.setContentType("application/msword"), 直接将页面当word下载了</li>
    </ul>
    </p>
    <p>9. HttpServletResponse，继承ServletResponse
    <ul>
        <li>response.sendRedirect(location)<span class="red">重定向</span>，地址/代表当前站点的根目录</li>
        <li>response.sendError()转到错误页面</li>
        <li>HttpServletRespnseWrapper,包装类，类似HttpServletRequestWrapper，可以做一些特殊操作</li>
    </ul>
    </p>
    <p>10. GenericServlet
    <ul>
        <li>注意重新定义的init()方法(此方法供子类覆盖（非生命周期方法），原始init(servletconfig)方法（生命周期方法）一般在子类中不覆盖)</li>
    </ul>
    </p>
    </p>
    <p>11. HttpServlet继承GenericServlet
    <ul>
        <li>有doGet, doPost, doDelete等一系列方法</li>
    </ul>
    </p>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>

<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>