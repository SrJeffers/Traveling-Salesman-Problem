package edu.smcm.ai.search;

import java.util.List;
import java.util.Random;

import edu.smcm.ai.travellingsalesman.TravellingSalesmanState;

/**
 * Implemementation of the Simulating Annealing Search. See "Artificial
 * Intelligence: A Modern Approach" (Third Edition) by Russell and Norvig p.
 * 126.
 */
public class SimulatedAnnealing extends LocalSearch {

	/**
	 * A random number generator.
	 */
	private static final Random oracle;

	/**
	 * The cooling schedule.
	 */
	private Schedule schedule;

	static {
		// TODO Remove seed for production code
		oracle = new Random(938456);
	}

	/**
	 * A constructor with a cooling schedule.
	 * 
	 * @param schedule cooling schedule
	 */
	public SimulatedAnnealing(Schedule schedule) {
		this.schedule = schedule;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
		// TODO Implement Simulated Annealing Search
		states_evaluated = 0;
		
		//declarations of variables
		
		State current = start;
		State next = null;
		double E;
		double T;
		
		for(int i = 0;; i++){
			
			//giving T a value based on the current temperature.
			T = schedule.temperature(i);
			
			//if T's value is 0, then the goal state has been met.
			if (T == 0){
				return current;
			}
			
			//a list of all possible actions is generated
			List<Action> actions = ((TravellingSalesmanState) current).actions();
			
			
			states_evaluated++;
			//next is given a value based on the result of a random action to be taken. 
			next = ((TravellingSalesmanState) current).result(actions.get(oracle.nextInt(actions.size())));
			
			//E is given a value based on the difference between the value of next and current
			E = ((TravellingSalesmanState) next).utility() - ((TravellingSalesmanState) current).utility();
			
			// if next's value is greater than current, it replaces current.
			if(E > 0){
				current = next;
			}else if (oracle.nextDouble() < Math.exp(E/T)){//if next is not less, there is a chance that it will still replace current. 
				current = next;
			}			
		}
	}
}
