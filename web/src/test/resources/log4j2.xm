<?xml version="1.0" encoding="UTF-8"?>
<configuration status="DEBUG">

    <Properties>
        <Property name="logPath">${web:rootDir}</Property>
        <Property name="contextName">${web:contextPath}</Property>
    </Properties>

    <appenders>
        <Console name="consolePrint" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>

        <File name="File" fileName="${logPath}logs\${contextName}_app.log"
              filePattern="$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n"/>
        </File>

        <RollingFile name="RollingFile" fileName="${logPath}logs\${contextName}_log.log"
                     filePattern="${logPath}logs\r_log_%d{yyyy-MM-dd}_%i.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy modulate="true" interval="24"/>
                <SizeBasedTriggeringPolicy size="51200 KB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>

        <RollingFile name="RollingFile_DEBUG" fileName="${logPath}logs\${contextName}_log_debug.log"
                     filePattern="${logPath}logs\${contextName}_log_%d{yyyy-MM-dd}_%i.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy modulate="true" interval="24"/>
                <SizeBasedTriggeringPolicy size="51200 KB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
            <Filters>
                <ThresholdFilter level="info" onMatch="DENY" onMismatch="NEUTRAL"/>
                <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
        </RollingFile>

        <RollingFile name="RollingFile_INFO" fileName="${logPath}logs\${contextName}_log_info.log"
                     filePattern="${logPath}logs\${contextName}_log_%d{yyyy-MM-dd}_%i.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n" />
            <Policies>
                <TimeBasedTriggeringPolicy modulate="true" interval="24"/>
                <SizeBasedTriggeringPolicy size="51200 KB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
            <Filters>
                <ThresholdFilter level="warn" onMatch="DENY" onMismatch="NEUTRAL"/>
                <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
        </RollingFile>

        <RollingFile name="RollingFile_WARNING" fileName="${logPath}logs\${contextName}_log_waring.log"
                     filePattern="${logPath}logs\${contextName}_log_%d{yyyy-MM-dd}_%i.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n" />
            <Policies>
                <TimeBasedTriggeringPolicy modulate="true" interval="24"/>
                <SizeBasedTriggeringPolicy size="51200 KB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
            <Filters>
                <ThresholdFilter level="error" onMatch="DENY" onMismatch="NEUTRAL"/>
                <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
        </RollingFile>

        <RollingFile name="RollingFile_ERROR" fileName="${logPath}logs\${contextName}_log_error.log"
                     filePattern="${logPath}logs\${contextName}_log_%d{yyyy-MM-dd}_%i.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n" />
            <Policies>
                <TimeBasedTriggeringPolicy modulate="true" interval="24"/>
                <SizeBasedTriggeringPolicy size="51200 KB"/>
            </Policies>
            <DefaultRolloverStrategy max="20"/>
            <Filters>
                <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
            </Filters>
        </RollingFile>
    </appenders>
    <loggers>
        <!-- OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、 ALL -->
        <root level="info">
            <appender-ref ref="consolePrint" />
            <!--<appender-ref ref="File" />-->
            <appender-ref ref="RollingFile" />
            <appender-ref ref="RollingFile_DEBUG" />
            <appender-ref ref="RollingFile_INFO" />
            <appender-ref ref="RollingFile_WARNING" />
            <appender-ref ref="RollingFile_ERROR" />
        </root>
        <!-- 将业务dao接口填写进去,并用控制台输出即可, sql也会输出 -->
        <logger name="com.zjy.bll.dao" level="DEBUG" additivity="false">
            <appender-ref ref="consolePrint"/>
        </logger>
        <logger name="com.zjy.baseframework" level="DEBUG" additivity="false">
            <appender-ref ref="consolePrint"/>
        </logger>
    </loggers>
</configuration>