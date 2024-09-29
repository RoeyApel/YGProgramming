#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

void smart(int* sumptr, int* multptr, int* bigptr, int a, int b);
void sumAndDifference(int* a, int* b);
void sort3Nums(int* a, int* b, int* c);
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
	int a, b, sum, mul, bigest;
	printf("num1:");
	scanf("%d", &a);
	printf("num2:");
	scanf("%d", &b);

	smart(&sum, &mul, &bigest, a, b);

	printf("sum: %d, mul: %d, biggest: %d", sum, mul, bigest);

	sumAndDifference(&a, &b);
	printf("\n%d, %d", a, b);

	int a2, b2, c2;
	
	printf("\n\nnum1:");
	scanf("%d", &a2);
	printf("num2:");
	scanf("%d", &b2);
	printf("num3:");
	scanf("%d", &c2);

	sort3Nums(&a2, &b2, &c2);

	printf("a2 = %d, b2 = %d, c2 = %d", a2, b2, c2);
}
//-----------------------------------------------------------------------------
//									Sort3Nums
//									---------
//
// General    : The function sorts three integers in descending order by swapping
//              their values via pointers.
//
// Parameters :
//   a - Pointer to the first integer (Input/Output).
//   b - Pointer to the second integer (Input/Output).
//   c - Pointer to the third integer (Input/Output).
//
// Return Value : None (void). The sorted integers are stored back in the
//                original locations using the provided pointers.
//
//-----------------------------------------------------------------------------
void sort3Nums(int* a, int* b, int* c) {
	if (*a < *b) {
		swap(a, b);
	}
	if (*a < *c) {
		swap(a, c);
	}
	if (*b < *c) {
		swap(b, c);
	}
}

//-----------------------------------------------------------------------------
//									Swap
//									----
//
// General    : The function swaps two integers using pointer arithmetic.
//              It doesn't use a temporary variable.
//
// Parameters :
//   a - Pointer to the first integer (Input/Output).
//   b - Pointer to the second integer (Input/Output).
//
// Return Value : None (void). The integers are swapped in place using their
//                respective pointers.
//
void swap(int* a, int* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}

//-----------------------------------------------------------------------------
//								SumAndDifference
//								----------------
//
// General    : The function calculates the sum and difference of two integers.
//              It stores the sum in the first pointer and the difference in the
//              second pointer.
//
// Parameters :
//   a - Pointer to the first integer, which will hold the sum after execution (Input/Output).
//   b - Pointer to the second integer, which will hold the difference after execution (Input/Output).
//
// Return Value : None (void). The sum and difference are stored in the original
//                pointer locations.
//
//-----------------------------------------------------------------------------
void sumAndDifference(int* a, int* b) {
	int temp;
	temp = *a + *b;
	*b = *a - *b;
	*a = temp;
}

//-----------------------------------------------------------------------------
//										Smart
//										-----
//
// General    : The function calculates the sum, product, and the larger of two
//              integers. The results are stored in the respective pointer arguments.
//
// Parameters :
//   sumptr  - Pointer to an integer that will store the sum (Output).
//   multptr - Pointer to an integer that will store the product (Output).
//   bigptr  - Pointer to an integer that will store the larger of the two numbers (Output).
//   a       - First integer value (Input).
//   b       - Second integer value (Input).
//
// Return Value : None (void). The sum, product, and the larger number are
//                stored in the respective pointers provided.
//
//-----------------------------------------------------------------------------
void smart(int* sumptr, int* multptr, int* bigptr, int a, int b) {
	*sumptr = a + b;
	*multptr = a * b;
	*bigptr = a > b ? a : b;
}

