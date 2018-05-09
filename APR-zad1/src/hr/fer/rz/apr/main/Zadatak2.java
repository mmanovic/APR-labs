package hr.fer.rz.apr.main;

import java.io.IOException;

import hr.fer.rz.apr.Matrix;
import hr.fer.rz.apr.SystemSolver;

/**
 * Testni razred.
 * 
 * @author Mato
 *
 */
public class Zadatak2 {

	/**
	 * Glavna metoda razreda koja uèitava potrebne matrice iz zadanih datoteka i
	 * obavlja razne operacije.
	 * 
	 * @param args
	 *            Nema ih.
	 * @throws IOException
	 *             Ukoliko postoje problemi s otvaranjem zadanih datoteka.
	 */
	public static void main(String[] args) throws IOException {
		Matrix A = Matrix.loadFromFile("./files/zad2A.txt");
		Matrix A2 = A.copy();
		Matrix b = Matrix.loadFromFile("./files/zad2B.txt");
		SystemSolver.solve(A, b, false, true);
		System.out.println("--------------------------------------------------");
		SystemSolver.solve(A2, b, true, true);

	}

}
