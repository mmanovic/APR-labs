package hr.fer.rz.apr.dz4.crossover;

import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;

public class BLXCrossover implements ICrossover<DoubleArraySolution> {
	private double alpha;
	private Random random = new Random();

	public BLXCrossover(double alpha) {
		super();
		this.alpha = alpha;
	}

	@Override
	public DoubleArraySolution cross(DoubleArraySolution x, DoubleArraySolution y) {
		double[] xValues = x.values;
		double[] yValues = y.values;
		int n = xValues.length;
		double[] childValues = new double[n];
		for (int i = 0; i < n; i++) {
			double cmin = Double.min(xValues[i], yValues[i]);
			double cmax = Double.max(xValues[i], yValues[i]);
			double interval = alpha * (cmax - cmin);
			childValues[i] = random.nextDouble() * (cmax - cmin + 2 * interval) + cmin - interval;
		}
		return new DoubleArraySolution(childValues).clipping(-50, 150);
	}

}
