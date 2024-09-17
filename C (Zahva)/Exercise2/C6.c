#include <stdio.h>
#include <string.h>

void printTriangle(int n);
void printLine(int n);

int main()
{
    printTriangle(4);
    return 0;
}

void printTriangle(int n)
{
    if (n > 0)
    {
        printTriangle(n - 1);
        printLine(n);
        printf("\n");
    }
}
void printLine(int n)
{
    if (n > 0)
    {
        printf("*");
        printLine(n - 1);
    }
}