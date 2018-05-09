package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

/**
 * Suèelje koje predstavlja funkciju.
 * 
 * @author Mato
 *
 */
public interface IFunction {
	/**
	 * Metoda koja za zadanu toèku vraæa vrijednost funkcije.
	 * 
	 * @param point
	 *            Toèka u kojoj raèunamo vrijednost funkcije.
	 * @return Vrijednost funkcije u zadanoj toèki.
	 */
	public double valueAt(Matrix point);

	/**
	 * Broj poziva ove funkcije.
	 * 
	 * @return Broj poziva ove funkcije.
	 */
	public int getCallCounter();

	/**
	 * Postavljanje brojaèa poziva funkcije na nulu.
	 */
	public void resetCallCounter();
}
