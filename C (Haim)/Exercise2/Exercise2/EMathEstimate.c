// EMathEstimate.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>

//-----------------------------------------------------------------------------------
//									E Math Estimate
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
void main9()
{
	// Variable definition
	float fEpsilon;
	float fEMathEstimate = 0;
	float fAddTerm;
	float fNFactorial    = 1;
	int   n              = 1;

	// Code section
	printf("# ");
	scanf("%f", &fEpsilon);

	do {
		fAddTerm = 1.0 / fNFactorial;
		fEMathEstimate += fAddTerm;
		n++;
		fNFactorial *= n;
	} while (fAddTerm > fEpsilon);

	// Complete the formula
	fEMathEstimate++;

	// Print E estimate
	printf("%.5f", fEMathEstimate);
}