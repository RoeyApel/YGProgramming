#include <stdio.h>

typedef struct
{
    int temperature;
    int humidityLevel;
} Weather;

typedef struct
{
    int day;
    int month;
    int year;
    Weather weather;
} Date;

typedef struct
{
    char name[10];
    Date dates[31];
} Month;

void getWeather(Weather *weather);
void printWeather(Weather *weather);
void printDate(Date *date);
void getDate(Date *date);
void getMonth(Month *month);
void printMonth(Month *month);

int main()
{
    Date d;
    getDate(&d);
    printDate(&d);
    return 0;
}

void getMonth(Month *month)
{
    printf("name: ");
    scanf("%s", month->dates);

    for (int i = 0; i < sizeof(month->dates) / sizeof(Date); i++)
    {
        getDate(&month->dates + i);
    }
}

void printMonth(Month *month)
{
    printf("name %s", month->name);

    for (int i = 0; i < sizeof(month->dates) / sizeof(Date); i++)
    {
        printDate(&month->dates + i);
    }
}

void getDate(Date *date)
{
    printf("day: ");
    scanf("%d", &date->day);

    printf("month: ");
    scanf("%d", &date->month);

    printf("year: ");
    scanf("%d", &date->year);

    getWeather(&date->weather);
}

void printDate(Date *date)
{
    printf("date: %d/%d/%d\n", date->day, date->month, date->year);

    printWeather(&date->weather);
}

void getWeather(Weather *weather)
{
    printf("temperature: ");
    scanf("%d", &weather->temperature);

    printf("humidity level: ");
    scanf("%d", &weather->humidityLevel);
}

void printWeather(Weather *weather)
{
    printf("--weather--\n");
    printf("temperature: %d, humidity level: %d%%", weather->temperature, weather->humidityLevel);
}