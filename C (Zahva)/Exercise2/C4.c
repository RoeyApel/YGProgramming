#include <stdio.h>

void printNToOne(int n);

int main()
{
    printNToOne(7);
    return 0;
}

void printNToOne(int n)
{
    if (n != 0)
    {
        printf("%d", n);
        printNToOne(n - 1);
    }
}