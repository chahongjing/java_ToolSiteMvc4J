<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>git学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>
            git
        </p>
        <ul>
            <li><a href="http://gitforwindows.org" target="_blank">下载git</a></li>
            <li>
                运行中输入git，选择git bash
            </li>
            <li>输入git config --global user.name chahongjing</li>
            <li>输入git config --global user.email 310510906@qq.com</li>
            <li>cd 切换目录</li>
            <li>git init初始化仓库</li>
            <li>添加项目文件</li>
            <li>git status, 查看文件状态</li>
            <li>git add 文件名，或git add .，点号代表当前目录</li>
            <li>git commit -m "初始化"，-m表示添加注释</li>
            <li>git rm read.txt，删除文件</li>
            <li>github或git.oschina.net上创建项目，拿到地址，如https://github.com/chahongjing/testPorject.git</li>
            <li>把代码推到服务器，git push https://github.com/chahongjing/testPorject.git master，如果不想写地址，可以给地址起别名</li>
            <li>地址起别名，git remote add 别名 https://github.com/chahongjing/testPorject.git</li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>