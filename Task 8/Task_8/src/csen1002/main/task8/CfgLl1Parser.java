package csen1002.main.task8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Nour Khaled ElFaramawy
 * @id 49-13354
 * @labNumber 07
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	ArrayList<String> cfgVariables;
	ArrayList<String> cfgTerminals;
	HashMap<String, ArrayList<String>> cfgRules;
	HashMap<String, ArrayList<String>> cfgVarFirst;
	HashMap<String, ArrayList<String>> cfgVarFollow;
	HashMap<String, HashMap<String, String>> parsingTable;
	Stack<String> cfgStack;
	
	
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		this.cfgVariables = new ArrayList<String>();
		this.cfgTerminals = new ArrayList<String>();
		this.cfgRules = new HashMap<>();
		this.cfgVarFirst = new HashMap<>();
		this.cfgVarFollow = new HashMap<>();
		this.parsingTable = new HashMap<>();
		this.cfgStack = new Stack<String>();
		
		
		String[] cfgGrammarSplit = input.split("#");
		String cfgVarsString = cfgGrammarSplit[0];
		String cfgTermString = cfgGrammarSplit[1];
		String cfgVarRulesString = cfgGrammarSplit[2];
		String cfgFirstString = cfgGrammarSplit[3];
		String cfgFollowtring = cfgGrammarSplit[4];
		
		String[] cfgVarArray = cfgVarsString.split(";");
		String[] cfgTermArray = cfgTermString.split(";");
		String[] cfgVarRulesArray = cfgVarRulesString.split(";");
		String[] cfgVarFirstArray = cfgFirstString.split(";");
		String[] cfgVarFollowArray = cfgFollowtring.split(";");
		
//		System.out.println("cfgVarArray"+cfgVarArray);
//		System.out.println("cfgTermArray"+cfgTermArray);
//		System.out.println("cfgVarRulesArray"+cfgVarRulesArray);
//		System.out.println("cfgVarFirstArray"+cfgVarFirstArray);
//		System.out.println("cfgVarFollowArray"+cfgVarFollowArray);
		
		//getting the variables
		for(int i = 0; i < cfgVarArray.length; i++)
		{
			cfgVariables.add(cfgVarArray[i]);
		}
		
		//getting the terminals
		for(int i = 0; i < cfgTermArray.length; i++)
		{
			cfgTerminals.add(cfgTermArray[i]);
		}
		
		//getting the variable rules
		for(int i = 0; i < cfgVarRulesArray.length; i++)
		{
			String[] temp = cfgVarRulesArray[i].split("/");
			String[] tempRules = temp[1].split(",");
			ArrayList<String> varRules = new ArrayList<>();
			
			for(int j = 0; j < tempRules.length; j++)
			{
				varRules.add(tempRules[j]);
			}
			cfgRules.put(temp[0], varRules);
		}
		
		//getting the variable firsts
		for(int i = 0; i < cfgVarFirstArray.length; i++)
		{
			String[] temp = cfgVarFirstArray[i].split("/");
			String[] tempRules = temp[1].split(",");
			ArrayList<String> varRules = new ArrayList<>();
			
			for(int j = 0; j < tempRules.length; j++)
			{
				varRules.add(tempRules[j]);
			}
			cfgVarFirst.put(temp[0], varRules);
		}
		
		//getting the variable follows
		for(int i = 0; i < cfgVarFollowArray.length; i++)
		{
			String[] temp = cfgVarFollowArray[i].split("/");
			String tempRules = temp[1];
			ArrayList<String> varRules = new ArrayList<>();
			
			
			for(int j = 0; j < tempRules.length(); j++)
			{
				varRules.add(tempRules.charAt(j)+"");
			}
			cfgVarFollow.put(temp[0], varRules);
		}
		
		//initializing the parsing table
		for(int i = 0; i < cfgVariables.size(); i++)
		{
			HashMap<String, String> parsingCol = new HashMap<>();
			for(int j = 0; j < cfgTerminals.size(); j++)
			{
				parsingCol.put(cfgTerminals.get(j), null);
			}
			
			parsingCol.put("$", null);
			
			parsingTable.put(cfgVariables.get(i), parsingCol);
		}
		
		cfgTerminals.add("$");
		cfgStack.push("$");
//		
//		System.out.println("cfgVariables"+cfgVariables);
//		System.out.println("cfgTerminals"+cfgTerminals);
//		System.out.println("cfgRules"+cfgRules);
//		System.out.println("cfgVarFirst"+cfgVarFirst);
//		System.out.println("cfgVarFollow"+cfgVarFollow);
//		System.out.println("parsingTable"+parsingTable);
		
		
		//filling the parsing table 
		for(String cfgVar : cfgVariables)
		{
			HashMap<String, String> varTableRules = new HashMap<>();
			
			for(String cfgTer : cfgTerminals)
			{
				for(String currRule : cfgRules.get(cfgVar))
				{
					String ruleChar = currRule.charAt(0)+"";
					
					if(cfgTerminals.contains(ruleChar))
					{
						if(ruleChar.equals(cfgTer))
						{
							varTableRules.put(cfgTer, currRule);
						}
					}
					else if(cfgVariables.contains(ruleChar))
					{
						if(cfgVarFirst.get(ruleChar).contains(cfgTer))
						{
							varTableRules.put(cfgTer, currRule);
						}
						else if(cfgVarFirst.get(ruleChar).contains("e"))
						{
							if(currRule.length() > 1)
							{
								for(int i = 0; i < currRule.length(); i++)
								{
									String followingChar = currRule.charAt(i)+"";
									
									if(cfgVariables.contains(followingChar))
									{
										if(cfgVarFirst.get(followingChar).contains(cfgTer))
										{
											varTableRules.put(cfgTer, currRule);
											break;
										}
									}
									else if(cfgTerminals.contains(followingChar))
									{
										if(followingChar.equals(cfgTer))
										{
											varTableRules.put(cfgTer, currRule);
										}
										break;
									}
								}
							}
						}
					}
					
					else if(currRule.equals("e"))
					{
						if(cfgVarFollow.get(cfgVar).contains(cfgTer))
						{
							varTableRules.put(cfgTer, currRule);
						}
					}
					
					if (!varTableRules.containsKey(cfgTer)) {
						varTableRules.put(cfgTer, null);
					}
				}
				parsingTable.put(cfgVar, varTableRules);
			}
			
		}
		
		
		
//		System.out.println("parsingTable"+parsingTable);
//		System.out.println("cfgStack "+ cfgStack);
		
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		// TODO Auto-generated method stub
		String finalString = ""; 
		
		finalString += cfgVariables.get(0)+"";
		
		cfgStack.push(cfgVariables.get(0));
//		System.out.println("cfgStack "+cfgStack);
		
		int count = 0; 
		
		
		while(!cfgStack.isEmpty())
		{
//			System.out.println("count "+count);
			String peekChar = cfgStack.pop();
//			System.out.println("peekChar "+peekChar);
			String currChar = "$";
//			System.out.println("currChar "+currChar);
			if(count < input.length())
			{
				currChar = input.charAt(count)+"";
//				System.out.println("currChar after "+currChar);
			}
			
			if(cfgVariables.contains(peekChar))
			{
//				System.out.println("dakhal if in cfgVariables");
				String currRule = parsingTable.get(peekChar).get(currChar);
//				System.out.println("currRule "+currRule);
				if(currRule == null)
				{
					finalString += ";ERROR";
					break;
				}
				if(!currRule.equals("e"))
				{
					for(int i = currRule.length() - 1; i >= 0; i--)
					{
						cfgStack.push(currRule.charAt(i)+"");
					}
				}
				finalString += ";";
				 if (count > 0)
				 {
					 finalString += input.substring(0,count);
					 
				 }
				 

				 String tempString = "";
				 for(int i = cfgStack.size() - 1; i >= 0 ; i--)
				 {
					 tempString += cfgStack.get(i);
				 }
	             
//	             System.out.println("tempString "+tempString);
	             
	             finalString += tempString.substring(0,tempString.length()-1);
//	             System.out.println("finalString after stackcontent "+finalString);
	              
			}
			else
			{
//				System.out.println("dakhal else");
				if(peekChar.equals(currChar))
				{
					count++;
//					System.out.println("count after add "+count);
				}
				else
				{
					finalString += ";ERROR";
					break;
				}
			}
			
		}
		
		
		
//		System.out.println("finalString "+finalString);
		return finalString;
	}
	
	public static void main(String[] args)
	{
		
//		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;T#a;c;i#S/iST,e;T/cS,a#S/i,e;T/c,a#S/$ac;T/$ac");
//		CfgLl1Parser c= new CfgLl1Parser("S;A;B#a;b;c;d#S/AaS,d;A/BbBaSc,e;B/e#S/a,b,d;A/b,e;B/e#S/$c;A/a;B/ab");
//		c.parse("abadcad$");
		}

}
