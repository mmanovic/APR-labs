package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

public class ImplicitConstraint4 implements IConstraint {
	@Override
	public boolean satisfy(Matrix point) {
		return 3 - point.get(0, 1) + 1.5 * point.get(0, 0) >= 0;
	}

	@Override
	public double constraintValue(Matrix point, double t) {
		double tmp = 3 - point.get(0, 1) + 1.5 * point.get(0, 0);
		if (tmp <= 0) {
			return Double.MAX_VALUE;
		} else {
			return -Math.log(tmp) / t;
		}
	}

	@Override
	public double constraintValue(Matrix point) {
		return 3 - point.get(0, 1) + 1.5 * point.get(0, 0);
	}
}
