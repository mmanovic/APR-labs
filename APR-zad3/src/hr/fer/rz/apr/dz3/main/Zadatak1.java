package hr.fer.rz.apr.dz3.main;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.OptimisationAlgorithms;
import hr.fer.rz.apr.dz3.functions.Function3;
import hr.fer.rz.apr.dz3.functions.IFunction;

public class Zadatak1 {

	public static void main(String[] args) {
		Matrix startPoint = new Matrix(1, 2);
		IFunction function = new Function3();
		System.out.println("Minimum funkcije s gradijentnim spustom bez odreðivanja optimalnog koraka:");
		System.out.println(OptimisationAlgorithms.gradientDescent(function, startPoint, false));
		System.out.println("\nMinimum funkcije s gradijentnim spustom sa odreðivanjem optimalnog koraka:");
		System.out.println(OptimisationAlgorithms.gradientDescent(function, startPoint, true));

	}

}
