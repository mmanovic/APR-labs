package hr.fer.rz.apr.dz4.solutions;

public class PassThroughDecoder implements IDecoder<DoubleArraySolution> {

	@Override
	public double[] decode(DoubleArraySolution solution) {
		return solution.values;
	}

	@Override
	public void decode(DoubleArraySolution solution, double[] values) {
		System.arraycopy(solution.values, 0, values, 0, values.length);
	}

}
