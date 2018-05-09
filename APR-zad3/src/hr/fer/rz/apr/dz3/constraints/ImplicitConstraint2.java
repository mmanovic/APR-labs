package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

public class ImplicitConstraint2 implements IConstraint {
	@Override
	public boolean satisfy(Matrix point) {
		return 2 - point.get(0, 0) >= 0;
	}

	@Override
	public double constraintValue(Matrix point, double t) {
		double tmp = 2 - point.get(0, 0);
		if (tmp <= 0) {
			return Double.MAX_VALUE;
		} else {
			return -Math.log(tmp) / t;
		}
	}

	@Override
	public double constraintValue(Matrix point) {
		return 2 - point.get(0, 0);
	}
}
