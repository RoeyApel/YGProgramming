#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
	float avg;
	int grades[3];
}grade_type;

typedef struct {
	int id;
	char name[10];
	grade_type grades;
}student_type, * student_ptr;

void main() {
	student_type students[3] = {
		{1, "Alice", {85.0, {90, 80, 85}}},       
		{2, "Bob", {88.5, {85, 90, 92}}},        
		{3, "Charlie", {78.5, {70, 75, 82}}}     
	};

	int id;
	printf("id: ");
	scanf("%d", &id);
	student_type temp;
	temp.id = id;

	qsort(students, 3, sizeof(students[0]), cmpById);


}

int cmpById(const void* a, const void* b) {
	student_ptr sa = (student_ptr)a;
	student_ptr sb = (student_ptr)b;

	return sa->id - sb->id;
}
int cmpByName(const void* a, const void* b) {
	student_ptr sa = (student_ptr)a;
	student_ptr sb = (student_ptr)b;

	return strcmp(sa->name, sb->name);
}
int cmpByAvg(const void* a, const void* b) {
	student_ptr sa = (student_ptr)a;
	student_ptr sb = (student_ptr)b;

	if (sa->grades.avg = sb->grades.avg) {
		return 0;
	}
	if (sa->grades.avg - sb->grades.avg > 0) {
		return 1;
	}
	return -1;
	// return (sa->grades.avg >= sb->grades.avg) - (sa->grades.avg <= sb->grades.avg);
}