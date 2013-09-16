#include <stdio.h>
#include <string.h>

#define numSymbols 33

char* symbols[numSymbols+1] = {"", "null", "","","+","-","*","/","odd","=","<>","<","<=",">",
">=","(",")",",",";",".",":=","begin","end","if","then","while","do","call","const","int","procedure",
"out","in","else"};

int isInvisibleChar(char c);
int isSpecialSymbol(char c);
int isAlphaLetter(char c);
int isNumber(char c);
int IndexLocator(char str[], char* array[]);
int ContextAnalyzer(char a, char b);
int SymbolLocator(char str[], char table[][12], int index);
int LetterLocator(char str[], int index);


FILE* ofp_parsecodegen;
FILE* output;
FILE* input;
FILE* lexemelist;
