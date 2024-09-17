#include <stdio.h>
int isOddEvenFits(int num);
int isOddEvenFits2(int num, int i);

int main()
{
    printf("%d", isOddEvenFits(187650));
    return 0;
}

int isOddEvenFits(int num)
{
    isOddEvenFits2(num, 0);
}
int isOddEvenFits2(int num, int i)
{
    if (num < 10)
    {
        if (i % 2 == 0)
        {
            return num % 2 == 0;
        }
        return num % 2 == 1;
    }
    if (i % 2 == 0 && num % 2 == 0)
    {
        return isOddEvenFits2(num / 10, i + 1);
    }
    if (i % 2 == 1 && num % 2 == 1)
    {
        return isOddEvenFits2(num / 10, i + 1);
    }
    return 0;
}