@echo off
chcp 65001


echo 파일 서버를 시작합니다.

echo === 본부 서버 시작 ===
start /B java -jar "%~dp0shq_recv.jar"
set PID1=%PROCESS_ID%

start /B java -jar "%~dp0shq_send.jar"
set PID2=%PROCESS_ID%
timeout /t 5 /nobreak >nul
echo === 본부 서버 시작 완료 ===
echo === 매장 서버 시작 ===
start /B java -jar "%~dp0ssm_recv.jar"
set PID3=%PROCESS_ID%
timeout /t 2 /nobreak >nul

start /B java -jar "%~dp0ssm_send.jar"
set PID4=%PROCESS_ID%
echo === 매장 서버 시작 완료 ===
timeout /t 2 /nobreak >nul

echo 파일 서버 시작 완료
echo === 캐시 서버 시작 ===
start /B java -jar "%~dp0cache_server.jar"
set PID5=%PROCESS_ID%
echo === 캐시 서버 시작 완료 ===
timeout /t 2 /nobreak >nul
echo 아무키나 입력하면 모든 서버를 종료합니다.
pause

