#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define G 6
typedef struct {
	float avg;
	int grades[G];
}grades_type;

typedef struct {
	long id;
	char name[30];
	grades_type grades;
}student_type, * student_ptr;

int* buildNArr(int* size);
void printArr(int* arr, int size);

void main() {
	srand(time(NULL));
	int size;
	student_ptr students;
	students = buildDClass(&size);

	qsort(students, size, sizeof(students[0]), cmpByStudentId);

	removeStudent(&students, &size, 16);

	free(students);
}
void removeStudent(student_ptr* students, int* size, long id) {
	student_type temp = { 0 };
	temp.id = id;
	student_ptr s = bsearch(&temp, students, size, sizeof(students[0]), cmpByStudentId);

	if (!s) {
		puts("Not Found");
		return;
	}

	int i;
	for (i = (s - *students) + 1; i < *size; i++)
	{
		*(*students + i + 1) = *(*students + i);
	}
	*students = (student_ptr)realloc(*students, --(*size) * sizeof(student_type));
}

void printArr(int* arr, int size) {
	int i;
	for (i = 0; i < size; i++)
	{
		printf("%d ", *(arr + i));
	}
}

int cmpByStudentId(void* a, void* b) {
	student_ptr sa = (student_ptr)a;
	student_ptr sb = (student_ptr)b;

	return (int)(sa->id - sb->id);
}
student_type* buildDClass(int* size) {
	int i;
	student_type* student = { 0 };
	student_type* students = NULL;

	printf("id ");
	scanf("%d", &student->id);

	printf("name ");
	scanf("%s", &student->name);

	int min = 0, max = 100;
	for (i = 0; i < G; i++)
	{
		*(student->grades.grades + i) = rand() % (max - min) + min;
	}

	while (student->id != 0) {
		*size += 1;
		students = (int*)realloc(students, *size * sizeof(int));
		*(students + *size - 1) = *student;

		printf("id ");
		scanf("%d", &student->id);

		printf("name ");
		scanf("%s", &student->name);

		for (i = 0; i < G; i++)
		{
			*(student->grades.grades + i) = rand() % (max - min) + min;
		}
	}
	return students;
}
// ג. מקבל כתובת מערך ת פויינתר לדןזק שלו וid עליה למצוא אותו במערך ולהסיר אותו

int* buildNArr(int* size) {
	int num, * arr, capacity = 5;
	*size = 0;
	arr = (int*)malloc(capacity * sizeof(int));

	printf("num:");
	scanf("%d", &num);

	while (num < 10 && num > 0) {
		if (*size >= capacity) {
			capacity *= 2;
			arr = (int*)realloc(arr, capacity * sizeof(int));
		}

		*(arr + *size) = num;
		*size += 1;
		printf("num:");
		scanf("%d", &num);
	}

	arr = (int*)realloc(arr, *size * sizeof(int));
	return arr;
}