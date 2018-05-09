package hr.fer.rz.apr.dz4.functions;

public class Function2 extends AbstractFunction {

	@Override
	protected double functionValue(double[] values) {
		double result = 0;
		for (int i = 1; i <= values.length; i++) {
			result += Math.pow(values[i - 1] - i, 2);
		}
		return result;
	}

}
