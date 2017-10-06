package edu.smcm.ai.travellingsalesman;

import java.util.Random;

/**
 * <P>
 * Representation of a city.
 * </P>
 * 
 * <P>
 * A city is represented by its "latitude" and "longitude" on a grid from 0.0
 * (inclusive) to 1.0 (exclusive).
 * </P>
 *
 */
public class City {

	/**
	 * Random number generator for the generation of cities
	 */
	private static Random oracle;

	/**
	 * The latitude (y coordinate) of the city.
	 */
	private double latitude;

	/**
	 * The longitude (x coordinate) of the city.
	 */
	private double longitude;

	static {
		// TODO Remove seed for production code
		oracle = new Random(874564);
	}

	/**
	 * A constructor.
	 * 
	 * Creates a city at a random location, evenly distributed over the grid.
	 */
	public City() {
		latitude = oracle.nextDouble();
		longitude = oracle.nextDouble();
	}

	/**
	 * Compute distance between cities.
	 * 
	 * @param that
	 *            second city
	 * @return distance between the two cities
	 */
	public double distanceTo(City that) {
		double latitude_difference;
		double longitude_difference;

		latitude_difference = this.latitude - that.latitude;
		longitude_difference = this.longitude - that.longitude;

		// Squares guarantee positive results
		// Don't use Math.pow for performance reasons
		return Math.sqrt(latitude_difference * latitude_difference + longitude_difference * longitude_difference);
	}
}
