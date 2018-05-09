package hr.fer.rz.apr.dz2.main;

import hr.fer.rz.apr.dz2.Matrix;
import hr.fer.rz.apr.dz2.OptimisationAlgorithms;
import hr.fer.rz.apr.dz2.functions.Function1;
import hr.fer.rz.apr.dz2.functions.IFunction;

public class Zadatak4 {

	public static void main(String[] args) {
		IFunction function = new Function1();
		double[][] values = new double[1][2];
		values[0][0] = 0.5;
		values[0][1] = 0.5;
		Matrix startPoint = new Matrix(values);

		for (int i = 1; i <= 20; i++) {
			OptimisationAlgorithms.MOVE_SIMPLEX = i;
			System.out.println("Simplex pretraživanje za funkciju kreæe iz toèke " + startPoint);
			System.out.println("Pronaðeni minimum je " + OptimisationAlgorithms.simplexAlgorithm(function, startPoint)
					+ " u " + function.getCallCounter() + " evaluacija funkcije.");
			function.resetCallCounter();
		}
		System.out.println("=======================================================");
		values = new double[1][2];
		values[0][0] = 20;
		values[0][1] = 20;
		startPoint = new Matrix(values);
		for (int i = 1; i <= 20; i++) {
			OptimisationAlgorithms.MOVE_SIMPLEX = i;
			System.out.println("Simplex pretraživanje za funkciju kreæe iz toèke " + startPoint);
			System.out.println("Pronaðeni minimum je " + OptimisationAlgorithms.simplexAlgorithm(function, startPoint)
					+ " u " + function.getCallCounter() + " evaluacija funkcije.");
			function.resetCallCounter();
		}
	}

}
