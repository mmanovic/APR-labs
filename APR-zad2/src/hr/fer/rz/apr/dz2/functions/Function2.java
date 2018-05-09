package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function2 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double x = values[0];
		double y = values[1];
		return Math.pow(x - 4, 2) + 4 * Math.pow(y - 2, 2);
	}

}
