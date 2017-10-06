package edu.smcm.ai.travellingsalesman;

import edu.smcm.ai.search.Action;

/**
 * <P>
 * Action in the Travelling Salesman Problem.
 * </P>
 * 
 * <P>
 * Simply a swap of two cities in the sequence of cities. This works for a local
 * search in a Cartesian space, but wouldn't work for other problems and search
 * techniques, such as genetic algorithms.
 * </P>
 */
public class TravellingSalesmanAction extends Action {

	/**
	 * First city in the swap.
	 */
	private int first;

	/**
	 * Second city in the swap.
	 */
	private int second;

	/**
	 * A constructor.
	 * 
	 * @param first
	 *            one city to be swapped
	 * @param second
	 *            the other city to be swapped
	 */
	public TravellingSalesmanAction(int first, int second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Accessor for the first city.
	 * 
	 * @return first city
	 */
	public int first() {
		return first;
	}

	/**
	 * Accessor for the second city.
	 * 
	 * @return second city
	 */
	public int second() {
		return second;
	}
}
