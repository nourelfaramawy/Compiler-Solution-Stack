package csen1002.main.task3;

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

public class FallbackDfa {

	/**
	 * Constructs a Fallback DFA
	 * 
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	
	ArrayList<Integer> currentDfaStates;
	ArrayList<String> currentDfaAlphabets;
	ArrayList<String> currentDfaTransitions;
	ArrayList<Integer> currentDfaAcceptStates;
	
	HashMap<Integer,ArrayList<transitions>> fdfaTransitions;
	
	int currentDfaStartState;

	
	Stack<Integer> statesStack;
	
	
	public FallbackDfa(String fdfa) {
		// TODO Auto-generated constructor stub
		this.currentDfaStates = new ArrayList<Integer>();
		this.currentDfaAlphabets = new ArrayList<String>();
		this.currentDfaTransitions = new ArrayList<String>();
		this.currentDfaAcceptStates = new ArrayList<Integer>();
		this.fdfaTransitions = new HashMap<>();
		
		String[] dfaInputString = fdfa.split("#");
		String[] dfaCurrentStatesString = dfaInputString[0].split(";");
		String[] dfaCurrentAlphabetsString = dfaInputString[1].split(";");
		String[] dfaCurrentTransitionsString = dfaInputString[2].split(";");
		currentDfaStartState = Integer.parseInt(dfaInputString[3]);
		String[] dfaCurrentAcceptStatesString = dfaInputString[4].split(";");
		
		//get the available states
		for(int i = 0; i < dfaCurrentStatesString.length; i++)
		{
			currentDfaStates.add(Integer.parseInt(dfaCurrentStatesString[i]));
		}
		
		//get the available alphabets 
		for(int i = 0; i < dfaCurrentAlphabetsString.length; i++)
		{
			currentDfaAlphabets.add(dfaCurrentAlphabetsString[i]);
		}
		
		//get the available transitions
		for(int i = 0; i < dfaCurrentTransitionsString.length; i++)
		{
			currentDfaTransitions.add(dfaCurrentTransitionsString[i]);
		}
		
		//get the available accept states 
		for(int i = 0; i < dfaCurrentAcceptStatesString.length; i++)
		{
			currentDfaAcceptStates.add(Integer.parseInt(dfaCurrentAcceptStatesString[i]));
		}
		
		//set the hashmap for the fdfa transitions
		for(int i = 0; i < currentDfaStates.size(); i++)
		{
			
			ArrayList<transitions> temp = new ArrayList<>();
			for(int j = 0; j < currentDfaTransitions.size(); j++)
			{
				String[] transitionString = currentDfaTransitions.get(j).split(",");
				int from = Integer.parseInt(transitionString[0]);
				if(from == currentDfaStates.get(i))
				{
					transitions newTransition = new transitions(Integer.parseInt(transitionString[0]), Integer.parseInt(transitionString[2]), transitionString[1].charAt(0));
					temp.add(newTransition);
				}
			}
			fdfaTransitions.put(currentDfaStates.get(i), temp);
		}
		
		
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 * 
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {
		// TODO Auto-generated method stub
		String fdfaResult = "";
		statesStack = new Stack<>();
		int R = 0;
		int L = 0; 

		
		while(R < input.length())
		{
			statesStack.push(currentDfaStartState);
			
			while(L < input.length())
			{
				
				char currentChar = input.charAt(L);
				int atState = statesStack.peek();
				ArrayList<transitions> currentStateTransitionsToGetToNextState = fdfaTransitions.get(atState);
				int nextState = 0;
				for(int i = 0; i < currentStateTransitionsToGetToNextState.size(); i++)
				{
					transitions temp = currentStateTransitionsToGetToNextState.get(i);
					if(currentChar == temp.character)
					{
						nextState = temp.to_state;
						break;
					}
				}
				statesStack.push(nextState);
				L++;
				
			}
//			System.out.println("statesStack "+statesStack);
			int rejectState = statesStack.peek();
			while(L >= R) 
			{
				int poppedState = statesStack.pop();
				if(currentDfaAcceptStates.contains(poppedState))
				{
					fdfaResult += input.substring(R, L) + "," + poppedState +";";
					break;
				}
				 L--;
				
			}
//			System.out.println("L "+L);
//			System.out.println("R "+R);
//			System.out.println("fdfaResult "+fdfaResult);
//			System.out.println("statesStack "+statesStack);
			
			if(statesStack.isEmpty())
			{
				fdfaResult += input.substring(R)+ "," + rejectState + ";";
				R = input.length();
				
				break;
			}
			else
			{
				R = L;
				statesStack = new Stack<>();
			}
			
		}
		
//		System.out.println("_____________________");
//		System.out.println("L "+L);
//		System.out.println("R "+R);
//		System.out.println("fdfaResult "+fdfaResult.substring(0, fdfaResult.length()-1));
//		System.out.println("statesStack "+statesStack);
		
		
		return fdfaResult.substring(0, fdfaResult.length()-1);
	}
	
	public static void main (String args[])
	{
//		FallbackDfa test = new FallbackDfa("0;1;2;3#a;b#0,a,0;0,b,1;1,a,2;1,b,1;2,a,0;2,b,3;3,a,3;3,b,3#0#1;2");
//		test.run("abaa");
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

}
