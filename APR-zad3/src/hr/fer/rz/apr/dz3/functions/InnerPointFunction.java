package hr.fer.rz.apr.dz3.functions;

import java.util.List;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.constraints.IConstraint;

public class InnerPointFunction extends AbstractFunction {
	List<IConstraint> constraints;

	public InnerPointFunction(List<IConstraint> constraints) {
		super();
		this.constraints = constraints;
	}

	@Override
	protected double functionValue(Matrix point) {
		double value = 0;
		for (IConstraint constraint : constraints) {
			if (constraint.constraintValue(point) < 0) {
				value -= constraint.constraintValue(point);
			}
		}
		return value;
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
