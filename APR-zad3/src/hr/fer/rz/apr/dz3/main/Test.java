package hr.fer.rz.apr.dz3.main;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.OptimisationAlgorithms;
import hr.fer.rz.apr.dz3.functions.Function4;
import hr.fer.rz.apr.dz3.functions.IFunction;

public class Test {

	public static void main(String[] args) {
		Matrix startPoint = new Matrix(1, 2);
		IFunction function = new Function4();
		System.out.println("Minimum funkcije s gradijentnim spustom bez odreðivanja optimalnog koraka:");
		System.out.println(OptimisationAlgorithms.newtonRaphson(function, startPoint, false));
		System.out.println("\nMinimum funkcije s gradijentnim spustom sa odreðivanjem optimalnog koraka:");
		System.out.println(OptimisationAlgorithms.newtonRaphson(function, startPoint, true));
	}

}
