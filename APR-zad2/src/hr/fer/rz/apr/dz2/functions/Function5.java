package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

public class Function5 extends AbstractFunction {

	@Override
	protected double functionValue(Matrix point) {
		return Math.pow(point.get(0, 0) - 3, 2) ;
	}

}
