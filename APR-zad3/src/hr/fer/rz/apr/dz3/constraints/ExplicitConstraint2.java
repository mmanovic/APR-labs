package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

public class ExplicitConstraint2 implements IExpConstraint {
	double minValue;
	double maxValue;

	public ExplicitConstraint2(double minValue, double maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public boolean satisfy(Matrix point) {
		return point.get(0, 1) >= minValue && point.get(0, 1) <= maxValue;

	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

}
