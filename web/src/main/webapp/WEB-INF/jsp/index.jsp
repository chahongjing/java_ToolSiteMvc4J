<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <style>
        .font {
            font-family: arial;
            font-weight: 500;
        }

        .next {
            display: inline-block;
            width: 100px;
            height: 46px;
            background-color: #D0DCED;
            margin: 0;
            padding: 0;
            border: 0;
            float: left;
            position: relative;
        }

        .next:before {
            position: absolute;
            top: 0px;
            right: -25px;
            z-index: 1;
            content: '';
            border: 23px solid black;
            border-right: none;
            border-color: transparent transparent transparent white;
        }

        .next:after {
            position: absolute;
            top: 0;
            right: -23px;
            z-index: 1;
            content: '';
            border: 23px solid white;
            border-right: none;
            border-color: transparent transparent transparent #D0DCED;
        }

        .pro:last-child {
            border: 1px solid black;
        }

        .pro span:last-child:before, .pro span:last-child:after {
            display: none;
        }

        /*a {*/
            /*display: inline-block;*/
            /*margin-right: 10px;*/
        /*}*/
    </style>
</head>
<body>
<div data-ng-controller="indexCtrl">
    <div class="col-sm-6" v-for="item in list">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="clearfix">
                    <a href="" class="pull-left thumb-md avatar b-3x m-r">
                    </a>
                    <div class="clear">
                        <div class="h3 m-t-xs m-b-xs">
                            {{item.name}}
                            <i class="fa fa-circle text-success pull-right text-xs m-t-sm"></i>
                        </div>
                        <small class="text-muted" v-text="item.subTitle"></small>
                    </div>
                </div>
            </div>
            <div class="list-group no-radius alt">
                <a class="list-group-item" href="javascript:void(0)"
                   @click="goPage(sub)" v-for="sub in item.children">
                    <i class="fa fa-comment fa-fw text-muted {{sub.iconClass}}"></i>
                    {{sub.name}}
                </a>
            </div>
        </div>
    </div>

    <h2>Hello World!</h2><span class="font"><% out.println("<div>abc</div>"); %></span>
    <br/>
    <br/>
    <a href="${ctx}/userinfo/loginpage.do">登录</a>
    <a href="javascript:void(0)" id="lnkLogout">注销</a>
    <br/>
    <br/>
    <a href="${ctx}/index/">跳转</a>
    <a href="${ctx}/userinfo/loginindex.do?code=zjy&name=曾&name=军" target="_blank">分页查询</a>
    <a href="javascript:void(0)" id="lnkTestPromise">测试promise</a>
    <a href="javascript:alert('请在单元测试中查看！')" id="lntAddSolrIndex">solr</a>
    <a href="${ctx}/test/test.do">测试@Value和加载部分页</a>
    <br/>
    <br/>
    <form method="post" action="${ctx}/test/testPostWithFile.do" enctype="multipart/form-data" target="_blank">
        <input type="text" name="name"/>
        <input type="hidden" name="age" value="28"/>
        <input type="text" name="test" value="测试部分页render:${username}"/>
        <input type="file" name="myfile" multiple="multiple"/>
        <button type="submit" name="tj" value="提交">提交</button>
        <button type="button" name="ajaxtj" value="提交">ajax提交</button>
    </form>
    <br/>
    <a href="${ctx}/cost/.do" target="_blank">财务管理</a>

    <br/>
    <br/>
    <div class="pro">
        <span class="next"></span>
        <span class="next"></span>
        <span class="next"></span>
        <span class="next"></span>
    </div>
    <br>
    <br>
    <button data-ng-click="showLoading()">显示loading</button>
    <br/>
</div>
<jsSection>
    <script>
        // app.controller('indexCtrl', ['$rootScope', '$scope', '$http', '$timeout', '$q',
        //     function ($rootScope, $scope, $http, $timeout, $q) {
        //         $scope.model = {};
        //
        //         $scope.showLoading = function () {
        //             $rootScope.showLoading();
        //             $timeout(function () {
        //                 $rootScope.hideLoading();
        //             }, 2000);
        //         }
        //     }]);

        var data = {};

        $(function () {
            $('#lnkTestPromise').one('click', function () {
                console.log('click');
                var p1 = $.ajax({
                    url: ctx + '/test/testP1.do'
                }).then(function (data) {
                    console.log('testP1');

                    return 'p1';
                });

                var p2 = $.ajax({
                    url: ctx + '/test/testP2.do'
                }).then(function (data) {
                    console.log('testP2');

                    return 'p2';
                });

                Promise.all([p1, p2]).then(function (result) {
                    console.log(result);
                });
            });

            $('button[name=ajaxtj]').click(function () {
                var formData = new FormData();
                var files = $('input[name=myfile]')[0].files;
                formData.append("name", "zjy");
                for (var i = 0; i < files.length; i++) {
                    formData.append("myfile", files[i]);
                }

                $.ajax({
                    //url: ctx + '/learn/fileupload.do',
                    //url: 'http://localhost:30000/restfulweb/rest/hello/testPostWithFile',
                    url: 'http://localhost:30001/api/rest/hello/testPostWithFile',
                    type: 'post',
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (resp) {

                    },
                    error:function(xhr, a, b) {
                        console.log(xhr);
                    }
                });
            });
        });

        var list = [];
        // region java
        var first = {name: 'java', children:[]};
        list.push(first);
        second = {name:  '下载', href: '/learn/download.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习servlet', href: '/learn/servletLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习jsp', href: '/learn/jspLearn.do?arr=1&arr=2&userName=曾军毅', iconClass:''};
        first.children.push(second);
        second = {name:  '学习el', href: '/learn/elLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习spring aop', href: '/learn/springAopLearn/123.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习spring Bean', href: '/learn/springBeanLearn/123.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习springMVC', href: '/learn/springMVCLearn/123.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习cookie', href: '/learn/cookieLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '学习session', href: '/learn/sessionLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  'java后台学习', href: '/learn/javaLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  'filter学习', href: '/learn/filterLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '事务学习', href: '/learn/springTransactionLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  'nio学习', href: '/learn/nioLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  'shiro学习', href: '/learn/shiroLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '反射学习', href: '/learn/reflectLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  '加密学习', href: '/learn/encryptLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region 服务器
        first = {name: '服务器', children:[]};
        list.push(first);
        second = {name:  '学习tomcat', href: '/learn/tomcatLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region maven
        first = {name: 'maven', children:[]};
        list.push(first);
        second = {name:  'maven学习', href: '/learn/mavenLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region echarts
        first = {name: 'echarts', children:[]};
        list.push(first);
        second = {name:  'echarts图表', href: '/echarts/index.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region angular
        first = {name: 'angular', children:[]};
        list.push(first);
        second = {name:  '测试angular', href: '/learn/testangular.do', iconClass:''};
        first.children.push(second);
        second = {name:  'angular demo', href: '/learn/angulardemo.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region vue
        first = {name: 'vue', children:[]};
        list.push(first);
        second = {name:  'vue demo', href: '/learn/vueDemo.do', iconClass:''};
        first.children.push(second);
        second = {name:  'vue learn', href: '/learn/vueLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region ueditor
        first = {name: 'ueditor', children:[]};
        list.push(first);
        second = {name:  'ueditor', href: '/learn/ueditorLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region js
        first = {name: 'js', children:[]};
        list.push(first);
        second = {name:  'js学习', href: '/learn/jsLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region 前端
        first = {name: '前端', children:[]};
        list.push(first);
        second = {name:  'css', href: '/learn/cssTest.do', iconClass:''};
        first.children.push(second);
        // endregion

        // region 其它
        first = {name: '其它', children:[]};
        list.push(first);
        second = {name:  'git学习', href: '/learn/gitLearn.do', iconClass:''};
        first.children.push(second);
        second = {name:  'oracle表转类', href: '/tool/tableToObject.do', iconClass:''};
        first.children.push(second);
        second = {name:  'sql生成器', href: '/tool/sqlGenerate.do', iconClass:''};
        first.children.push(second);
        second = {name:  '其它', href: '/learn/otherLearn.do', iconClass:''};
        first.children.push(second);
        // endregion

        vueData = {list: list};

        vueMethods = {
            goPage: goPage
        };

        vueMounted = function (){
        };

        function goPage(item) {
            window.location = ctx + item.href;
        }
    </script>
</jsSection>
<fmt:message key="username">
    <fmt:param value="a"/>
    <fmt:param value="b"/>
</fmt:message>
</body>
</html>
