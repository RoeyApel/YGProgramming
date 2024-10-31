#include <stdio.h>

typedef enum
{
    SOUTH = 1,
    CENTER,
    NORTH
} Area;

typedef struct
{
    int id;
    Area area;
    int rentingDays;
    float pricePerDay;
    int renterAge;
} Rental;

Rental rents[500];

int main()
{
    return 0;
}

void printDoc(Rental *rents, int size)
{
    int sumSouth = 0, sumNorth = 0, sumCenter = 0;

    int i;
    for (i = 0; i < size; i++)
    {
        if ((rents + i)->area == SOUTH)
        {
            sumSouth++;
        }else if((rents + i)->area == NORTH){
            sumNorth++;
        }else{
            sumCenter++;
        }
    }

    printf("Sum South: %d, Sum North: %d, Sum Center: %d\n");
}

void printLongestTime(Rental *rents, int size)
{
    int i, max = -1;
    for (i = 0; i < size; i++)
    {
        if (max > (rents + i)->rentingDays)
        {
            max = (rents + i)->rentingDays;
        }
    }

    for (i = 0; i < size; i++)
    {
        if (max == (rents + i)->rentingDays)
        {
            printf("Age %d: %d", i + 1, (rents + i)->renterAge);
        }
    }
}
