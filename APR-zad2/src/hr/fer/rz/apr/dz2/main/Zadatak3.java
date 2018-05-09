package hr.fer.rz.apr.dz2.main;

import hr.fer.rz.apr.dz2.Matrix;
import hr.fer.rz.apr.dz2.OptimisationAlgorithms;
import hr.fer.rz.apr.dz2.functions.Function4;
import hr.fer.rz.apr.dz2.functions.IFunction;

public class Zadatak3 {

	public static void main(String[] args) {
		IFunction function = new Function4();
		double[][] values = new double[1][2];
		values[0][0] = 5;
		values[0][1] = 5;
		Matrix startPoint = new Matrix(values);

		System.out.println("Simplex pretraživanje za funkciju kreæe iz toèke " + startPoint);
		System.out.println("Pronaðeni minimum je " + OptimisationAlgorithms.simplexAlgorithm(function, startPoint)
				+ " u " + function.getCallCounter() + " evaluacija funkcije.");
		function.resetCallCounter();

		System.out.println("Hooke-Jeeves pretraživanje za funkciju kreæe iz toèke " + startPoint);
		System.out.println("Pronaðeni minimum je " + OptimisationAlgorithms.hookeJeeves(function, startPoint) + " u "
				+ function.getCallCounter() + " evaluacija funkcije.\n");
		function.resetCallCounter();
	}

}
