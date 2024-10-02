#include <stdio.h>

#define LEN 5

typedef struct
{
    int code;
    float price;
    int amount;
} Item;

void getItemsFromUser(Item *arr);
float *findMostExpensive(Item *arr);
void printLess10(Item *arr);
void printItem(Item item);
float calculatePriceSum(Item *arr);
Item *getLowwestAmountItem(Item *arr);
float getPriceAverage(Item *arr);

int main()
{
    Item arr[LEN];
    getItemsFromUser(arr);
    findMostExpensive(arr);

    printf("----------------------\n");

    printLess10(arr);

    printf("----------------------\n");

    printf("sum: %.2f\n", calculatePriceSum(arr));
    printf("lowwest: %d\n", getLowwestAmountItem(arr)->amount);
    printf("average: %.2f\n", getPriceAverage(arr));

    return 0;
}

float getPriceAverage(Item *arr)
{
    return calculatePriceSum(arr) / LEN;
}

Item *getLowwestAmountItem(Item *arr)
{
    int *min = &(arr->amount);
    Item *item = arr;
    int i;
    for (i = 1; i < LEN; i++)
    {
        if ((arr + i)->amount < *min)
        {
            *min = (arr + i)->amount;
            item = arr + i;
        }
    }
    return item;
}

float calculatePriceSum(Item *arr)
{
    float sum = 0;
    int i;

    for (i = 0; i < LEN; i++)
    {
        sum += (arr + i)->price;
    }
    return sum;
}

void printLess10(Item *arr)
{
    int i;
    for (i = 0; i < LEN; i++)
    {
        if ((arr + i)->amount <= 10)
        {
            printItem(*(arr + i));
        }
    }
}

void printItem(Item item)
{
    printf("Code: %d, Price: %.2f, Amount: %d\n", item.code, item.price, item.amount);
}

float *findMostExpensive(Item *arr)
{
    float *max = &(arr->price);
    float *currentPrice;
    Item item = *arr;

    int i;
    for (i = 1; i < LEN; i++)
    {
        currentPrice = &((arr + i)->price);
        if (*currentPrice > *max)
        {
            max = currentPrice;
            item = *(arr + i);
        }
    }

    printItem(item);
    return max;
}

void getItemsFromUser(Item *arr)
{
    int i;
    for (i = 0; i < LEN; i++)
    {
        printf("Item %d\n", i + 1);
        printf("code: ");
        scanf("%d", &(arr + i)->code);
        printf("price: ");
        scanf("%f", &(arr + i)->price);
        printf("amount: ");
        scanf("%d", &(arr + i)->amount);
        printf("\n");
    }
}
