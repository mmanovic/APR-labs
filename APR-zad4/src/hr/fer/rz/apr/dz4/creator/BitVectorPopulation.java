package hr.fer.rz.apr.dz4.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;

public class BitVectorPopulation implements ICreator<BitVectorSolution> {
	private int populationsize;
	private int nOfBits;
	private Random random = new Random();

	public BitVectorPopulation(int populationsize, int nOfBits) {
		super();
		this.populationsize = populationsize;
		this.nOfBits = nOfBits;
	}

	@Override
	public List<BitVectorSolution> create() {
		List<BitVectorSolution> population = new ArrayList<>();
		for (int i = 0; i < populationsize; i++) {
			BitVectorSolution solution = new BitVectorSolution(nOfBits);
			solution.randomize(random);
			population.add(solution);
		}
		return population;
	}

}
