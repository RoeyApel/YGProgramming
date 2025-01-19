; multi-segment executable file template.

data segment
    ; add your data here!

    arr1 dw  6412, 9698, 7619


    arr2 dw  2146, 8999, 9167
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
    mov cx, 3
    push cx
    lea si, arr2
    push si
    lea si, arr1
    push si
    
    call Test
    push cx
    push cx
    push cx
    push cx 
    push cx
    push cx
    jmp temp
    
    proc Test 
        push bp
        push bx 
        mov bp, sp
        mov si, [bp+6] ;address arr1
        
        mov cx, [bp+10] ;len
        cmp cx, 0
        je stop
        
        mov di, 10
        mov cx, 4 
        xor bx, bx
        mov ax, [si] 
        
  again:xor dx, dx 
        div di  
        push ax 
        push dx    
        xor dx, dx
        mov ax, bx
        mul di
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
        
   stop:mov si,[bp+8] ;address arr2 
        sub si,2   
        pop bx
        cmp bx, [si]
        jne no
        inc cx 
       
     no:pop bp
        ret 6 
        
    endp Test
            
    mov ah, 9
    int 21h        ; output string at ds:dx
 temp:    
    ; wait for any key....    
    mov ah, 1
    int 21h

   
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
