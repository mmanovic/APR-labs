package hr.fer.rz.apr.dz3.main;

import java.util.Arrays;
import java.util.List;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.OptimisationAlgorithms;
import hr.fer.rz.apr.dz3.constraints.IConstraint;
import hr.fer.rz.apr.dz3.constraints.ImplicitConstraint1;
import hr.fer.rz.apr.dz3.constraints.ImplicitConstraint2;
import hr.fer.rz.apr.dz3.functions.Function1;
import hr.fer.rz.apr.dz3.functions.Function2;
import hr.fer.rz.apr.dz3.functions.IFunction;

public class Zadatak4 {

	public static void main(String[] args) {
		IFunction function1 = new Function1();
		double[][] values = new double[1][2];
		values[0][0] = -1.9;
		values[0][1] = 2;
		Matrix startPoint1 = new Matrix(values);

		IFunction function2 = new Function2();
		double[][] values2 = new double[1][2];
		values2[0][0] = 0.1;
		values2[0][1] = 0.3;
		Matrix startPoint2 = new Matrix(values2);

		List<IConstraint> impConstraints = Arrays.asList(new ImplicitConstraint1(), new ImplicitConstraint2());

		System.out.println(
				"Pretraživanje transformacijom u problem bez ogranièenja za funkciju 1 kreæe iz toèke " + startPoint1);
		System.out.println("Pronaðeni minimum je "
				+ OptimisationAlgorithms.transformation(function1, startPoint1, impConstraints, 1) + " u "
				+ function1.getCallCounter() + " evaluacija funkcije.");
		function1.resetCallCounters();
		System.out.println();
		System.out.println(
				"Pretraživanje transformacijom u problem bez ogranièenja za funkciju 2 kreæe iz toèke " + startPoint2);
		System.out.println("Pronaðeni minimum je "
				+ OptimisationAlgorithms.transformation(function2, startPoint2, impConstraints, 1) + " u "
				+ function2.getCallCounter() + " evaluacija funkcije.");
		function2.resetCallCounters();
	}

}
