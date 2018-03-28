<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<link rel="stylesheet" href="${ctx}/js/ueditor1_4_3_3/umeditor.min.css">
<style>
</style>
<title>tomcat学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div id="myeditor"></div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<script type="text/javascript" src="${ctx}/js/ueditor1_4_3_3/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor1_4_3_3/ueditor.all.js"></script>
<script>
    var opt = {
        imageUrl: "${ctx}/learn/ueditorPicUpload.do",
        serverUrl: '${ctx}/learn/ueditorServer.do',
        imagePath: '',
        toolbar: ['image',
            ' undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize',
            '| justifyleft justifycenter justifyright justifyjustify |'

        ],
        initialFrameHeight: 300,
        autoHeightEnabled: true,
        autoFloatEnabled: true,
        width: "100%",
        opt: {zIndex: 100},
        isBase64: true
    };
    UE.getEditor('myeditor', opt);
</script>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>
