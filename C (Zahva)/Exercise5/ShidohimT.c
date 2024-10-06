#include <stdio.h>

typedef struct
{
    char name[25];
    char gender;
    int age;
} Shidoh;

void printShidohs(Shidoh *shidohs, int size, Shidoh shidoh);

int main()
{
    Shidoh shidohs[10] = {{"gfdhd", 'M', 0}};
    Shidoh newShidoh = {"gfdd", 'M', 12};
    printShidohs(shidohs, 10, newShidoh);

    return 0;
}

void printShidohs(Shidoh *shidohs, int size, Shidoh shidoh)
{
    int foundShidoh = 0;

    for (int i = 0; i < size; i++)
    {
        int ageDifferance = (shidohs + i)->age - shidoh.age;
        ageDifferance = ageDifferance < 0 ? ageDifferance * -1 : ageDifferance;
        if ((shidohs + i)->gender != shidoh.gender && ageDifferance <= 10)
        {
            foundShidoh = 1;
            printf("Name: %s, Gender: %c, Age: %d\n",
                   (shidohs + i)->name, (shidohs + i)->gender, (shidohs + i)->age);
        }
    }
    if (!foundShidoh)
    {
        printf("didnt found!");
    }
}