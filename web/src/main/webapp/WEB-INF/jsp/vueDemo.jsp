<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
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
    <div id="abc">
        <p v-if="isShow == 1">isShow == 1</p>
        <p v-else="isShow == 1">isShow != 1</p><!-- v-else-if -->
        <p v-show="isShow == 1">vshow</p>
        <input v-model="isShow"/>v-once:<span v-once="isShow" v-text="isShow"></span><br>
        <button v-on:v-on:click="toggle($event)">点击切换可缩写为@click</button>
        <ul>
            <todo-item v-for="item in list" v-bind:key="item.id" v-bind:todo="item">
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
        <pagination v-bind:pagerInfo="pagerInfo" v-bind:click="testMe"></pagination>
    </div>
</div>
<script type="text/template" id="tpl">
    <li v-text="todo.name"></li>
</script>
<script type="text/template" id="pagination">
    <div class="pager">
        <ul class="pagination">
            <li class="page-item" v-for="item in list" @click="goPage(item)" :class="getClass(item)">
                <a class="page-link" href="#" v-text="item.name"></a>
            </li>
        </ul>
    </div>
</script>
<jsSection>
    <script>
        Vue.component('pagination', {
            props: ['pagerInfo', 'click'],
            data: function () {
                return {list: []};
            },
            template: '#pagination',
            created: function () {
                var pagerInfo = this.$attrs['pagerinfo'];
                var list = this.handData(pagerInfo);
                this.list = list;
            },
            methods: {
                handData: function (pagerInfo) {
                    var list = [], start = pagerInfo.pageNum - 2, end = pagerInfo.pageNum + 2,
                        pre = pagerInfo.pageNum - 1,
                        next = pagerInfo.pageNum + 1;
                    if (start < 1) start = 1;
                    if (end > pagerInfo.pages) end = pagerInfo.pages;
                    if (pre < 1) pre = 1;
                    if (next > pagerInfo.pages) next = pagerInfo.pages;
                    var obj;
                    obj = {name: '上一页', value: pre, isDisabled: false};
                    if (obj.value == pagerInfo.pageNum) {
                        obj.isDisabled = true;
                    }
                    list.push(obj);
                    for (var i = start; i < end + 1; i++) {
                        obj = {name: i, value: i, isDisabled: false};
                        if (pagerInfo.pageNum == i) {
                            obj.isCurrent = true;
                            obj.isDisabled = true;
                        }
                        list.push(obj);
                    }
                    obj = {name: '下一页', value: next, isDisabled: false};
                    if (obj.value == pagerInfo.pageNum) {
                        obj.isDisabled = true;
                    }
                    list.push(obj);
                    return list;
                },
                getClass: function (pageItem) {
                    return {'isCurrent': pageItem.isCurrent, 'disabled': pageItem.isDisabled || pageItem.isCurrent}
                },
                goPage: function (page) {
                    if (page.isDisabled) return;
                    this.click && this.click(page);
                }
            }
        });

        Vue.component('todo-item', {
            // todo-item 组件现在接受一个
            // "prop"，类似于一个自定义特性。
            // 这个 prop 名为 todo。
            props: ['todo'],
            template: '#tpl'
        });

        var data = {
            isShow: 1,
            list: [{id: 1, name: 'A'}, {id: 2, name: 'B'}, {id: 3, name: 'C'}, {id: 4, name: 'D'}],
            html: '<p>这是<b style="color:red;">一段</b>文字</p>',
            checkedNames: ['Mike', 'John'],
            picked: '',
            selected: '',
            options: [
                {text: 'One', value: 'A'},
                {text: 'Two', value: 'B'},
                {text: 'Three', value: 'C'}
            ],
            pagerInfo: {pageNum: 5, pages: 10}
        };


//        var vm1 = new Vue({
//            el: '#twoV',
//            data: {message: 'mymes'},
//            methods: {
//                toggle: function() {
//                    console.log('dsfasfd');
//                }
//            }
//        });

        var vm = new Vue({
            el: '#myApp',
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
                },
                testMe: function (a) {
                    console.log(a.value);
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
            <%--}--%>,
        });

        console.log(vm.list === data.list);

        function toggle($event) {
            this.isShow = (this.isShow) % 2 + 1;
        }
    </script>
</jsSection>
</body>
</html>