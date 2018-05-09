package hr.fer.rz.apr.dz3.functions;

import hr.fer.rz.apr.dz3.Matrix;

/**
 * Su�elje koje predstavlja funkciju.
 * 
 * @author Mato
 *
 */
public interface IFunction {

	public Matrix getValueOfGradientAt(Matrix matrix);

	public Matrix getHesseMatrix(Matrix matrix);

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

	public int getGradientCalls();

	public int getHesseCalls();

	/**
	 * Postavljanje broja�a poziva funkcije na nulu.
	 */
	public void resetCallCounters();
}
