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
    <p><b>input</b></p>
    <input type="text" v-model="inputValue"/>
    <input type="text" v-model="inputValue" :disabled="true"/>
    <br/><br/>
    <p><b>radio</b></p>
    <label class="radio_checkbox" v-for="item in repeatList">
        <input type='radio' name="radio" :value="item.value" v-model="radioChecked"
               :disabled="radioDisabled" />
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <span v-text="radioChecked"></span>
    <br/><br/>
    <p><b>checkbox</b></p>
    <label class="radio_checkbox" v-for="item in repeatList">
        <input type='checkbox' name="checkbox" :value="item.value" v-model="checkboxChecked" :disabled="checkboxDisabled" />
        <i></i>
        <span v-text="item.name"></span>
    </label>
    <span v-text="checkboxChecked"></span>
    <br/><br/>
    <p><b>select</b></p>
    <select v-model="selectedValue">
        <option value="" disabled>--全部--</option>
        <option v-for="item in repeatList" :value="item.value" v-text="item.name"></option>
    </select>
    <span v-text="selectedValue"></span>
    <br/><br/>
    <p><b>event</b></p>
    <input type="button" value="测试事件" @click="toggle()"/>
    <span v-show="showSpan">show显示和隐藏, v-hide</span>
    <span v-if="!showSpan">v-if, v-else, v-else-if</span>
    <br><br>
    <p>once</p>
    <span v-once="showSpan" v-text="'只调用一次' + showSpan"></span>
    <br/><br/>
    <p><b>span</b></p>
    <p class="testclass" v-text="inputValue" :class="{'red': hasClass}"></p>
    <span v-text="'v-text: ' + htmlValue"></span><br>
    <span v-html="'v-html: ' + htmlValue"></span>
    <br><br>
    <p>style</p>
    <div :class="getClass()" :style="getStyle()">abc</div>
    <br><br>

    <ul>
        <todo-item v-for="item in repeatList" v-bind:key="item.id" v-bind:todo="item">
        </todo-item>
    </ul>

    <hello hello></hello>
    <pagination v-bind:pagerInfo="pagerInfo" v-bind:click="testMe"></pagination>
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

        var list = [];
        list.push({name: '电影', value: 1});
        list.push({name: '看书', value: 2});
        list.push({name: '打游戏', value: 3});

        vueData = {
            inputValue: '测试数据',
            hasClass: true,
            radioChecked: 2,
            radioDisabled: false,
            repeatList: list,
            checkboxChecked:[3],
            checkboxDisabled: false,
            selectedValue: 3,
            htmlValue: '<span class="red">v-bind:id绑定属性，可简写为 :id；v-on:click绑定事件，可简写为 @click</span>',
            showSpan:true,
            pagerInfo: {pageNum: 5, pages: 10}
        };

        vueMethods = {
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
            },
            toggle: function () {
                this.showSpan = !this.showSpan;
                this.hasClass = !this.hasClass;
                console.log('myapp');
            }
        };
    </script>
</jsSection>
</body>
</html>