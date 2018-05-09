package hr.fer.rz.apr.dz4.mutations;

import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;

public class DoubleArrayMutation implements IMutation<DoubleArraySolution> {

	private double mutationProbability;
	private double sigma;
	private Random random = new Random();

	public DoubleArrayMutation(double mutationProbability, double sigma) {
		super();
		this.mutationProbability = mutationProbability;
		this.sigma = sigma;
	}

	@Override
	public void mutate(DoubleArraySolution solution) {
		double[] values = solution.values;
		for (int i = 0; i < values.length; i++) {
			if (random.nextDouble() <= mutationProbability) {
				values[i] += random.nextGaussian() * sigma;
			}
		}
		solution.clipping(-50, 150);
	}

}
