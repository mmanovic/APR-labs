package hr.fer.rz.apr.dz5.integrator;

import hr.fer.rz.apr.dz5.algebra.Matrix;

public interface IIntegrator {
	public void integrate(Matrix A, Matrix B, Matrix x, double T, double simulationTime);
}
