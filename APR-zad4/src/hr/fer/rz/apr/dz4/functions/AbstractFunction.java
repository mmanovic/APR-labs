package hr.fer.rz.apr.dz4.functions;

/**
 * Abstraktni razred koji predstavlja abstraktnu funkciju koja implementira
 * su�elje {@link IFunction}.
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
	public double valueAt(double[] values) {
		calls++;
		return functionValue(values);
	}

	/**
	 * Metoda koju izvode konkretne implementacije funkcije. Metoda koja vra�a
	 * vrijednost funkcije u zadanoj to�ki.
	 * 
	 * @param point
	 *            To�ka u kojoj ra�unamo vrijednost funkcije.
	 * @return Vrijednost funkcije u zadanoj to�ki.
	 */
	protected abstract double functionValue(double[] values);

	@Override
	public int getCallCounter() {
		return calls;
	}

	@Override
	public void resetCallCounter() {
		calls = 0;
	}
}
