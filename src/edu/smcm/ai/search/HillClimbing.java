package edu.smcm.ai.search;

import java.util.List;

import edu.smcm.ai.travellingsalesman.TravellingSalesmanState;

/**
 * Implementation of the Hill Climbing Search. See "Artificial Intelligence: A
 * Modern Approach" (Third Edition) by Russell and Norvig p. 122.
 */
public class HillClimbing extends LocalSearch {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.smcm.ai.search.LocalSearch#search(edu.smcm.ai.search.State)
	 */
	@Override
	public State search(State start) {
		// TODO Implement Hill Climbing Search
		
		//declarations of variables
		State current = start;
		State neighbor = null;
		List<Action> actions;
		states_evaluated = 0;
		double temp;
		
		for(;;){
			//nVal is the value of the neighbor with the best value
			double nVal = Integer.MIN_VALUE;
			
			//a list of actions is created based on the current location and we iterate through it.
			actions = ((TravellingSalesmanState) current).actions();
			for(Action a: actions){
				
				states_evaluated++;
				//temp is given the value of the next action in the list. Temp is used so that .utility() used rarely
				temp = ((TravellingSalesmanState) current).result(a).utility();

				
				//if the value of temp is better than the value of the previously highest neighbor, then it it's 
				//state replaces the current neighbor and nVal.
				if(nVal < temp){
					
					neighbor =  ((TravellingSalesmanState) current).result(a);
					nVal = temp;
				}
			}
			
		
			//if the neighbor has a smaller value than the current state, then the best value has been found and is returned
			if(nVal <= ((TravellingSalesmanState) current).utility()){
				return current;
			}
			//if the neighbor's value is higher than current, it replaces current
			current = neighbor;
			
		}
	}
}
