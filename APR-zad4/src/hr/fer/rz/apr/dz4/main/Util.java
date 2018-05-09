package hr.fer.rz.apr.dz4.main;

import java.util.ArrayList;
import java.util.List;

import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;
import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;

public class Util {
	public static int nOfBits(int max, int min, int precision) {
		int n = (int) Math.ceil((Math.log(Math.floor(1 + (max - min) * Math.pow(10, precision))) / Math.log(2)));
		return n;
	}

	public static List<BitVectorSolution> createBitVectorPopulation(int populationSize, int nOfBits) {
		List<BitVectorSolution> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(new BitVectorSolution(nOfBits));
		}
		return population;
	}

	public static List<DoubleArraySolution> createDoubleArrayPopulation(int populationSize, int dimension) {
		List<DoubleArraySolution> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(new DoubleArraySolution(dimension));
		}
		return population;
	}
}
