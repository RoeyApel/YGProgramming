; multi-segment executable file template.

data segment
    ; add your data here!  
    arra db 1,2,3,4,5
    arrb db 3,5,6,1,2,3,4,5,1,2 
    v db ?
    p db ?
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
    mov cx, 5
    xor bx, bx
    mov al, arra[bx]
    mov [v], al
    push bx
    push cx
    call TEST
    mov dh, [p]
    cmp dh, -1
    jz no
    inc bx
loopX:  
    mov al, arra[bx]
    mov [v], al    
    push bx
    push cx
    call TEST
    mov dl, [p]
    cmp dl , 0
    jle no
    dec dl
    cmp dl, dh
    jnz no 
    inc dh 
    inc bx
    loop loopX    
    mov bl, 1 
    jmp sof
no: mov bl, 0   
sof1:    
    
    proc TEST            
        mov cx, 10
        mov bx, 0 
        mov al, [v]
    lop:cmp al, arrb[bx]
        jz yes    
        inc bx
        loop lop 
        mov [p], -1
        jmp sof  
    yes:mov [p], bl
    sof:  
        pop ax
        pop cx
        pop bx
        push ax 
        ret
    endp TEST
         
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
