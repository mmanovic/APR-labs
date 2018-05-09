package hr.fer.rz.apr.dz4.crossover;

import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;

public class ArithmeticalCrossover implements ICrossover<DoubleArraySolution> {

	@Override
	public DoubleArraySolution cross(DoubleArraySolution x, DoubleArraySolution y) {
		double[] xValues = x.values;
		double[] yValues = y.values;
		int n = xValues.length;
		double[] childValues = new double[n];
		for (int i = 0; i < n; i++) {
			childValues[i] = (xValues[i] + yValues[i]) / 2;
		}
		return new DoubleArraySolution(childValues);
	}

}
