@echo off
title toolsitemvc4j push
echo.
set /p comment=�����뱸ע:
git commit -m %comment%
git push toolsitemvc4j master
pause;