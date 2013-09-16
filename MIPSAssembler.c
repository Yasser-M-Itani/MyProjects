/********* MySPIM ***********/
// Yasser Itani		PID: y2662507
// Haines Stockwell	PID: s2248311
// UCF CDA 3103
// Professor Mark Heinrich
// 3 December 2012

/*** Contribution Breakdown ***/
/*
	Yasser Itani:
		- Completion of the following functions for project.c:
			- Sign_extend(...)
			- ALU_operations(...)
			- rw_memory(...)
			- write_register(...)
			- PC_update(...)
		- Debugging Completed Code
			- Checking over values of registers
			- Checking values stored in memory
			- Checking halts were reached
			- Look over all code for project.c
		- Assembler Construction
			- Function cases
				- Setting function/opcode/target for calls


	Haines Stockwell:
		- Completion of the following fucntions for project.c:
			- ALU(...)
			- instruction_fetch(...)
			- instruction_partition(...)
			- instruction_decoder(...)
			- read_register(...)
		- Assembler Construction
			- Coding of string parser
			- Functions to construct binaries
			- Debugging of code
				- Checking if proper binaries were formed for each command
				- Running and compiling code


*/

#include "spimcore.h"

unsigned createMask(unsigned a, unsigned b);

/*****START HAINES STOCKWELL'S SECTION*****/
/* ALU */
/* 10 Points */
void ALU(unsigned A,unsigned B,char ALUControl,unsigned *ALUresult,char *Zero){

	switch(ALUControl){
		case 0:	//Add, Addi
			*ALUresult = A+B;
			break;
		case 1:	//Subr
			*ALUresult = A-B;
			break;
		case 2:	//Slt, Slti
			*ALUresult = (A < B)?1:0;
			break;
		case 3:	//Sltu, Sltiu
			*ALUresult = (A < B)?1:0;
			break;
		case 4:	//And
			*ALUresult = A&B;
			break;
		case 5:	//Or
			*ALUresult = A|B;
			break;
		case 6:	//Shift
			*ALUresult = B << 16;
			break;
		case 7:	//Not
			*ALUresult = ~A;
			break;
	}

	//Assign 1 to Zero if result is 0
	if (ALUresult == 0)
		*Zero = 1;
	else
		*Zero = 0;

}

/* instruction fetch */
/* 10 Points */
int instruction_fetch(unsigned PC,unsigned *Mem,unsigned *instruction){

	//Check PC is in bounds
	if(PC % 4 != 0){
		return 1;
	}

	*instruction = Mem[PC >> 2];

	return 0;
}

/* instruction partition */
/* 10 Points */
void instruction_partition(unsigned instruction, unsigned *op, unsigned *r1,unsigned *r2, unsigned *r3, unsigned *funct, unsigned *offset, unsigned *jsec){

	//Set vars to the binary value between the indexes in createMask
	*op = createMask(26,31) & instruction;
	*op = *op >> 26;
	*r1 = createMask(21,25) & instruction;
	*r1 = *r1 >> 21;
	*r2 = createMask(16,20) & instruction;
	*r2 = *r2 >> 16;
	*r3 = createMask(11,15) & instruction;
	*r3 = *r3 >> 11;
	*funct = createMask(0,5) & instruction;
	*offset = createMask(0,15) & instruction;
	*jsec = createMask(0,25) & instruction;

	return;

}

/* instruction decode */
/* 15 Points */
int instruction_decode(unsigned op,struct_controls *controls){

	//Initialize to Zero
	controls->RegDst = 0;
	controls->Jump = 0;
	controls->Branch = 0;
	controls->MemRead = 0;
	controls->MemtoReg = 0;
	controls->ALUOp = 0;
	controls->MemWrite = 0;
	controls->ALUSrc = 0;
	controls->RegWrite = 0;

	switch(op){
		case 0x0: //R type instructions (add,sub,slt,sltu, etc included) //0x0
			controls->RegDst=1;
			controls->ALUOp=7;		// ALUop 111 for an R-type instruction
			controls->RegWrite=1;
			break;
		case 0x2: //J
			controls->Jump = 1;
			break;
		case 0x4: //Beq
		        controls->Branch = 1;
			break;
		case 0x8: //Addi
			controls->ALUSrc = 1;
			controls->RegWrite = 1;
			break;
		case 0xb: //Sltiu
			controls->ALUOp = 3;
			controls->ALUSrc = 1;
			controls->RegWrite = 1;
			break;
		case 0xa: //Slti
			controls->ALUOp = 2;
			controls->ALUSrc = 1;
			controls->RegWrite = 1;
			break;
		case 0xf: //Lui
			controls->ALUOp = 6;
			controls->ALUSrc = 1;
			controls->RegWrite = 1;
			break;
		case 0x23: //Lw
			controls->ALUSrc = 1;
			controls->MemtoReg = 1;
			controls->RegWrite = 1;
			controls->MemRead = 1;
			break;
		case 0x2b: //Sw
			controls->ALUSrc = 1;
			controls->MemWrite = 1;
			controls->RegDst = 2;
			controls->MemtoReg = 2;
			break;
		default:
			return 1;
	}

	return 0;

}

/* Read Register */
/* 5 Points */
void read_register(unsigned r1,unsigned r2,unsigned *Reg,unsigned *data1,unsigned *data2){

	*data1 = Reg[r1];
	*data2 = Reg[r2];

}

/*****END HAINES STOCKWELL'S SECTION*****/
/*****START YASSER ITANI'S SECTION*****/

/* Sign Extend */
/* 10 Points */
void sign_extend(unsigned offset,unsigned *extended_value){

	// To check if offset is negative, we right shift by 15
	unsigned offsetshift = offset >> 15;
	unsigned extndby = 0xFFFF0000;

	if (offsetshift == 1)
		// bitwise inclusive OR offset to extend
		*extended_value = extndby|offset;
	else{
		*extended_value = offset;
	}

}

/* ALU operations */
/* 10 Points */
int ALU_operations(unsigned data1,unsigned data2,unsigned extended_value,unsigned funct,char ALUOp,char ALUSrc,unsigned *ALUresult,char *Zero){

	//If command is for an address calculation (lw, sw)
	if (ALUSrc == 1)
		data2 = extended_value;

	switch (ALUOp) {

		//Add, Addi
		case 0:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//Add, Addi
		case 1:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//Slt, Slti
		case 2:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//Sltu, Sltui
		case 3:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//And
		case 4:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//Or
		case 5:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//Shift & Extend
		case 6:
			ALU(data1,data2,ALUOp,ALUresult,Zero);
			break;

		//R-type
		case 7:

			switch (funct) {

				//Add, Addi - Binary: 10 000 = 32
				case 32:
					ALU(data1,data2,0,ALUresult,Zero);
					break;

				//Sub - Binary: 10 0010 = 34
				case 34:
					ALU(data1,data2,1,ALUresult,Zero);
					break;

				//And - Binary: 10 0100 = 36
				case 36:
					ALU(data1,data2,4,ALUresult,Zero);
					break;

				//Or - Binary: 10 0101 = 37
				case 37:
					ALU(data1,data2,5,ALUresult,Zero);
					break;

				//Not Or -> Nor - Binary: 10 0111 = 39
				case 39:
					ALU(data1,data2,7,ALUresult,Zero);
					break;

				//Slt, Slti - Binary: 10 1010 = 42
				case 42:
					ALU(data1,data2,2,ALUresult,Zero);
					break;

				//Sltu, Sltui - Binary: 10 1011 = 43
				case 43:
					ALU(data1,data2,3,ALUresult,Zero);
					break;

				default: return 1;
			}

			//Return 0 otherwise if halt doesn't occur
			return 0;
			break;

		// Return 1 if halt occurs
		default:return 1;
	}

	return 0;

}

/* Read / Write Memory */
/* 10 Points */
int rw_memory(unsigned ALUresult,unsigned data2,char MemWrite,char MemRead,unsigned *memdata,unsigned *Mem){

    if(MemWrite & 1){
	//ALUresult must be of modulo 4 to be execute
        if((ALUresult%4)!= 0){
            return 1; //Halt
        }
        Mem[ALUresult>>2] = data2; //Data to mem
    }
    if(MemRead & 1){
        if((ALUresult%4)!= 0){
            return 1;   //Halt
        }
        *memdata = Mem[ALUresult>>2]; //Mem to data
    }
    return 0;

}

/* Write Register */
/* 10 Points */
void write_register(unsigned r2,unsigned r3,unsigned memdata,unsigned ALUresult,char RegWrite,char RegDst,char MemtoReg,unsigned *Reg){
	/*
         ----------------------------------------------------------------------------------------------------------
	| RegDst      |         store into r2		    |      store into r3                                   |
	| MemtoReg    |		Store ALUresult to Reg      |      store memory to Reg                             |
	| RegWrite    |		Don't write		    |      store to reg r2 if I type or reg r3 for R type  |
	 ----------------------------------------------------------------------------------------------------------
	 */

    	if (RegWrite == 1 && MemtoReg == 1){
        //Select output from memdata and select register specified in r2 as destination
		if (RegDst == 0){Reg[r2] = memdata;}
		//Select output from memdata and select register specified in r3 as destination
		else {Reg[r3] = memdata;}
	}

	if (RegWrite == 1 && MemtoReg == 0){
	    //Select output from ALU and select register specified in r2 as destination
		if (RegDst == 0){Reg [r2] = ALUresult;}
		//Select output from ALU and select register specified in r3 as destination
		else{Reg [r3] = ALUresult;}
	}

}

/* PC update */
/* 10 Points */
void PC_update(unsigned jsec,unsigned extended_value,char Branch,char Jump,char Zero,unsigned *PC){

	unsigned pctemp = *PC + 4;

	if(Jump == 0){
		// Branch case
		if(Branch == 1 && Zero == 0)
			*PC =(extended_value * 4) + (*PC + 4);

		// Otherwise update pc+4
		else
		*PC = *PC + 4;
	}

	// Jump case
	else if(Jump == 1){
		pctemp = ((*PC + 4) & 0xF0000000)|(jsec << 2);
		*PC = pctemp | jsec << 2;
	}

	return;

}

/*****END YASSER SECTION*****/

//Creates Bit Mask
unsigned createMask(unsigned a, unsigned b){
   unsigned r = 0;
   unsigned i;
   for (i=a; i<=b; i++)
       r |= 1 << i;
   return r;
}
