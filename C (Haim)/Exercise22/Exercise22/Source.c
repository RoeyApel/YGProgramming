#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

typedef struct {
	char lastName[25];
	char firstName[25];
	int grade;
	char address[50];
	char phone[11];
	int status;

}Talmid, * PTalmid;

typedef struct {
	char lastName[25];
	char firstName[25];
	char address[50];
	char phone[11];

}Boger, * PBoger;

void addY(char* filenameS, char* filenameM);
void transferYB(char* filenameS, char* filenameB);

void main() {

}


void addY(char* filenameS, char* filenameM) {
	FILE* pfileS = fopen(filenameS, "a+");
	FILE* pfileM = fopen(filenameM, "r");

	Talmid t;

	while (fread(&t, sizeof(Talmid), 1, pfileM))
	{
		fwrite(&t, sizeof(Talmid), 1, pfileS);
	}
	fclose(pfileM);
	fclose(pfileS);
}

void transferYB(char* filenameS, char* filenameB) {
	FILE* pfileS = fopen(filenameS, "r");
	FILE* pfileB = fopen(filenameB, "w");

	Talmid t;
	Boger b = { {0} };

	while (fread(&t, sizeof(Talmid), 1, pfileS))
	{
		if (t.grade == 12) {
			t.status = 0;
			strcpy(b.firstName, t.firstName);
			strcpy(b.address, t.address);
			strcpy(b.lastName, t.lastName);
			strcpy(b.phone, t.phone);
			fwrite(&b, sizeof(Boger), 1, pfileB);
		}
	}
	fclose(pfileB);
	fclose(pfileS);
}