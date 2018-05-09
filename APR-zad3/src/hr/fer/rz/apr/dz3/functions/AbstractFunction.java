package hr.fer.rz.apr.dz3.functions;

import hr.fer.rz.apr.dz3.Matrix;

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
	private int gradientCalls = 0;
	private int hesseCalls = 0;
	
	

	public int getGradientCalls() {
		return gradientCalls;
	}

	public int getHesseCalls() {
		return hesseCalls;
	}

	@Override
	public double valueAt(Matrix point) {
		calls++;
		return functionValue(point);
	}

	@Override
	public Matrix getValueOfGradientAt(Matrix matrix) {
		gradientCalls++;
		return valueOfGradientAt(matrix);
	}

	@Override
	public Matrix getHesseMatrix(Matrix matrix) {
		hesseCalls++;
		return hesseMatrix(matrix);
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

	protected abstract Matrix valueOfGradientAt(Matrix point);

	protected abstract Matrix hesseMatrix(Matrix point);

	@Override
	public int getCallCounter() {
		return calls;
	}

	@Override
	public void resetCallCounters() {
		calls = 0;
		gradientCalls = 0;
		hesseCalls = 0;
	}
}
