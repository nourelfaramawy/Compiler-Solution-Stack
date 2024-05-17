package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Write your info here
 *
 * @name Nour Khaled ElFaramawy
 * @id 49-13354
 * @labNumber 07
 */

public class RegExToNfa {

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 *
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */

	Stack <NFA> nfas;
	int expressionSize;
	int lastStateCount;
	String alphabets;
	String regularExpression;
	ArrayList<Integer> statesToLoopInString = new ArrayList<Integer>();


	//this gets the alphabet and regular expression
	public void getAlphabet(String input)
	{

		int hashIndex = 0;

		for(int i = 0; i < input.length();i++)
		{
			if(input.charAt(i) == '#')
			{
				hashIndex = i;
				break;
			}
		}

		for(int i=0;i<hashIndex;i++)
		{
			char temp = input.charAt(i);

			if(temp != ';')
			{
				if(temp != '#')
				{
					if(i == hashIndex - 1)
					{
						alphabets += temp;
					}
					else
					{
						alphabets += temp + ";";
					}
				}
			}
		}
//		System.out.println("alphabets: " + alphabets);

		String regEx = "";

		for(int i = hashIndex + 1; i < input.length(); i++)
			regEx += input.charAt(i);

//		System.out.println("regEx: " + regEx);

		regularExpression = regEx;
	}

//	prints stack just for testing
//	public void printStack(Stack input)
//	{
//		Stack temp = input;
//		for(int i=0;!temp.isEmpty();i++)
//		{
//			NFA poppedNFA = (NFA) temp.pop();
//
//			System.out.println("starting state: "+ poppedNFA.starting_state);
//			System.out.println("ending state: "+ poppedNFA.ending_state);
//			for(int j=0;j<poppedNFA.nfa_transitions.size();j++)
//				System.out.println("state from: "+ poppedNFA.nfa_transitions.get(j).from_state +
//						" "+ "state to: "+ poppedNFA.nfa_transitions.get(j).to_state +
//						" "+ "symbol: "+ poppedNFA.nfa_transitions.get(j).character);
//
//			System.out.println("____________________________________________");
//
//		}
//	}

	//method to get all the available states
	public String getStates(ArrayList<transitions> nfaTransitions )
	{
		String availableStates = "";
		ArrayList<Integer> existingStates = new ArrayList<Integer>();

		for(int i = 0; i < nfaTransitions.size(); i++)
		{
			transitions temp = nfaTransitions.get(i);
			if(!existingStates.contains(temp.from_state))
			{
				existingStates.add(temp.from_state);
			}
			if(!existingStates.contains(temp.to_state))
			{
				existingStates.add(temp.to_state);
			}
		}

//		System.out.println("existingStates: " + existingStates.toString());
		statesToLoopInString = existingStates;
//		System.out.println("statesToLoopInString: " + statesToLoopInString.toString());

		for(int i = 0; i < existingStates.size(); i++)
		{
			if(i == existingStates.size() - 1)
				availableStates += existingStates.get(i);
			else
				availableStates += existingStates.get(i)+";";
		}

//		System.out.println("availableStates: "+availableStates);

		return availableStates;
	}

	public RegExToNfa(String input) {
		// TODO Auto-generated constructor stub
		alphabets = "";
		regularExpression = "";
		getAlphabet(input);

//		expressionSize = input.length();
		expressionSize = regularExpression.length();
//		System.out.println("expressionSize "+ expressionSize);
//		System.out.println("regularExpression "+regularExpression);
//		System.out.println("regularExpressionSize "+ regularExpression.length());
        nfas = new Stack <NFA>();
        lastStateCount = 0;

        for(int i=0;i<expressionSize;i++)
        {
        	char temp = regularExpression.charAt(i);

        	if((temp >= 'a'&& temp <= 'z'))
        	{
        		NFA newNFA = new NFA(temp, lastStateCount);
        		lastStateCount += 2;
        		nfas.push(newNFA);
        	}
        	else if(temp == '*')
        	{
        		NFA oldNFA = nfas.pop();
        		int old_start_state = oldNFA.starting_state;
        		int old_ending_state = oldNFA.ending_state;
        		int new_start_state = lastStateCount;
        		int new_end_state = lastStateCount + 1;

        		oldNFA.nfa_transitions.add(new transitions(old_ending_state, old_start_state,'e'));
        		oldNFA.nfa_transitions.add(new transitions(new_start_state, old_start_state,'e'));
        		oldNFA.nfa_transitions.add(new transitions(new_start_state, new_end_state,'e'));
        		oldNFA.nfa_transitions.add(new transitions(old_ending_state, new_end_state,'e'));

        		oldNFA.starting_state = new_start_state;
        		oldNFA.ending_state = new_end_state;

        		lastStateCount += 2;

        		nfas.push(oldNFA);

        	}
        	else if(temp == '|')
        	{
        		NFA oldNFA2 = nfas.pop();
        		NFA oldNFA1 = nfas.pop();

        		int new_start_state = lastStateCount;
        		int new_end_state = lastStateCount + 1;

        		NFA newNFA = new NFA();
        		newNFA.starting_state = new_start_state;
        		newNFA.ending_state = new_end_state;

        		for(int j = 0; j < oldNFA1.nfa_transitions.size();j++)
        		{
        			newNFA.nfa_transitions.add(oldNFA1.nfa_transitions.get(j));
        		}

        		for(int j = 0; j < oldNFA2.nfa_transitions.size();j++)
        		{
        			newNFA.nfa_transitions.add(oldNFA2.nfa_transitions.get(j));
        		}

        		newNFA.nfa_transitions.add(new transitions(new_start_state, oldNFA1.starting_state,'e'));
        		newNFA.nfa_transitions.add(new transitions(new_start_state, oldNFA2.starting_state,'e'));
        		newNFA.nfa_transitions.add(new transitions(oldNFA1.ending_state, new_end_state,'e'));
        		newNFA.nfa_transitions.add(new transitions(oldNFA2.ending_state, new_end_state,'e'));

        		lastStateCount += 2;

        		nfas.push(newNFA);

        	}
        	else if(temp == '.')
        	{
        		NFA nfa2 = nfas.pop();
        		NFA nfa1 = nfas.pop();

        		int nfa1_end_state = nfa1.ending_state;
        		int nfa2_start_state = nfa2.starting_state;

        		NFA newNFA = new NFA();
        		newNFA.starting_state = nfa1.starting_state;
        		newNFA.ending_state = nfa2.ending_state;

        		for(int j = 0;j < nfa1.nfa_transitions.size(); j++)
        		{
        			newNFA.nfa_transitions.add(nfa1.nfa_transitions.get(j));
        		}

        		for(int j = 0; j < nfa2.nfa_transitions.size(); j++)
        		{
        			if(nfa2.nfa_transitions.get(j).from_state == nfa2_start_state)
        			{
        				newNFA.nfa_transitions.add(new transitions(nfa1_end_state,nfa2.nfa_transitions.get(j).to_state,nfa2.nfa_transitions.get(j).character));
        			}
        			else
        			{
        				newNFA.nfa_transitions.add(nfa2.nfa_transitions.get(j));
        			}
        		}

        		nfas.push(newNFA);

        	}

        }
//         printStack(nfas);
//        NFA b = nfas.pop();
//        String a = getStates(b.nfa_transitions);
//        System.out.println("a "+a);

//        String a = toString();


	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String finalString = "";
		NFA finalNFA = nfas.pop();

		String finalStates = getStates(finalNFA.nfa_transitions);
		String finalAlphabets = alphabets;

		finalString = finalStates + "#" + finalAlphabets + "#" ;

		for(int i = 0; i < statesToLoopInString.size(); i++)
		{
			int tempState = statesToLoopInString.get(i);

			for(int j = 0; j < finalNFA.nfa_transitions.size(); j++)
			{
				transitions tempTransition = finalNFA.nfa_transitions.get(j);

				if(tempTransition.from_state == tempState)
				{
					finalString += tempTransition.from_state + "," + tempTransition.character
							+ "," + tempTransition.to_state + ";";
				}

			}
		}

		finalString = finalString.substring(0,finalString.length()-1)+"#"+finalNFA.starting_state+"#"
				+finalNFA.ending_state;

//		System.out.println("finalString "+finalString);


		return finalString;
	}

	public static void main (String args[])
	{
//		RegExToNfa test = new RegExToNfa("eab*|.a.*");
//		RegExToNfa test = new RegExToNfa("t;w;x#wt|*x.");


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

class NFA {
	int starting_state;
	int ending_state;
	ArrayList<transitions> nfa_transitions;

	public NFA()
	{
	    this.starting_state = 0;
	    this.ending_state = 0;
	    this.nfa_transitions = new ArrayList<transitions>();
	}

	public NFA(char character, int last_state) {
        this.starting_state = last_state;
        this.ending_state = last_state + 1;
        this.nfa_transitions = new ArrayList<transitions>();
        this.nfa_transitions.add(new transitions(starting_state, ending_state, character));
    }



}
