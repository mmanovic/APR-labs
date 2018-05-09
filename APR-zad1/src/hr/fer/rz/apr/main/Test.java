package hr.fer.rz.apr.main;

import java.io.IOException;

import hr.fer.rz.apr.Matrix;

/**
 * Testni razred.
 * 
 * @author Mato
 *
 */
public class Test {

	/**
	 * Glavna metoda razreda koja uèitava potrebne matrice iz zadanih datoteka i obavlja razne operacije.
	 * @param args Nema ih.
	 * @throws IOException Ukoliko postoje problemi s otvaranjem zadanih datoteka.
	 */
	public static void main(String[] args) throws IOException {
		Matrix mat1 = Matrix.loadFromFile("./files/zad6B.txt");
		System.out.println(mat1.getColumns() + " " + mat1.getRows());
		System.out.println(mat1);
		mat1.saveToFile("./files/mato.txt");
	}

}
