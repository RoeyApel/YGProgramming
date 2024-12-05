@echo off
set exe_name=runner
gcc *.c -o %exe_name% && %exe_name%