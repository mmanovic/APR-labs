package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function3 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += Math.pow(values[i] - i, 2);
		}
		return sum;
	}

}
