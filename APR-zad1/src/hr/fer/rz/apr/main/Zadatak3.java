package hr.fer.rz.apr.main;

import java.io.IOException;

import hr.fer.rz.apr.Matrix;

/**
 * Testni razred.
 * 
 * @author Mato
 *
 */
public class Zadatak3 {

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
		Matrix A = Matrix.loadFromFile("./files/zad3A.txt");
		Matrix A2 = A.copy();
		A.decompose(false);
		System.out.println(A + "\n\n");
		A2.decompose(true);
		System.out.println(A2);
	}

}
