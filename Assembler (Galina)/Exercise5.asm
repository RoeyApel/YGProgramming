; multi-segment executable file template.

data segment
    ; add your data here!
    arr db 8,3,55,6,3
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
    
    mov bx, offset arr 
    mov ax, 5
    push ax
    push bx
    call SortArr
    
    SortArr proc 
        push bp
        mov bp, sp
        mov bx, [bp+4]
        mov cx, [bp+6]
        
        cmp cx, 1
        je con
        
        push cx
        push bx
        call FindMin 
        
        mov al, [bx+di]
        xchg al, [bx]  
        xchg [bx+di], al 
        
        dec cx
        push cx
        inc bx
        push bx
        call SortArr
        
    sof:pop bp
        
        ret 3
    endp SortArr
    
    FindMin proc  
        pop dx 
        pop si 
        pop di
        push ax
        push cx
        push bx
        push si 
        xor bx,bx      
        mov al, 0fh
        xor ah, ah
        mov cx, di 
        
     ag:cmp al, [bx+si]
        jle con
        mov al, [bx+si]    
        mov ah, bl
    con:inc bl
        loop ag
        
        mov al,ah 
        mov ah, 0
        mov di, ax
        
        pop si
        pop bx
        pop cx
        pop ax 
        push dx
        ret
    endp FindMin
    ; add your code here

    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
