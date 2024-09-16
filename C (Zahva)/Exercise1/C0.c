#include <stdio.h>

int AtLeastOne(int num, int d);
void uniqueDigits(int num, int i);

int main()
{
    uniqueDigits(4656897, 4);
    return 0;
}
void uniqueDigits(int num, int i)
{
    if (i <= 9)
    {
        if (AtLeastOne(num, i))
        {
            printf("%d ", i);
        }
        uniqueDigits(num, i + 1);
    }
}
int AtLeastOne(int num, int d)
{
    if (num % 10 == d)
    {
        return 1;
    }
    if (num < 10)
    {
        return num == d;
    }
    return AtLeastOne(num / 10, d);
}