package hr.fer.rz.apr.dz5.main;

import java.io.IOException;

import hr.fer.rz.apr.dz5.algebra.Matrix;
import hr.fer.rz.apr.dz5.integrator.IIntegrator;
import hr.fer.rz.apr.dz5.integrator.RungeKuttaIntegrator;
import hr.fer.rz.apr.dz5.integrator.TrapeseIntegrator;

public class Zadatak4 {

	public static void main(String[] args) throws IOException {
		Matrix A = Matrix.loadFromFile("./files/zad4A.txt");
		Matrix B = Matrix.loadFromFile("./files/zad4B.txt");
		Matrix x = Matrix.loadFromFile("./files/zad4x.txt");
		// IIntegrator integrator = new RungeKuttaIntegrator();
		IIntegrator integrator = new TrapeseIntegrator();
		integrator.integrate(A, B, x, 0.1, 10);
	}

}
