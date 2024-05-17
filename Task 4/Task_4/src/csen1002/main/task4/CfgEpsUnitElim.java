package csen1002.main.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Nour Khaled ElFaramawy
 * @id 49-13354
 * @labNumber 07
 */

public class CfgEpsUnitElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *             representation follows the one in the task description
	 */
	ArrayList<String> cfgVariables;
	ArrayList<String> cfgTerminals;
	ArrayList<Integer> epsilonVarRules;
	HashMap<Integer, ArrayList<String>> cfgCurrentRules;
	HashMap<Integer, ArrayList<String>> cfgNewEpsElimRules;
	HashMap<Integer, ArrayList<String>> finalCfgRules;
	
	
	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		
		this.cfgVariables = new ArrayList<String>();
		this.cfgTerminals = new ArrayList<String>();
		this.epsilonVarRules = new ArrayList<Integer>();
		this.cfgCurrentRules = new HashMap<>();
		this.cfgNewEpsElimRules = new HashMap<>();
		this.finalCfgRules = new HashMap<>();
		
//		ArrayList<Integer> test = new ArrayList<>();
//		test.add(5);
//		test.add(2);
//		System.out.println("test "+test);
		
		String[] cfgStringComponents = cfg.split("#");
		String[] cfgVaribleString = cfgStringComponents[0].split(";");
		String[] cfgTerminalsString = cfgStringComponents[1].split(";");
		String[] cfgRulesString = cfgStringComponents[2].split(";");
		
		//get the variables 
		for(int i = 0; i < cfgVaribleString.length; i++)
		{
			cfgVariables.add(cfgVaribleString[i]);
		}
		
		//get the terminals 
		for(int i = 0; i < cfgTerminalsString.length; i++)
		{
			cfgTerminals.add(cfgTerminalsString[i]);
		}
		
		//get rules
		for(int i = 0; i < cfgRulesString.length; i++)
		{
			String[] tempRule = cfgRulesString[i].split("/");
//			System.out.println("tempRule[0] "+tempRule[0]);
			String[] tempVarRulesString = tempRule[1].split(",");
			ArrayList<String> tempVarRules = new ArrayList<String>();
			
			for(int j = 0; j < tempVarRulesString.length; j++)
			{
				tempVarRules.add(tempVarRulesString[j]);
			}
			
			cfgCurrentRules.put(i, tempVarRules);
			
		}
		
//		System.out.println("cfgVariables "+cfgVariables);
//		System.out.println("cfgTerminals "+cfgTerminals);
//		System.out.println("cfgCurrentRules "+cfgCurrentRules);
		
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String result = "";
		
		//add variables 
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			result += cfgVariables.get(i)+";";
		}
		
		result = result.substring(0, result.length() - 1) + "#";
		
		//add the terminals
		for(int i = 0; i < cfgTerminals.size(); i++)
		{
			result += cfgTerminals.get(i)+";";
		}
		
		result = result.substring(0, result.length() - 1) + "#";
		
		//add the rules
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			result += cfgVariables.get(i) + "/";
			
			ArrayList<String> temp = finalCfgRules.get(i);
			for(int j = 0; j < temp.size(); j++)
			{
				result += temp.get(j) + ",";
			}
			
			result = result.substring(0, result.length() - 1) + ";";
			
		}
		
		result = result.substring(0, result.length() - 1);
		
//		System.out.println("result " + result);
		
		return result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
		HashMap<Integer, ArrayList<String>> cfgRulesReplica = new HashMap<>();
		if(finalCfgRules.isEmpty())
		{
			cfgRulesReplica =  cfgCurrentRules;
		}
		else
		{
			cfgRulesReplica = finalCfgRules;
		}
//		System.out.println("cfgRulesReplica "+cfgRulesReplica);
//		ArrayList<Integer> indexesOfEpsilon = indexesContainingEpsilon(cfgRulesReplica);
		ArrayList<Integer> indexesOfEpsilon = new ArrayList<>();
		indexesContainingEpsilon(cfgRulesReplica, indexesOfEpsilon);
		
		//array to know which variable i removed from the epsilon first
		ArrayList<String> removedEpsilonFrom = new ArrayList<String>();
//		int count = 0;
		while(indexesOfEpsilon.size() > 0 )
		{
//			for(int i = 0; i < indexesOfEpsilon.size(); i++)
//			{
				//ArrayList<String> tempCfgCurrentRules = cfgCurrentRules.get(i);
				int tempIndex = indexesOfEpsilon.get(0);
//				System.out.println("indexesOfEpsilon "+indexesOfEpsilon);
//				System.out.println("tempIndex "+tempIndex);
				String currentVariableRemoving = cfgVariables.get(tempIndex);
//				System.out.println("currentVariableRemoving "+currentVariableRemoving);
				for(int j = 0; j < cfgRulesReplica.size(); j++)
				{
					ArrayList<String> tempStringArray = cfgRulesReplica.get(j);
//					ArrayList<String> newStringArray = new ArrayList<String>();
					
					if(j == tempIndex)
					{
						tempStringArray.remove("e");
//						newStringArray = tempStringArray;
						for(int k = 0; k < tempStringArray.size(); k++)
						{
							String tempString = tempStringArray.get(k);
//							System.out.println("tempString "+tempString);
							
							if(tempString.contains(currentVariableRemoving) && !tempString.equals(currentVariableRemoving))
							{
								ArrayList<String> temp2Array = getAllElimStrings(tempString, currentVariableRemoving, removedEpsilonFrom);
//								ArrayList<String> temp2Array = getAllElimStrings(tempString, currentVariableRemoving);
								addAllElimStrings(tempStringArray, temp2Array);
							}
//							System.out.println("tempStringArray in loop "+tempStringArray);
							
//							newStringArray.add(tempString);
						}
//						System.out.println("tempStringArray "+tempStringArray);
					}
					else
					{
						for(int k = 0; k < tempStringArray.size(); k++)
						{
							String tempString = tempStringArray.get(k);
//							System.out.println("tempString "+tempString);
							
							if(tempString.contains(currentVariableRemoving))
							{
								ArrayList<String> temp2Array = getAllElimStrings(tempString, currentVariableRemoving, removedEpsilonFrom);
//								ArrayList<String> temp2Array = getAllElimStrings(tempString, currentVariableRemoving);
								addAllElimStrings(tempStringArray, temp2Array);
							}
//							System.out.println("tempStringArray in loop "+tempStringArray);
							
//							newStringArray.add(tempString);
						}
//						System.out.println("tempStringArray "+tempStringArray);
					}
					
					cfgRulesReplica.replace(j, tempStringArray);
//					System.out.println("cfgRulesReplica during "+cfgRulesReplica);
				}
				
				indexesOfEpsilon.remove(0);
				removedEpsilonFrom.add(cfgVariables.get(tempIndex));
//				System.out.println("removedEpsilonFrom "+removedEpsilonFrom);
//				count++;
				//indexesOfEpsilon = indexesContainingEpsilon(cfgRulesReplica);
				indexesContainingEpsilon(cfgRulesReplica, indexesOfEpsilon);
//			}
		}
//		System.out.println("cfgRulesReplica "+cfgRulesReplica);
		for(int i = 0; i < cfgRulesReplica.size(); i++)
		{
			ArrayList<String> temp = cfgRulesReplica.get(i);
			String[] arrayToBeSorted = returnStringArray(temp);
			ArrayList<String> sortedArray = new ArrayList<String>(List.of(arrayToBeSorted));
			Collections.sort(sortedArray);
			cfgRulesReplica.replace(i, sortedArray);
		}
		
//		System.out.println("cfgRulesReplica after "+cfgRulesReplica);
		finalCfgRules = cfgRulesReplica;

	}
	
	public String[] returnStringArray(ArrayList<String> input)
	{
		String[] result = new String[input.size()];
		
		for(int i = 0; i < input.size(); i++)
		{
			result[i] = input.get(i);
		}
		
		return result;
	}
	
	public void addAllElimStrings(ArrayList<String> input, ArrayList<String> input2)
	{
		for(int i = 0; i < input2.size(); i++)
		{
			if(!input.contains(input2.get(i)))
			{
				input.add(input2.get(i));
			}
		}
	}
	
	public ArrayList<Integer> getAllElimIndexesOfChar(String input, String var)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == var.charAt(0))
			{
				result.add(i);
			}
		}
		
		return result;
	}
	
	public ArrayList<String> getAllElimStrings(String input, String var, ArrayList<String> removed)
//	public ArrayList<String> getAllElimStrings(String input, String var)
	{
		ArrayList<String> result = new ArrayList<String>();
		result.add(input);
//		System.out.println("input in elim "+input);
		ArrayList<Integer> varIndexes = new ArrayList<Integer>();
		
		varIndexes = getAllElimIndexesOfChar(input, var);
		
		for(int i = 0; i < result.size(); i++)
		{
			String temp = result.get(i);
//			System.out.println("temp in elim "+temp);
//			for(int j = 0; varIndexes.size() > 0; j++)
			while(varIndexes.size() > 0)
			{
//				System.out.println("varIndexes "+varIndexes);
				//System.out.println("varIndexes.indexOf(j) "+varIndexes.get(j));
				String newString = temp.substring(0,varIndexes.get(0)) + temp.substring(varIndexes.get(0) + 1, temp.length());
//				System.out.println("newString "+ newString);
//				if(removed.contains(temp))
//				{
//					System.out.println("dakhalt");
//				}
				if(newString.equals("") && !removed.contains(temp))
				{
					result.add("e");
				}
//				else if(newString.equals("") && removed.contains(temp))
//				{
//					System.out.println("dddd");
//				}
				else if(!result.contains(newString)&& !temp.equals(var))
				{
					result.add(newString);
				}
//				System.out.println("result "+result);
				varIndexes.remove(0);
			}
		}
		
		return result; 
	}
	
	//returns an arraylist of indexes that contain epsilon rules
//	public ArrayList<Integer> indexesContainingEpsilon(HashMap<Integer, ArrayList<String>> input)
//	{
//		ArrayList<Integer> result = new ArrayList<>();
//		
//		for(int i = 0; i < input.size(); i++)
//		{
//			ArrayList<String> tempRules = input.get(i);
//			
//			for(int j = 0; j < tempRules.size(); j++)
//			{
//				String temp = tempRules.get(j);
//				if(temp.equals("e"))
//				{
//					result.add(i);
//				}
//			}
//		}
//		
//		return result;
//	}
	
	public void indexesContainingEpsilon(HashMap<Integer, ArrayList<String>> input, ArrayList<Integer> indexesArray)
	{
//		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 0; i < input.size(); i++)
		{
			ArrayList<String> tempRules = input.get(i);
			
			for(int j = 0; j < tempRules.size(); j++)
			{
				String temp = tempRules.get(j);
				if(temp.equals("e") && !indexesArray.contains(i))
				{
					indexesArray.add(i);
				}
			}
		}
		
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
		HashMap<Integer, ArrayList<String>> cfgRulesReplica = new HashMap<>();
		if(finalCfgRules.isEmpty())
		{
			cfgRulesReplica =  cfgCurrentRules;
		}
		else
		{
			cfgRulesReplica = finalCfgRules;
		}
		
//		System.out.println("cfgRulesReplica beginning "+cfgRulesReplica);
		
		ArrayList<String> unitEliminated = new ArrayList<>();
		
		//loop on the variables
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			String currentVariable = cfgVariables.get(i);
			unitEliminated.add(currentVariable);
//			System.out.println("unitEliminated "+unitEliminated);
//			System.out.println("currentVariable "+currentVariable);
			
			
			for(int j = 0; j < cfgRulesReplica.size(); j++)
			{
				ArrayList<String> tempRules = cfgRulesReplica.get(j);
				String currentVarRules = cfgVariables.get(j);
//				System.out.println("currentVarRules "+currentVarRules);
				for(int k = 0; k < tempRules.size(); k++)
				{
					String tempString = tempRules.get(k);
//					System.out.println("tempString "+tempString);
					if(tempString.equals(currentVariable))
					{
						tempRules.remove(currentVariable);
						ArrayList<String> getArray = cfgRulesReplica.get(getIndexOfVar(currentVariable));
						addVarRules(tempRules, getArray, currentVarRules, unitEliminated);
//						System.out.println("tempRules "+tempRules);
//						System.out.println("getArray "+getArray);
//						System.out.println("currentVarRules "+currentVarRules);
//						System.out.println("cfgRulesReplica after add "+cfgRulesReplica);
						break;
					}
				}
				
				cfgRulesReplica.replace(j, tempRules);
				
			}
			
//			System.out.println("cfgRulesReplica unit during"+cfgRulesReplica);
		}
		
//		System.out.println("cfgRulesReplica "+cfgRulesReplica);
		for(int i = 0; i < cfgRulesReplica.size(); i++)
		{
			ArrayList<String> temp = cfgRulesReplica.get(i);
			String[] arrayToBeSorted = returnStringArray(temp);
			ArrayList<String> sortedArray = new ArrayList<String>(List.of(arrayToBeSorted));
			Collections.sort(sortedArray);
			cfgRulesReplica.replace(i, sortedArray);
		}
		
//		System.out.println("cfgRulesReplica after "+cfgRulesReplica);
		
		finalCfgRules = cfgRulesReplica;
		
	}
	
	public void addVarRules(ArrayList<String> input, ArrayList<String> input2, String var, ArrayList<String> unitElim)
	{
//		System.out.println("input "+input);
//		System.out.println("unitElim "+unitElim);
		for(int i = 0; i < input2.size(); i++)
		{
			if(!input.contains(input2.get(i)) && !input2.get(i).equals(var) && !unitElim.contains(input2.get(i)))
			{
				input.add(input2.get(i));
			}
		}
	}
	
	public int getIndexOfVar(String input)
	{
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			if(cfgVariables.get(i).equals(input))
				return i;
		}
			
		return 0;
	}
	
	public static void main (String args[])
	{
//		CfgEpsUnitElim a = new CfgEpsUnitElim("S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e");
//		CfgEpsUnitElim a = new CfgEpsUnitElim("S;A;B;C#a;b;c;d;x#S/aAb,ab,x,xB;A/Bc,C,c,d;B/A,AA,AC,ACA,C,CA,CAA,CAC,CACA,CC,CCA;C/A,b");
//		CfgEpsUnitElim a = new CfgEpsUnitElim ("S;G;P;E;I#l;v;z#S/E,IPl,P,S,lIz,lPG;G/GE,SEvP,lEE,zES;P/P,PG,zE;E/GlIlP,Sz,vGGl;I/GGv,vPlS,vSv,zIzG");
//		CfgEpsUnitElim a = new CfgEpsUnitElim ("S;V;Z;R;W#m;n;w#S/RnV,m;V/S,V,Z,e,n,nRmWS,nVmRR;Z/S,WwZ,ZRR,e,nSnV;R/R,SRwVZ,SW,Zw,e;W/ZVR,mWn");
//		a.eliminateEpsilonRules();
//		a.eliminateUnitRules();
//		a.toString();
//		System.out.println("res "+ a.getAllElimStrings("ASA","A"));
	}
	

}
