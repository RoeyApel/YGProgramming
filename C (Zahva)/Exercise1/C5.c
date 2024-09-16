#include <stdio.h>

int findGreatestCommonD(int a, int b);

int main()
{
    printf("%d", findGreatestCommonD(36, 3));
    return 0;
}

int findGreatestCommonD(int a, int b)
{
    if (a == b)
    {
        return a;
    }
    if (a > b)
    {
        findGreatestCommonD(b, a - b);
    }
    else
    {
        findGreatestCommonD(a, b - a);
    }
}