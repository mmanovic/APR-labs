package hr.fer.rz.apr.dz4.solutions;

import java.util.Arrays;
import java.util.Random;

public class DoubleArraySolution extends SingleObjectiveSolution {
	public double[] values;

	public DoubleArraySolution(int size) {
		super();
		this.values = new double[size];
	}

	public DoubleArraySolution(double[] values) {
		super();
		this.values = values;
	}

	public DoubleArraySolution newLikeThis() {
		return new DoubleArraySolution(values.length);
	}

	public DoubleArraySolution duplicate() {
		return new DoubleArraySolution(Arrays.copyOf(values, values.length));
	}

	public void randomize(Random random, double[] min, double[] max) {
		int n = values.length;
		for (int i = 0; i < n; i++) {
			values[i] = min[i] + random.nextDouble() * (max[i] - min[i]);
		}
	}

	public DoubleArraySolution clipping(double min, double max) {
		int n = values.length;
		for (int i = 0; i < n; i++) {
			values[i] = Double.max(min, values[i]);
			values[i] = Double.min(max, values[i]);
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		for (double value : values) {
			string.append(value + " ");
		}
		string.append("]");
		return string.toString();
	}

}
