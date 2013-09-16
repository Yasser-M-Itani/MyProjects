/*
 * Yasser Itani
 * Discrete Structures II
 * Turing Machine
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class tm{
	
	public static int q,r,n,s,numinputstr;
	public static int maxsteps;
	public static String rules[][];
	
	public static ArrayList <Character> tape;
	public static ArrayList <Character> inputlist;
	
	public static Integer inputstate [];
	public static Character chartoread [];
	public static Integer outputstate [];
	public static Character chartowrite [];
	public static Character dirtomove [];
	public static Character inputs[][];
	
	public static int newoutstate;
	public static char newctr;
	public static char newdir;
	public static int ctrindex;
	
	public static void main (String args[]){

		try{
			
			File istream = new File("tm.in");
			Scanner cin = new Scanner(istream);
			
			n = cin.nextInt();	
			
			// Run for number of sets
			for (int m = 1; m <= n; m++){
				
				System.out.println("Machine #"+ m +":");
				
				q = cin.nextInt();
				r = cin.nextInt();

				inputstate = new Integer[r];
				chartoread = new Character[r];
				outputstate = new Integer[r];
				chartowrite = new Character[r];
				dirtomove = new Character[r];
				
				// Read in rules for the inputs
				for (int i = 0; i < r; i++){
		
					String es = cin.nextLine();
					
					inputstate[i] = cin.nextInt();
					chartoread[i] = cin.next().charAt(0);
					outputstate[i] = cin.nextInt();
					chartowrite[i] = cin.next().charAt(0);
					dirtomove[i] = cin.next().charAt(0);
					
				}
				
				String line = cin.nextLine();
				numinputstr = cin.nextInt();
				maxsteps = cin.nextInt();
				inputs = new Character[numinputstr][1000];
				line = cin.nextLine();
				
				// Read in input strings to run into the machine
				inputlist = new ArrayList <Character>();
				
				for (int j = 0; j < numinputstr; j++){
					String linej = cin.nextLine();
					for (int k = 0; k < linej.length(); k++){
						inputs[j][k] = linej.charAt(k);
					}
				}			
		
				// Run machine for all input stirngs
				for (int l = 0; l < numinputstr; l++){
				
					tape = new ArrayList <Character>();
				
					int tptr = 0;
					int counter = 0;
					int state = 0;
					String outstr;
					
					// If accept or reject states aren't reached continue running machine
					while (state != 1 && state != 2){
						
						outstr = new String();
						
						for (int i =0; i< 1000; i++){
							tape.add(inputs[l][i]);
							if (inputs[l][i] == null)
								break;
							outstr += inputs[l][i];
						}
						
						// If machine doesn't exceed the maximum steps desired continue
						if (counter <= maxsteps){
							Character temptape = tape.get(tptr);
							
							// Remove current value in the tape
							tape.remove(tptr);
							
							// Place the new character in the tape based on the current state, the char to read and the next char
							// to write in the tape
							tape.add(tptr, newchartowrite(inputstate, chartoread, chartowrite, state, temptape));
							
							// Move the tape head in the direction indicated by the current state and the char to read
							if (newdirtomove(inputstate, chartoread, dirtomove, state,temptape) == 'R'){
								state = newstate(inputstate, chartoread, outputstate, state, temptape);
								tptr++;
								
								// If index in tape isn't filled, insert a blank
								if(tape.get(tptr) == null)
									tape.add(tptr,'B');
							}
							
							if (newdirtomove(inputstate, chartoread, dirtomove, state,temptape) == 'L'){
								state = newstate(inputstate, chartoread, outputstate, state, temptape);
								tptr--;
								
								if(tape.get(tptr) == null)
									tape.add(tptr,'B');
		
							}					
							counter++;
						}
						
						else {System.out.println(outstr+": DOES NOT HALT IN "+ maxsteps +" STEPS"); break;}
						
						if (state == 2) System.out.println(outstr+": NO");
						if (state == 1) System.out.println(outstr+": YES");
		
					}
				}
				System.out.println();
			}
			cin.close();
		}
		
		catch (FileNotFoundException e)
		{	
			System.out.println("File not found" + e);
		}
		catch (@SuppressWarnings("hiding") IOException e)
		{
			System.out.println("Input output error" + e);
		}
	}

	// Function determines what the new state is going to be based on the current state and the character in the string
	public static Integer newstate (Integer inputstate[], Character chartoread[], Integer outputstate[], int currentstate, char ctr){
		for (int i = 0; i < r; i++){
			if (inputstate[i] == currentstate && chartoread[i].compareTo(ctr) == 0)
				newoutstate = outputstate[i];
		}
		return newoutstate;
	}
	
	// Function determines what the new character is going to be written to the tape based on the current state 
	// and the character in the string
	public static Character newchartowrite (Integer inputstate[], Character chartoread[], Character chartowrite[], int currentstate, char ctr){
		for (int i = 0; i < r; i++){
			if (inputstate[i] == currentstate && chartoread[i].compareTo(ctr) == 0)
				newctr = chartowrite[i];
		}
		return newctr;
	}
	
	// Function determines what direction the tape head is going to move based on the current state and the character 
	// in the string
	public static Character newdirtomove (Integer inputstate[], Character chartoread[], Character dirtomove[], int currentstate, char ctr){
		for (int i = 0; i < r; i++){
			if (inputstate[i] == currentstate && chartoread[i].compareTo(ctr) == 0)
				newdir = dirtomove[i];
		}
		return newdir;
	}
	
}
 
