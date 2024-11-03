#include <stdio.h>

float secret(int x, int y);

int main()
{
    printf("%.2f", secret(2, 5));
    return 0;
}

float secret(int x, int y)
{
    if (x == y)
        return x;

    if (x > y)
    {
        if (y - x == 1)
            return x + 0.5;
        return secret(x - 1, y + 1);
    }

    if (y - x == 1)
        return x + 0.5;
    return secret(x + 1, y - 1);
}