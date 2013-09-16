#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_SYMBOL_TABLE_SIZE 100
#define NUM_RERSERVED_WORDS 13

#define CONST 1
#define INT 2
#define PROC 3

char *instructions[] = {" ", "LIT", "OPR", "LOD", "STO", "CAL", "INC", "JMP", "JPC", "SIO"};

char * Reserved_Words[] = {"","const", "var", "procedure", "call", "begin", "}", "if", "then", "else", "while", "do", "read", "write"};



char * symbolNames[] = {"", "nulsym", "identsym", "numbersym", "plussym", "minussym", //5
                         "multsym", "slashsym", "oddsym", "eqlsym", "neqsym", "lessym", //11
                         "leqsym", "grtsym", "geqsym", "lparentsym", "rparentsym", "commasym", //17
                         "semicolonsym", "periodsym", "becomessym", "beginsym", "endsym", //22
                         "ifsym", "thensym", "whilesym", "dosym", "callsym", "constsym", //28
                         "intsym","procsym","writesym","readsym","elsesym" }; //33
typedef enum {
    nulsym = 1, identsym, numbersym, plussym, minussym,
    multsym,  slashsym, oddsym, eqsym, neqsym, lessym, leqsym,
    gtrsym, geqsym, lparentsym, rparentsym, commasym, semicolonsym,
    periodsym, becomessym, beginsym, endsym, ifsym, thensym,
    whilesym, dosym, callsym, constsym, varsym, procsym, writesym,
    readsym , elsesym } token_type;

char * tokenSymbolName[100];
char * currTN;
int cTI = 0;

void getToken();
void emit(int op, int l, int m);

void ErrorMessages(int errnum);

FILE *ifp;
FILE *input1;


typedef struct{
	int kind;
	char name[11];
	int val;
	int level;
	int addr;
} symbol;

typedef struct{
    int op;
    int l;
    int m;
} code;


symbol symbol_table[500];
code code_stack[500];
int sym_index=0;
int code_index=0;
int stack_pointer=0;


void program(FILE* input);
int Block(FILE* input, int token);
int const_dec(FILE* input);
int var_dec(FILE* input);
int Statement(FILE* input, int token);
int Condition(FILE* input, int token);
int Expression(FILE* input, int token);
int Term(FILE* input, int token);
int Factor(FILE* input, int token);
void error(int code);
void add_symbol(int k, char str[], int val);
void codegen(int op, int l, int m);
int find_index(char name[]);

FILE *printin;
FILE *printout;
FILE *printoutsym;
FILE *printsym;
