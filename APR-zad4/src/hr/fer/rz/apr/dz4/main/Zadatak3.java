package hr.fer.rz.apr.dz4.main;

import java.util.Arrays;
import java.util.List;

import hr.fer.rz.apr.dz4.algorithm.EliminationAlgorithm;
import hr.fer.rz.apr.dz4.creator.BitVectorPopulation;
import hr.fer.rz.apr.dz4.creator.DoubleArrayPopulation;
import hr.fer.rz.apr.dz4.creator.ICreator;
import hr.fer.rz.apr.dz4.crossover.ArithmeticalCrossover;
import hr.fer.rz.apr.dz4.crossover.ICrossover;
import hr.fer.rz.apr.dz4.crossover.OnePointCrossover;
import hr.fer.rz.apr.dz4.functions.Function3;
import hr.fer.rz.apr.dz4.functions.Function4;
import hr.fer.rz.apr.dz4.functions.IFunction;
import hr.fer.rz.apr.dz4.mutations.BitVectorMutation;
import hr.fer.rz.apr.dz4.mutations.DoubleArrayMutation;
import hr.fer.rz.apr.dz4.mutations.IMutation;
import hr.fer.rz.apr.dz4.selection.ISelection;
import hr.fer.rz.apr.dz4.selection.TournamentSelection;
import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;
import hr.fer.rz.apr.dz4.solutions.DoubleArraySolution;
import hr.fer.rz.apr.dz4.solutions.IDecoder;
import hr.fer.rz.apr.dz4.solutions.NaturalBinaryDecoder;
import hr.fer.rz.apr.dz4.solutions.PassThroughDecoder;

public class Zadatak3 {

	public static void main(String[] args) {
		for (int n : Arrays.asList(3, 6)) {
			System.out.println("Double array solution, Schaffer f6 function, Dimension: " + n);
			for (int i = 0; i < 10; i++) {
				function3(n);
			}
			System.out.println();
			System.out.println("Bitvector solution, Schaffer f6 function, Dimension: " + n);
			for (int i = 0; i < 10; i++) {
				function3Binary(n);
			}
			System.out.println();


		}
		System.out.println("==============================================");
		System.out.println();
		for (int n : Arrays.asList(3, 6)) {
			System.out.println("Double array solution, Schaffer f7 function, Dimension: " + n);
			for (int i = 0; i < 10; i++) {
				function4(n);
			}
			System.out.println();
			System.out.println("Bitvector solution, Schaffer f7 function, Dimension: " + n);
			for (int i = 0; i < 10; i++) {
				function4Binary(n);
			}
			System.out.println();

		}
	}

	private static void function4Binary(int dimension) {
		int populationSize = 50;
		int maxIteration = 100000;
		double mutationProbability = 0.1;
		int tournamentSize = 3;
		int precision = 4;
		IDecoder<BitVectorSolution> decoder = new NaturalBinaryDecoder(-50, 150, Util.nOfBits(150, -50, precision),
				dimension);
		// IDecoder<BitVectorSolution> decoder = new GrayBinaryDecoder(-50, 150,
		// Util.nOfBits(150, -50, precision), 2);
		IFunction function = new Function4();
		double[] mins = new double[dimension];
		double[] maxs = new double[dimension];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<BitVectorSolution> creator = new BitVectorPopulation(populationSize,
				Util.nOfBits(150, -50, precision) * dimension);
		List<BitVectorSolution> population = creator.create();

		ISelection<BitVectorSolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<BitVectorSolution> crossover = new OnePointCrossover();
		IMutation<BitVectorSolution> mutation = new BitVectorMutation(mutationProbability);

		EliminationAlgorithm<BitVectorSolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		// System.out.println("Dekodirano rješenje: " + new
		// DoubleArraySolution(decoder.decode(ga.run())));
		ga.run();
	}

	private static void function3Binary(int dimension) {
		int populationSize = 50;
		int maxIteration = 100000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		int precision = 4;
		IDecoder<BitVectorSolution> decoder = new NaturalBinaryDecoder(-50, 150, Util.nOfBits(150, -50, precision),
				dimension);
		// IDecoder<BitVectorSolution> decoder = new GrayBinaryDecoder(-50, 150,
		// Util.nOfBits(150, -50, precision), 2);
		IFunction function = new Function3();
		double[] mins = new double[dimension];
		double[] maxs = new double[dimension];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<BitVectorSolution> creator = new BitVectorPopulation(populationSize,
				Util.nOfBits(150, -50, precision) * dimension);
		List<BitVectorSolution> population = creator.create();

		ISelection<BitVectorSolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<BitVectorSolution> crossover = new OnePointCrossover();
		IMutation<BitVectorSolution> mutation = new BitVectorMutation(mutationProbability);

		EliminationAlgorithm<BitVectorSolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		// System.out.println("Dekodirano rješenje: " + new
		// DoubleArraySolution(decoder.decode(ga.run())));
		ga.run();

	}

	private static void function4(int dimension) {
		int populationSize = 150;
		int maxIteration = 100000;
		double mutationProbability = 0.5;
		int tournamentSize = 3;
		double sigma = 1E-3;

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
