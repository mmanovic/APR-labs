package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

/**
 * Su�elje koje predstavlja funkciju.
 * 
 * @author Mato
 *
 */
public interface IFunction {
	/**
	 * Metoda koja za zadanu to�ku vra�a vrijednost funkcije.
	 * 
	 * @param point
	 *            To�ka u kojoj ra�unamo vrijednost funkcije.
	 * @return Vrijednost funkcije u zadanoj to�ki.
	 */
	public double valueAt(Matrix point);

	/**
	 * Broj poziva ove funkcije.
	 * 
	 * @return Broj poziva ove funkcije.
	 */
	public int getCallCounter();

	/**
	 * Postavljanje broja�a poziva funkcije na nulu.
	 */
	public void resetCallCounter();
}
