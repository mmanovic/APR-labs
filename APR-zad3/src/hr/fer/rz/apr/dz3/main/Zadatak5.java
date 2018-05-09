package hr.fer.rz.apr.dz3.main;

import java.util.ArrayList;
import java.util.List;

import hr.fer.rz.apr.dz3.Matrix;
import hr.fer.rz.apr.dz3.OptimisationAlgorithms;
import hr.fer.rz.apr.dz3.constraints.IConstraint;
import hr.fer.rz.apr.dz3.constraints.ImplicitConstraint3;
import hr.fer.rz.apr.dz3.constraints.ImplicitConstraint4;
import hr.fer.rz.apr.dz3.constraints.ImplicitConstraint5;
import hr.fer.rz.apr.dz3.functions.Function4;
import hr.fer.rz.apr.dz3.functions.IFunction;

public class Zadatak5 {

	public static void main(String[] args) {
		IFunction function = new Function4();
		double[][] values = new double[1][2];
		values[0][0] = 5;
		values[0][1] = 5;
		Matrix startPoint = new Matrix(values);
		List<IConstraint> impConstraints = new ArrayList<>();
		impConstraints.add(new ImplicitConstraint3());
		impConstraints.add(new ImplicitConstraint4());
		Matrix innerPoint = OptimisationAlgorithms.innerPointSearch(startPoint, impConstraints);
		System.out.println("Unutarnja toèka: " + innerPoint);
		impConstraints.add(new ImplicitConstraint5());
		System.out.println(
				"Pretraživanje transformacijom u problem bez ogranièenja za funkciju 4 kreæe iz toèke " + innerPoint);
		System.out.println(
				"Pronaðeni minimum je " + OptimisationAlgorithms.transformation(function, innerPoint, impConstraints, 1)
						+ " u " + function.getCallCounter() + " evaluacija funkcije.");
		function.resetCallCounters();
	}

}
