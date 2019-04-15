@echo off
title toolsitemvc4j push
echo.
set /p comment=ÇëÊäÈë±¸×¢:
git commit -m %comment%
git push toolsitemvc4j master
pause;