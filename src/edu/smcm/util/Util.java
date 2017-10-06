package edu.smcm.util;

public class Util {
	
	public static boolean between(int start, int value, int end) {
		return start <= value && value < end;
	}
	
	public static double maxDouble(double a, double b) {
		return a > b ? a : b;
	}
	
	public static double minDouble(double a, double b) {
		return a < b ? a : b;
	}
}
