package edu.smcm.ai.search;

/**
 * <P>
 * A linear cooling schedule.
 * </P>
 * 
 * <P>
 * The temperature drops linearly over a given number of steps from given
 * maximum.
 * </P>
 */
public class LinearSchedule extends Schedule {

	/**
	 * The number of steps.
	 */
	private int steps;
	
	/**
	 * The initial (maximum) value.
	 */
	private double maximum;

	/**
	 * A constructor.
	 * 
	 * @param steps number of steps in schedule
	 * @param maximum initial (maximum) value
	 */
	public LinearSchedule(int steps, double maximum) {
		this.steps = steps;
		this.maximum = maximum;
	}

	/* (non-Javadoc)
	 * @see edu.smcm.ai.search.Schedule#temperature(int)
	 */
	@Override
	public double temperature(int time) {
		return maximum * (double) (steps - time) / (double) steps;
	}
}
