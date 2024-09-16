#include <stdio.h>

void printNToOneToN(int n);

int main()
{
    printNToOneToN(5);
    return 0;
}

void printNToOneToN(int n)
{
    if (n != 1)
    {
        printf("%d", n);
        printNToOneToN(n - 1);
        printf("%d", n);
    }else{
        printf("1");
    }
}