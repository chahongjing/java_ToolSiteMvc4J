<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>tomcat学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div id="app">
        <p v-if="isShow == 1">isShow == 1</p>
        <p v-else="isShow == 1">isShow != 1</p>
        <p v-show="isShow == 1">vshow</p>
        <input v-model="isShow" />v-once:<span v-once="isShow">{{isShow}}</span><br>
        <button v-on:click="toggle()">点击切换可缩写为@click</button>
        <ul>
            <todo-item v-for="item in list" v-bind:title="item.name" v-bind:todo="item" v-bind:key="item.id">
            </todo-item>
        </ul>
        <div v-html="html">

        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script src="${ctx}/js/vue/vue.js"></script>
<script>
    Vue.component('todo-item', {
        // todo-item 组件现在接受一个
        // "prop"，类似于一个自定义特性。
        // 这个 prop 名为 todo。
        props: ['todo'],
        template: '<li>{{ todo.name }}</li>'
    });

    var data = {
        isShow: 1,
        list: [{id:1,name:'A'},{id:2,name:'B'},{id:3,name:'C'},{id:4,name:'D'}],
        html: '<p>这是<b style="color:red;">一段</b>文字</p>'
    };

    var vm = new Vue({
        el: '#app',
        data: data,
        methods: {
            toggle: toggle
        }
    });

    console.log(vm.list === data.list);

    function toggle() {
        this.isShow = (this.isShow) % 2 + 1;
    }
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>