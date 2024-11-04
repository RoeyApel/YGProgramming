; multi-segment executable file template.

data segment
    ; add your data here! 
    arr db 3,16,5,3,5,3,5,16,5,0
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
    mov ax, 4
    push ax
    mov ax, 5
    push ax
    call TEST
    mov bx, 0
    
    proc TEST 
        pop dx
        pop bx 
        pop cx 
        xor di,di
    loopX:        
        cmp bl, arr[di]  
        je yes
        inc di
        loop loopX
        mov al, 0
        jmp sof
    yes:mov al, 1 
    sof:
        push dx
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
