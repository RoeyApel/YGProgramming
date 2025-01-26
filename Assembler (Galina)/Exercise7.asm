; multi-segment executable file template.

data segment
    ; add your data here!
    result db 0 
    str db "aaa",0
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
    lea si, str
    
    mov [result], 3
    xor cx, cx
    
 l1:xor ax, ax
    
    lodsb  
    cmp al, 0
    je sof
    
    mov ah, al  
    inc cl
      
    lodsb 
    cmp al, 0
    je sof
    
    cmp ah, al
    je equl
    
    cmp ah, al
    ja big
             
    cmp result,2
    je unord 
    mov [result], 1
    jmp l1  
    
equl:jmp l1

big:cmp result,1
    je unord 
    mov [result], 2
    jmp l1 
     
unord:
    mov [result], 3
    jmp sof
    
sof:cmp cl, 0
    jne done
    mov [result], 0
done:  
  
    mov ah, 9
    int 21h        ; output string at ds:dx
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
