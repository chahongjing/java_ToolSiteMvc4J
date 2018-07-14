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
<div>

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
    <label class="radio_checkbox" v-for="item in radioList">
        <input type='radio' name="radio" :value="item.value" v-model="radioChecked"
               :disabled="radioDisabled" />
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <br/><br/>
    <p>checkbox</p>
    <label class="radio_checkbox" v-for="item in checkboxList">
        <input type='checkbox' name="checkbox" :value="item.value" v-model="checkboxChecked" :disabled="checkboxDisabled" />
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <br/><br/>
    <label class="togglecheckbox" v-for="item in checkboxList">
        <input type='checkbox' name="checkbox" :value="item.value" v-model="checkboxChecked" :disabled="checkboxDisabled" />
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <br/><br/>
</div>

<jsSection>
    <script>

        var data = {
            radioList: [],
            radioChecked: 2,
            radioDisabled: false,
            checkboxList: [],
            checkboxChecked: [2],
            checkboxDisabled: false
        };
        data.radioList.push({name: '电影', value: 1});
        data.radioList.push({name: '看书', value: 2});
        data.radioList.push({name: '打游戏', value: 3});
        data.checkboxList.push({name: '电影', value: 1});
        data.checkboxList.push({name: '看书', value: 2});
        data.checkboxList.push({name: '打游戏', value: 3});
        var vm = new Vue({
            el: '#myApp',
            data: data,
            methods: {
            },
            computed: {
            }
        });
    </script>
</jsSection>
</body>
</html>