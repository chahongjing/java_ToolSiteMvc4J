<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试angular</title>
    <link rel="stylesheet" href="${ctx}/Controls/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
</head>
<body>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <ul>
        <li data-ng-repeat="item in model.list" data-ng-bind="item.name" ng-repeat-finish="model.myAfterRender(param)">
        </li>
    </ul>

    <div data-ng-include="includeUrl" data-ng-controller="includePageCtrl" onload="init()"></div>
    <span data-ng-bind-template="{{param}}[abc]{{paramb}}"></span>
    <span data-ng-bind="paramb"></span>
    <div test-url>
        <span data-ng-bind="model.name"></span>
    </div>

    <hr>
    <div id="testTemplate">

    </div>

    <hr>
    <ul ztree setting="treeSetting" reload="treeSetting.reload">
    </ul>
</div>

<script type="text/ng-template" id="my.html">
    <h4>lovestory</h4>
    <p>这是script标签获取模板文件的方式</p>
    <a href="{{url}}">标签启用templateCache方式</a>
</script>
<jsSection>
    <script src="${ctx}/js/angular/angular.js"></script>
    <script src="${ctx}/js/angular/angular_main.js"></script>
    <script src="${ctx}/js/angular/directives/ng_repeat_finish.js"></script>
    <script src="${ctx}/js/angular/commonService.js"></script>
    <script src="${ctx}/js/angular/directives/test_templateurl.js"></script>
    <script src="${ctx}/js/angular/directives/testDir.js"></script>
    <script src="${ctx}/js/angular/templates/includePageCtrl.js"></script>
    <script src="${ctx}/js/angular/directives/ztree.js"></script>
    <script src="${ctx}/Controls/zTree/js/jquery.ztree.all-3.5.js"></script>
    <script>
        app.controller('testCtrl', ['$scope', '$http', '$timeout', '$compile', '$templateCache', 'commonService',
            function ($scope, $http, $timeout, $compile, $templateCache, commonSrv) {
                $scope.model = {};
                $scope.param = '123';
                $scope.paramb = '<div>abc</div>';

                $scope.init = function () {
                    $scope.model.list = [];
                    $scope.model.list.push({id: 1, name: '第一条数据'});
                    $scope.model.list.push({id: 2, name: '第二条数据'});
                    $scope.model.list.push({id: 3, name: '第三条数据'});
                    $scope.model.name = "父级";
//                $scope.model.testGet();
//                $scope.model.testPost();

                    $scope.includeUrl = '${ctx}/js/angular/templates/includePage.html';

                    $timeout(function () {
                        $scope.$broadcast('callChildFuncId', {a: '参数'});
                    });

                    var template = $templateCache.get('my.html');
                    $scope.url = 'http://www.baidu.com';
                    angular.element('#testTemplate').append($compile(template)($scope));

                    initTree();
                };

                $scope.model.myAfterRender = function (param) {
                    console.log('myAfterRender:' + param);
                };

                $scope.model.testGet = function () {
                    $http.get('${ctx}/test/testajax.do', {params: {a: 1}}).success(function (resp) {
                        console.log('success');
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                };

                $scope.model.testPost = function () {
                    $http.post('${ctx}/test/testajax.do', {a: 11}, {params: {b: 2}}).success(function (resp) {
                        console.log('success');
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                };

                $scope.parentFunc = function () {
                    console.log('这是父页面方法,通过$parent调用父方法!');
                }

                var m = $scope.$on('callParentFuncId', function ($event, param) {
                    console.log('这是父页面方法,通过emit+on调用父方法!' + JSON.stringify(param));
                });

                function initTree() {
                    // 准备数据
                    var list = [];
                    list.push({id: 1, name: '第一层', pId: null});
                    list.push({id: 2, name: '第二层A', pId: 1});
                    list.push({id: 3, name: '第二层B', pId: 1});
                    list.push({id: 4, name: '第三层C', pId: 2});
                    list.push({id: 5, name: '第三层D', pId: 2});

                    // 树配置
                    $scope.treeSetting = {
                        // id 和 数据源
                        id: 'abc', data: list,
                        // 默认不修改
                        reload: 0, // expandLevel: 3,
                        // 根据实际情况添加
                        check: {
                            chkStyle: 'checkbox'
                        },
                        event: {
                            onClick: treeClick,
                            addDiyDom: treeAddDiyDom
                        }
                    };
                }

                // 节点点击事件
                function treeClick(event, treeId, treeNode) {
                    treeNode.isHide = !treeNode.isHide;
                    commonSrv.treeUtils.updateDiyDom(treeId, treeNode);
                }

                // 树节点自定义dom
                function treeAddDiyDom(treeId, treeNode) {
                    var nodeA = $("#" + treeNode.tId + "_a");
                    var diyHtml = [];
                    var diyDomClass = 'diydomclass';
                    if (treeNode.isHide) {
                        diyHtml.push('<span class="' + diyDomClass + ' tipicon"><i class="fa fa-eye-slash">a</i></span>');
                    } else {
                        diyHtml.push('<span class="' + diyDomClass + ' tipicon"><i class="fa fa-eye">b</i></span>');
                    }
                    if (diyHtml.length > 0) {
                        nodeA.parent().find('> .' + diyDomClass).remove();
                        nodeA.after(diyHtml.join(''));
                    }
                }
            }]);
    </script>
</jsSection>
</body>
</html>