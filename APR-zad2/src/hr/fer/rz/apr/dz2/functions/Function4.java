package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function4 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double x = values[0];
		double y = values[1];
		double a = x * x - y * y;
		double b = x * x + y * y;
		return Math.abs(a) + Math.sqrt(b);
	}

}
