#include <stdio.h>

void printOneToN(int n);

int main()
{
    printOneToN(1);
    return 0;
}

void printOneToN(int n)
{
    if(n != 0){
        printOneToN(n - 1);
        printf("%d", n);
    }
}