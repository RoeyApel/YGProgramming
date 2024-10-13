#include <stdio.h>
#include "printShit.c"

int main()
{
    int arr[5][4] = {
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4}};

    int row = sizeof(arr) / sizeof(*arr);
    int col = sizeof(*arr) / sizeof(**arr);
    print2dIntArr((int *)arr, row, col);

    return 0;
}