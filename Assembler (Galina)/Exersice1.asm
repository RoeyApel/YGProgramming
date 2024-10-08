; multi-segment executable file template.

data segment
    ; add your data here! 
    numbers db 100 dup(?)  
    app db 256 dup(0)
    pkey db "press any key...$"
ends

stack segment 
    dw   128h  dup(0)     
ends

code segment
start:
; set segment registers:
    mov ax, data
    mov ds, ax
    mov es, ax
    
    ; add your code here
    mov cx, 100
    mov si, 0 
A1: mov di, [numbers + si] 
    mov bl, [app + di]
    inc bl
    mov [app + al], bl 
    inc si
    loop A1
    
           
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
