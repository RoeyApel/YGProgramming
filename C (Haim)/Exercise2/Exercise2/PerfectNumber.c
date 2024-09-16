// PerfectNumber.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

//-----------------------------------------------------------------------------------
//								Perfect Number
//								--------------
//
// General : This program Checks if a number is a "Perfect Number".
//
// Input : A number.
//
// Process : Calculate sum of factors of the num and check if equals the given number.
// 
// Output : Print if the number is a perfect number.
//-----------------------------------------------------------------------------------
// Programer : Roey Apel
// Date : 16.09.24
//-----------------------------------------------------------------------------------
void main() 
{
	// Variable definition
	int nNum;
	int nSum = 0;

	// Code section
	printf("Num: ");
	scanf("%d", &nNum);

	// Calculate sum of factors
	for (int i = 1; i < nNum - 1; i++)
	{
		if (nNum % i == 0) {
			nSum += i;
		}
	}

	// check if the number is a perfect number
	if (nSum == nNum) {
		printf("The Number Is A Prerfect Number!");
	}
	else
	{
		printf("The Number Is Not A Prerfect Number!");
	}
}