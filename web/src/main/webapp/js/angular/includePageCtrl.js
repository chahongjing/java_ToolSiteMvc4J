/**
 * Created by chahongjing on 2017/7/12.
 */
app.controller('includePageCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.model = {};

        $scope.init = function () {
            $scope.$parent.parentFunc();
            var m = $scope.$emit('callParentFuncId', {a: '参数'});
        };

        $scope.$on('callChildFuncId', function($event, param) {
            console.log('这是子页面方法！通过broadcast+on调用子方法!' + JSON.stringify(param));
        });
    }]);