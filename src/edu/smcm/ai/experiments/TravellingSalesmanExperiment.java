package edu.smcm.ai.experiments;

import edu.smcm.ai.search.BeamSearch;
import edu.smcm.ai.search.HillClimbing;
import edu.smcm.ai.search.LinearSchedule;
import edu.smcm.ai.search.LocalSearchState;
import edu.smcm.ai.search.SimulatedAnnealing;
import edu.smcm.ai.travellingsalesman.TravellingSalesmanState;

/**
 * An experiment comparing hill-climbing, beam search and simulated annealing
 * for solving the travelling salesman problem. Each technique is tried on the
 * same 100 configurations of cities and the utility of the solution found and
 * the number of states explored are compared.
 * 
 * TODO Find mean and standard deviation of each metric
 */
public class TravellingSalesmanExperiment {

	/**
	 * The number of stages used in the simulated annealing search.
	 */
	private static final int simulated_annealing_stages;

	/**
	 * The number of cities in the problems solved.
	 */
	private static final int number_of_cities;

	/**
	 * The "length" of the beam used during the beam search.
	 */
	private static final int beam_length;

	/**
	 * Number of trials to perform
	 */
	private static final int number_of_trials;
	
	static {
		simulated_annealing_stages = 1000000;
		number_of_cities = 100;
		beam_length = 10;
		number_of_trials = 100;
	}

	/**
	 * The main function which simply creates the appropriate engines, then
	 * loops creating a problem to be explored and using hill climbing, beam
	 * search and simulated annealing on them, printing out the results in a
	 * tabular form.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		LocalSearchState start; // Randomly chosen start state for each problem
		LocalSearchState hill_climbing; // Solution found by hill climbing 
		LocalSearchState simulated_annealing; // Solution found by simulated annealing
		LocalSearchState beam_search; // Solution found by beam search 
		HillClimbing hill_climbing_engine; // Object used to do hill climbing search
		SimulatedAnnealing simulated_annealing_engine; // Object used to do simulated annealing search
		BeamSearch beam_search_engine;  // Object used to do beam search

		/* Create search engines */
		hill_climbing_engine = new HillClimbing();
		simulated_annealing_engine = new SimulatedAnnealing(new LinearSchedule(simulated_annealing_stages, 2.0));
		beam_search_engine = new BeamSearch(beam_length);

		/* Print out table header */
		System.out.println("**************************************************************************************");
		System.out.println("*  Starting  *    Hill Climbing      *       Beam Search     *  Simulated Annealing  *");
		System.out.println("*   Utility  *   Utility  |  States  *   Utility  |  States  *   Utility  |  States  *");
		System.out.println("**************************************************************************************");

		/* Perform trials of search methods repeatedly */
		for (int count = 0; count < number_of_trials; count++) {

			/* Generate a problem */
			start = new TravellingSalesmanState(number_of_cities);

			/* Perform searches using engines */
			hill_climbing = (LocalSearchState) hill_climbing_engine.search(start);
			simulated_annealing = (LocalSearchState) simulated_annealing_engine.search(start);
			beam_search = (LocalSearchState) beam_search_engine.search(start);

			/* Print out table entry */
			System.out.printf("* ");
			System.out.printf("% 10.6f", -start.utility());
			System.out.printf(" * ");
			System.out.printf("% 10.6f | % 8d", -hill_climbing.utility(), hill_climbing_engine.statesEvaluated());
			System.out.print(" * ");
			System.out.printf("% 10.6f | % 8d", -beam_search.utility(), beam_search_engine.statesEvaluated());
			System.out.print(" * ");
			System.out.printf("% 10.6f | % 8d", -simulated_annealing.utility(),
					simulated_annealing_engine.statesEvaluated());
			System.out.printf(" *\n");

		}
		
		/* Print out table footer */
		System.out.println("**************************************************************************************");
	}
}
