@echo off
title redeploy project
echo.
echo %cd%\   mvn clean tomcat7:redeploy -DskipTests -Pdevelopment
mvn clean tomcat7:redeploy -DskipTests -Pdevelopment
pause;