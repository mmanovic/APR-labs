package hr.fer.rz.apr.dz5.integrator;

import hr.fer.rz.apr.dz5.algebra.Matrix;

public class TrapeseIntegrator implements IIntegrator {

	@Override
	public void integrate(Matrix A, Matrix B, Matrix x, double T, double simulationTime) {
		int dimension = x.getRows();
		Matrix U = Matrix.getUnitMatrix(dimension);
		Matrix AT = A.multiply(T / 2);
		Matrix IsubAT = U.subtract(AT);
		Matrix IaddAT = U.add(AT);
		Matrix IsubATInv = IsubAT.inverse();
		if (IsubATInv == null) {
			throw new IllegalArgumentException("Matrix are not invertible.");
		}

		Matrix R = IsubATInv.multiply(IaddAT);
		Matrix S = IsubATInv.multiply(T / 2).multiply(B);

		int iterations = (int) (simulationTime / T);
		Matrix Xk = x.copy();

		for (int i = 1; i <= iterations; i++) {
			// System.out.println("Iteracija: " + i);
			// System.out.println(Xk);
			System.out.print(Xk.get(0, 0) + " " + Xk.get(1, 0) + "\n");
			Xk = R.multiply(Xk).add(S);
		}
	}

}
