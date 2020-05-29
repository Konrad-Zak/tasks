call runcrud.bat
if ERRORLEVEL 0 goto getTasks
echo.
echo runcrud.bat has errors - breaking work
goto fail

:getTasks
sleep 20
set GET_TASKS=http://localhost:8080/crud/v1/task/getTasks
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" %GET_TASKS%
echo.
echo Open Web side
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.