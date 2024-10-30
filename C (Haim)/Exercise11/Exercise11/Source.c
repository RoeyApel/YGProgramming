#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Functions declerations
void strReverse(char* str);
void swapCh(char* a, char* b);
void strDelete(char* str, int place, int len);
void strJump(char* str, char* place);
void strInsert(char* str1, int place, char* str2);
void strEncrypt(char* str);

//----------------------------------------------------------------------
//								Targilim
//								--------
// 
// General : Store many unrelated functions.
// 
// Input : None.
// 
// Process : Many different random things.
// 
// Output : None.
// 
//----------------------------------------------------------------------
// Programmer : Roey Apel
// Date : 28.09.24
//----------------------------------------------------------------------
void main() {
}
//-----------------------------------------------------------------------------
//								strEncrypt
//								----------
//
// General : This function encrypts a string by replacing spaces with '@' and
//           converting other characters to uppercase. Each non-space character
//           is moved forward in the string by one position.
//
// Parameters :
//		str - Pointer to the character string to be encrypted (Input/Output)
//
// Return Value : None (The function modifies the string in place).
//
//-----------------------------------------------------------------------------
void strEncrypt(char* str) {
	char ch;

	while (*str) {
		ch = (char)*str;
		if (ch == ' ') {
			*str = '@';
			str++;
		}
		else {
			ch = toupper(ch);
			strJump(str, str + 1);
			*(str + 1) = ch;
			str += 2;
		}
	}
}
//-----------------------------------------------------------------------------
//								strInsert
//								---------
//
// General : This function inserts a string (str2) into another string (str1)
//           at a specified position.
//
// Parameters :
//		str1  - Pointer to the main character string to be modified (Input/Output)
//		place - Integer representing the insertion position in str1 (Input)
//		str2  - Pointer to the string to be inserted into str1 (Input)
//
// Return Value : None (The function modifies str1 in place).
//
//-----------------------------------------------------------------------------
void strInsert(char* str1, int place, char* str2) {
	char* strTemp = str1 + place;
	char* str3[] = { 0 };
	strcpy(str3, strTemp);

	*(str1 + place) = '\0';
	strcat(str1, str2);
	strcat(str1, str3);
}

//-----------------------------------------------------------------------------
//							strJump
//							-------
//
// General : This function shifts characters in a string to create space for 
//           inserting a new character at a specified position.
//
// Parameters :
//		str   - Pointer to the original string (Input/Output)
//		place - Pointer to the position where space is needed (Input)
//
// Return Value : None (The function modifies the string in place).
//
//-----------------------------------------------------------------------------
void strJump(char* str, char* place) {
	int len = strlen(str);
	if ((str + len) == place) {
		*(str + len) = ' ';
		*(str + len + 1) = '\0';
	}
	int i;

	for (i = len; (str + i) != place; i--)
	{
		*(str + i + 1) = *(str + i);
	}
	*(str + i + 1) = *(str + i);
	*(str + i) = ' ';
	*(str + len + 1) = '\0';
}

//-----------------------------------------------------------------------------
//							strReverse
//							----------
//
// General : This function reverses a string in place.
//
// Parameters :
//		str - Pointer to the character string to be reversed (Input/Output)
//
// Return Value : None (The function modifies the string in place).
//
//-----------------------------------------------------------------------------
void strReverse(char* str) {
	int i, len = strlen(str);
	for (i = 0; i < len / 2; i++)
	{
		swapCh(str + i, str + len - 1 - i);
	}
}

//-----------------------------------------------------------------------------
//								strDelete
//							    ---------
//
// General : This function deletes a substring from a string starting at a 
//           specified position and extending for a given length.
//
// Parameters :
// str   - Pointer to the character string to be modified (Input/Output)
// place - Integer indicating the starting position of deletion (Input)
// len   - Integer indicating the number of characters to delete (Input)
//
// Return Value : None (The function modifies the string in place).
//
//-----------------------------------------------------------------------------
void strDelete(char* str, int place, int len) {
	if (len == 0) {
		return;
	}
	int totalLen = strlen(str);

	int i;
	for (i = place + len; i < totalLen; i++)
	{
		*(str + i - len) = *(str + i);
	}
	*(str + totalLen - len) = 0;
}

//-----------------------------------------------------------------------------
//								swapCh
//								------
//
// General : This function swaps the values of two characters.
//
// Parameters :
//		a - Pointer to the first character (Input/Output)
//		b - Pointer to the second character (Input/Output)
//
// Return Value : None (The function modifies the characters in place).
//
//-----------------------------------------------------------------------------
void swapCh(char* a, char* b) {
	*a += *b;
	*b = *a - *b;
	*a -= *b;
}