// PiEstimate.c

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>

//-----------------------------------------------------------------------------------
//									Pi Estimate
//								  ---------------
//
// General : This program calculate an estimate of PI. 
//
// Input : Null.
//
// Process : Loop and calculate an estimate of PI with the formula.
// 
// Output : Print estimate of PI.
//-----------------------------------------------------------------------------------
// Programer : Roey Apel
// Date : 17.09.24
//-----------------------------------------------------------------------------------
void main3()
{
	// Variable definition
	float fPiEstimate = 0;
	float fAddTerm    = 0;
	int   n           = 1;
	int   nSign       = 1;

	// Code section

	// Loops, Add terms in PI series till added term is 0.00001
	do{
		fAddTerm = 1.0 / n * nSign;
		fPiEstimate += fAddTerm;
		n += 2;
		nSign = nSign == 1 ? -1 : 1;
	} while (fabs(fAddTerm) > 0.00001);

	// Complete according to the formula.
	fPiEstimate *= 4.0;

	// Print PI estimate
	printf("Pi Estimate: %.5f", fPiEstimate);
}