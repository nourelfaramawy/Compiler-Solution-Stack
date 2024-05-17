package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Write your info here
 * 
 * @name Nour Khaled ElFaramawy
 * @id 49-13354
 * @labNumber 07
 */

public class CfgFirstFollow {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	ArrayList<String> cfgVariables;
	ArrayList<String> cfgTerminals;
	HashMap<String, ArrayList<String>> cfgRules;
	HashMap<String, ArrayList<String>> cfgFirstRules;
	HashMap<String, ArrayList<String>> cfgFollowRules;
	
	
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		this.cfgVariables = new ArrayList<String>();
		this.cfgTerminals = new ArrayList<String>();
		this.cfgRules = new HashMap<>();
		this.cfgFirstRules = new HashMap<>();
		this.cfgFollowRules = new HashMap<>();
		
		String[] cfgInputStringArray = cfg.split("#");
		String[] cfgIputVarArray = cfgInputStringArray[0].split(";");
		String[] cfgIputTerArray = cfgInputStringArray[1].split(";");
		String[] cfgIputRuleArray = cfgInputStringArray[2].split(";");
		
		//get the variables from input 
		for(int i = 0; i < cfgIputVarArray.length; i++)
		{
			cfgVariables.add(cfgIputVarArray[i]);
		}
		
		//get the terminals from input
		for(int i = 0; i < cfgIputTerArray.length; i++)
		{
			cfgTerminals.add(cfgIputTerArray[i]);
		}
		
		//get the rules
		for(int i = 0; i < cfgIputRuleArray.length; i++)
		{
			String[] tempRuleArray = cfgIputRuleArray[i].split("/");
			String[] tempList = tempRuleArray[1].split(",");
			ArrayList<String> tempVarRules = new ArrayList<String>();
			
			for(int j = 0; j < tempList.length; j++)
			{
				tempVarRules.add(tempList[j]);
			}
			cfgRules.put(tempRuleArray[0], tempVarRules);
			
		}
		
		//initialize the arrays and hashmap for each variable for the firsts 
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			ArrayList<String> tempNewArray = new ArrayList<>();
			cfgFirstRules.put(cfgVariables.get(i), tempNewArray);
		}
		
		//initialize the arrays and hashmap for each variable for the firsts 
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			ArrayList<String> tempNewArray = new ArrayList<>();
			if(cfgVariables.get(i).equals("S"))
			{
				tempNewArray.add("$");
			}
			cfgFollowRules.put(cfgVariables.get(i), tempNewArray);
		}
		
//		System.out.println("cfgVariables "+cfgVariables);
//		System.out.println("cfgTerminals "+cfgTerminals);
//		System.out.println("cfgRules "+cfgRules);
//		System.out.println("cfgFirstRules "+cfgFirstRules);
//		System.out.println();
//		System.out.println("cfgFollowRules "+cfgFollowRules);
		
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		String finalFirstString = "";
		
		boolean changed = true; 
//		int z = 0;
		
		while(changed)
		{
			changed = false;
			
			for(int i = 0; i < cfgVariables.size(); i++)
			{
				//get the current variable i am currently iterating on
				String currentVariable = cfgVariables.get(i);
//				System.out.println("currentVariable "+currentVariable);
				//get the current variable's cfg rules
				ArrayList<String> currentVarRules = cfgRules.get(currentVariable);
//				System.out.println("currentVarRules "+currentVarRules);
				//get the current variable first array 
				ArrayList<String> currentVarFirst = cfgFirstRules.get(currentVariable);
//				System.out.println("currentVarFirst "+currentVarFirst);
				
				for(int j = 0; j < currentVarRules.size(); j++)
				{
					String tempVarRule = currentVarRules.get(j);
//					System.out.println("tempVarRule "+tempVarRule);
					String firstChar = tempVarRule.charAt(0)+"";
//					System.out.println("firstChar "+firstChar);
					//if the first char is a terminal
					if(cfgTerminals.contains(firstChar))
					{
						if(!currentVarFirst.contains(firstChar))
						{
							cfgFirstRules.get(currentVariable).add(firstChar);
//							System.out.println("after adding terminal  "+cfgFirstRules.get(currentVariable));
							changed = true;
						}
					}
					//else if first char not a terminal
					else
					{
						//if first char is epsilon
						if(firstChar.equals("e"))
						{
							if(!currentVarFirst.contains("e"))
							{
								cfgFirstRules.get(currentVariable).add("e");
//								System.out.println("cfgFirstRules after adding epsilon  "+cfgFirstRules.get(currentVariable));
								changed = true;
							}
						}
						else
						{
							//get all the firsts of the variable and add it to the firsts
							//of the current variable
							ArrayList<String> nonTermFirst = cfgFirstRules.get(firstChar);
//							System.out.println("nonTermFirst before if "+nonTermFirst);
							if(!nonTermFirst.isEmpty())
							{
								for(int k = 0; k < nonTermFirst.size(); k++)
								{
									if(!currentVarFirst.contains(nonTermFirst.get(k)) && !nonTermFirst.get(k).equals("e"))
									{
										cfgFirstRules.get(currentVariable).add(nonTermFirst.get(k));
										changed = true;
									}
								}
//								System.out.println("cfg after adding first of nonterm "+cfgFirstRules.get(currentVariable));
								//check if the nontermfirst contains epsilon 
								if(nonTermFirst.contains("e"))
								{
//									System.out.println("nonterm contains epsilon");
									int varLength = tempVarRule.length();
//									System.out.println("varLength "+varLength);
									int endOfLength = 0;
									for(int h = 0; h < tempVarRule.length(); h++)
									{
										//get the first char 
										String tempChar = tempVarRule.charAt(h)+"";
										if(cfgTerminals.contains(tempChar))
										{
											if(!cfgFirstRules.get(currentVariable).contains(tempChar))
											{
												cfgFirstRules.get(currentVariable).add(tempChar);
												changed = true;
											}
											break;
										}
										else
										{
											ArrayList<String> tempCharFirst = cfgFirstRules.get(tempChar);
											for(int g = 0; g < tempCharFirst.size(); g++)
											{
												if(!cfgFirstRules.get(currentVariable).contains(tempCharFirst.get(g))&& !tempCharFirst.get(g).equals("e"))
												{
													cfgFirstRules.get(currentVariable).add(tempCharFirst.get(g));
													changed = true;
												}
											}
											if(!tempCharFirst.contains("e"))
											{
												break;
											}
											endOfLength++;
											
										}
											
									}
									if(endOfLength == varLength)
									{
										if(!cfgFirstRules.get(currentVariable).contains("e"))
										{
											cfgFirstRules.get(currentVariable).add("e");
//											changed = true;
										}
									}
									
								}
								
//								if(nonTermFirst.contains("e")&&!cfgFirstRules.get(currentVariable).contains("e"))
//								{
//									cfgFirstRules.get(currentVariable).add("e");
//								}
//								System.out.println("after adding other var first "+cfgFirstRules.get(currentVariable));
//								changed = true;
							}
							
						}
						
					}
				}
//				System.out.println("____________________________");
//				System.out.println();
			}
//			z++;
		}
//		System.out.println("cfgFirstRules "+cfgFirstRules);
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			Collections.sort(cfgFirstRules.get(cfgVariables.get(i)));
		}
//		System.out.println("cfgFirstRules "+cfgFirstRules);
		
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			finalFirstString += cfgVariables.get(i)+"/";
			ArrayList<String> temp = cfgFirstRules.get(cfgVariables.get(i));
			for(int j = 0; j < temp.size(); j++)
			{
				finalFirstString += temp.get(j);
			}
			
			finalFirstString += ";";
		}
		
		finalFirstString = finalFirstString.substring(0,finalFirstString.length()-1);
//		System.out.println("finalFirstString "+finalFirstString);
		return finalFirstString;
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		first();
		String finalFollowString = "";
		
		boolean changed = true; 
//		int z = 0;
		while(changed)
		{
			changed = false;
			
			for(int i = 0; i < cfgVariables.size(); i++)
			{
				//get the current variable i am currently iterating on
				String currentVariable = cfgVariables.get(i);
//				System.out.println("currentVariable "+currentVariable+":");
//				System.out.println();
				
				//start looping on all variables and their rules
				for(int j = 0; j < cfgVariables.size(); j++)
				{
//					System.out.println("looping variables "+cfgVariables.get(j));
//					System.out.println("cfg follow "+cfgFollowRules.get(currentVariable));
					ArrayList<String> varRuleArray = cfgRules.get(cfgVariables.get(j));
					
					for(int k = 0; k < varRuleArray.size(); k++)
					{
						String varRuleString = varRuleArray.get(k);
//						System.out.println("varRuleString "+varRuleString);
						
						if(varRuleString.contains(currentVariable))
						{
							ArrayList<Integer> varIndexes = getIndexes(varRuleString, currentVariable.charAt(0));
//							System.out.println("varIndexes "+varIndexes);
							//if it is the last char in the rule
							
							for(int t = 0; t < varIndexes.size(); t++)
							{
								int varIndex = varIndexes.get(t);
//								System.out.println("index "+varIndex);

								if(varIndex + 2 > varRuleString.length())
								{
									ArrayList<String> varLHSFollow = cfgFollowRules.get(cfgVariables.get(j));
									if(!varLHSFollow.isEmpty())
									{
										for(int p = 0; p < varLHSFollow.size(); p++)
										{
											if(!cfgFollowRules.get(currentVariable).contains(varLHSFollow.get(p)))
											{
												
												cfgFollowRules.get(currentVariable).add(varLHSFollow.get(p));
												changed = true;

											}
										}
//										System.out.println("after adding follow of last "+cfgFollowRules);
									}
									
								}
								else
								{
									String nextChar = varRuleString.charAt(varIndex + 1) + "";
//									System.out.println("nextChar "+nextChar);
									
									//if next char is a terminal 
									if(cfgTerminals.contains(nextChar))
									{
										if(!cfgFollowRules.get(currentVariable).contains(nextChar))
										{
											cfgFollowRules.get(currentVariable).add(nextChar);
//											System.out.println("after adding terminal "+cfgFollowRules);
											changed = true;
										}
//										break;
										
									}
									//else if it is not a terminal
									else
									{
										ArrayList<String> nextCharFirsts = cfgFirstRules.get(nextChar);
										
										for(int u = 0; u < nextCharFirsts.size(); u++)
										{
											if((!cfgFollowRules.get(currentVariable).contains(nextCharFirsts.get(u)))
												&&(!nextCharFirsts.get(u).equals("e")))
											{
												cfgFollowRules.get(currentVariable).add(nextCharFirsts.get(u));
												changed = true;
											}
										}
//										System.out.println("follow after adding firsts of var "+cfgFollowRules.get(currentVariable));
										if(nextCharFirsts.contains("e"))
										{
//											System.out.println("dakhalt next char has e ");
//											System.out.println("varIndex "+varIndex);
//											int nextIndex = getIndexOf(varRuleString.substring(varIndex, varRuleString.length()), nextChar.charAt(0)) + 1;
											int nextIndex = varIndex + 1;
//											System.out.println("vvv "+varRuleString.substring(varIndex, varRuleString.length()));
//											System.out.println("nextIndex "+ (varIndex +1));
											if(nextIndex + 2 > varRuleString.length())
											{
//												System.out.println("dakhalt next char has e but last var in rule");
												ArrayList<String> varLHSFollow = cfgFollowRules.get(cfgVariables.get(j));
												for(int p = 0; p < varLHSFollow.size(); p++)
												{
													if(!cfgFollowRules.get(currentVariable).contains(varLHSFollow.get(p)))
													{
														cfgFollowRules.get(currentVariable).add(varLHSFollow.get(p));
														changed = true;
													}
												}
											}
											else
											{
												int endOfString = 0;
												for(int d = nextIndex; d < varRuleString.length(); d++)
												{
													String tempChar = varRuleString.charAt(d)+"";
//													System.out.println("tempChar "+tempChar);
													//if its a terminal
													if(cfgTerminals.contains(tempChar))
													{
														
														if(!cfgFollowRules.get(currentVariable).contains(tempChar))
														{
															cfgFollowRules.get(currentVariable).add(tempChar);
															changed = true; 
//															System.out.println("next char has ep but then term "+cfgFollowRules.get(currentVariable));
//															break;
														}
//														else
														break;
													}
													//its a variable
													else
													{
														ArrayList<String> tempCharFirsts = cfgFirstRules.get(tempChar);
														for(int y = 0; y < tempCharFirsts.size(); y++)
														{
															if((!cfgFollowRules.get(currentVariable).contains(tempCharFirsts.get(y)))
																	&&(!tempCharFirsts.get(y).equals("e")))
																{
																	cfgFollowRules.get(currentVariable).add(tempCharFirsts.get(y));
																	changed = true;
																}
														}
//														System.out.println("cfg after adding temp firsts "+ cfgFollowRules.get(currentVariable));
														if(!tempCharFirsts.contains("e"))
														{
															break;
														}
													}
													endOfString++;
												}
												if(endOfString == varRuleString.length())
												{
													ArrayList<String> varLHSFollow = cfgFollowRules.get(cfgVariables.get(j));
													for(int p = 0; p < varLHSFollow.size(); p++)
													{
														if(!cfgFollowRules.get(currentVariable).contains(varLHSFollow.get(p)))
														{
															cfgFollowRules.get(currentVariable).add(varLHSFollow.get(p));
															changed = true;
														}
													}
//													System.out.println("after adding follow of var "+cfgFollowRules.get(currentVariable));
												}
											}
											
										}
										
//										ArrayList<String> varLHSFollow = cfgFollowRules.get(cfgVariables.get(j));
//										for(int p = 0; p < varLHSFollow.size(); p++)
//										{
//											if(!cfgFollowRules.get(currentVariable).contains(varLHSFollow.get(p)))
//											{
//												cfgFollowRules.get(currentVariable).add(varLHSFollow.get(p));
//												changed = true;
//											}
//										}
									}
								}
							}
							
							
							
						}
						
						
					}
//					System.out.println("______________________________________");
//					System.out.println();
				}
				
			}
//				System.out.println("______________________________________");
//				System.out.println();
//				z++;
		}
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			Collections.sort(cfgFollowRules.get(cfgVariables.get(i)));
		}
//		System.out.println("cfgFollowRules "+cfgFollowRules);
		
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			finalFollowString += cfgVariables.get(i)+"/";
			ArrayList<String> temp = cfgFollowRules.get(cfgVariables.get(i));
			for(int j = 0; j < temp.size(); j++)
			{
				finalFollowString += temp.get(j);
			}
			
			finalFollowString += ";";
		}
		
		finalFollowString = finalFollowString.substring(0,finalFollowString.length()-1);
//		System.out.println("finalFollowString "+finalFollowString);
		
		return finalFollowString;
	}
	
	public int getIndexOf(String rule, char var)
	{
		int index = 0; 
		for(int i = 0; i < rule.length(); i++)
		{
			if(rule.charAt(i) == var)
			{
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	public ArrayList<Integer> getIndexes(String rule, char var)
	{
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 0; i < rule.length(); i++)
		{
			if(rule.charAt(i) == var)
			{
				result.add(i);
			}
		}
		
		return result;
	}
	
	public static void main (String args[])
	{
		//S;Z;R;D;E;U;O#i;l;m;p;q;u;x#S/x,E;Z/EOl,RRO,U;R/SxDx,ZSxUp,U,Ux,Z,S;D/iZpZ,UxO,R,e;E/ZZOq,Ox;U/uOE,pEZmO,e,U;O/U,p,m,ORE
//		CfgFirstFollow a = new CfgFirstFollow("S;R;L;W;V;B;K#a;b;d;l;q;z#S/dWq,V;R/lLa,lBL,bSKSR,VRWR;L/zBKK,zWqKW,l,zLS,lWl,dVdSq,e;W/zLaS,VV,V,S;V/dWRz,aWd,e,S,V;B/VKd,KVdL;K/LBa,VV,BbL");

//		CfgFirstFollow a = new CfgFirstFollow("S;P;I;T;W;X;C#g;h;l;t;v;x;z#S/gXCPI,SvI;P/g,gThCX,W;I/Sv,XXIC,S;T/XTWx,TCT,WP,hITXS,SCTXh,lWt;W/IWWX,vCXT,e,X,S,I;X/xCx,z,e,X;C/vXX,xXCXX,PITx,zX,SlPv");
//		a.first();
//		a.follow();
	}

}
