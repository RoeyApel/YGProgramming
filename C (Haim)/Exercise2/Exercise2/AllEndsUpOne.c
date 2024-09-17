// AllEndsUpOne.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

//-----------------------------------------------------------------------------------
//								All Ends Up One
//								---------------
//
// General : This program does Mathematical operations on a number till it becomes one. 
//
// Input : A number.
//
// Process : Does certain mathematical operations on a number till it becomes one.
// 
// Output : Print the number of the operations till we get one.
//-----------------------------------------------------------------------------------
// Programer : Roey Apel
// Date : 17.09.24
//-----------------------------------------------------------------------------------
void main()
{
	// Variable definition
	int nNum;
	int nCount = 0;

	// Code section
	printf("Num: ");
	scanf("%d", &nNum);

	// Loops till num equals one
	while (nNum != 1) {
		// Number is an even number
		if (nNum % 2 == 0) {
			nNum /= 2;
			nCount++;
		}
		// Number is an odd number
		else
		{
			nNum = (nNum * 3 + 1) / 2;
			nCount += 3;
		}
	}

	// Print number of operations
	printf("Num of operation: % d", nCount);
}