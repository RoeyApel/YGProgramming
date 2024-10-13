#include <stdio.h>

typedef struct
{
    int id;
    char name[50];
    char specialization[50];
    int veterancy;
} Doctor;

typedef struct
{
    int id;
    char name[50];
    int docorsIds[1000];
    int appointments;
} Patient;

Doctor doctors[50];
Patient patients[3000];

int highestVeterancy();
void printBestDoctors();

int main()
{
    printBestDoctors();
    return 0;
}

int highestVeterancy()
{
    int size = sizeof(doctors) / sizeof(Doctor);
    int i, max = doctors->veterancy;
    for (i = 1; i < size; i++)
    {
        if ((doctors + i)->veterancy > max)
        {
            max = (doctors + i)->veterancy;
        }
    }
    return max;
}

void printBestDoctors()
{
    int doctorsScore[50] = {0};
    int numOfPatients = sizeof(patients) / sizeof(Patient);
    int i, docId;
    Patient *patient;
    for (i = 0; i < numOfPatients; i++)
    {
        patient = patients + i;
        for (int j = 0; j < patient->appointments; j++)
        {
            docId = *(patient->docorsIds + j);
            *(doctorsScore + docId) += 1;
        }
    }

    int *max = doctorsScore;
    int maxId = 0;
    for (i = 1; i < sizeof(doctorsScore) / sizeof(*doctorsScore); i++)
    {
        if (*max < *(doctorsScore + i))
        {
            max = doctorsScore + i;
            maxId = i;
        }
    }

    for (i = 0; i < sizeof(doctors) / sizeof(*doctors); i++)
    {
        if ((doctors + i)->id == maxId)
        {
            printf("Id: %d, name: %s, sepcia: %s, verty: %d");
        }
    }
}