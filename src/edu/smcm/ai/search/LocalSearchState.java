package edu.smcm.ai.search;

import java.util.List;

/**
 * Abstract representation of a state for local searches.
 *
 */
public abstract class LocalSearchState extends State implements Comparable<LocalSearchState> {

	/**
	 * Utility of the state.
	 * 
	 * @return utility of the state
	 */
	public abstract double utility();

	/**
	 * Allowable actions in this state.
	 * 
	 * @return allowable actions in this state
	 */
	public abstract List<Action> actions();

	/**
	 * Result of taking an action in this state.
	 * 
	 * @param action
	 *            action to be taken
	 * @return state that results from taking action
	 */
	public abstract LocalSearchState result(Action action);

	/**
	 * Create a random state.
	 * 
	 * Create a random state in the search space.
	 * 
	 * @return random state
	 */
	public abstract LocalSearchState random();

	/**
	 * A comparison method.
	 * 
	 * This method compares the <EM>utility</EM> of the states.
	 * NOTE: This compare method is incompatible with the .equals(). That is to
	 * say compare(a, b) may be 0 but a.equals(c) may be false.
	 * 
	 * @param that other state in comparison
	 * @return result of comparing the two states
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(LocalSearchState that) {
		return Double.compare(this.utility(), that.utility());
	}

	/**
	 * Compare for equality.
	 * 
	 * Every subclass must provide an equals() method to be used in local search
	 * algorithms.
	 * 
	 * @param that
	 *            object for comparison
	 * @return true if the states are the same
	 */
	public abstract boolean equals(LocalSearchState that);
}
