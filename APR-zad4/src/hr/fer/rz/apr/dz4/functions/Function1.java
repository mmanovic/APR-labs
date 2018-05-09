package hr.fer.rz.apr.dz4.functions;

public class Function1 extends AbstractFunction {

	@Override
	protected double functionValue(double[] values) {
		double x = values[0];
		double y = values[1];

		return 100 * Math.pow((y - x * x), 2) + Math.pow((1 - x), 2);
	}

	
	

}
