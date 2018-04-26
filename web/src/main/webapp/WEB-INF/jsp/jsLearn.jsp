<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<title>学习js</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
<a href="javascript:void(0)" onclick="blobDownload()">blob下载</a><br/>
<a href="javascript:void(0)" onclick="upload()">上传文件</a>
    <form id="formId" enctype="multipart/form-data" action="<c:url value="/learn/fileupload.do" />">
        <input type="hidden" name="id" value="abcde" />
        <input type="file" name="myfile" />
    </form>
</div>

<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script>
    // blob下载
    function blobDownload() {
        var url = ctx + "/learn/download.do";
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
                if(fileName) {
                    fileName = decodeURIComponent(fileName).replace(/attachment;\s*filename=/g, '');
                    if (fileName.lastIndexOf('.') > -1) {
                        var suffix = fileName.substr(fileName.lastIndexOf('.'));
                    }
                }
                var blob = new Blob([this.response], { type: contentType });
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
                console.log(data);
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
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>