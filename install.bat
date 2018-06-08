@echo off
title install project
echo.
echo %cd%\   mvn clean install -DskipTests -Pdevelopment
mvn clean install -DskipTests -Pdevelopment
pause;