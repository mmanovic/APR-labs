package hr.fer.rz.apr.dz5.main;

import java.io.IOException;

import hr.fer.rz.apr.dz5.algebra.Matrix;

public class Zadatak2 {

	public static void main(String[] args) throws IOException {
		Matrix A = Matrix.loadFromFile("./files/zad2matrix.txt");
		System.out.println(A.inverse());

	}

}
