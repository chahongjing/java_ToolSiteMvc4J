<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/beginHead.jsp" %>
<%-- 页头，添加title, mate信息, link样式, script脚本(建议在script节中添加) --%>
<style>
</style>
<title>nio学习</title>
<%@ include file="/WEB-INF/jsp/common/endHeadAndBeginBody.jsp" %>
<%-- html正文 --%>
<div>
    <div>
        <p>
            对比，具体例子见此页面后面代码
        </p>
        <ul>
            <li>
                传统io是面向流的，单向的，阻塞式的，nio是，nio是面向缓冲区的，双向的，非阻塞式的
            </li>
            <li>nio核心在于通道和缓冲区，通道负责传输，不存储数据，缓冲区负责处理数据</li>
        </ul>
    </div>
    <div>
        <p>
            提供了多种缓冲区，除了Boolean没有。缓冲区通过 allocate()获取缓冲区
        </p>
        <ul>
            <li>ByteBuffer</li>
            <li>CharBuffer</li>
            <li>ShortBuffer</li>
            <li>IntBuffer</li>
            <li>LongBuffer</li>
            <li>FloatBuffer</li>
            <li>DoubleBuffer</li>
        </ul>
    </div>
    <div>
        <p>缓冲区四个核心属性, 0 &lt;= mark &lt;= position &lt;= limit &lt;= capacity</p>
        <ul>
            <li>mark，标记，用于记录当前position的位置，可以通过reset()恢复到mark的位置</li>
            <li>position，位置，缓冲区中正在操作数据的位置</li>
            <li>limit：界限，表示缓冲区中可以操作数据的大小，limit后面的数据是不可以读写</li>
            <li>capacity：容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变。</li>
        </ul>
    </div>
    <div>
        <p>
            操作方法
        </p>
        <ul>
            <li>flip()， 切换为读模式，</li>
            <li>rewind()，可重复读</li>
            <li>mark，标记当前位置</li>
            <li>reset，重置到mark</li>
            <li>hasRemaining，是否还有可操作空间</li>
            <li>remaining，可操作空间/li>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/endBodyAndBeginScript.jsp" %>
<%-- js脚本 --%>
<%@ include file="/WEB-INF/jsp/common/endScript.jsp" %>