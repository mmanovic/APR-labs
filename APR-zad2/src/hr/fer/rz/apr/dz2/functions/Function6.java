package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function6 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double squaresSum = 0;
		for (double value : values) {
			squaresSum += value * value;
		}
		double x = Math.pow(Math.sin(Math.sqrt(squaresSum)), 2) - 0.5;
		double y = Math.pow(1 + 0.001 * squaresSum, 2);
		return 0.5 + x / y;
	}

}
