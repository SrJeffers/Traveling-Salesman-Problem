package edu.smcm.ai.search;

/**
 * A generic class for local search algorithms.
 * 
 */
public abstract class LocalSearch {
	
	/**
	 * Number of states evaluated by the search.
	 */
	protected int states_evaluated;
	
	/**
	 * Number of states evaluated during the search.
	 * 
	 * @return number of states evaluated during the search
	 */
	public int statesEvaluated() {
		return states_evaluated;
	}

	/**
	 * Search the state space using a local search algorithm.
	 * 
	 * @param start starting state for search
	 * @return goal state found
	 */
	public abstract State search(State start);
}
