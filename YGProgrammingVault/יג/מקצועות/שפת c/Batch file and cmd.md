@echo off // לא להראות את הפקודות

set exe_name=runner // משתנה 

gcc *.c -o %exe_name% 
&& 
%exe_name% // פקודות