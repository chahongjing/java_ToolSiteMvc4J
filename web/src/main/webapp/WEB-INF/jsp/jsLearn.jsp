<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习js</title>
    <style>
        .list{padding-left:20px;}
        .list, .list li{
            list-style-type:circle;
        }
        .list li{padding:5px 0;}
    </style>
</head>
<body>
<%-- html正文 --%>
<div>
    <a href="javascript:void(0)" onclick="blobDownload()">blob下载</a><br/>
    <a href="javascript:void(0)" onclick="upload()">上传文件</a>
    <form id="formId" enctype="multipart/form-data" action="<c:url value="/learn/fileupload1" />">
        <input type="hidden" name="id" value="abcde"/>
        <input type="file" name="myfile"/>
    </form>
    <a href="javascript:void(0)" id="lnkTestPromise">测试promise</a>

    <form method="post" action="${ctx}/test/testPostWithFile" enctype="multipart/form-data" target="_blank">
        <input type="text" name="name"/>
        <input type="hidden" name="age" value="28"/>
        <input type="text" name="test" value="测试部分页render:${username}"/>
        <input type="file" name="myfile" multiple="multiple"/>
        <button type="submit" name="tj" value="提交">提交</button>
        <button type="button" name="ajaxtj" value="提交">ajax提交</button>
    </form>

    <div>
        <p>Array</p>
        <ul class="list">
            <li>Array.isArray(myArr)</li>
            <li>myArr.every(function(item) { return item.abc > 1; })</li>
            <li>myArr.filter(function(item) { return item.abc > 1; })</li>
            <li>找到第一个适合的元素。myArr.find(function(item) { return item.abc > 1; })</li>
            <li>找到第一个适合的元素的索引。myArr.indexOf(element, 3), myArr.findIndex(function(item) { return item.abc > 1; })</li>
            <li>是否符合条件。myArr.includes(element), myArr.some(function(item) { return item.abc > 1; });</li>
            <li>myArr.keys()</li>
            <li>myArr.reduce(callback[, initialValue])</li>
            <li>reverse, shift, sort, unshift, splice, myArr.slice([begin[, end]])</li>
            <li>Array.prototype.push.apply(arr1, arr2);</li>
            <li>Math.max.apply(null, [2,1,3]);</li>
        </ul>
    </div>


    <div>
        <p>amd cmd commonjs</p>
        <ul class="list">
            <li>服务端：commonjs，框架有node.js</li>
            <li>浏览器：amd, cmd; amd框架有requirejs, cmd框架有seajs</li>
            <li>commonjs, var math = require('math'); math.add(2, 3);只有math.js加载完成后才会执行后一句代码，同步的，服务端文件都在本地，
            加载时间为读取文件时间，但浏览器端却要从服务器下载文件，导致页面卡住，因此就有了amd异步加载模块</li>
            <li><pre>AMD, <br>require(['math'], function (math) { <br>math.add(2, 3);<br>});<br>
                //AMD <br>define(['./a','./b'],function(a,b){<br> a.doSomething()<br> b.dosomething()<br> })<br>
                //CMD <br>define(function(require,exports,module){ <br>var a=require('./a') <br>a.doSomethimg()
                <br>var b=require('./b') <br>b.doSomething() <br>})</pre>
            </li>
            <li>AMD和CMD最大的区别是对依赖模块的执行时机处理不同,注意不是加载的时机或者方式不同
                加载模块都是异步的,只不过AMD依赖前置,js可以方便知道依赖模块是谁,立即加载,而CMD就近依赖,需要使用把模块变为字符串解析一遍才知道依赖了那些模块,这也是很多人诟病CMD的一点,牺牲性能来带来开发的便利性,实际上解析模块用的时间短到可以忽略
            </li>
        </ul>
    </div>
</div>
<jsSection>
    <%-- js脚本 --%>
    <script>
        // blob下载
        function blobDownload() {
            var url = ctx + "/learn/download";
            var xhr;
            if (window.XMLHttpRequest) {
                xhr = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xhr.open('GET', url, true);
            xhr.responseType = "blob";
            xhr.onload = function () {
                if (this.status == 200) {
                    // application/x-zip-compressed
                    var contentType = this.getResponseHeader('Content-Type');
                    var fileName = this.getResponseHeader('Content-Disposition');
                    if (fileName) {
                        fileName = decodeURIComponent(fileName).replace(/attachment;\s*filename=/g, '');
                        if (fileName.lastIndexOf('.') > -1) {
                            var suffix = fileName.substr(fileName.lastIndexOf('.'));
                        }
                    }
                    var blob = new Blob([this.response], {type: contentType});
                    var a = document.createElement("a");
                    a.style.display = 'none';
                    a.download = fileName;
                    a.href = URL.createObjectURL(blob);
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                } else {
                    var reader = new FileReader();
                    var text = reader.readAsText(this.response, 'utf-8');
                    reader.onload = function (e) {
                        var result = reader.result;
                        console.log(result);
                        var startIndex = result.indexOf("<title>");
                        if (startIndex > 0) {
                            var endIndex = result.indexOf("</title>");
                            alert(result.substring(startIndex + 7, endIndex));
                        } else {
                            alert(result);
                        }
                    }
                }
            }
            xhr.send();
        }

        function upload() {
            var $form = $('#formId');
            $.ajax({
                type: 'POST',
                url: $form.attr('action'),
                data: new FormData($form[0]),
                processData: false,
                contentType: false, // 如果form没有指定enctype，则可以在此处指定
                success: function (data) {
                    if(data.status == Constant.AjaxStatus.OK) {
                        alert('上传成功！' + data.value);
                    } else {
                        alert(data.message);
                    }
                },
                error: function(xhr,  type, message) {
                    if(xhr.status == 511) {
                        window.location.reload();
                    }
                }
            });
            // angular $http请求发送FormData 数据要使用
//        var $form = $('#loginForm');
//        var form = $form[0];
//        var formData = new FormData(form);
//        $http({
//            url: form.action,
//            method: 'post',
//            data: formData,
//            headers: {'Content-Type': undefined},
//            processData: false,
//            transformRequest: angular.identity
//        }).success(function(resp) {
//            window.location = form['redirectUrl'].value;
//        });
        }


        $(function () {
            $('#lnkTestPromise').one('click', function () {
                console.log('click');
                var p1 = $.ajax({
                    url: ctx + '/test/testP1'
                }).then(function (data) {
                    console.log('testP1');

                    return 'p1';
                });

                var p2 = $.ajax({
                    url: ctx + '/test/testP2'
                }).then(function (data) {
                    console.log('testP2');

                    return 'p2';
                });

                Promise.all([p1, p2]).then(function (result) {
                    console.log(result);
                });
            });

            $('button[name=ajaxtj]').click(function () {
                var formData = new FormData();
                var files = $('input[name=myfile]')[0].files;
                formData.append("name", "zjy");
                for (var i = 0; i < files.length; i++) {
                    formData.append("myfile", files[i]);
                }

                $.ajax({
                    //url: ctx + '/learn/fileupload',
                    //url: 'http://localhost:30000/restfulweb/rest/hello/testPostWithFile',
                    url: 'http://localhost:30001/api/rest/hello/testPostWithFile',
                    type: 'post',
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (resp) {

                    },
                    error:function(xhr, a, b) {
                        console.log(xhr);
                    }
                });
            });
        });
    </script>

</jsSection>
</body>
</html>