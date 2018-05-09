package hr.fer.rz.apr.dz2.main;

import hr.fer.rz.apr.dz2.Matrix;
import hr.fer.rz.apr.dz2.OptimisationAlgorithms;
import hr.fer.rz.apr.dz2.functions.Function1;
import hr.fer.rz.apr.dz2.functions.Function2;
import hr.fer.rz.apr.dz2.functions.Function3;
import hr.fer.rz.apr.dz2.functions.Function4;
import hr.fer.rz.apr.dz2.functions.Function5;
import hr.fer.rz.apr.dz2.functions.IFunction;

public class Zadatak1 {

	public static void main(String[] args) {
		IFunction function = new Function5();

		for (int i = 10; i <= 40; i = i + 5) {
			System.out.println("Koordinatno pretraživanje za funkciju kreæe iz toèke " + i);
			System.out
					.println("Pronaðeni minimum je " + OptimisationAlgorithms.coordinateSearch(function, new Matrix(i))
							+ " u " + function.getCallCounter() + " evaluacija funkcije.");
			function.resetCallCounter();

			System.out.println("Simplex pretraživanje za funkciju kreæe iz toèke " + i);
			System.out
					.println("Pronaðeni minimum je " + OptimisationAlgorithms.simplexAlgorithm(function, new Matrix(i))
							+ " u " + function.getCallCounter() + " evaluacija funkcije.");
			function.resetCallCounter();

			System.out.println("Hooke-Jeeves pretraživanje za funkciju kreæe iz toèke " + i);
			System.out.println("Pronaðeni minimum je " + OptimisationAlgorithms.hookeJeeves(function, new Matrix(i))
					+ " u " + function.getCallCounter() + " evaluacija funkcije.\n");
			function.resetCallCounter();
		}

	}

}
