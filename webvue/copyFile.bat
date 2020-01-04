@echo off
title deploy ToolSiteMvc4J and webvue

set javaDir=E:\workplace\java\toolsitemvc4j\web\target\ToolSiteMvc4J\
set vueDir=D:\webvue\
set targetDir=D:\devSoftwareInstall\apache-tomcat-9.0.20_service\webapps\ToolSiteMvc4J\
set configDir=D:\devSoftwareInstall\apache-tomcat-9.0.20_service\webapps\ToolSiteMvc4J\static\

echo.
set /p type=deploy type: all(1), java(2), vue(3):
if "%type%"=="1" (
goto copyJava
)
if "%type%"=="2" (
goto copyJava
)
if "%type%"=="3" (
goto copyVue
)
echo.
echo wrong type
goto end

:copyJava
echo stop tomcat service
net stop tomcat9
echo.
echo delete ToolSiteMvc4J
rmdir /s/q %targetDir%
echo.
echo copy ToolSiteMvc4J
xcopy %javaDir%*.* %targetDir% /s/e/y

echo.
echo start tomcat service
net start tomcat9
if "%type%"=="1" (
goto copyVue
)
goto end

:copyVue
echo.
echo delete static
rmdir /s/q %targetDir%static
echo.
echo copy webvue
xcopy %vueDir%*.* %targetDir% /s/e/y
echo.
echo copy config
xcopy %configDir%*.* %targetDir%static\ /s/e/y

:end
echo.