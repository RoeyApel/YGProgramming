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

int main()
{
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
    int i;
    Patient *patient;
    for (i = 0; i < numOfPatients; i++)
    {
        patient = patients + i;
        for (int j = 0; j < patient->appointments; j++)
        {
            for (int k = 0; k < sizeof(patient->docorsIds) / sizeof(patient->docorsIds[0]); k++)
            {
                *(doctorsScore + k) += 1;
            }
        }
    }
}