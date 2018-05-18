<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>vue学习</title>
</head>
<body>
<div>
    <div>
        <p>
            注意事项
        </p>
        <ul>
            <li>
                页面中使用的变量，必须先定义
            </li>
            <li>
                定义的组件必须在使用前定义
            </li>
            <li>只有定义在new Vue中定义的属性才是双向绑定的，如果使用var vm = new Vue中未定义变量，然后使用vm.otherP = 'abcd'，则不是响应式的</li>
            <li>v-once指令只会绑定一次，后续变化将不再刷新视图，此指令会影响节点上所有的数据绑定</li>
            <li>元素属性不能使用{{id}}来绑定，应使用v-bind:属性来绑定，如v-bind:id</li>
            <li>v-bind绑定属性，v-text绑定文本，也可用{{myvar}}，v-bind绑定html</li>
            <li>v-bind:id可缩写为:id, v-on:click可缩写为@click</li>
            <li>computed计算属性，methods方法比较，计算属性基于绑定依赖，如果依赖没有发生变化，会直接返回缓存，而methods每次重新渲染都会重新计算</li>
            <li>&lt;template&gt;是不可见元素</li>
            <li>v-for
                <ul>
                    <li>
                        数组(item, index), index为索引，v-for="(item, index) in items"
                    </li>
                    <li>
                        对象(value, key, index)，key为键值，index为索引，v-for="(value, key, index) in object"
                    </li>
                </ul>
            </li>
            <li>
                事件修饰v-on:click.stops&nbsp;
                .stop&nbsp;
                .prevent&nbsp;
                .capture&nbsp;
                .self&nbsp;
                .once&nbsp;
                <br>
                阻止单击事件继续传播
                &lt;a v-on:click.stop="doThis"&gt;&lt;/a&gt;<br>

                提交事件不再重载页面
                &lt;form v-on:submit.prevent="onSubmit"&gt;&lt;/form&gt;<br>

                修饰符可以串联
                &lt;a v-on:click.stop.prevent="doThat"&gt;&lt;/a&gt;<br>

                只有修饰符
                &lt;form v-on:submit.prevent&gt;&lt;/form&gt;<br>

                添加事件监听器时使用事件捕获模式
                即元素自身触发的事件先在此处处理，然后才交由内部元素进行处理
                &lt;div v-on:click.capture="doThis"&gt;...&lt;/div&gt;<br>

                只当在 event.target 是当前元素自身时触发处理函数
                即事件不是从内部元素触发的
                &lt;div v-on:click.self="doThat"&gt;...&lt;/div&gt;<br>

                点击事件将只会触发一次
                &lt;a v-on:click.once="doThis"&gt;&lt;/a&gt;
            </li>
            <li>
                enter事件
                &lt;input v-on:keyup.enter="submit"&gt;
                <br>
                缩写语法
                &lt;input @keyup.enter="submit"&gt;<br>
                .enter<br>
                .tab<br>
                .delete (捕获“删除”和“退格”键)<br>
                .esc<br>
                .space<br>
                .up<br>
                .down<br>
                .left<br>
                .right<br>
            </li>
            <li>
                mode修饰,.lazy,.number,.trim
            </li>
        </ul>
    </div>

    <div>
        <div id="divOne">
            <my-component></my-component>
        </div>
        <div id="divTwo">
            <my-component></my-component>
        </div>
    </div>
</div>
<jsSection>
    <script src="${ctx}/js/vue/vue.js"></script>
    <script>
        Vue.component('myComponent', {
            template: '<p>abcd<button>测试</button></p>'
        });

        new Vue({
            el: '#divOne'
        });
        new Vue({
            el: '#divTwo'
        });
    </script>
</jsSection>
</body>
</html>