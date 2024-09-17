// OneSequence.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

//-----------------------------------------------------------------------------------
//									One Sequence
//								   --------------
//
// General : This program take a binary sequence and find the longest ones sequence.
//
// Input : Series of binary number (0,1)
//
// Process : Inc var in start of sequence till stop and store in max if biger than max.
// 
// Output : Print longest sequence of ones.
//-----------------------------------------------------------------------------------
// Programer : Roey Apel
// Date : 17.09.24
//-----------------------------------------------------------------------------------
void main4()
{
	// Variable definition
	int nNum;
	int nMaxCount = 0;
	int nCount    = 0;

	// Code section

	do {
		printf("# ");
		scanf("%d", &nNum);
		if (nNum == 1) {
			nCount++;
		}
		else
		{
			nMaxCount = nCount > nMaxCount ? nCount : nMaxCount;
			nCount = 0;
		}
	} while (nNum == 1 || nNum == 0);
	

	// Print longest sequence of ones
	printf("%d", nMaxCount);
}