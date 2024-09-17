//BaseConversion.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>

//-----------------------------------------------------------------------------------
//									Base Conversion
//								    ---------------
//
// General : 
// 
// Input : 
//
// Process : 
// 
// Output : 
//-----------------------------------------------------------------------------------
// Programer : Roey Apel
// Date : 17.09.24
//-----------------------------------------------------------------------------------
void main6()
{
	// Variable definition
	long long lBinaryNum;
	int       nNumBaseTen = 0;
	int		  n           = 0;

	// Code section
	printf("# ");
	scanf("%lld", &lBinaryNum);

	while (lBinaryNum != 0) {
		nNumBaseTen += (lBinaryNum % 10) * pow(2, n);
		n++;
		lBinaryNum /= 10;
	}

	// Print number in base ten
	printf("%d", nNumBaseTen);
}