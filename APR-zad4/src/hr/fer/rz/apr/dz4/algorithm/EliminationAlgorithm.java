package hr.fer.rz.apr.dz4.algorithm;

import java.util.Collections;
import java.util.List;

import hr.fer.rz.apr.dz4.crossover.ICrossover;
import hr.fer.rz.apr.dz4.functions.IFunction;
import hr.fer.rz.apr.dz4.mutations.IMutation;
import hr.fer.rz.apr.dz4.selection.ISelection;
import hr.fer.rz.apr.dz4.solutions.IDecoder;
import hr.fer.rz.apr.dz4.solutions.SingleObjectiveSolution;

public class EliminationAlgorithm<T extends SingleObjectiveSolution> implements IOptAlgorithm<T> {
	private int MAX_ITERATION = 50000;
	private double EPSILON = 1E-6;
	private List<T> population;
	private IMutation<T> mutation;
	private ICrossover<T> crossover;
	private ISelection<T> selection;
	private IFunction function;
	private IDecoder<T> decoder;

	public EliminationAlgorithm(int mAX_ITERATION, List<T> population, IMutation<T> mutation, ICrossover<T> crossover,
			ISelection<T> selection, IFunction function, IDecoder<T> decoder) {
		super();
		MAX_ITERATION = mAX_ITERATION;
		this.population = population;
		this.mutation = mutation;
		this.crossover = crossover;
		this.selection = selection;
		this.function = function;
		this.decoder = decoder;
	}

	public T run() {
		for (T unit : population) {
			unit.setError(function.valueAt(decoder.decode(unit)));
		}
		int iteration = 0;
		double error = Double.MAX_VALUE;
		while (error > EPSILON && iteration < MAX_ITERATION) {

			T x = selection.select(population);
			T y = selection.select(population);
			T child = crossover.cross(x, y);
			mutation.mutate(child);
			child.setError(function.valueAt(decoder.decode(child)));

			int indexOfWorst = -1;
			double worstError = 0;
			int currentIndex = 0;
			for (T solution : population) {
				if (indexOfWorst == -1 || solution.getError() >= worstError) {
					worstError = solution.getError();
					indexOfWorst = currentIndex;
				}
				currentIndex++;
			}
			// System.out.println(indexOfWorst + " " + worstError);

			if (child.getError() <= worstError) {
				population.remove(indexOfWorst);
				population.add(child);
			}

			error = Math.min(error, Collections.min(population).getError());
			iteration++;
			if (iteration % 1000 == 0) {
				//System.out.println(error + " Iteration: " + iteration);
			}
		}

//		System.out.println("Final solution: " + Collections.min(population));
		System.out.println("Error value: " + error);
		return Collections.min(population);
	}
}
