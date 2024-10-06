#include <stdio.h>

#define N 30

typedef struct
{
    int grade;
    float money;
} Student;

int main()
{
    Student students[N] = {0};
    int count = 0, sum = 0;
    for (int i = 0; i < N; i++)
    {
        printf("Grade: ");
        scanf("%d", &(students + i)->grade);
        printf("Money: ");
        scanf("%d", &(students + i)->money);
        if ((students + i)->grade)
        {
            sum += (students + i)->grade;
            count++;
        }
    }
    float average = (float)sum / count;

    for (int i = 0; i < N; i++)
    {
        if ((students + i)->grade < 56 && (students + i)->grade)
        {
            printf("Student%d: Grade: %d, Money: %.2f", i + 1, (students + i)->grade, (students + i)->money);
        }
    }

    for (int i = 0; i < N; i++)
    {
        if ((students + i)->grade >= 95)
        {
            (students + i)->money *= 0.65;
        }
    }

    return 0;
}