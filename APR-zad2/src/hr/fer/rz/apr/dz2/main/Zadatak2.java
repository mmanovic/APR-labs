package hr.fer.rz.apr.dz2.main;

import hr.fer.rz.apr.dz2.Matrix;
import hr.fer.rz.apr.dz2.OptimisationAlgorithms;
import hr.fer.rz.apr.dz2.functions.Function1;
import hr.fer.rz.apr.dz2.functions.Function2;
import hr.fer.rz.apr.dz2.functions.Function3;
import hr.fer.rz.apr.dz2.functions.Function4;
import hr.fer.rz.apr.dz2.functions.IFunction;

public class Zadatak2 {

	public static void main(String[] args) {
		IFunction[] functions = new IFunction[4];
		Matrix[] startPoints = new Matrix[4];
		functions[0] = new Function1();
		double[][] values = new double[1][2];
		values[0][0] = -1.9;
		values[0][1] = 2;
		startPoints[0] = new Matrix(values);

		functions[1] = new Function2();
		values = new double[1][2];
		values[0][0] = 0.1;
		values[0][1] = 0.3;
		startPoints[1] = new Matrix(values);

		functions[2] = new Function3();
		values = new double[1][5];
		values[0][0] = 0;
		values[0][1] = 0;
		values[0][2] = 0;
		values[0][3] = 0;
		values[0][4] = 0;
		startPoints[2] = new Matrix(values);

		functions[3] = new Function4();
		values = new double[1][2];
		values[0][0] = 5.1;
		values[0][1] = 1.1;
		startPoints[3] = new Matrix(values);

		for (int i = 0; i < 4; i++) {
			System.out
					.println("Koordinatno pretraživanje za funkciju " + (i + 1) + " kreæe iz toèke " + startPoints[i]);
			System.out.println(
					"Pronaðeni minimum je " + OptimisationAlgorithms.coordinateSearch(functions[i], startPoints[i])
							+ " u " + functions[i].getCallCounter() + " evaluacija funkcije.");
			functions[i].resetCallCounter();

			System.out.println("Simplex pretraživanje za funkciju " + (i + 1) + " kreæe iz toèke " + startPoints[i]);
			System.out.println(
					"Pronaðeni minimum je " + OptimisationAlgorithms.simplexAlgorithm(functions[i], startPoints[i])
							+ " u " + functions[i].getCallCounter() + " evaluacija funkcije.");
			functions[i].resetCallCounter();

			System.out
					.println("Hooke-Jeeves pretraživanje za funkciju " + (i + 1) + " kreæe iz toèke " + startPoints[i]);
			System.out
					.println("Pronaðeni minimum je " + OptimisationAlgorithms.hookeJeeves(functions[i], startPoints[i])
							+ " u " + functions[i].getCallCounter() + " evaluacija funkcije.\n");
			functions[i].resetCallCounter();
		}

	}

}
