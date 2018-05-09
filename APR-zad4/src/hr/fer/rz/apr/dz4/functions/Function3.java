package hr.fer.rz.apr.dz4.functions;

public class Function3 extends AbstractFunction {

	@Override
	protected double functionValue(double[] values) {

		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += Math.pow(values[i], 2);
		}

		double a = Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5;
		double b = Math.pow(1 + 0.001 * sum, 2);

		return 0.5 + a / b;
	}

}
