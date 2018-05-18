<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>git学习</title>
</head>
<body>
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
            <li>cd 切换目录，ls查看文件，dir</li>
            <li>git init初始化仓库</li>
            <li>添加项目文件</li>
            <li>git status, 查看文件状态</li>
            <li>git add 文件名，或git add .，点号代表当前目录，相当于add</li>
            <li>git commit -m "初始化"，-m表示添加注释，相当于commit</li>
            <li>git rm read.txt，删除文件，相当于delete</li>
            <li>github或git.oschina.net上创建项目，拿到地址，如https://github.com/chahongjing/testPorject.git</li>
            <li>把代码推到服务器，git push https://github.com/chahongjing/testPorject.git master，如果不想写地址，可以给地址起别名</li>
            <li>地址起别名，git remote add 别名 https://github.com/chahongjing/testPorject.git</li>
            <li>git clone https://github.com/chahongjing/testPorject.git，相当于checkout</li>
            <li>git pull https://github.com/chahongjing/testPorject.git master，获取最新，相当于update</li>
            <li>git log，显示日志，相当于show logs，此命令显示信息较多，可使用git log --pretty=oneline显示简要信息</li>
            <li>git reset --hard HEAD^^，切换到其它版本，2个^^表示回到前两个版本，也可以使用git reset --hard HEAD 版本号或其中一部分（版本号是一串数字和字母）</li>
            <li>git branch，查看分支</li>
            <li>git branch 分支名，创建分支</li>
            <li>git checkout master，切换分支</li>
            <li>git merge 分支名，合并分支，配合上面的命令，表示把分支合并到主干，（先进入主干版本，然后再merge，表示把其它分支合并到主干）</li>
            <li>git remote -v，查看远程仓库地址</li>
            <li>git remote remove 远程库别名</li>
            <li>git remote rename 旧名称 新名称 个性远程库别名</li>
            <li>ssh-keygen -t rsa -C 310510906@qq.com，一直按enter，创建密钥，访问仓库时不必再输入用户名和密码</li>
        </ul>
    </div>
    <div>
        <p>其它介绍</p>
        <ul>
            <li>工作区，可add到暂存区</li>
            <li>暂存区，可commit到仓库</li>
            <li>仓库</li>
            <li>暂存区和仓库在.git隐藏文件夹下</li>
        </ul>
    </div>
</div>
<jsSection>

</jsSection>
</body>
</html>