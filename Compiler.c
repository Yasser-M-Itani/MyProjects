#include "lexicalanalyzer.h"
#include "parser_codegen.h"
#include "VM.h"

#define CODE_SIZE 100

int counter = 0;

code codeStack[MAX_SYMBOL_TABLE_SIZE];

int main(){

    FILE* ofp_parsecodegen = fopen("lexlist.txt", "w");

    FILE* input1 = fopen("input.in", "r");
    FILE* output = fopen("output.txt", "w");

    char x;

    fprintf(output, "Source Program:\n");
    printf("Source Program:\n");
    fscanf(input1, "%c", &x);

    while(!feof(input1)){

        fprintf(output, "%c", x);
        printf("%c", x);
        fscanf(input1, "%c", &x);
    }

    fclose(input1);

    FILE* input = fopen("input.in", "r");
    FILE* lexemelist = fopen("lexemelist.txt", "w");

    fprintf(output, "\n\nLexeme Table:\n");
    fprintf(output, "Lexeme\t\tToken Type\n");

    printf("\n\n");

    char declared_symbols[1000][12];
    char cToken[12];
    char pChar;
    char cChar;

    int index;
    int comment = 0;
    int symbolNum = 0;
    int divind = 0;
    int SymbolIndex = 0;

    fscanf(input, "%c", &pChar);

    cToken[0] = pChar;

    cToken[1] = '\0';

    index = 1;

    while(!feof(input)){
        fscanf(input, "%c", &cChar);

        if(comment == 1){

            cToken[0] = cChar;

            cToken[1] = '\0';

            index = 1;

            pChar = cChar;

            comment = 0;

            continue;
        }

        if(comment == 2){

            if(pChar =='*' && cChar == '/')
                comment = 1;

            pChar = cChar;

            continue;
        }

        if(pChar == '/' && cChar == '*'){

            comment = 2;

            pChar = cChar;

            continue;
        }

        if(ContextAnalyzer(pChar, cChar)){

            if(isInvisibleChar(pChar)){

                cToken[0] = cChar;

                cToken[1] = '\0';

                index = 1;

                pChar = cChar;

                continue;
            }

            symbolNum = IndexLocator(cToken, symbols);

            if(symbolNum == -1){

                if(SymbolLocator(cToken, declared_symbols, SymbolIndex))

                    symbolNum = 2;
            }

            if(symbolNum == 28 || symbolNum == 29 || symbolNum == 30)

                divind = 1;

            if(pChar ==';')

                divind = 0;

            if(symbolNum == -1 && isAlphaLetter(cToken[0]) && divind){

                if(index <= 11){

                    symbolNum = 2;

                    strcpy(declared_symbols[SymbolIndex], cToken);

                    SymbolIndex++;
                }
                else {

                    fprintf(output, "%s\t\tIDENTIFIER TOO LONG!\n", cToken);
                    fprintf(ofp_parsecodegen, "%s\t\tIDENTIFIER TOO LONG!\n", cToken);

                    symbolNum = 0;
                }
            }

            else if(symbolNum == -1 && !isAlphaLetter(cToken[0]) && LetterLocator(cToken, index) && divind){

                fprintf(output, "%s\t\tINVALID VARIABLE NAME\n", cToken);
                fprintf(ofp_parsecodegen, "%s\t\tINVALID VARIABLE NAME\n", cToken);

                symbolNum = 0;
            }

            else if(symbolNum == -1 && isNumber(cToken[0])){

                if(index <= 5){

                    symbolNum = 3;
                }

                else {

                    fprintf(output, "%s\t\tNUMBER TOO LONG!\n", cToken);
                    fprintf(ofp_parsecodegen, "%s\t\tNUMBER TOO LONG!\n", cToken);

                    symbolNum = 0;
                }
            }

            if(symbolNum == -1){

                fprintf(output, "%s\t\tUNKNOWN TOKEN ERROR\n", cToken);
                fprintf(ofp_parsecodegen, "%s\t\tUNKNOWN TOKEN ERROR\n", cToken);
            }

            else if(symbolNum == 0);

            else {

                fprintf(output, "%s\t\t%d\n", cToken, symbolNum);
                fprintf(ofp_parsecodegen, "%s %d\n", cToken, symbolNum);

                if(symbolNum == 2 || symbolNum == 3){

                    fprintf(lexemelist, "%d %s ", symbolNum, cToken);

                    printf("%d %s ", symbolNum, cToken);
                }

                else {

                    fprintf(lexemelist, "%d ", symbolNum);

                    printf("%d ", symbolNum);
                }
            }

            cToken[0] = cChar;

            cToken[1] = '\0';

            index = 1;

            pChar = cChar;

            continue;
        }

        else{

            if(isInvisibleChar(cChar)){

                pChar = cChar;

                continue;
            }

            if(cChar == '/'){

                symbolNum = IndexLocator(cToken, symbols);

                if(symbolNum == -1){

                    fprintf(output, "%s\t\tUNKNOWN TOKEN ERROR\n", cToken);
                    fprintf(ofp_parsecodegen, "%s\t\tUNKNOWN TOKEN ERROR\n", cToken);

                }

                else {

                    fprintf(output, "%s\t\t%d\n", cToken, symbolNum);
                    fprintf(ofp_parsecodegen, "%s %d\n", cToken, symbolNum);

                    if(symbolNum == 2 || symbolNum == 3){

                        fprintf(lexemelist, "%d %s ", symbolNum, cToken);

                        printf("%d %s ", symbolNum, cToken);
                    }

                    else

                        fprintf(lexemelist, "%d ", symbolNum);
                }

                cToken[0] = cChar;

                cToken[1] = '\0';

                index = 1;

                pChar = cChar;

                continue;
            }

            cToken[index] = cChar;

            cToken[index+1] = '\0';

            index++;

            pChar = cChar;
        }

    }

    cToken[index-1] = '\0';

    symbolNum = IndexLocator(cToken, symbols);

    if(strcmp(cToken, "") == 0);

    else if(symbolNum == -1){

        fprintf(output, "%s\t\t%UNKNOWN TOKEN ERROR\n", cToken);
        fprintf(ofp_parsecodegen, "%s\t\t%UNKNOWN TOKEN ERROR\n", cToken);
    }
    else {

        fprintf(output, "%s\t\t%d\n", cToken, symbolNum);
        fprintf(ofp_parsecodegen, "%s %d\n", cToken, symbolNum);

        if(symbolNum == 2 || symbolNum == 3){

            fprintf(lexemelist, "%d %s ", symbolNum, cToken);

            printf("%d %s ", symbolNum, cToken);
        }

        else{
            fprintf(lexemelist, "%d ", symbolNum);

            printf("%d ", symbolNum);
        }
    }

    fclose(input1);
    fclose(input);
    fclose(output);
    fclose(ofp_parsecodegen);
    fclose(lexemelist);

    printf("\n\nLexical Analysis Complete:\t ./compile\n");
    int c;
    printf("Press 1 for Code Generation\t./compile continued else press 2 to exit\n");
    scanf("%d",&c);
    if(c == 2){
        return;
    }

    char str1[20], str2[20];
    input = fopen("lexemelist.txt","r");

    program(input);
    printf("No errors, program is syntactically correct.\n");
    fclose(input);

    output = fopen("VMinput.txt", "w");
    int i;
    for(i=0; i<code_index; i++){
        fprintf(output, "%d %d %d\n", code_stack[i].op, code_stack[i].l, code_stack[i].m);

        printf("%d %d %d\n", code_stack[i].op, code_stack[i].l, code_stack[i].m);
    }
    fclose(output);

    int cs;
    printf("\nPress 1 to Run VM \t./compile –v or E else press 2 to exit\n");
    scanf("%d",&cs);
    if(cs == 2){
        return;
    }

    stack[1] = 0;
    stack[2] = 0;
    stack[3] = 0;

    ifpVM = fopen("VMinput.txt","r");

    ofpVM = fopen("VMoutput.txt","w");

    int opcode, L, M;
    int is = 0;
    while (fscanf(ifpVM,"%d", &opcode) != EOF){

        fscanf(ifpVM, "%d", &L);
        fscanf(ifpVM, "%d", &M);

        codes[is].op = opcode;
        codes[is].l = L;
        codes[is].m = M;
        is++;
    }

    ics = is;

    printAssemblyInt();

    printf("\n\n");

    printVMInt();

    fclose(ifpVM);

    printf("Compile Ran ./compile –l –a –v\n\n");

    printsym = fopen("lexemelist.txt","r");
    printoutsym = fopen("symbols.txt","w");

    int digscan;
    char charscan[10];

    int restruct = 0;

    while((fscanf(printsym,"%s",&charscan))!= EOF){

            restruct = (int) strtol(charscan, (char **)NULL, 10);

            if (restruct != 0){
                printf("%s ",symbolNames[restruct]);
                fprintf(printoutsym,"%s ",symbolNames[restruct]);
            }
            else{
                printf("%s ",charscan);
                fprintf(printoutsym,"%s ",charscan);
            }
    }

    return 0;
}

int isInvisibleChar(char c){
    if(c ==' ' || c =='\n' || c =='\t' || c =='\r')
        return 1;
    else
        return 0;
}

int isSpecialSymbol(char c){
    if(c =='+' || c =='-' || c =='*' || c =='/')
        return 1;
    else if(c =='(' || c ==')' || c =='=' || c ==',')
        return 1;
    else if(c =='.' || c =='<' || c =='>' || c ==';' || c ==':')
        return 1;
    else
        return 0;
}

int isAlphaLetter(char c){
    if(c =='a' || c =='b' || c =='c' || c =='d' || c =='e' || c =='f' || c =='g' || c =='h' || c =='i' || c =='j' || c =='k' || c =='l' ||
        c =='m' || c =='n' || c =='o' || c =='p' || c =='q' || c =='r' || c =='s' || c =='t' || c =='u' || c =='v' || c =='w' || c =='x' ||
        c =='y' || c =='z' || c =='A' || c =='B' || c =='C' || c =='D' || c =='E' || c =='F' || c =='G' || c =='H' || c =='I' || c =='J' ||
        c =='K' || c =='L' || c =='M' || c =='N' || c =='O' || c =='P' || c =='Q' || c =='R' || c =='S' || c =='T' || c =='U' || c =='V' ||
        c =='W' || c =='X' || c =='Y' || c =='Z')
        return 1;

    else
        return 0;
}

int isNumber(char c){

    if(c =='1' || c =='2' || c =='3' || c =='4' || c =='5' || c =='6' || c =='7' || c =='8' || c =='9' || c =='0')
        return 1;

    else
        return 0;
}

int IndexLocator(char str[], char* array[]){

    int i;

    for(i = 1; i <= numSymbols; i++){

        if(strcmp(str, array[i])==0)
            return i;
    }
    return -1;
}

int ContextAnalyzer(char a, char b){

    if(isSpecialSymbol(a) && !isSpecialSymbol(b))
        return 1;

    else if(isSpecialSymbol(b) && !isSpecialSymbol(a))
        return 1;

    else if(isInvisibleChar(a) && !isInvisibleChar(b))
        return 1;

    else if(isInvisibleChar(b) && !isInvisibleChar(a))
        return 1;

    else if(a =='(' || a ==')' || b =='(' || b ==')')
        return 1;

    else
        return 0;
}

int SymbolLocator(char str[], char table[][12], int index){

    int i;

    for(i = 0; i < index; i++){

        if(strcmp(str, table[i])==0)
            return 1;
    }
    return 0;
}

int LetterLocator(char str[], int index){

    int i;

    for(i = 0; i < index; i++){

        if(isAlphaLetter(str[i]))
            return 1;
    }
    return 0;
}

void program(FILE* input){

    int token;

    fscanf(input, "%d", &token);

    codegen(6, 0, 3);

    stack_pointer += 3;

    token = Block(input, token);

    if(token != 19) error(9);

    codegen(2, 0, 0);

}

int Block(FILE* input, int token){

    if(token == 28)
        token = const_dec(input);
    if(token == 29)
        token = var_dec(input);

    token = Statement(input, token);

    return token;
}

int const_dec(FILE* input){

    int token;
    char string[20];
    int number;

    do{
        fscanf(input, "%d", &token);

        if(token == 2) fscanf(input, "%s", string);
        else error(4);

        fscanf(input, "%d", &token);

        if(token != 9) error(3);

        fscanf(input, "%d", &token);

        if(token == 3) fscanf(input, "%d", &number);
        else error(2);

        add_symbol(1, string, number);
        fscanf(input, "%d", &token);
    }while(token == 17);

    if(token != 18) error(5);

    fscanf(input, "%d", &token);

    return token;
}

int var_dec(FILE* input){

    int token;
    char string[20];

    do{
        fscanf(input, "%d", &token);

        if(token == 2) fscanf(input, "%s", string);
        else error(4);

        add_symbol(2, string, 0);
        fscanf(input, "%d", &token);

    }while(token == 17);

    if(token != 18) error(5);

    fscanf(input, "%d", &token);

    return token;
}

int Statement(FILE* input, int token){

    if(token == 2){

        char string[20];

        fscanf(input, "%s", string);

        fscanf(input, "%d", &token);

        if(token != 20) error(3);
        if(symbol_table[find_index(string)].kind == 1) error(12);

        fscanf(input, "%d", &token);

        token = Expression(input, token);

        codegen(4, 0, symbol_table[find_index(string)].addr);
    }

    else if(token == 21){
        do{
            fscanf(input, "%d", &token);

            token=Statement(input, token);

        }while(token == 18);

        if(token != 22) error(17);

        fscanf(input, "%d", &token);
    }
    else if(token == 23){

        fscanf(input, "%d", &token);

        token = Condition(input, token);

        if(token != 24) error(16);

        fscanf(input, "%d", &token);

        int ctemp = code_index;

        codegen(8, 0, 0);

        token = Statement(input, token);

        code_stack[ctemp].m = code_index;
    }
    else if(token==25){

        int tempindex = code_index;

        fscanf(input, "%d", &token);

        token = Condition(input, token);

        int tempindex2 = code_index;

        codegen(8, 0, 0);

        if(token!=26) error(18);

        fscanf(input, "%d", &token);

        token = Statement(input, token);

        codegen(7, 0, tempindex);

        code_stack[tempindex2].m = code_index;
    }

    return token;
}

int Condition(FILE* input, int token){
    if(token == 8){

        fscanf(input, "%d", &token);

        token = Expression(input, token);

        codegen(2, 0, 6);
    }
    else{

        token = Expression(input, token);

        if(token < 9 || token > 14) error(20);

        int relop=token;

        fscanf(input, "%d", &token);

        token = Expression(input, token);

        if(relop == 9)
            codegen(2, 0, 8);
        else if (relop == 10)
            codegen(2, 0, 9);
        else if (relop == 11)
            codegen(2, 0, 10);
        else if (relop == 12)
            codegen(2, 0, 11);
        else if (relop == 13)
            codegen(2, 0, 12);
        else if (relop == 14)
            codegen(2, 0, 13);
    }

    return token;
}

int Expression(FILE* input, int token){

    int addop;

    if(token == 4 || token == 5){

        addop = token;

        fscanf(input, "%d", &token);

        token = Term(input, token);

        if(addop == 5)
            codegen(2, 0, 1);
    }

    else token = Term(input, token);

    while(token == 4 || token == 5){

        addop = token;

        fscanf(input, "%d", &token);

        token = Term(input, token);

        if(addop == 4)
            codegen(2, 0, 2);
        else
            codegen(2, 0, 3);
    }

    return token;
}

int Term(FILE* input, int token){

    int mulop;

    token = Factor(input, token);

    while(token == 6 || token == 7){

        mulop = token;

        fscanf(input, "%d", &token);

        token = Factor(input, token);

        if(mulop == 6)
            codegen(2, 0, 4);
        else
            codegen(2, 0, 5);
    }
    return token;
}

int Factor(FILE* input, int token){

    if(token == 2){

        char string[20];

        fscanf(input, "%s", string);

        codegen(3, 0, symbol_table[find_index(string)].addr);

        fscanf(input, "%d", &token);
    }
    else if(token == 3){

        int number;

        fscanf(input, "%d", &number);

        codegen(1, 0, number);

        fscanf(input, "%d", &token);
    }
    else if(token == 15){

        fscanf(input, "%d", &token);

        token=Expression(input, token);

        if(token != 16)
            error(22);

        fscanf(input, "%d", &token);
    }

    else
        error(23);

    return token;
}

void error(int code){

    if(code == 1)
        printf("Error #%d: Use of = instead of :=\n", code);

    else if(code == 2)
        printf("Error #%d: = must be followed by a number\n", code);

    else if(code == 3)
        printf("Error #%d: Identifier must be followed by =\n", code);

    else if(code == 4)
        printf("Error #%d: const, int, procedure must be followed by identifier\n", code);

    else if(code == 5)
        printf("Error #%d: Semicolon or comma missing\n", code);

    else if(code == 6)
        printf("Error #%d: Incorrect symbol after procedure declaration\n", code);

    else if(code == 7)
        printf("Error #%d: Statement expected\n", code);

    else if(code == 8)
        printf("Error #%d: Incorrect symbol after Statement part in Block\n", code);

    else if(code == 9)
        printf("Error #%d: Period expected\n", code);

    else if(code == 10)
        printf("Error #%d: Semicolon between Statements missing\n", code);

    else if(code == 11)
        printf("Error #%d: Undeclared identifier\n", code);

    else if(code == 12)
        printf("Error #%d: Assignment to constant or procedure is not allowed\n", code);

    else if(code == 13)
        printf("Error #%d: Assignment operator expected\n", code);

    else if(code == 14)
        printf("Error #%d: call must be followed by an identifier\n", code);

    else if(code == 15)
        printf("Error #%d: call of a constant or variable is meaningless\n", code);

    else if(code == 16)
        printf("Error #%d: then expected\n", code);

    else if(code == 17)
        printf("Error #%d: end expected\n", code);

    else if(code == 18)
        printf("Error #%d: do expected\n", code);

    else if(code == 19)
        printf("Error #%d: Incorrect symbol following Statement\n", code);

    else if(code == 20)
        printf("Error #%d: Relational operator expected\n", code);

    else if(code == 21)
        printf("Error #%d: Expression must not contain a procedure identifier\n", code);

    else if(code == 22)
        printf("Error #%d: Right parenthesis missing\n", code);

    else if(code == 23)
        printf("Error #%d: The preceding Factor cannot begin with this symbol\n", code);

    else if(code == 24)
        printf("Error #%d: An Expression cannot begin with this symbol\n", code);

    else if(code == 25)
        printf("Error #%d: This number is too large\n", code);

    else if(code == 26)
        printf("Error #%d: Too many lines of code\n", code);

    exit(0);

}

void add_symbol(int k, char str[], int val){

    if(sym_index>=500) error(27);

    else{

        symbol_table[sym_index].kind = k;

        strcpy(symbol_table[sym_index].name , str);

        symbol_table[sym_index].val = val;

        codegen(1, 0, val);

        stack_pointer++;

        symbol_table[sym_index].level = 0;

        symbol_table[sym_index].addr = stack_pointer;

        sym_index++;
    }
}

void codegen(int op, int l, int m){

    if(code_index >= MAX_CODE_LENGTH)
        error(26);

    else{
        code_stack[code_index].op = op;
        code_stack[code_index].l = l;
        code_stack[code_index].m = m;
        code_index++;
    }
}

int find_index(char name[]){

    int i, index=-1;

    for(i=0; i<sym_index; i++){

        if(strcmp(name, symbol_table[i].name)==0){

            index=i;

            break;
        }
    }
    return index;
}

void printAssemblyInt(){

    fprintf(ofpVM, "Interpreted Assembly Lanuage\n");
    fprintf(ofpVM,"\nLine\t OP\t L\t M\n");

    printf("Interpreted Assembly Lanuage\n");
    printf("\nLine\t OP\t L\t M\n");

    int i;
    for (i = 0; i < ics; i++){
        fprintf(ofpVM,"%d\t %s\t %d\t %d\n", i, ALL_OPERATIONS[codes[i].op], codes[i].l, codes[i].m);
        printf("%d\t %s\t %d\t %d\n", i, ALL_OPERATIONS[codes[i].op], codes[i].l, codes[i].m);
    }
}

void printExecution(int line){

    fprintf(ofpVM,"%d\t %s\t %d\t %d", line, ALL_OPERATIONS[codes[line].op], codes[line].l, codes[line].m);
    printf("%d\t %s\t %d\t %d", line, ALL_OPERATIONS[codes[line].op], codes[line].l, codes[line].m);
}

void printStackVal(){

    int numBPtrs = 1;
    int BPtrs[100];
    int cBP = BP;
    int i;

    while(cBP > 1) {
        BPtrs[100 - numBPtrs] = cBP;
        cBP = stack[cBP+1];
        numBPtrs++;
    }

    numBPtrs--;

    for(i = 1; i <= SP; i++) {
        if (BPtrs[100-numBPtrs] == i) {
                printf("| ");
                numBPtrs--;
        }
        fprintf(ofpVM," %d ", stack[i]);
        printf(" %d ", stack[i]);

    }

}

void printVMInt(){

    fprintf(ofpVM,"Virtual Machine Execution\n");
    fprintf(ofpVM,"\t\t\t\t pc\t bp\t sp\t stack\n");
    fprintf(ofpVM,"Initial Values\t\t\t %d\t %d\t %d\n", PC, BP, SP);

    printf("Virtual Machine Execution\n");
    printf("\t\t\t\t pc\t bp\t sp\t stack\n");
    printf("Initial Values\t\t\t %d\t %d\t %d\n", PC, BP, SP);

    printStackVal();

    while(BP > 0) {
        if (PC < ics) {

            printExecution(PC);

            executeInstruction();

            fprintf(ofpVM,"\t %d\t %d\t %d\t", PC, BP, SP);
            printf("\t %d\t %d\t %d\t", PC, BP, SP);

            printStackVal();

            fprintf(ofpVM,"\n");
            printf("\n");

        }
    }

}

void executeInstruction(){

    IR = codes[PC];
    PC++;

    switch(IR.op){
        case 1:
                SP++;
                stack[SP] = IR.m;
                break;
        case 2:
                StackOPR();
                break;
        case 3:
                SP++;
                stack[SP] = stack[base(IR.l, BP) + IR.m];
                break;
        case 4:
                stack[base(IR.l, BP) + IR.m] = stack[SP];
                SP--;
                break;
        case 5:
                stack[SP + 1] = 0;
                stack[SP + 2] = base(IR.l, BP);
                stack[SP + 3] = BP;
                stack[SP + 4] = PC;
                BP = SP + 1;
                PC = IR.m;
                break;
        case 6:
                SP += IR.m;
                break;
        case 7:
                PC = IR.m;
                break;
        case 8:
                if(stack[SP] == 0){
                        PC = IR.m;
                        SP--;
                }
                break;
        case 9:
                StackIO_Operation();
                break;
        case 10:
                StackIO_Operation();
                break;
        default:
                fprintf(ofpVM,"Invalid Opcode\n");
                printf("Invalid Opcode\n");
    }

}

void StackOPR (){

    switch (IR.m) {
        case 0:
                SP = BP - 1;
                PC = stack[SP + 4];
                BP = stack[SP + 3];
                break;
        case 1:
                stack[SP] *= -1;
                break;
        case 2:
                SP--;
                stack [SP] = stack[SP] + stack[SP + 1];
                break;
        case 3:
                SP--;
                stack[SP] = stack[SP] - stack[SP + 1];
                break;
        case 4:
                SP--;
                stack[SP] = stack[SP] * stack[SP + 1];
                break;
        case 5:
                SP--;
                stack[SP] = stack[SP] / stack[SP + 1];
                break;
        case 6:
                stack[SP] %= 2;
                break;
        case 7:
                SP--;
                stack[SP] %= stack[SP + 1];
                break;
        case 8:
                SP--;
                stack[SP] = stack[SP] == stack[SP+1];
                break;
        case 9:
                SP--;
                stack[SP] = stack[SP] != stack[SP + 1];
                break;
        case 10:
                SP--;
                stack[SP] = stack[SP] < stack[SP + 1];
                break;
        case 11:
                SP--;
                stack[SP] = stack[SP] <= stack[SP + 1];
                break;
        case 12:
                SP--;
                stack[SP] = stack[SP] > stack[SP + 1];
                break;
        case 13:
                SP--;
                stack[SP] = stack[SP] >= stack[SP + 1];
                break;
        default:
                fprintf(ofpVM,"Invalid Operator\n");
                printf("Invalid Operator\n");

    }

}

void StackIO_Operation(){

    switch(IR.m){

        case 1:
            fprintf(ofpVM,"%d ", stack[SP]);
            printf("%d ", stack[SP]);
            SP--;
            break;
        case 2:
            SP++;
            int i;
            fprintf(ofpVM,"\t Enter a value for SP ");
            printf("\t Enter a value for SP ");
            scanf("%d", &i);
            fprintf(ofpVM,"\t\t\t");
            printf("\t\t\t");
            stack[SP] = i;
            break;
        default:
            fprintf(ofpVM,"Invalid Operation\n");
            printf("Invalid Operation\n");
    }

}


int base(l, base)
{
    int b1;
    b1 = base;
    while (l > 0)
    {
        b1 = stack[b1 + 1];
        l--;
    }
    return b1;
}
