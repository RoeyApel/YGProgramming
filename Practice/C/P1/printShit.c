#include "printShit.h"
#include <stdio.h>

void print2dIntArr(int *arr, int row, int col)
{
    int value;

    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            value = *((arr + i * col) + j);
            printf("%d ", value);
        }
        printf("\n");
    }
}