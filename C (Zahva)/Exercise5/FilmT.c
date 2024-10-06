#include <stdio.h>

#define N 10

typedef struct
{
    long director;
    int budget, year;
} Film;

int funFilm(Film *films, int director);

int main()
{
    Film films[N] = {{0, 26000000, 2011}};
    printf("\n%d", funFilm(films, 0));
    return 0;
}

int funFilm(Film *films, int director)
{
    int countFilms = 0;
    int budgetSum = 0;
    int printed = 0;
    int i;

    for (i = 0; i < N; i++)
    {
        if ((films + i)->director == director)
        {
            if ((films + i)->budget > 25000000 && (films + i)->year > 2010)
            {
                printed = 1;
                printf("Director: %ld, Budget: %d, Year: %d\n",
                       (films + i)->director, (films + i)->budget, (films + i)->year);
            }
            countFilms++;
            budgetSum += (films + i)->budget;
        }
    }
    if (!printed)
    {
        printf("Didn't Print\n");
    }
    return (float)budgetSum / countFilms;
}