#include <stdio.h>

#define SIZE 30
typedef struct apartment
{
    char address[SIZE];
    float rooms;
    double price;
} Apartment;

int funApartments(Apartment *apartments, int size, float rooms, double maxPrice);

int main()
{
    Apartment apartments[10] = {{"46646", 3, 53454}};
    printf("%d", funApartments(apartments, 10, 3, 99000));
    return 0;
}

int funApartments(Apartment *apartments, int size, float rooms, double maxPrice)
{
    int count = 0;
    for (int i = 0; i < size; i++)
    {
        if ((apartments + i)->price <= maxPrice && (apartments + i)->rooms == rooms)
        {
            count++;
            printf("Address: %s, Rooms: %.0f, Price: %.2lf\n", (apartments + i)->address, (apartments + i)->rooms, (apartments + i)->price);
        }
    }
    if (count == 0)
    {
        printf("didnt print!");
    }
    return count;
}