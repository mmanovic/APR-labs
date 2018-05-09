package hr.fer.rz.apr.dz3.functions;

import hr.fer.rz.apr.dz3.Matrix;

public class Function1 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		double[] values = point.getValues()[0];
		double x = values[0];
		double y = values[1];

		return 100 * Math.pow((y - x * x), 2) + Math.pow((1 - x), 2);
	}

	@Override
	public Matrix valueOfGradientAt(Matrix matrix) {
		double[] values = matrix.getValues()[0];
		double x = values[0];
		double y = values[1];

		double[][] rez = new double[2][1];
		rez[0][0] = -400 * x * (y - x * x) - 2 * (1 - x);
		rez[1][0] = 200 * (y - x * x);
		return new Matrix(rez);
	}

	@Override
	public Matrix hesseMatrix(Matrix matrix) {
		double[] point = matrix.getValues()[0];
		double x = point[0];
		double y = point[1];
		double[][] values = new double[2][2];
		values[0][0] = 400 * (3 * x * x - y) + 2;
		values[0][1] = -400 * x;
		values[1][0] = -400 * x;
		values[1][1] = 200;

		double determinant = values[0][0] * values[1][1] - values[0][1] * values[1][0];
		double tmp = values[0][0];
		values[0][0] = values[1][1] / determinant;
		values[0][1] /= -determinant;
		values[1][0] /= -determinant;
		values[1][1] = tmp / determinant;
		return new Matrix(values);
	}

	

}
