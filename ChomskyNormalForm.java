/*
 * Yasser Itani
 * COT 4210 Discrete Structures II
 * Chomsky Normal Form Program
 */

package CYK;

import java.io.*;
import java.util.*;

public class chomsky
{
	public static ArrayList<ArrayList> rules; //for ArrayList of ArrayLists
	public static ArrayList<String> input; //holding the input string
	public static String ElemTable[][];

	public chomsky()
	{
		rules = new ArrayList<ArrayList>();
		input = new ArrayList<String>();
	}
	
	public static void setInput(ArrayList<String> in)
	{
		input = in;
	}

	public static void setRules(ArrayList<ArrayList> lst)
	{
		rules = lst;
	}
	
	public static ArrayList<String> getInput()
	{
		return input;
	}

	public static ArrayList<ArrayList> getRules()
	{
		return rules;
	}
	
	public static void CopyArrayList(ArrayList<String> dest, ArrayList<String> src)
	{
		for(int i = 0; i < src.size(); i++)
		{
			dest.add(src.get(i));
		}
	}
    
    public static void main(String[] args) 
    {
		try
		{
			File istream = new File("src/grammar1.in");
			Scanner cin = new Scanner(istream);
			
			while (cin.hasNextLine())
			{
				String line = cin.nextLine();
				StringTokenizer tokens = new StringTokenizer (line," ");
				int numGram = Integer.parseInt(tokens.nextToken());
				
				// Read in grammar
				for (int ng = 0; ng < numGram; ng++)
				{
					System.out.println("Grammar #" + (ng+1) + ":");
					ArrayList<ArrayList> rules = new ArrayList<ArrayList>();
					ArrayList<String> input = new ArrayList<String>();
					line = cin.nextLine();
					tokens = new StringTokenizer (line," ");
					int numVars = Integer.parseInt(tokens.nextToken());
					
					// Read in variables
					for (int nv = 0; nv < numVars; nv++)
					{
						line = cin.nextLine();
						tokens = new StringTokenizer (line," ");
						int numRules = Integer.parseInt(tokens.nextToken());
						ArrayList<String> tempList = new ArrayList<String>();
						ArrayList<String> allList = new ArrayList<String>();
						String temp = String.valueOf(tokens.nextToken());
						allList.add(temp);
						
						// Read in rules
						for (int nr = 0; nr < numRules; nr++)
						{
							String temp1 = String.valueOf(tokens.nextToken());
							tempList.add(temp);
							tempList.add(temp1);
							allList.add(temp1);
							ArrayList<String> templ = new ArrayList<String>();
							CopyArrayList(templ, tempList);
							if (!rules.contains(templ))
								rules.add(templ);
							tempList.clear();
						}
						ArrayList<String> templ = new ArrayList<String>();
						CopyArrayList(templ, allList);
						if (templ.size() > 2 && !rules.contains(templ))
							rules.add(templ);
						allList.clear();						
					}	
					line = cin.nextLine();
					tokens = new StringTokenizer (line," ");
					int numStrs = Integer.parseInt(tokens.nextToken());
					for (int ns = 0; ns < numStrs; ns++)
					{
						line = cin.nextLine();
						tokens = new StringTokenizer (line," ");
						int maxStrlen = 0;
						while (tokens.hasMoreTokens())
						{
							String str = String.valueOf(tokens.nextToken());
							if (str.length() > maxStrlen)
								maxStrlen = str.length(); 
							input.add(str);
						}
				 	    chomsky C =  new chomsky();
				 	    C.setRules(rules);
				 	    C.setInput(input);
				 	    
				 	    // Formation of rule set
				 	    if (C.CheckForGram())
				 	    {
				 	    	System.out.println(input + ": YES");
				 	    }
				 	    else
				 	    {
				 	    	ArrayList<ArrayList> oldrules = new ArrayList<ArrayList>();
				 	    	oldrules.addAll(rules);
							for (int i = 0; i < rules.size(); i++)
							{
								if ( rules.get(i).get(1).toString().length() <= maxStrlen)
								{
									for (int j = 0; j < rules.get(i).get(1).toString().length(); j++)
									{
										char trule = rules.get(i).get(1).toString().charAt(j);
										if ((rules.get(i).size() == 2) && (Character.isUpperCase(trule)))
										{
											for (int k =0; k <  rules.size(); k++)
											{
												if ( rules.get(k).get(1).toString().length() <= maxStrlen)
												{
													if (rules.get(k).get(0).toString().equals(Character.toString(trule)))
													{
														String s1 = rules.get(i).get(1).toString().substring(0,j);
														int s1s = s1.length();
														String s2 = rules.get(k).get(1).toString();
														int rs = rules.get(i).get(1).toString().length();
														String xrule = "";
														if (j+1 < rs-s1s)
														{
															String s3 = rules.get(i).get(1).toString().substring(j+1,rs-s1s);
															xrule = s1.concat(s2).concat(s3);
														}
														else
														{
															xrule = s1.concat(s2);
														}
														ArrayList<String> tempList = new ArrayList<String>();
														tempList.add(rules.get(i).get(0).toString());
														tempList.add(xrule);
														ArrayList<String> templ = new ArrayList<String>();
														CopyArrayList(templ, tempList);
														if (!rules.contains(templ))
															rules.add(templ);
														tempList.clear();
													}
												}
											}
										}
									}
								}
							}
							C.setRules(rules);
							if (C.CheckForGram())
					 	    {
					 	    	System.out.println(input + ": YES");
					 	    }
					 	    else
					 	    {
					 	    	System.out.println(input + ": NO");
					 	    }
							rules.clear();
							rules.addAll(oldrules);
				 	    }
				 	    input.clear();
					}
					System.out.println();
				}
			}
			cin.close();
		}
		
		catch (FileNotFoundException e)
		{	
			System.out.println("File not found" + e);
		}
		catch (IOException e)
		{
			System.out.println("Input output error" + e);
		}
	}

	// CheckForGram uses input to fill in ElemTable
	public Boolean CheckForGram()
	{
		ElemTable = new String[input.size()][input.size()];

		// initialize ElemTable
		for (int i = 0; i < input.size(); i++)
		{
			for (int j = 0; j < input.size(); j++)
			{
				ElemTable[i][j] = "";
			}
		}


		for (int i = 0; i < input.size(); i++)
		{
			for (int j = 0; j < rules.size(); j++)
			{
				ArrayList value = new ArrayList();

				value = (ArrayList) rules.get(j);
				if (input.get(i).equals(value.get(1)))
				{
					ElemTable[i][0] = (String) ElemTable[i][0] + value.get(0);
				}
			}
		}
		
		// fill ElemTable
		// for each i = 2 to n -- Length of span
		for (int row = 1; row < input.size(); row++)
		{
			//for each j = 1 to n-i+1 -- Start of span
			for (int col = 0; col < input.size() - row; col++)
			{
				// for each k = 1 to i-1 -- Partition of span
				for (int k = 0; k < row; k++)
				{
					// for each production RA -> RB RC
					for (int m = 0; m < rules.size(); m++)
					{
						ArrayList prodRules = new ArrayList();

						prodRules = (ArrayList) rules.get(m);

						if (prodRules.size() > 2)
						{
							if ((!ElemTable[col + k + 1][row - k - 1].equals("")) && (!ElemTable[col][k].equals("")))
							{
								// For each production A -> BC
								// if P[j,k,B] and P[j+k,i-k,C] then set P[j,i,A] = true
								if ((ElemTable[col][k].indexOf((String) prodRules.get(1)) != -1)
										&& (ElemTable[col + k + 1][row - k - 1].indexOf((String)prodRules.get(2)) != -1))
								{
									ifDuplicate(col, row, prodRules);
								}
							}
						}
					}
				}
			}
		}
		return(InGram());
	}

	//Search for multiple instances of the same nonterminal character
	
	private void ifDuplicate(int first, int second, ArrayList value1)
	{
		if (ElemTable[first][second].length() > 0)
	{
			boolean present = false;

			// scan what's in the table already
			for (int z = 0; z < ElemTable[first][second].length(); z++)
			{
				if (ElemTable[first][second].indexOf((String) value1.get(0)) != -1)
				{
					present = true;
				}
			}

			if (!present)
			{
				ElemTable[first][second] = ElemTable[first][second] + (String) value1.get(0);
			}
	}
		else
		{
			ElemTable[first][second] = (String) value1.get(0);
		}
	}

	// InGram checks to see if the given input is in the grammar L
	private Boolean InGram()
	{
		if (ElemTable[0][input.size() - 1].indexOf("S") == -1)
		{
			return(false);
		}
		else
		{
			return true;
		}
	}
}
