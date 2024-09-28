; multi-segment executable file template.

data segment
    ; add your data here!
    k dw 5
    arr1 dw 2025h,1061h,1492h,5777h,1948h
    arr2 dw 1948h,1601h,2914h,7775h,8491h
    pkey db "press any key...$"
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
; set segment registers:
    mov ax, data
    mov ds, ax
    mov es, ax

    ; add your code here 
  
  
    mov dx, 0h 
    mov ax, 2
    mul [k] 
    mov di, ax
    sub di, 2
    
while:cmp di, 0
      je sof  
      
      mov bx, offset arr1 
      mov ax,[bx + di]  
      
      mov bx, offset arr2
      mov cx,[bx + di]
      
      rol ax, 8
      rol al, 4
      rol ah, 4
      
      sub di, 2  
      
      cmp ax, cx
      jne while  
      inc dx 
    
      jmp while
sof:
     
   
            
    lea dx, pkey
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
