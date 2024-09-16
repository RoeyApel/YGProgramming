#include <stdio.h>
void printReverse(int num);

int main()
{
    printReverse(1);
    return 0;
}
void printReverse(int num)
{
    if (num < 10)
    {
        printf("%d", num);
        return;
    }
    printf("%d",num % 10);
    printReverse(num / 10);
}