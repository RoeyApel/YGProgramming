#include <stdio.h>

// functions declarations
int choose(int n, int k);
void printPascal(int n);

int main()
{
    printPascal(3);
    return 0;
}

void printPascal(int n)
{
    int k;
    for (k = 0; k < n; k++)
    {
        for (int i = 0; i < n - k; i++)
        {
            printf(" ");
        }

        for (int j = 0; j <= k; j++)
        {
            printf("%d ", choose(k, j));
        }
        printf("\n");
    }
}

int choose(int n, int k)
{
    if (k > n)
    {
        return 0;
    }
    if (k == 0 || k == n)
    {
        return 1;
    }
    return choose(n - 1, k - 1) + choose(n - 1, k);
}