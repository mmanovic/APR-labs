package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function1 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double x = values[0];
		double y = values[1];

		return 100 * Math.pow((y - x * x), 2) + Math.pow((1 - x), 2);
	}

}
