<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>tomcat学习</title>
</head>
<body>
<div>
    <div id="myeditor"></div>
</div>
<jsSection>
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
</jsSection>
</body>
</html>