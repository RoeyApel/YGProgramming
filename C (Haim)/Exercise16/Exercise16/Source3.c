#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

#define DAYS 7
#define SHOPS 5

typedef struct {
	int day;
	int shopId;
	float cost;
}Order;

void mainx() {

}

int ordersReport(Order* orders, int size) {
	int i, bestShopCode;
	float maxByDay = 0, maxByShop = 0;
	float earningsByDays[DAYS] = { 0 };
	float earningsByShops[SHOPS] = { 0 };

	for ( i = 0; i < size; i++)
	{
		*(earningsByDays + (orders + i)->day) += (orders + i)->cost;
		*(earningsByShops + (orders + i)->shopId) += (orders + i)->cost;
		if (*(earningsByDays + (orders + i)->day) > maxByDay)
		{
			maxByDay = *(earningsByDays + (orders + i)->day);
		}
		if (*(earningsByShops + (orders + i)->shopId) > maxByShop)
		{
			maxByShop = *(earningsByShops + (orders + i)->shopId);
			bestShopCode = i;
		}
	}

	for ( i = 0; i < DAYS; i++)
	{
		if (maxByDay == *(earningsByDays + i))
		{
			printf("%d, ", i + 1);
		}
	}

	return bestShopCode;
}