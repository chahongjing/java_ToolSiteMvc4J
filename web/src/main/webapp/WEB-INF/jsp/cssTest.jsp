<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>css学习</title>
    <style>
    </style>
</head>
<body>
<div data-ng-controller="cssTestCtrl" data-ng-init="init()">

    <p>switch</p>
    <label class="switch">
        <input type="checkbox" class="hide"/>
        <div>
            <span>ON</span>
            <span></span>
            <span>OFF</span>
        </div>
    </label>
    <br><br>
    <p>radio</p>
    <label class="radio_checkbox" data-ng-repeat="item in model.radioList">
        <input type='radio' name="radio" data-ng-value="item.value" data-ng-model="model.radioChecked"
               data-ng-disabled="model.radioDisabled" />
        <i></i>
        <span data-ng-bind="item.name"></span>
    </label>
    <br/><br/>
    <p>checkbox</p>
    <label class="radio_checkbox" data-ng-repeat="item in model.checkboxList">
        <input type='checkbox' name="checkbox" data-ng-model="item.isChecked"
               data-ng-disabled="model.checkboxDisabled || item.isDisabled" />
        <i></i>
        <span data-ng-bind="item.name"></span>
    </label>
    <br/><br/>

    <label class="togglecheckbox" data-ng-repeat="item in model.checkboxList">
        <input type='checkbox' name="checkbox" data-ng-model="item.isChecked"
               data-ng-disabled="model.checkboxDisabled || item.isDisabled" />
        <i></i>
        <span data-ng-bind="item.name"></span>
    </label>
    <br/><br/>
</div>

<jsSection>
    <script>
        app.controller('cssTestCtrl', ['$rootScope', '$scope', '$http', '$timeout', '$q',
            function ($rootScope, $scope, $http, $timeout, $q) {
                $scope.model = {};

                $scope.init = function () {
                    var list = [];
                    list.push({name: '电影', value: 1});
                    list.push({name: '看书', value: 2});
                    list.push({name: '打游戏', value: 3});
                    $scope.model.radioChecked = 2;
                    $scope.model.radioDisabled = false;
                    $scope.model.radioList = list;

                    list = [];
                    list.push({name: '电影', value: 1, isChecked: false});
                    list.push({name: '看书', value: 2, isChecked: true, isDisabled: false});
                    list.push({name: '打游戏', value: 3, isChecked: false, isDisabled: false});
                    $scope.model.checkboxList = list;
                    $scope.model.checkboxDisabled = false;
                }
            }]);
    </script>
</jsSection>
</body>
</html>