#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STACK_HEIGHT 2000
#define MAX_CODE_LENGTH 500
#define MAX_LEXI_LEVELS 3

const char* ALL_OPERATIONS[] = {"","LIT", "OPR", "LOD", "STO", "CAL", "INC", "JMP", "JPC", "SIO","SIO"};

void printAssemblyInt();
void printExecution(int line);
void printStackVal();
void printVMInt();
void executeInstruction();
void StackOPR();
void StackIO_Operation();
int base(int, int base);

struct instruction{
    int op;
    int  l;
    int  m;
};
