package hr.fer.rz.apr.dz2.main;

import java.util.Random;

import hr.fer.rz.apr.dz2.Matrix;
import hr.fer.rz.apr.dz2.OptimisationAlgorithms;
import hr.fer.rz.apr.dz2.functions.Function6;
import hr.fer.rz.apr.dz2.functions.IFunction;

public class Zadatak5 {

	public static void main(String[] args) {
		Random random = new Random();
		IFunction function = new Function6();
		int foundMinimumHooke = 0;
		int foundMinimumSimplex = 0;
		for (int i = 0; i < 1000; i++) {
			double[][] values = new double[1][2];
			values[0][0] = random.nextDouble() * 100 - 50;
			values[0][1] = random.nextDouble() * 100 - 50;
			Matrix startPoint = new Matrix(values);

			Matrix solution1 = OptimisationAlgorithms.hookeJeeves(function, startPoint);
			// Matrix solution2 =
			// OptimisationAlgorithms.simplexAlgorithm(function, startPoint);
			// if (function.valueAt(solution2) <= 1e-4) {
			// foundMinimumSimplex++;
			// }
			// System.out.println(solution);
			if (function.valueAt(solution1) <= 1e-4) {
				foundMinimumHooke++;
			}

		}
		System.out.println(foundMinimumHooke);
		// System.out.println(foundMinimumSimplex);

	}

}
