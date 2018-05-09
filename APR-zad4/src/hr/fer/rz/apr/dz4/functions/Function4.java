package hr.fer.rz.apr.dz4.functions;

public class Function4 extends AbstractFunction {

	@Override
	protected double functionValue(double[] values) {

		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += Math.pow(values[i], 2);
		}

		double a = Math.pow(sum, 0.25);
		double b = 1 + Math.pow(Math.sin(50 * Math.pow(sum, 0.1)), 2);

		return a * b;
	}

}
