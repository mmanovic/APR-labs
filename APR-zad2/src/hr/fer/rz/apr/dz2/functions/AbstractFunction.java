package hr.fer.rz.apr.dz2.functions;

import hr.fer.rz.apr.dz2.Matrix;

/**
 * Abstraktni razred koji predstavlja abstraktnu funkciju koja implementira
 * suèelje {@link IFunction}.
 * 
 * @author Mato
 *
 */
public abstract class AbstractFunction implements IFunction {
	/**
	 * Varijabla koja pohranjuje broj poziva funkcije.
	 */
	private int calls = 0;

	@Override
	public double valueAt(Matrix point) {
		calls++;
		return functionValue(point);
	}

	/**
	 * Metoda koju izvode konkretne implementacije funkcije. Metoda koja vraæa
	 * vrijednost funkcije u zadanoj toèki.
	 * 
	 * @param point
	 *            Toèka u kojoj raèunamo vrijednost funkcije.
	 * @return Vrijednost funkcije u zadanoj toèki.
	 */
	protected abstract double functionValue(Matrix point);

	@Override
	public int getCallCounter() {
		return calls;
	}

	@Override
	public void resetCallCounter() {
		calls = 0;

	}
}
