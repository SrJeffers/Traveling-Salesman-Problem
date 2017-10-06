package edu.smcm.ai.travellingsalesman;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.search.Action;
import edu.smcm.ai.search.LocalSearchState;

/**
 * <P>
 * State of a Travelling Salesman Problem
 * </P>
 * 
 * <P>
 * The state is represented simply as a sequence of the cities that are visited.
 * This is suitable for many purposes but not for genetic algorithms which would
 * require a more sophisticated representation.
 * </P>
 */
public class TravellingSalesmanState extends LocalSearchState {

	/**
	 * Random number generator.
	 */
	private static Random oracle;

	static {
		// TODO Remove seed in production code
		oracle = new Random(782576);
	}

	/**
	 * The list of cities.
	 */
	private List<City> cities;

	/**
	 * A cache of possible actions. This is done to avoid continually
	 * regenerating the same list of actions repeatedly. This is generally a bad
	 * idea since the list could be altered in another class, but it solves some
	 * performance issues. TODO Try to find an alternative method, such as
	 * cloning.
	 */
	private List<Action> action_cache;

	/**
	 * A constructor for a random set of cities.
	 * 
	 * @param number_of_cities
	 *            number of cities to generate
	 */
	public TravellingSalesmanState(int number_of_cities) {
		cities = new ArrayList<City>(number_of_cities);
		for (int count = 0; count < number_of_cities; count++) {
			cities.add(new City());
		}

		action_cache = new ArrayList<Action>();

		for (int first = 0; first < cities.size(); first++) {
			for (int second = first + 1; second < cities.size(); second++) {
				action_cache.add(new TravellingSalesmanAction(first, second));
			}
		}
	}

	/**
	 * A copy constructor.
	 * 
	 * @param state
	 *            state to copy
	 */
	public TravellingSalesmanState(TravellingSalesmanState state) {
		cities = new ArrayList<City>(state.cities);
		action_cache = state.action_cache;
	}

	/**
	 * Accessor for the number of cities in the problem.
	 * 
	 * @return number of cities
	 */
	public int size() {
		return cities.size();
	}

	/**
	 * Accessor for a particular city.
	 * 
	 * @param index
	 *            index of city
	 * @return object representing the city
	 */
	public City city(int index) {
		return cities.get(index);
	}

	/**
	 * The utility of this state.
	 * 
	 * This is the circuit distance of all the cities in the order. Notice that
	 * distance needs to wrap around the end of the list.
	 * 
	 * NOTE: Utility is inversely proportional to distance. Don't use
	 * 1.0/distance as this involves division, use -distance.
	 * 
	 * @return utility of state
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.LocalSearchState#utility()
	 */
	public double utility() {
		double result;
		City this_city;
		City next_city;

		result = 0.0;
		for (int count = 0; count < cities.size(); count++) {
			this_city = cities.get(count);
			next_city = cities.get((count + 1) % cities.size());
			result = result + this_city.distanceTo(next_city);
		}

		return -result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearchState#actions()
	 */
	@Override
	public List<Action> actions() {
		return action_cache;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearchState#result(edu.smcm.ai.search.Action)
	 */
	@Override
	public LocalSearchState result(Action action) {
		TravellingSalesmanAction actual;
		City first_city;
		City second_city;
		TravellingSalesmanState result;

		actual = (TravellingSalesmanAction) action;
		result = new TravellingSalesmanState(this);

		first_city = cities.get(actual.first());
		second_city = cities.get(actual.second());

		result.cities.set(actual.first(), second_city);
		result.cities.set(actual.second(), first_city);

		return result;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearchState#random()
	 */
	@Override
	public LocalSearchState random() {
		List<City> permutation;
		List<City> temporary;
		TravellingSalesmanState result;
		int choice;

		temporary = new LinkedList<City>(cities);
		permutation = new ArrayList<City>(cities.size());

		while (!temporary.isEmpty()) {
			choice = oracle.nextInt(temporary.size());
			permutation.add(temporary.get(choice));
			temporary.remove(choice);
		}

		result = new TravellingSalesmanState(this);
		result.cities = permutation;

		return result;
	}

	// TODO Why can't the parameter here be TSS?
	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.LocalSearchState#equals(edu.smcm.ai.search.LocalSearchState)
	 */
	public boolean equals(LocalSearchState other) {
		boolean result;
		TravellingSalesmanState that;

		that = (TravellingSalesmanState) other;

		if (this.cities.size() != that.cities.size()) {
			result = false;
		} else {
			result = true;
			for (int i = 0; i < this.cities.size(); i++) {
				// NOTE We really do mean "reference equality" here
				result = result && this.cities.get(i) == that.cities.get(i);
			}
		}

		return result;
	}
}
