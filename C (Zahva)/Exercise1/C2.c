#include <stdio.h>

int isUpNum(int num);

int main()
{
    printf("%d", isUpNum(12345676));
    return 0;
}
int isUpNum(int num)
{
    if (num < 10)
    {
        return 1;
    }
    if (num % 10 - 1 == (num / 10) % 10)
    {
        return isUpNum(num / 10);
    }
    else
    {
        return 0;
    }
}