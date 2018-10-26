<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/commonVar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习thread</title>
    <style>
        * {
            font-family: "Microsoft YaHei UI";
            font-size: 14px;
        }

        .bold {
            font-weight: bold;
        }

        .red {
            color: #f00;
        }

        .inline {
            display: inline-block;
        }
    </style>
</head>
<body>
<div>
    <p>
    <h2>多线程</h2>
    <ul>
        <li>继承Thread类, 它继承Runnable接口</li>
        <li>实现Runnable接口，实现run方法，没有返回值不能抛出受检查的异常</li>
        <li>实现Callable接口，实现call方法，他有返回值，可以抛出受检查的异常</li>
        <li>线程池
            <ul>
                <li>Executor，Executors，ExecutorService，CompletionService，Future，Callable</li>
				<li>FixedThreadPool:创建固定数目线程的线程池。</li>
				<li>CachedThreadPool:创建一个可缓存的线程池,如果现有线程没有可用的，则创建一个新线 程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。</li>
				<li>SingleThreadExecutor:创建一个单线程化的Executor。</li>
				<li>ScheduledThreadPool:创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。</li>
				<li>https://www.cnblogs.com/fengsehng/p/6048609.html</li>
            </ul>
        </li>
    </ul>
    </p>
</div>
<jsSection>

</jsSection>
</body>
</html>