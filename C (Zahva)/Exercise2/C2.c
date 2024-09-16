#include <stdio.h>

int AtLeastOneEven(int n);

int main()
{
    printf("%d", AtLeastOneEven(897531));
    return 0;
}

int AtLeastOneEven(int n)
{
    if (n < 10)
    {
        return n % 2 == 0;
    }
    if ((n % 10) % 2 == 0)
    {
        return 1;
    }
    return AtLeastOneEven(n / 10);
}