package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

public class ImplicitConstraint5 implements IConstraint {
	@Override
	public boolean satisfy(Matrix point) {
		return Math.abs(point.get(0, 1) - 1) < 1E-6;
	}

	@Override
	public double constraintValue(Matrix point, double t) {
		return t * (point.get(0, 1) - 1) * (point.get(0, 1) - 1);
	}

	@Override
	public double constraintValue(Matrix point) {
		return Math.abs(point.get(0, 1) - 1);
	}
}
