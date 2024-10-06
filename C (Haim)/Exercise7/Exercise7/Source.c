#define _CRT_SECURE_NO_WARNINGS
#define N 10

#include <stdio.h>

void shiftToStart(int* arr, int size, int shifts);
void swap(int* a, int* b);

//----------------------------------------------------------------------
//								Targilim
//								--------
// 
// General : Store many unrelated functions.
// 
// Input : Null .
// 
// Process : Many different random things.
// 
// Output : Null.
// 
//----------------------------------------------------------------------
// Programmer : Roey Apel
// Date : 28.09.24
//----------------------------------------------------------------------
void main() {
	int arr[N] = { 1,2,3,4,5,6,7,8,9,10 };
	shiftToStart(arr, N, 5);

	for (int i = 0; i < N; i++)
	{
		printf("%d ", *(arr + i));
	}
}
//-----------------------------------------------------------------------------
//							ShiftToStart
//							------------
//
// General : This function shifts the first 'shifts' number of elements in 
//           the array to the end while moving the remaining elements to 
//           the front of the array.
//
// Parameters :
// arr   - Pointer to the integer array (Input/Output)
// size  - The total number of elements in the array (Input)
// shifts - The number of elements to shift from the start to the end (Input)
//
// Return Value : None (The function modifies the array in place).
//
//-----------------------------------------------------------------------------
void shiftToStart(int* arr, int size, int shifts) {
	int i;
	int sarr[100] = { 0 };

	for (i = 0; i < shifts; i++)
	{
		*(sarr + i) = *(arr + i);
	}

	for (i = shifts; i < size; i++)
	{
		*(arr + i - shifts) = *(arr + i);
	}

	int k;
	for (k = 0, i = size - shifts; i < size; k++, i++)
	{
		*(arr + i) = *(sarr + k);
	}
}
//-----------------------------------------------------------------------------
//									Swap
//									----
//
// General : This function swaps the values of two integers.
//
// Parameters :
// a - Pointer to the first integer (Input/Output)
// b - Pointer to the second integer (Input/Output)
//
// Return Value : None (The function modifies the values of the integers).
//
// Note: This function is defined but not used in the current code.
//
//-----------------------------------------------------------------------------
void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}