package hr.fer.rz.apr.dz3.functions;

import hr.fer.rz.apr.dz3.Matrix;

public class Function3 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double x = values[0];
		double y = values[1];
		return Math.pow(x - 2, 2) + Math.pow(y + 3, 2);
	}

	@Override
	public Matrix valueOfGradientAt(Matrix matrix) {
		double[] values = matrix.getValues()[0];
		double x = values[0];
		double y = values[1];

		double[][] rez = new double[2][1];
		rez[0][0] = 2 * (x - 2);
		rez[1][0] = 2 * (y + 3);
		return new Matrix(rez);
	}

	@Override
	public Matrix hesseMatrix(Matrix matrix) {
		double[][] values = new double[2][2];
		values[0][0] = 0.5;
		values[0][1] = 0;
		values[1][0] = 0;
		values[1][1] = 0.5;

		return new Matrix(values);
	}

}
