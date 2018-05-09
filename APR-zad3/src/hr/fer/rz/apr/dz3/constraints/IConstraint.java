package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

/**
 * su�elje koje definira implicitno ograni�enje.
 * 
 * @author Mato
 *
 */
public interface IConstraint {
	/**
	 * Zadovoljava li uvjet.
	 * 
	 * @param point
	 * @return
	 */
	public boolean satisfy(Matrix point);

	/**
	 * Vrijednost funkcije ograni�enja uz parametar t.
	 * 
	 * @param point
	 *            To�ka u kojoj ra�unamo.
	 * @param t
	 *            Parametar t.
	 * @return Minus logaritam funkcije.
	 */
	public double constraintValue(Matrix point, double t);

	/**
	 * Vrijednost funkcije ograni�enja.
	 * 
	 * @param point
	 *            To�ka u kojoj ra�unamo.
	 * @return Vrijednost funkcije.
	 */
	public double constraintValue(Matrix point);
}
