; multi-segment executable file template.

data segment
    ; add your data here!

    arr1 dw 1258, 6412, 9654, 2311, 7619


    arr2 dw 6999, 2146, 9999, 8888, 9167
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
    proc Test 
        push bp
        push bx 
        mov bp, sp
        mov si, [bp+6] ;address arr1
        
        mov cx, [bp+10] ;len
        cmp cx, 0
        je stop
        
        mov cx, 4 
        xor bx, bx
        mov ax, [si] 
        
  again:xor dx, dx
        div word ptr 10  
        push ax 
        push dx    
        xor dx, dx
        mov ax, bx
        mul word ptr 10
        mov bx, ax
        pop dx
        add bx, dx
        pop ax
        loop again
        
        mov ax, [bp+10] ;len   
        dec ax
        push ax
        mov ax, [bp+8] ;address arr2
        add ax, 2
        push ax
        add si, 2
        push si
        
        call Test
        
   stop:pop bx
        pop bp
        mov si,[bp+4] ;address arr2
        cmp bx, [si]
        jne no
        inc cx
     no:ret 6 
        
    endp Test
            
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
