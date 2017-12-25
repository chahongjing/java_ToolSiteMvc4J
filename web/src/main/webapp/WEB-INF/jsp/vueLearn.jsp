<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>tomcat学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
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
            <li>元素属性不能使用{{id}}来绑定，应使用v-bind:属性来绑定</li>
            <li>v-bind:id可缩写为:id, v-on:click可缩写为@click</li>
            <li>computed计算属性，methods方法比较，计算属性基于绑定依赖，如果依赖没有发生变化，会直接返回缓存，而methods每次重新渲染都会重新计算</li>
            <li>&lt;template&gt;是不可见元素</li>
            <li>v-for
                <ul>
                    <li>
                        数组(item, index), index为索引
                    </li>
                    <li>
                        对象(value, key, index)，key为键值，index为索引
                    </li>
                </ul>
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
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
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
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>