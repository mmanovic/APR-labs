package hr.fer.rz.apr.dz4.main;

import java.util.List;

import hr.fer.rz.apr.dz4.algorithm.EliminationAlgorithm;
import hr.fer.rz.apr.dz4.creator.BitVectorPopulation;
import hr.fer.rz.apr.dz4.creator.DoubleArrayPopulation;
import hr.fer.rz.apr.dz4.creator.ICreator;
import hr.fer.rz.apr.dz4.crossover.ArithmeticalCrossover;
import hr.fer.rz.apr.dz4.crossover.BLXCrossover;
import hr.fer.rz.apr.dz4.crossover.ICrossover;
import hr.fer.rz.apr.dz4.crossover.OnePointCrossover;
import hr.fer.rz.apr.dz4.functions.Function1;
import hr.fer.rz.apr.dz4.functions.Function2;
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

public class Zadatak1 {

	public static void main(String[] args) {
		//function1();
		//function1Binary();
		 //function2();
		function3();
		//function4();
	}
	
	private static void function4() {
		int populationSize = 200;
		int maxIteration = 300000;
		double mutationProbability = 0.5;
		int tournamentSize = 3;
		double sigma = 1E-3;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function4();
		double[] mins = new double[2];
		double[] maxs = new double[2];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, 2, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new ArithmeticalCrossover();
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();		
		System.out.println(function.getCallCounter());
	}

	private static void function3() {
		int populationSize = 150;
		int maxIteration = 500000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		double sigma = 1;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function3();
		double[] mins = new double[2];
		double[] maxs = new double[2];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, 2, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new ArithmeticalCrossover();
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();		
	}

	private static void function2() {
		int populationSize = 50;
		int maxIteration = 500000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		double alpha = 0.4;
		double sigma = 0.1;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function2();
		double[] mins = new double[5];
		double[] maxs = new double[5];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, 5, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new BLXCrossover(alpha);
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();
	}

	private static void function1Binary() {
		int populationSize = 50;
		int maxIteration = 500000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		int precision = 4;
		System.out.println(Util.nOfBits(150, -50, precision));
		IDecoder<BitVectorSolution> decoder = new NaturalBinaryDecoder(-50, 150, Util.nOfBits(150, -50, precision), 2);
		// IDecoder<BitVectorSolution> decoder = new GrayBinaryDecoder(-50, 150,
		// Util.nOfBits(150, -50, precision), 2);
		IFunction function = new Function1();
		double[] mins = new double[2];
		double[] maxs = new double[2];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<BitVectorSolution> creator = new BitVectorPopulation(populationSize,
				Util.nOfBits(150, -50, precision) * 2);
		List<BitVectorSolution> population = creator.create();

		ISelection<BitVectorSolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<BitVectorSolution> crossover = new OnePointCrossover();
		IMutation<BitVectorSolution> mutation = new BitVectorMutation(mutationProbability);

		EliminationAlgorithm<BitVectorSolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		System.out.println("Dekodirano rješenje: " + new DoubleArraySolution(decoder.decode(ga.run())));
	}

	private static void function1() {
		int populationSize = 50;
		int maxIteration = 500000;
		double mutationProbability = 0.3;
		int tournamentSize = 3;
		double alpha = 0.4;
		double sigma = 0.1;

		IDecoder<DoubleArraySolution> decoder = new PassThroughDecoder();
		IFunction function = new Function1();
		double[] mins = new double[2];
		double[] maxs = new double[2];
		for (int i = 0; i < mins.length; i++) {
			mins[i] = -50;
			maxs[i] = 150;
		}
		ICreator<DoubleArraySolution> creator = new DoubleArrayPopulation(populationSize, 2, mins, maxs);
		List<DoubleArraySolution> population = creator.create();

		ISelection<DoubleArraySolution> selection = new TournamentSelection<>(tournamentSize);
		ICrossover<DoubleArraySolution> crossover = new BLXCrossover(alpha);
		IMutation<DoubleArraySolution> mutation = new DoubleArrayMutation(mutationProbability, sigma);

		EliminationAlgorithm<DoubleArraySolution> ga = new EliminationAlgorithm<>(maxIteration, population, mutation,
				crossover, selection, function, decoder);
		ga.run();

	}

}
