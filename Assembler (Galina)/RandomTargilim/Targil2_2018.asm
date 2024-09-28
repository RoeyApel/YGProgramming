; multi-segment executable file template.

data segment
    ; add your data here!
    arr db -2, 0, -42, 77, 33, 55, -1  
    rez db 1
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
    
    mov dl, 0  
    
    mov bx, offset arr
    mov si, offset rez
    sub si, bx
    mov di, 0
    
while: cmp si, di
       je gSof
       
       mov al, [bx + di]
       mov ah, [bx + di + 1]
       inc di
       
       cmp dl, 1
       je checkS
       
       cmp ah, al
       jle sof
       inc dl
       jmp while  
checkS:       
       cmp ah, al
       jge sof
       dec dl
       jmp while 
sof:
       mov [rez], 0
gSof:

        
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
