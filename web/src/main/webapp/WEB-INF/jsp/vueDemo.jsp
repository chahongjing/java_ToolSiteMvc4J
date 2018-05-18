<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>vue Demo</title>
    <style>
        .bold {
            font-weight: bold;
        }

        .red {
            color: red;
        }
    </style>
</head>
<body>
<div>
    <div id="app">
        <p v-if="isShow == 1">isShow == 1</p>
        <p v-else="isShow == 1">isShow != 1</p><!-- v-else-if -->
        <p v-show="isShow == 1">vshow</p>
        <input v-model="isShow"/>v-once:<span v-once="isShow">{{isShow}}</span><br>
        <button v-on:click="toggle($event)">点击切换可缩写为@click</button>
        <ul>
            <todo-item v-for="item in list" v-bind:title="item.name" v-bind:todo="item" v-bind:key="item.id">
            </todo-item>
        </ul>

        <ul>
            <li v-for="item in list" v-text="item.name"></li>
        </ul>

        <div :class="getClass()" :style="getStyle()">abc</div>
        <div v-html="html">

        </div>

        <div>{{html | myFilter}}</div>
        <div v-text="html"></div>

        <div id='example-3'>
            <input type="checkbox" id="jack" value="Jack" v-model="checkedNames">
            <label for="jack">Jack</label>
            <input type="checkbox" id="john" value="John" v-model="checkedNames">
            <label for="john">John</label>
            <input type="checkbox" id="mike" value="Mike" v-model="checkedNames">
            <label for="mike">Mike</label>
            <br>
            <span>Checked names: {{ checkedNames }}</span>
        </div>
        <div id="example-4">
            <input type="radio" id="one" value="One" v-model="picked">
            <label for="one">One</label>
            <br>
            <input type="radio" id="two" value="Two" v-model="picked">
            <label for="two">Two</label>
            <br>
            <span>Picked: {{ picked }}</span>
        </div>
        <select v-model="selected">
            <option v-for="option in options" v-bind:value="option.value" v-text="option.text">
            </option>
        </select>
        <span>Selected: {{ selected }}</span>

        <hello hello></hello>
    </div>
</div>
<jsSection>
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
            list: [{id: 1, name: 'A'}, {id: 2, name: 'B'}, {id: 3, name: 'C'}, {id: 4, name: 'D'}],
            html: '<p>这是<b style="color:red;">一段</b>文字</p>',
            checkedNames: [],
            picked: '',
            selected: '',
            options: [
                {text: 'One', value: 'A'},
                {text: 'Two', value: 'B'},
                {text: 'Three', value: 'C'}
            ]
        };

        var vm = new Vue({
            el: '#app',
            data: data,
            methods: {
                toggle: toggle,
                getClass: function () {
                    return {
                        bold: true,
                        red: true
                    }
                },
                getStyle: function () {
                    return {
                        backgroundColor: '#eee'
                    }
                }
            },
            computed: {
                // 计算属性的 getter
                reversedMessage: function () {
                    // `this` 指向 vm 实例
                    return this.message.split('').reverse().join('')
                },
                fullName: {
                    // getter
                    get: function () {
                        return this.firstName + ' ' + this.lastName
                    },
                    // setter
                    set: function (newValue) {
                        var names = newValue.split(' ')
                        this.firstName = names[0]
                        this.lastName = names[names.length - 1]
                    }
                }
            },
            filters: {
                myFilter: function (v) {
                    return (v || '') + 'filterInfo';
                }
            }
            <%--components: {--%>
            <%--'hello': () => import('${ctx}/js/vue/component/hello.vue')--%>
            <%--}--%>
        });

        console.log(vm.list === data.list);

        function toggle($event) {
            this.isShow = (this.isShow) % 2 + 1;
        }
    </script>
</jsSection>
</body>
</html>