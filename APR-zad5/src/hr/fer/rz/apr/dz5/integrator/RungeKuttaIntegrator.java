package hr.fer.rz.apr.dz5.integrator;

import hr.fer.rz.apr.dz5.algebra.Matrix;

public class RungeKuttaIntegrator implements IIntegrator {

	@Override
	public void integrate(Matrix A, Matrix B, Matrix x, double T, double simulationTime) {
		int iterations = (int) (simulationTime / T);
		Matrix Xk = x.copy();

		for (int i = 1; i <= iterations; i++) {
			// System.out.println("Iteracija: " + i);
			// System.out.println(Xk);
			System.out.print(Xk.get(0, 0) + " " + Xk.get(1, 0) + "\n");
			Matrix m1 = A.multiply(Xk).addToThis(B);
			Matrix m2 = A.multiply(Xk.add(m1.multiply(T / 2.0))).addToThis(B);
			Matrix m3 = A.multiply(Xk.add(m2.multiply(T / 2.0))).addToThis(B);
			Matrix m4 = A.multiply(Xk.add(m3.multiply(T))).addToThis(B);

			Matrix m = m1.addToThis(m2.multiply(2.0)).addToThis(m3.multiply(2.0)).addToThis(m4);
			Xk = Xk.add(m.multiply(T / 6.0));
		}
	}
}
