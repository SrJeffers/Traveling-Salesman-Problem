package edu.smcm.ai.search;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.travellingsalesman.TravellingSalesmanState;

/**
 * <P>
 * An implementation of the Beam Search.
 * </P>
 *
 * <P>
 * There is no pseudocode for the local beam search in "Artificial Intelligence:
 * A Modern Approach" (Third Edition) by Russell and Norvig. However, it is well
 * described on pp. 125-126. The description is as follows:
 * </P>
 * 
 * <BLOCKQUOTE> The <B>local beam search</B> algorithm keeps track of <I>k</I>
 * states rather than just one. It begins with <I>k</I> randomly generated
 * states. At each step, all the successors of all <I>k</I> states are
 * generated. If any one is a goal, the algorithm halts. Otherwise, it selects
 * the <I>k</I> best from the complete list and repeats. </BLOCKQUOTE>
 */
public class BeamSearch extends LocalSearch {

	/**
	 * The length of the beam.
	 */
	private int beam_length;

	/**
	 * A constructor.
	 * 
	 * @param beam_length
	 *            length of the beam
	 */
	public BeamSearch(int beam_length) {
		this.beam_length = beam_length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
		// TODO Implement Beam Search
		
		states_evaluated = 0;
		
		//declarations of variables
		State max = null;
		double maxVal = -10000000;//keeps the highest value of states
		double prevVal = -10000000;//The highest value from the previous generation
		double tempVal;// used to cut down on the number of times .utility() is called
		double bestVal = -100000;//used to keep the highest value in a group of states
		
		ArrayList<State> generations = new ArrayList<State>();
		ArrayList<State> generations2 = new ArrayList<State>();
		ArrayList<State> nextGeneration = new ArrayList<State>();
		
		//the first generation of random states is generated and added to the generations ArrayList
		for(int i = 0; i < beam_length; i++){
			
			states_evaluated++;
			generations.add(((TravellingSalesmanState) start).random());
		
		}
		
		
		
		//the highest value of the first generation is found and placed in prev
		for(State gen: generations){
			tempVal = ((TravellingSalesmanState) gen).utility();
			if(tempVal> prevVal){
				prevVal = tempVal;
			}
		}
		
		for(;;){
			//each state in the current generation is iterated through
			for(State gen: generations){
				
				//the possible actions for each state is generated
				List<Action> actions = ((TravellingSalesmanState) gen).actions();
				
				//each action in the state is added to the generations2 list
				for(Action a: actions){
					
					states_evaluated++;
					generations2.add(((TravellingSalesmanState) gen).result(a));
				}				
				
				
				max = null;
				maxVal = -10000000;
				
				//for loop moves through each of the states created for the current generation
				for(State gen2: generations2){
					
					//tempVal is used to minimize the use of .utility()
					tempVal = ((TravellingSalesmanState) gen2).utility();
					
					//if the lowest value has been reached, then the current state is returned.  
					if(tempVal == prevVal){
						return gen2;
					}else if(maxVal < tempVal){//if tempVal is higher than the current max, it is set as the new max 
						max = gen2;
						maxVal = tempVal;
					}
										
				}
				
				//the max value from generations2 is added to nextGeneration
				nextGeneration.add(max);
				
				//the maximum value from the current generation is placed in bestVal
				if (maxVal > bestVal){
					bestVal = maxVal;
				}
				 
				generations2.clear();
			}
			
			//prevVal is replaced by the best value from the nextgeneration.
			prevVal = bestVal;			
			
			generations.clear();
			//The next generation of states replaces the current generation
			for(State next : nextGeneration){
				generations.add(next);
				
			}
			nextGeneration.clear();
		}
		
		
	}
}
