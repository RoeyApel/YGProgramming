// BiggestThree.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

//-----------------------------------------------------------------------------------
//									Biggest Three
//								   ---------------
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
void main5()
{
	// Variable definition
	int nLastLastNum = 0;
	int nLastNum     = 0;
	int nCurrentNum  = 0;
	int nSum         = 0;
	int nMaxSum      = 0;

	// Code section

	do {
		printf("# ");
		scanf("%d", &nCurrentNum);

		nSum = nCurrentNum + nLastNum + nLastLastNum;
		if (nSum > nMaxSum) {
			nMaxSum = nSum;
		}
		nLastLastNum = nLastNum;
		nLastNum = nCurrentNum;

	} while (nCurrentNum >= 0);


	// Print Biggest sum of three numbers
	printf("%d", nMaxSum);
}