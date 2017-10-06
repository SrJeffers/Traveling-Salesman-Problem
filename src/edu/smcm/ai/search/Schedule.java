package edu.smcm.ai.search;

/**
 * An abstract class for the cooling schedule used in simulated annealing
 * searches.
 */
public abstract class Schedule {

	/**
	 * Compute the temperature at a given time.
	 * 
	 * The scale of the temperature versus time is not specified at this level.
	 * NOTE: At the end of the schedule this method must return 0.0, an
	 * approximation of this (as results from a computation) is not sufficient.
	 * 
	 * @param time
	 *            time at which to obtain temperature
	 * @return temperature temperature at given time
	 */
	public abstract double temperature(int time);

}
