#include <stdio.h>

int countCouples(int num);

int main()
{
    printf("%d ", countCouples(22455665));
    return 0;
}
int countCouples(int num)
{
    if (num < 10)
    {
        return 0;
    }
    if (num % 10 - 1 == (num / 10) % 10)
    {
        return countCouples(num / 10) + 1;
    }
    else
    {
        return countCouples(num / 10);
    }
}