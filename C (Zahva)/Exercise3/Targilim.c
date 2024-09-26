#include <stdio.h>
#include <limits.h>

int getMax();
int getMin();
int countLower(int n, int k);
int calculPow(int x, int n);

int main()
{
    printf("result: %d", calculPow(2, 8));
    return 0;
}

int calculPow(int x, int n)
{
    if (n == 1)
    {
        return x;
    }

    if (n % 2 == 0)
    {
        return calculPow(x, n / 2) * calculPow(x, n / 2);
    }
    return calculPow(x, n / 2) * calculPow(x, n / 2) * calculPow(x, 1);
}

int countLower(int n, int k)
{
    int current;
    printf("Type number: ");
    scanf("%d", &current);

    if (n == 1)
    {
        return (k > current);
    }

    return k > current ? countLower(n - 1, k) + 1 : countLower(n - 1, k);
}

int getMax()
{
    int currentNum;
    printf("num please: ");
    scanf("%d", &currentNum);

    if (currentNum == -1)
    {
        return -1;
    }

    int nextNum = getMax();
    return (nextNum > currentNum) ? nextNum : currentNum;
}

int getMin()
{
    int currentNum;
    printf("num please: ");
    scanf("%d", &currentNum);

    if (currentNum == -1)
    {
        return -1;
    }

    int nextNum = getMin();

    if (nextNum == -1)
    {
        return currentNum;
    }
    return (nextNum < currentNum) ? nextNum : currentNum;
}