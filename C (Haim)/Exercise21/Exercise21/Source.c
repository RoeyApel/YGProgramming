#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
void writeToFile(char* filename);

void main() {
	char filename[100];
	printf("path+filename: ");
	gets(filename);
	writeToFile(filename);

	FILE* pf = fopen(filename, "rb");

	if (!pf) {
		printf("error in file opening");
		exit(1);
	}

	int nums[10] = {0}, i = 1;
	fread(nums, sizeof(int), 1, pf);

	while (!feof(pf)) {
		fread(nums + i, sizeof(int), 1, pf);
		i++;
	}
	for (i = 0; i < sizeof(nums) / sizeof(int); i++)
	{
		printf("%d ", *(nums + i));
	}
	fclose(pf);
}

void writeToFile(char* filename) {
	FILE* pf = fopen(filename, "wb");

	if (!pf) {
		printf("error in file opening");
		exit(1);
	}

	int num;
	printf("num: ");
	scanf("%d", &num);

	while (num >= 0) {
		fwrite(&num, sizeof(num), 1, pf);
		printf("num: ");
		scanf("%d", &num);
	}
	fclose(pf);
}