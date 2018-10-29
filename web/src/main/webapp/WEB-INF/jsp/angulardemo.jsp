<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试angular</title>
    <link rel="stylesheet" href="${ctx}/Controls/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <style>.red{color:#f00;}</style>
</head>
<body>
<div data-ng-controller="testCtrl" data-ng-init="init()">
    <div>
        <p><b>input</b></p>
        <input type="text" data-ng-model="model.inputValue"/>
        <input type="text" data-ng-model="model.inputValue" data-ng-disabled="true" />
        <br/><br/>
        <p><b>radio</b></p>
        <label class="radio_checkbox" data-ng-repeat="item in model.repeatList"
               ng-repeat-finish="myAfterRender(param)">
            <input type='radio' name="radio" data-ng-value="item.value" data-ng-model="model.radioChecked"
                   data-ng-disabled="model.radioDisabled"/>
            <i></i>
            <span data-ng-bind="item.name"></span>
        </label>
        <span data-ng-bind="model.radioChecked"></span>
        <br/><br/>
        <p><b>checkbox</b></p>
		<label class="radio_checkbox">
            <input type='checkbox' name="checkbox" data-ng-model="model.allCheck"
			    data-ng-click='checkAll()' />
            <i></i>
            <span>全选</span>
        </label>
        <label class="radio_checkbox" data-ng-repeat="item in model.repeatList">
            <input type='checkbox' name="checkbox" data-ng-model="item.isChecked"
                   data-ng-disabled="model.checkboxDisabled || item.isDisabled" data-ng-click='checkItem(item)' />
            <i></i>
            <span data-ng-bind="item.name + item.isChecked"></span>
        </label>
        <br/><br/>
        <p><b>select</b></p>
        <select data-ng-options="item.value as item.name for item in model.repeatList"
                data-ng-model="model.selectedValue">
            <option value="" disabled>--全部--</option>
        </select>
        <span data-ng-bind="model.selectedValue"></span>
        <br/><br/>
        <p><b>event</b></p>
        <input type="button" value="测试事件" data-ng-click="toggle()" />
        <span data-ng-show="model.showSpan">show显示和隐藏, data-ng-hide, data-ng-switch</span>
        <span data-ng-if="!model.showSpan">if显示和隐藏</span>
        <br/><br/>
        <p><b>span</b></p>
        <p data-ng-bind="model.inputValue" data-ng-class="{'red': model.hasClass}"></p>
        <span data-ng-bind-template="{{model.inputValue}}[abc]{{model.inputValue}}"></span>
        <input type="file" id="myfile" >
        <button data-ng-click="testPost()" value="测试">测试</button>
    </div>
    <br><br><br>

    <div data-ng-include="includeUrl" data-ng-controller="includePageCtrl" onload="init()"></div>
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
                    $scope.model.inputValue = '测试数据';
                    $scope.model.hasClass = true;
                    var list = [];
                    list.push({name: '电影', value: 1});
                    list.push({name: '看书', value: 2});
                    list.push({name: '打游戏', value: 3});
                    $scope.model.radioChecked = 2;
                    $scope.model.radioDisabled = false;
                    $scope.model.repeatList = list;
                    $scope.model.checkboxDisabled = false;
                    $scope.model.selectedValue = 3;



                    $scope.model.name = "父级";
//                $scope.testGet();
//                $scope.testPost();
                    $scope.includeUrl = '${ctx}/js/angular/templates/includePage.html';
                    $timeout(function () {
                        $scope.$broadcast('callChildFuncId', {a: '参数'});
                    });
                    var template = $templateCache.get('my.html');
                    $scope.url = 'http://www.baidu.com';
                    angular.element('#testTemplate').append($compile(template)($scope));
                    initTree();
                };

                $scope.myAfterRender = function (param) {
                    console.log('myAfterRender:' + param);
                };

                $scope.toggle = function() {
                    $scope.model.showSpan = !$scope.model.showSpan;
                    $scope.model.hasClass = !$scope.model.hasClass;
                }

                $scope.testGet = function () {
                    $http.get('${ctx}/test/testajax', {params: {a: 1}}).success(function (resp) {
                        console.log('success');
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                };

                $scope.testPost = function () {
                    var formData = new FormData();
                    formData.append("a", 1);
                    formData.append("d", new Date());
                    formData.append('myfile', $('#myfile')[0].files[0]);
                    commonSrv.postFormData('/learn/fileupload1', formData);return;
                    $http.post('${ctx}/learn/testajax', {a: 11}, {params: {b: 2}}).success(function (resp) {
                        console.log('success');
                    }).error(function (data, status, headers, config) {
                        console.log('error');
                    });
                };

                $scope.parentFunc = function () {
                    console.log('这是父页面方法,通过$parent调用父方法!');
                }

                $scope.blobDownload = function() {
                    // $http.post(url, param, {responseType: "arraybuffer"}); // {responseType: "blob"}
                    biJiSrv.export($scope.model.searchParm).success(function (resp, status, responseHeaders, xhr) {
                        Utility.blobDownload([resp], responseHeaders());
                    });
                }
				
				$scope.checkItem = function(item) {
				    refreshAllCheck();
				}
				
				$scope.checkAll = function() {
				    for(var i = 0; i < $scope.model.repeatList.length; i++) {
					    $scope.model.repeatList[i].isChecked = $scope.model.allCheck
					}
				}
				
				function refreshAllCheck() {
				    var isAllCheck = true;
					if(!$scope.model.repeatList || $scope.model.repeatList.length == 0) {
					    $scope.model.allCheck = false;
						return;
					}
				    for(var i = 0; i < $scope.model.repeatList.length; i++) {
					    if(!$scope.model.repeatList[i].isChecked) {
						    isAllCheck = false;
							break;
						}
					}
					$scope.model.allCheck = isAllCheck;
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
