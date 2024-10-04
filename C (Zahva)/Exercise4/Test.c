#include <stdio.h>
#include <string.h>
#define N 10
main()
{
    struct data
    {

        int num;
        struct data *d;
    };
    struct data d1[10];
    struct data d2[10];
    int vec1[10];
    int vec2[10];
    int i;
    for (i = 0; i < N; i++)
    {
        d1[i].num = i;
        d2[i].num = i * i;
        d1[i].d = (d2 + i);
        d2[i].d = (d1 + N - 1 - i);
    }
    for (i = 0; i < N; i++)
    {
        vec1[i] = d1[i].num + d1[i].d->num;
        vec2[i] = d2[i].num + d2[i].d->num;
    }
    printf("\n\n\n vec1 vec2 \n ");
    for (i = 0; i < N; i++)
        printf(" % d % d\n ", vec1[i], vec2[i]);
    return 0;
}