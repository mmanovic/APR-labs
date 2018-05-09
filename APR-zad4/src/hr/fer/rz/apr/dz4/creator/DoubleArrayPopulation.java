package hr.fer.rz.apr.dz4.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;

public class DoubleArrayPopulation implements ICreator<DoubleArraySolution> {
	private int populationSize;
	private int dimension;
	private double[] mins;
	private double[] maxs;

	public DoubleArrayPopulation(int populationSize, int dimension, double[] mins, double[] maxs) {
		super();
		this.populationSize = populationSize;
		this.dimension = dimension;
		this.mins = mins;
		this.maxs = maxs;
	}

	@Override
	public List<DoubleArraySolution> create() {
		List<DoubleArraySolution> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			DoubleArraySolution solution = new DoubleArraySolution(dimension);
			solution.randomize(new Random(), mins, maxs);
			population.add(solution);

		}
		return population;
	}

}
