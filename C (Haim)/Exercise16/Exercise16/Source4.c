#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 5

typedef struct car
{
    int mode;
    float price;
    int year;

} Car;

int findCar(Car cars[]);

void mainx()
{
    srand(time(NULL));

    Car cars[5];

    for (int i = 0; i < 5; i++)
    {
        cars[i].mode = rand() % 10;                  
        cars[i].price = (float)(rand() % 40000 + 10000); 
        cars[i].year = rand() % 32 + 1990;               
    }

    for (int i = 0; i < 5; i++)
    {
        printf("Car %d: Mode = %d, Price = %.2f, Year = %d\n",
            i + 1, cars[i].mode, cars[i].price, cars[i].year);
    }

    int count = findCar(cars);
    printf("\n%d", count);

}
int findCar(Car* cars)
{
    int mode, year;
    float price;
    int count = 0;
    printf("mode: ");
    scanf("%d", &mode);
    printf("price: ");
    scanf("%f", &price);
    printf("year: ");
    scanf("%d", &year);

    for (int i = 0; i < SIZE; i++)
    {
        if (mode == (cars + i)->mode && price >= (cars + i)->price && year <= (cars + i)->year)
        {
            printf("Model: %d, Price: %.2f, Year: %d", cars[i].mode, cars[i].price, cars[i].year);
            count++;
        }
    }
    return count;
}