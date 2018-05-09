package hr.fer.rz.apr.dz3.main;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.OptimisationAlgorithms;
import hr.fer.rz.apr.dz3.functions.Function1;
import hr.fer.rz.apr.dz3.functions.Function2;
import hr.fer.rz.apr.dz3.functions.IFunction;

public class Zadatak2 {

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

		System.out.println("Gradijentni spust za funkciju 1 kreæe iz toèke " + startPoint1);
		System.out.println(
				"Pronaðeni minimum je " + OptimisationAlgorithms.gradientDescent(function1, startPoint1, true));
		System.out.println("Broj evaluacija funkcije: " + function1.getCallCounter() + " Broj evaluacija gradijenta: "
				+ function1.getGradientCalls() + " Broj evaluacija hesse-a: " + function1.getHesseCalls());
		function1.resetCallCounters();

		System.out.println();
		System.out.println("Gradijentni spust za funkciju 2 kreæe iz toèke " + startPoint2);
		System.out.println(
				"Pronaðeni minimum je " + OptimisationAlgorithms.gradientDescent(function2, startPoint2, true));
		System.out.println("Broj evaluacija funkcije: " + function2.getCallCounter() + " Broj evaluacija gradijenta: "
				+ function2.getGradientCalls() + " Broj evaluacija hesse-a: " + function2.getHesseCalls());
		function2.resetCallCounters();

		System.out.println();
		System.out.println("Newton-Raphson za funkciju 1 kreæe iz toèke " + startPoint1);
		System.out
				.println("Pronaðeni minimum je " + OptimisationAlgorithms.newtonRaphson(function1, startPoint1, true));
		System.out.println("Broj evaluacija funkcije: " + function1.getCallCounter() + " Broj evaluacija gradijenta: "
				+ function1.getGradientCalls() + " Broj evaluacija hesse-a: " + function1.getHesseCalls());
		function1.resetCallCounters();

		System.out.println();
		System.out.println("Newton-Raphson za funkciju 2 kreæe iz toèke " + startPoint2);
		System.out
				.println("Pronaðeni minimum je " + OptimisationAlgorithms.newtonRaphson(function2, startPoint2, true));
		System.out.println("Broj evaluacija funkcije: " + function2.getCallCounter() + " Broj evaluacija gradijenta: "
				+ function2.getGradientCalls() + " Broj evaluacija hesse-a: " + function2.getHesseCalls());
		function2.resetCallCounters();

	}

}
