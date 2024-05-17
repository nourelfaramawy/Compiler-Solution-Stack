package csen1002.main.task5;

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

public class CfgLeftRecElim {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	
	ArrayList<String> cfgVariables;
	ArrayList<String> cfgTerminals;
	ArrayList<String> dashedVariables;
	
	HashMap<String,ArrayList<String>> cfgCurrentRules;
	
	public CfgLeftRecElim(String cfg) {
		// TODO Auto-generated constructor stub
		this.cfgVariables = new ArrayList<>();
		this.cfgTerminals = new ArrayList<>();
		this.dashedVariables = new ArrayList<>();
		this.cfgCurrentRules = new HashMap<>();
		
		String[] cfgInputStringArray = cfg.split("#");
		String[] cfgInputVariablesArray = cfgInputStringArray[0].split(";");
		String[] cfgInputTerminalsArray = cfgInputStringArray[1].split(";");
		String[] cfgInputRulesArray = cfgInputStringArray[2].split(";");
		
		//get variables 
		for(int i = 0; i < cfgInputVariablesArray.length; i++)
		{
			cfgVariables.add(cfgInputVariablesArray[i]);
		}
		
		//get terminals
		for(int i = 0; i < cfgInputTerminalsArray.length; i++)
		{
			cfgTerminals.add(cfgInputTerminalsArray[i]);
		}
		
		//get rules
		for(int i = 0; i < cfgInputRulesArray.length; i++)
		{
			String[] varRuleSplitString = cfgInputRulesArray[i].split("/");
			String[] varRulesString = varRuleSplitString[1].split(",");
			ArrayList<String> tempArray = new ArrayList<>();
			
			for(int j = 0; j < varRulesString.length; j++)
			{
				tempArray.add(varRulesString[j]);
			}
			cfgCurrentRules.put(varRuleSplitString[0], tempArray);
		}
//		
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
		String finalString = "";
		
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			finalString += cfgVariables.get(i)+";";
		}
		
		for(int i = 0; i < dashedVariables.size(); i++)
		{
			finalString += dashedVariables.get(i)+";";
		}
		
		finalString = finalString.substring(0, finalString.length()-1)+"#";
		
		for(int i = 0; i < cfgTerminals.size(); i++)
		{
			finalString += cfgTerminals.get(i)+";";
		}
		
		finalString = finalString.substring(0, finalString.length()-1)+"#";
		
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			finalString += cfgVariables.get(i) + "/";
			
			ArrayList<String> tempArr = cfgCurrentRules.get(cfgVariables.get(i));
//			Collections.sort(tempArr);
			
			
			for(int j = 0; j < tempArr.size(); j++)
			{
				finalString += tempArr.get(j)+",";
			}
			finalString = finalString.substring(0, finalString.length()-1) + ";";
			
		}
		
		for(int i = 0; i < dashedVariables.size(); i++)
		{
			finalString += dashedVariables.get(i) + "/";
			
			ArrayList<String> tempArr = cfgCurrentRules.get(dashedVariables.get(i));
//			Collections.sort(tempArr);
			
			for(int j = 0; j < tempArr.size(); j++)
			{
				finalString += tempArr.get(j)+",";
			}
			finalString = finalString.substring(0, finalString.length()-1) + ";";
			
		}
		
		finalString = finalString.substring(0, finalString.length()-1);
		
		
//		System.out.println("finalString "+finalString);
		
		return finalString;
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			char currentVar = cfgVariables.get(i).charAt(0);
//			System.out.println("currentVar "+currentVar);
			ArrayList<String> currentVarRules = cfgCurrentRules.get(cfgVariables.get(i));
			
			if(i != 0)
			{
//				System.out.println("loop on var before ");
				//loop on the vars before 
				for(int j = 0; j < i; j++)
				{
					char varBefore = cfgVariables.get(j).charAt(0);
//					System.out.println("varBefore "+varBefore);
					ArrayList<String> newArray = new ArrayList<>();
					currentVarRules = cfgCurrentRules.get(cfgVariables.get(i));;
					//loop on curr var rules to see if it'll change
					for(int k = 0; k < currentVarRules.size(); k++)
					{
						String currentVarRuleString = currentVarRules.get(k);
//						System.out.println("currentVarRuleString "+currentVarRuleString);
						
						if(currentVarRuleString.charAt(0) == varBefore)
						{
							
							ArrayList<String> varBeforeArray = cfgCurrentRules.get(varBefore+"");
							//loop and replace 
							for(int l = 0; l < varBeforeArray.size(); l++)
							{
								String newString = varBeforeArray.get(l) + currentVarRuleString.substring(1);
								newArray.add(newString);
							}
						}
						else
						{
							newArray.add(currentVarRuleString);
						}
						
					}
//					System.out.println("newArray "+newArray);
//					System.out.println("cfgCurrentRules before newArray "+cfgCurrentRules);
					cfgCurrentRules.replace(currentVar+"", newArray);
//					System.out.println("cfgCurrentRules after newArray "+cfgCurrentRules);
					
				}
			}
			
//			System.out.println("cfgCurrentRules after if "+cfgCurrentRules);
			ArrayList<String> currVarNewArray = new ArrayList<>();
			ArrayList<String> currVarDashArray = new ArrayList<>();
			currentVarRules = cfgCurrentRules.get(currentVar+"");
			//loop on the rules to check if there is a left recursion
			for(int j = 0; j < currentVarRules.size(); j++)
			{
				String currRule = currentVarRules.get(j);
				
				
				if(currRule.charAt(0) == currentVar)
				{
					String newString = currRule.substring(1) + currentVar +"'";
					currVarDashArray.add(newString);
				}
				else
				{
					String newString = currRule + currentVar +"'";
					currVarNewArray.add(newString);
				}
			}
			//if new dash array not empty 
			if(currVarDashArray.size() > 0)
			{
				currVarDashArray.add("e");
				cfgCurrentRules.put(currentVar +"'", currVarDashArray);
				dashedVariables.add(currentVar +"'");
				cfgCurrentRules.replace(cfgVariables.get(i), currVarNewArray);
			}
			
//			System.out.println("cfgCurrentRules after each "+cfgCurrentRules);
		}
		
	}
	
	public static void main (String args[])
	{
//		CfgLeftRecElim a = new CfgLeftRecElim("S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
//		a.eliminateLeftRecursion();
//		a.toString();
	}

}
