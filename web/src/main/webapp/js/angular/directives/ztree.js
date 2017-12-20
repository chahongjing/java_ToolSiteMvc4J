/**
 * Created by jyzeng on 2017/12/20.
 *
 */

'use strict';
app.directive('ztree', ['commonService', function (commonSrv) {
    return {
        restrict: 'A',
        scope: {
            setting: '=',
            reload: '='
        },
        link: function ($scope, element, attrs) {
            // 默认配置
            var setting = {
                check: {
                    // 是否显示
                    enable: false,
                    // Y 属性定义 checkbox 被勾选后的情况； N 属性定义 checkbox 取消勾选后的情况
                    // "p" 表示操作会影响父级节点；"s" 表示操作会影响子级节点。
                    chkboxType: {"Y": "ps", "N": "ps"},
                    // radio, checkbox
                    chkStyle: 'radio',
                    // all所有数据为一组，level同一级的数据为一组
                    radioType: 'all'
                },
                data: {
                    simpleData: {enable: true}
                },
                edit: {
                    enable: false,
                    showRemoveBtn: false,
                    showRenameBtn: false,
                },
                view: {
                    showIcon: false
                },
                callback: {}
            };

            $scope.$watch('reload', function (sourcedata) {
                // 无数据则返回
                if (sourcedata === '' || sourcedata === null || sourcedata === undefined) {
                    return;
                }
                if (!$scope.setting) {
                    return;
                }

                // 绑定事件
                if ($scope.setting.event) {
                    if ($scope.setting.event.onBeforeClick) {
                        setting.callback.onBeforeClick = $scope.setting.event.onBeforeClick;
                    }
                    if ($scope.setting.event.onClick) {
                        setting.callback.onClick = $scope.setting.event.onClick;
                    }
                    if ($scope.setting.event.onDblClick) {
                        setting.callback.onDblClick = $scope.setting.event.onDblClick;
                    }
                    if ($scope.setting.event.onDrop) {
                        setting.callback.onDrop = $scope.setting.event.onDrop;
                    }
                    if ($scope.setting.event.onCheck) {
                        setting.callback.onCheck = $scope.setting.event.onCheck;
                    }
                    if ($scope.setting.event.addDiyDom) {
                        setting.view.addDiyDom = $scope.setting.event.addDiyDom;
                    }
                }
                if ($scope.setting.check) {
                    setting.check.enable = true;
                    $.extend(true, setting.check, $scope.setting.check);
                }
                if (!element.hasClass('ztree')) {
                    element.addClass('ztree')
                }
                if (!element.attr('id') || element.attr('id').indexOf('{{') > -1) {
                    element.attr('id', $scope.setting.id);
                }
                $.fn.zTree.init(element, setting, $scope.setting.data);

                var treeObj = commonSrv.treeUtils.getTree($scope.setting.id);
                // 按层级展开，未实现
                if (!$scope.setting.expandLevel) {
                    treeObj.expandAll(true);
                } else if ($scope.setting.expandLevel > 0) {
                    var nodes = commonSrv.treeUtils.getNodes($scope.setting.id);
                    var level = $scope.setting.expandLevel - 1;
                    nodes = nodes.filter(function (node) {
                        return node.level < level;
                    });
                    commonSrv.treeUtils.expandNodes($scope.setting.id, nodes, true);
                }
            });
        }
    }
}]);