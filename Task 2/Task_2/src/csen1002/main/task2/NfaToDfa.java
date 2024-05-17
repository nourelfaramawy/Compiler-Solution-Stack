package csen1002.main.task2;

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

public class NfaToDfa {

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	
//	ArrayList<Integer> hashtagPos;
	ArrayList<Integer> currentNfaStatesNumbers;
	ArrayList<String> alphabet;
	ArrayList<transitions> currentNfaTransitions;
	ArrayList<transitions> currentNfaEpsilonTransitions;
	ArrayList<transitions> currentNfaNotEpsilonTransitions;
	ArrayList<ArrayList<Integer>> acceptStates;
	ArrayList<ArrayList<Integer>> dfaStates;
	ArrayList<DFATransitions> dfaTransitions;
	ArrayList<DFATransitions> dfaDeadTransitions;
	ArrayList<Integer> dfaStartState;
	ArrayList<Integer> currentNfaAcceptStates;


	
//	HashMap<ArrayList<Integer>, ArrayList<DFATransitions>> dfaStatesMap;
	HashMap<Integer, ArrayList<Integer>> epsilonClosureMap;
	HashMap<Integer, String> addedEpsilonChanges;
	
	int nfaInitialState;
	int nfaAcceptState;
	
	DFA finalDFA;
	
//	public NfaToDfa(ArrayList<Integer> currentNfaStates, ArrayList<Character> alphabet, ArrayList<transitions> currentNfaTransitions)
//	{
//		this.currentNfaStates = currentNfaStates;
//		this.alphabet = alphabet;
//		this.currentNfaTransitions = currentNfaTransitions;
//	}
	
	//method to get the hashtag positions from the input string 
//	public ArrayList<Integer> getHashTagPos(String input)
//	{
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		
//		for(int i = 0; i < input.length(); i++)
//		{
//			char temp = input.charAt(i);
//			
//			if(temp == '#')
//				result.add(i);
//		}
//				
//		return result;
//	}
	
	public ArrayList<transitions> getEpsilonTransitions(ArrayList<transitions> input)
	{
		ArrayList<transitions> result = new ArrayList<>();
		
		for(int i = 0; i < currentNfaTransitions.size(); i++)
		{
			char temp = currentNfaTransitions.get(i).character;
			if(temp == 'e')
			{
				result.add(currentNfaTransitions.get(i));
			}
		}
		
		return result;
	}
	
	public ArrayList<transitions> getNotEpsilonTransitions(ArrayList<transitions> input)
	{
		ArrayList<transitions> result = new ArrayList<>();
		
		for(int i = 0; i < currentNfaTransitions.size(); i++)
		{
			char temp = currentNfaTransitions.get(i).character;
			if(temp != 'e')
			{
				result.add(currentNfaTransitions.get(i));
			}
		}
		
		return result;
	}
	
	//check if there is a semicolon in the accept states
	public boolean nfaAcceptStatesCount(String input)
	{
		boolean checker = false;
		
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == ';')
			{
				checker = true;
				break;
			}
		}
		
		return checker;
	}
	
	//method to split up input String
	public void processInputString(String input)
	{
//		ArrayList<Integer> hashPos = getHashTagPos(input);
//		
//		int statesIndex = hashPos.get(0);
//		int alphabetsIndex = hashPos.get(1);
//		int transitionsIndex = hashPos.get(2);
//		int startIndex = hashPos.get(3);
//		System.out.println("statesIndex "+statesIndex);
//		System.out.println("alphabetsIndex "+alphabetsIndex);
//		System.out.println("transitionsIndex "+transitionsIndex);
//		System.out.println("startIndex "+startIndex);
		
		String[] allNfaComponents = input.split("#");
//		System.out.println("states "+allNfaComponents[0]);
//		System.out.println("alphabets "+allNfaComponents[1]);
//		System.out.println("transitions "+allNfaComponents[2]);
//		System.out.println("start state "+allNfaComponents[3]);
//		System.out.println("acceptstates "+allNfaComponents[4]);
		
//		String getStates = input.substring(0,statesIndex);
		String getStates = allNfaComponents[0];
		String[] nfa_states = getStates.split(";");
		
		//get the nfa states numbers
		for(int i = 0; i < nfa_states.length; i++)
		{
			currentNfaStatesNumbers.add(Integer.parseInt(nfa_states[i]));
		}
		
//		String languageAlphabets = input.substring(statesIndex + 1,alphabetsIndex);
		String languageAlphabets = allNfaComponents[1];
		String[] language_alphabets = languageAlphabets.split(";");
		
		//get the alphabets
		for(int i = 0; i < language_alphabets.length; i++)
		{
			alphabet.add(language_alphabets[i]);
		}
		
//		System.out.println("input.substring(transitionsIndex + 1, startIndex) "+input.substring(transitionsIndex + 1, startIndex));
//		System.out.println(input.substring(startIndex + 1, input.length()));
//		nfaInitialState = Integer.parseInt(input.substring(transitionsIndex + 1, startIndex));
		nfaInitialState = Integer.parseInt(allNfaComponents[3]);
//		nfaAcceptState = Integer.parseInt(input.substring(startIndex + 1, input.length()));
		String nfaAcceptStatesString = allNfaComponents[4];
//		System.out.println("nfaAcceptStatesString "+nfaAcceptStatesString);
		boolean acceptStatesChecker = nfaAcceptStatesCount(nfaAcceptStatesString);
		
		if(acceptStatesChecker)
		{
			String[] nfa_accept_states_array = nfaAcceptStatesString.split(";");
			for(int i = 0; i < nfa_accept_states_array.length; i++)
			{
				currentNfaAcceptStates.add(Integer.parseInt(nfa_accept_states_array[i]));
			}
		}
		else
		{
//			currentNfaAcceptStates.add(Integer.parseInt(input.substring(startIndex + 1, input.length())));
			currentNfaAcceptStates.add(Integer.parseInt(nfaAcceptStatesString));
		}
		
//		System.out.println("currentNfaAcceptStates: "+ currentNfaAcceptStates.toString());
		
		//get the nfa transitions
//		String nfaTransitionString = input.substring(alphabetsIndex + 1,startIndex-2);
		String nfaTransitionString = allNfaComponents[2];
//		System.out.println("nfaTransitionString: "+ nfaTransitionString);
//		System.out.println("nfaInitialState: "+ nfaInitialState);
		String[] nfaTransitionsArray = nfaTransitionString.split(";");
		
		for(int i = 0; i < nfaTransitionsArray.length; i++)
		{
			String[] temp = nfaTransitionsArray[i].split(",");
			transitions temp1 = new transitions(Integer.parseInt(temp[0]),Integer.parseInt(temp[2]),temp[1].charAt(0));
			currentNfaTransitions.add(temp1);
			
		}
		
		//get epsilon transitions 
		 currentNfaEpsilonTransitions = getEpsilonTransitions(currentNfaTransitions);
		 
		 //not epsilon transitions
		 currentNfaNotEpsilonTransitions = getNotEpsilonTransitions(currentNfaEpsilonTransitions);
		
//		for(int i = 0; i < currentNfaNotEpsilonTransitions.size(); i++)
//		{
//			System.out.println("currentNfaNotEpsilonTransitions"+ " "+ currentNfaNotEpsilonTransitions.get(i).from_state +" "+ currentNfaNotEpsilonTransitions.get(i).to_state+" "+currentNfaNotEpsilonTransitions.get(i).character);
//			
//		}
		
		//fill in the hashmap of the closure with the initial state
		for(int i = 0; i < currentNfaStatesNumbers.size(); i++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(currentNfaStatesNumbers.get(i));
			epsilonClosureMap.put(currentNfaStatesNumbers.get(i), temp);
		}
		
		
		//fill in the hashmap for added 
		
//		for(int i = 0; i < currentNfaStatesNumbers.size(); i++)
//		{
//			addedEpsilonChanges.put(currentNfaStatesNumbers.get(i), "true");
//		}
		
//		System.out.println("addedEpsilonChanges hashmap: "+addedEpsilonChanges.toString());
		

		
		//add the epsilon closures

			for(int i = 0; i < epsilonClosureMap.size() ;i++)
			{
				ArrayList<Integer> temp = epsilonClosureMap.get(i);
				for(int j = 0; j < temp.size(); j++)
				{
					int state = temp.get(j);
					
					ArrayList<Integer> temp2 = getCurrentStateEpsilon(state);
					//System.out.println("temp2: "+temp2.toString());
					if(temp2.size() != 0)
					{
						addIf(i,temp2);
					}
				}
				
			}
			
			
			//just to sort the epsilon closures
			for(int i = 0; i < epsilonClosureMap.size(); i++)
			{
				ArrayList<Integer> temporary = epsilonClosureMap.get(i);
				
				Collections.sort(temporary);
				
				epsilonClosureMap.replace(i, temporary);
			}
			
			
			ArrayList<Integer> first_DFA_State = epsilonClosureMap.get(nfaInitialState);
			dfaStartState = first_DFA_State;
			dfaStates.add(first_DFA_State);

			
			for(int i = 0; i < dfaStates.size(); i++)
			{
				//current state i am at 
				ArrayList<Integer> temp_state = dfaStates.get(i);
				
				//temp arraylist of all the transitions from this state 
				ArrayList<transitions> temp_state_transitions = new ArrayList<transitions>();
				
				//looping on the current state to get all transitions 
				for(int j = 0; j < temp_state.size(); j++)
				{
					int temp_num = temp_state.get(j);
					
					ArrayList<transitions> temp_num_transitions = getTransitions(temp_num);
					
					if(temp_num_transitions != null)
					{
						if(temp_num_transitions.size() != 0)
						{
							temp_state_transitions.addAll(temp_num_transitions);
						}
					}
					
				}
								
//				for(int u = 0; u < temp_state_transitions.size(); u++)
//				{
//					System.out.println(temp_state_transitions.get(u).from_state+" "+temp_state_transitions.get(u).to_state+" "+temp_state_transitions.get(u).character);
//					System.out.println("________________________________________");
//				}
				
				//split the transitions to the alphabets 
				HashMap<Character,ArrayList<transitions>> splitAlphabetTransitions = transitionsForEachAlphabet(temp_state_transitions);
				
				//loop on the alphabet hashmap to get the transitions to the states 
				for(int u = 0; u < alphabet.size(); u++)
				{
					ArrayList<transitions> temp_alpha_transitions = splitAlphabetTransitions.get(alphabet.get(u).charAt(0));
					if(temp_alpha_transitions == null || temp_alpha_transitions.size() == 0)
					{
						//create a transition to a dead state 
						DFATransitions dead_transition = new DFATransitions(temp_state, alphabet.get(u).charAt(0));
						dfaTransitions.add(dead_transition);
						dfaDeadTransitions.add(dead_transition);
					}
					else
					{
						ArrayList<Integer> temp_new_transitional_state = new ArrayList<Integer>();
						for(int h = 0; h < temp_alpha_transitions.size(); h++)
						{
							transitions temp_alpha_transition = temp_alpha_transitions.get(h);
							ArrayList<Integer> temp_num_closure = epsilonClosureMap.get(temp_alpha_transition.to_state);
							if(temp_new_transitional_state == null || temp_new_transitional_state.size() == 0)
							{
								temp_new_transitional_state.addAll(temp_num_closure);
								Collections.sort(temp_new_transitional_state);

							}
							else
							{
								
								for(int g = 0; g < temp_num_closure.size(); g++)
								{
//									System.out.println("temp_new_transitional_state "+temp_new_transitional_state);
									if(!temp_new_transitional_state.contains(temp_num_closure.get(g)))
									{
										temp_new_transitional_state.add(temp_num_closure.get(g));
										Collections.sort(temp_new_transitional_state);
									}
								}
							}
						}
						
						//after creating the new transition state we should check if it is in the dfa states or not
						
						if(!dfaStates.contains(temp_new_transitional_state))
						{
							dfaStates.add(temp_new_transitional_state);
						}
						
						//make the dfa transitions 
						DFATransitions new_dfa_transition = new DFATransitions(temp_state, temp_new_transitional_state, alphabet.get(u).charAt(0));
						dfaTransitions.add(new_dfa_transition);
						
						
					}
				}	
			}
//			for(int b = 0; b < dfaTransitions.size(); b++)
//			{
//				DFATransitions tempi = dfaTransitions.get(b);
//				System.out.println("from "+tempi.state_from+" to "+ tempi.state_to+" char "+tempi.character);
//			}
//			
//			
//			System.out.println("dfaStates "+dfaStates);
//			System.out.println("epsilonClosureMap hashmap: "+epsilonClosureMap.toString());

			
			addAcceptStates(dfaStates);
			
//			System.out.println("acceptStates "+ acceptStates);
			
			//sort the dfa states to print them correctly 
			sortDfaStatesandAcceptStates(dfaStates);
			sortDfaStatesandAcceptStates(acceptStates);
//			System.out.println("dfaStates after "+dfaStates);
//			System.out.println("acceptStates after"+ acceptStates);

			
			
		
	}
	
	//sort lists of dfastates and dfaacceptstates
	public void sortDfaStatesandAcceptStates(ArrayList<ArrayList<Integer>> input)
	{
		for(int i = 0; i < input.size(); i++)
		{
			for(int j = i + 1; j < input.size(); j++)
			{
				if(sortingHelper(input.get(i),input.get(j)) > 0)
				{
					ArrayList<Integer> swap = input.get(i);
					input.set(i, input.get(j));
					input.set(j, swap);
				}
			}
		}
	}
	
	public int sortingHelper(ArrayList<Integer> input1, ArrayList<Integer> input2)
	{
		int smallerArraySize = Math.min(input1.size(), input2.size());
		
		for(int i = 0; i < smallerArraySize; i++)
		{
			int compareVal = Integer.compare(input1.get(i), input2.get(i));
			if(compareVal != 0)
			{
				return compareVal;
			}
		}
		
		return Integer.compare(input1.size(), input2.size());
	}
	
	//after all the states are created and in dfaStates array check the accept states
	public void addAcceptStates(ArrayList<ArrayList<Integer>> input)
	{
		for(int i = 0; i< input.size(); i++)
		{
			ArrayList<Integer> temp = input.get(i);
			for(int j = 0; j < temp.size(); j++)
			{
				int temp_state = temp.get(j);
				if(currentNfaAcceptStates.contains(temp_state))
				{
					acceptStates.add(temp);
					break;
				}
			}
//			if(temp.contains(nfaAcceptState))
//			{
//				acceptStates.add(temp);
//			}
		}
	}
	
	//to separate the transitions of a current state to what goes to a and what goes to b 
	public HashMap<Character,ArrayList<transitions>> transitionsForEachAlphabet(ArrayList<transitions> input)
	{
		HashMap<Character,ArrayList<transitions>> result = new HashMap<>();
		
		for(int i = 0; i < alphabet.size(); i++)
			result.put(alphabet.get(i).charAt(0), null);
		
		for(int i = 0; i < input.size(); i++)
		{
			transitions temp = input.get(i);
			for(int j = 0; j < alphabet.size(); j++)
			{
				if(temp.character == alphabet.get(j).charAt(0))
				{
					ArrayList<transitions> temp2 = result.get(alphabet.get(j).charAt(0));
					if(temp2 == null)
					{
						ArrayList<transitions> temp3 = new ArrayList<transitions>();
						temp3.add(temp);
						result.put(alphabet.get(j).charAt(0), temp3);
					}
					else
					{
						temp2.add(temp);
						result.replace(alphabet.get(j).charAt(0),temp2);
					}
					
				}
			}
		}
		
		return result;
	}
	
	
	//to get the non epsilon closures of each num state in a dfa state
	public ArrayList<transitions> getTransitions(int input)
	{
		ArrayList<transitions> result = new ArrayList<transitions>();
		
		for(int i = 0; i < alphabet.size(); i++)
		{
			for(int j = 0; j < currentNfaNotEpsilonTransitions.size(); j++)
			{
				transitions temp = currentNfaNotEpsilonTransitions.get(j);
				
				if(temp.from_state == input && temp.character == alphabet.get(i).charAt(0))
				{
					result.add(temp);
				}
			}
		}
		
		return result;
	}
	
	//used to get all the additional closures
	public void addIf(int key, ArrayList<Integer> newClosures)
	{
		ArrayList<Integer> temp = epsilonClosureMap.get(key);
		boolean changed = false;
		for(int i = 0; i < newClosures.size(); i++)
		{
			if(!temp.contains(newClosures.get(i)))
			{
				temp.add(newClosures.get(i));
				changed = true;
			}
		}
		if(changed == true)
		{
			epsilonClosureMap.replace(key, temp);
			//addedEpsilonChanges.replace(key, "true");
		}
		if(changed == false)
		{
			addedEpsilonChanges.replace(key, "false");
		}

	}
	
	public ArrayList<Integer> getCurrentStateEpsilon(int input)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 0; i < currentNfaEpsilonTransitions.size(); i++)
		{
			if(currentNfaEpsilonTransitions.get(i).from_state == input)
			{
				result.add(currentNfaEpsilonTransitions.get(i).to_state);
			}
		}
		
		return result;
	}
	
	
	public NfaToDfa(String input) {
		// TODO Auto-generated constructor stub
		this.currentNfaStatesNumbers = new ArrayList<Integer>();
		this.alphabet = new ArrayList<String>();
		this.currentNfaTransitions = new ArrayList<transitions>();
		this.currentNfaEpsilonTransitions = new ArrayList<transitions>();
		this.currentNfaNotEpsilonTransitions = new ArrayList<transitions>();
		this.acceptStates = new ArrayList<ArrayList<Integer>>();
		this.dfaStates = new ArrayList<ArrayList<Integer>>();
		this.dfaTransitions = new ArrayList<DFATransitions>();
		this.dfaDeadTransitions = new ArrayList<DFATransitions>();
		this.dfaStartState = new ArrayList<Integer>();
		this.currentNfaAcceptStates = new ArrayList<Integer>();
		
		this.epsilonClosureMap = new HashMap<>();
		this.addedEpsilonChanges = new HashMap<>();
		
		this.finalDFA = new DFA();
		
		processInputString(input);
		
		finalDFA = new DFA(dfaStartState, dfaTransitions, acceptStates);
		
//		String testhh = toString();
//		System.out.println("testhh "+testhh);
				
		
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String finalString = "";
		
		if(dfaDeadTransitions.size() > 0)
		{
			finalString += "-1;";
		}
		
		for(int i = 0; i < dfaStates.size(); i++)
		{
			ArrayList<Integer> tempState = dfaStates.get(i);
			String backDash = "";
			for(int j = 0; j < tempState.size(); j++)
			{
				if(j == tempState.size() - 1)
				{
					backDash += tempState.get(j);
				}
				else
				{
					backDash += tempState.get(j)+"/";
				}
			}
			finalString += backDash + ";";
		}
		
		//get the states 
		finalString = finalString.substring(0, finalString.length() - 1) + "#";
		
		//get the alphabets
		for(int i = 0; i < alphabet.size(); i++)
		{
			finalString += alphabet.get(i) + ";";
		}
		
		finalString = finalString.substring(0, finalString.length() - 1) + "#";
		
		//get the dfa transitions
		if(dfaDeadTransitions.size() > 0)
		{
			for(int i = 0; i < alphabet.size(); i++)
			{
				finalString += "-1," + alphabet.get(i)+",-1"+";";
			}
		}
		
//		for(int i = 0; i < )
		
		for(int i = 0; i< dfaStates.size(); i++)
		{
			ArrayList<Integer> currentStateToPrint = dfaStates.get(i);
			for(int j = 0; j < dfaTransitions.size(); j++)
			{
				DFATransitions tempTransition = dfaTransitions.get(j);
				ArrayList<Integer> tempTransitionFromState = tempTransition.state_from;
				if(tempTransitionFromState.equals(currentStateToPrint))
				{
					finalString += returnDashedStateString(tempTransition.state_from) + "," + tempTransition.character + "," + returnDashedStateString(tempTransition.state_to)+ ";";
				}
			}
		}
		
//		for(int i = 0; i < dfaTransitions.size(); i++)
//		{
//			DFATransitions tempTransition = dfaTransitions.get(i);
//			finalString += returnDashedStateString(tempTransition.state_from) + "," + tempTransition.character + "," + returnDashedStateString(tempTransition.state_to)+ ";";
//			
//		}
		
		finalString = finalString.substring(0, finalString.length() - 1) + "#";
		
		//get start state
		finalString += returnDashedStateString(dfaStartState) + "#";
		
		//get accept states
		for(int i = 0; i < acceptStates.size(); i++)
		{
			finalString += returnDashedStateString(acceptStates.get(i)) + ";";
		}
		
		finalString = finalString.substring(0, finalString.length() - 1);
		
		return finalString;
	}
	
	public String returnDashedStateString(ArrayList<Integer> input)
	{
		String result = "";
		
		for(int i = 0; i < input.size(); i++)
		{
			if(i == input.size() - 1)
			{
				result += input.get(i);
			}
			else
			{
				result += input.get(i)+"/";
			}
		}
		
		return result;
	}
	
	public static void main (String args[])
	{
		
//		NfaToDfa test = new NfaToDfa("0;1;2;3;4;5;6;7;8;9#i;r#0,i,1;1,e,5;2,e,3;3,e,5;4,e,0;4,e,2;5,e,9;6,r,7;7,e,9;8,e,4;8,e,6#8#9");
//		NfaToDfa test = new NfaToDfa("0;1;2;3;4;5;6;7;8;9;10#b;c;g;i;j#0,b,0;0,b,3;0,b,5;0,b,7;0,b,8;0,b,9;0,b,10;0,c,0;0,c,3;0,c,5;0,c,6;0,c,10;0,e,7;0,g,3;0,g,4;0,g,6;0,g,9;0,i,0;0,i,2;0,i,3;0,i,4;0,i,6;0,i,9;0,i,10;0,j,0;0,j,2;0,j,3;0,j,6;0,j,8;0,j,10;1,b,0;1,b,1;1,b,2;1,b,6;1,b,9;1,b,10;1,c,0;1,c,3;1,c,4;1,c,5;1,c,9;1,c,10;1,g,0;1,g,3;1,g,4;1,g,5;1,g,8;1,g,9;1,g,10;1,i,0;1,i,1;1,i,5;1,i,10;1,j,1;1,j,2;1,j,4;1,j,7;1,j,10;2,b,6;2,b,7;2,b,8;2,b,9;2,c,0;2,c,1;2,c,7;2,c,10;2,e,3;2,e,5;2,g,0;2,g,1;2,g,5;2,g,6;2,i,1;2,i,3;2,i,5;2,i,7;2,i,8;2,j,0;2,j,3;2,j,5;2,j,6;3,b,0;3,b,2;3,b,3;3,b,7;3,b,8;3,b,9;3,c,0;3,c,4;3,c,8;3,c,9;3,e,2;3,g,0;3,g,6;3,g,8;3,g,9;3,i,3;3,i,4;3,i,6;3,i,7;3,i,8;3,i,10;3,j,4;3,j,5;3,j,6;3,j,7;3,j,8;4,b,1;4,b,2;4,b,6;4,b,8;4,b,9;4,c,0;4,c,2;4,c,6;4,c,7;4,c,10;4,e,5;4,g,1;4,g,2;4,g,5;4,g,6;4,g,7;4,g,8;4,g,10;4,i,1;4,i,2;4,i,3;4,i,4;4,i,6;4,i,8;4,i,10;4,j,0;4,j,1;4,j,5;4,j,7;4,j,8;4,j,9;5,b,0;5,b,2;5,b,5;5,b,7;5,c,3;5,c,4;5,c,5;5,c,6;5,c,8;5,c,9;5,e,8;5,g,3;5,g,5;5,g,8;5,g,9;5,i,2;5,i,3;5,i,6;5,i,7;5,i,8;5,i,9;5,j,1;5,j,5;5,j,6;5,j,7;5,j,8;6,b,1;6,b,2;6,b,5;6,b,9;6,c,1;6,c,3;6,c,6;6,c,8;6,g,2;6,g,3;6,g,4;6,g,6;6,g,9;6,g,10;6,i,0;6,i,2;6,i,3;6,i,4;6,i,5;6,i,8;6,i,10;6,j,1;6,j,3;6,j,5;6,j,8;7,b,0;7,b,4;7,b,5;7,b,6;7,b,7;7,c,0;7,c,4;7,c,5;7,c,7;7,c,8;7,e,1;7,g,0;7,g,2;7,g,4;7,g,6;7,g,7;7,g,8;7,g,9;7,i,1;7,i,3;7,i,4;7,i,9;7,i,10;7,j,3;7,j,6;7,j,8;7,j,10;8,b,0;8,b,4;8,b,5;8,b,7;8,b,8;8,b,9;8,b,10;8,c,1;8,c,2;8,c,6;8,c,7;8,c,8;8,c,9;8,c,10;8,g,1;8,g,2;8,g,6;8,g,8;8,g,10;8,i,0;8,i,4;8,i,9;8,i,10;8,j,0;8,j,1;8,j,4;8,j,5;8,j,6;8,j,10;9,b,1;9,b,2;9,b,3;9,b,5;9,b,8;9,b,9;9,c,0;9,c,4;9,c,5;9,c,7;9,c,8;9,c,9;9,c,10;9,e,2;9,g,1;9,g,3;9,g,4;9,g,9;9,i,5;9,i,8;9,i,9;9,i,10;9,j,0;9,j,4;9,j,7;9,j,9;10,b,2;10,b,4;10,b,6;10,b,7;10,b,8;10,b,9;10,b,10;10,c,0;10,c,1;10,c,3;10,c,7;10,c,8;10,c,10;10,e,5;10,g,0;10,g,5;10,g,7;10,g,8;10,i,0;10,i,1;10,i,2;10,i,3;10,i,4;10,i,6;10,i,9;10,j,0;10,j,2;10,j,3;10,j,8;10,j,9#10#0;4;7;10");
//		NfaToDfa test = new NfaToDfa("0;1;2;3;4;5;6;7;8;9#i;r#0,i,1;1,e,5;2,e,3;3,e,5;4,e,0;4,e,2;5,e,9;6,r,7;7,e,9;8,e,4;8,e,6#8#9");
//		NfaToDfa test = new NfaToDfa("0;1;2;3;4;5;6;7;8;9;10#a;b#0,e,1;1,b,2;2,e,3;3,e,4;3,e,9;4,e,5;4,e,7;5,a,6;6,e,4;6,e,9;7,b,8;8,e,4;8,e,9;9,a,10#0#10");


	}
	
	

}

class DFA {
	ArrayList<ArrayList<Integer>> acceptStates;
	ArrayList<DFATransitions> dfaTransitions;
	ArrayList<Integer> startState;
	
	public DFA() {
		this.startState = new ArrayList<Integer>();
		this.acceptStates = new ArrayList<ArrayList<Integer>>();
		this.dfaTransitions = new ArrayList<DFATransitions>();
	}
	
	public DFA(ArrayList<Integer> startState,ArrayList<DFATransitions> dfaTransitions, ArrayList<ArrayList<Integer>> acceptStates)
	{
		this.startState = startState;
		this.dfaTransitions = dfaTransitions;
		this.acceptStates = acceptStates;
	}
}

class DFATransitions{
	ArrayList<Integer> state_from;
	ArrayList<Integer> state_to;
	char character;
	
	public DFATransitions(char character) {
        this.state_from = new ArrayList<Integer>();
        this.state_to = new ArrayList<Integer>();
        this.character = character;
    }
	
	public DFATransitions(ArrayList<Integer> state_from, ArrayList<Integer> state_to, char character) {
        this.state_from = state_from;
        this.state_to = state_to;
        this.character = character;
    }
	
	//for dead state transitions 
	public DFATransitions(ArrayList<Integer> state_from, char character) {
        this.state_from = state_from;
        this.state_to = new ArrayList<Integer>();
        this.state_to.add(-1);
        this.character = character;
    }
	
}



class transitions{
	int from_state;
	int to_state;
	char character;

	public transitions(int from_state, int to_state, char character) {
        this.from_state = from_state;
        this.to_state = to_state;
        this.character = character;
    }
}

