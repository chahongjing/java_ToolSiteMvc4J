<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<title>学习js</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <a href="javascript:void(0)" onclick="blobDownload()">blob下载</a>
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
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>