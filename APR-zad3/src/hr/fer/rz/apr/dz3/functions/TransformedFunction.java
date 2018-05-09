package hr.fer.rz.apr.dz3.functions;

import java.util.List;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.constraints.IConstraint;

public class TransformedFunction extends AbstractFunction {
	private IFunction function;
	private double t;
	private List<IConstraint> constraints;

	public TransformedFunction(IFunction function, double t, List<IConstraint> constraints) {
		super();
		this.function = function;
		this.t = t;
		this.constraints = constraints;
	}

	@Override
	protected double functionValue(Matrix point) {
		double value = function.valueAt(point);
		for (IConstraint constraint : constraints) {
			double constraintValue = constraint.constraintValue(point, t);
			if (constraintValue >= Double.MAX_VALUE) {
				return Double.MAX_VALUE;
			} else {
				value += constraintValue;
			}
		}
		return value;
	}

	public IFunction getFunction() {
		return function;
	}

	public void setFunction(IFunction function) {
		this.function = function;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public List<IConstraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<IConstraint> constraints) {
		this.constraints = constraints;
	}

	@Override
	protected Matrix valueOfGradientAt(Matrix point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Matrix hesseMatrix(Matrix point) {
		// TODO Auto-generated method stub
		return null;
	}

}
