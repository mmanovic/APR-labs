package hr.fer.rz.apr.dz4.main;

import java.util.Arrays;
import java.util.List;

import hr.fer.rz.apr.dz4.algorithm.EliminationAlgorithm;
import hr.fer.rz.apr.dz4.creator.DoubleArrayPopulation;
import hr.fer.rz.apr.dz4.creator.ICreator;
import hr.fer.rz.apr.dz4.crossover.ArithmeticalCrossover;
import hr.fer.rz.apr.dz4.crossover.ICrossover;
import hr.fer.rz.apr.dz4.functions.Function3;
import hr.fer.rz.apr.dz4.functions.Function4;
import hr.fer.rz.apr.dz4.functions.IFunction;
import hr.fer.rz.apr.dz4.mutations.DoubleArrayMutation;
import hr.fer.rz.apr.dz4.mutations.IMutation;
import hr.fer.rz.apr.dz4.selection.ISelection;
import hr.fer.rz.apr.dz4.selection.TournamentSelection;
import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;
import hr.fer.rz.apr.dz4.solutions.IDecoder;
import hr.fer.rz.apr.dz4.solutions.PassThroughDecoder;

public class Zadatak2 {
	public static void main(String[] args) {
		for (int n : Arrays.asList(1, 3, 6, 10)) {
            function3(n);
        }
		System.out.println("==============================================");
		for (int n : Arrays.asList(1, 3, 6, 10)) {
            function4(n);
        }
	}

	private static void function4(int dimension) {
		int populationSize = 150;
		int maxIteration = 100000;
		double mutationProbability = 0.9;
		int tournamentSize = 3;
		double sigma = 1E-4;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function4();
		double[] mins = new double[dimension];
		double[] maxs = new double[dimension];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, dimension, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new ArithmeticalCrossover();
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();
	}

	private static void function3(int dimension) {
		int populationSize = 150;
		int maxIteration = 100000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		double sigma = 1;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function3();
		double[] mins = new double[dimension];
		double[] maxs = new double[dimension];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, dimension, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new ArithmeticalCrossover();
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();
	}
}
