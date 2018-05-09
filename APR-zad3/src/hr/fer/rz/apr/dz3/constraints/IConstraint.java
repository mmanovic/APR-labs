package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

/**
 * suèelje koje definira implicitno ogranièenje.
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
	 * Vrijednost funkcije ogranièenja uz parametar t.
	 * 
	 * @param point
	 *            Toèka u kojoj raèunamo.
	 * @param t
	 *            Parametar t.
	 * @return Minus logaritam funkcije.
	 */
	public double constraintValue(Matrix point, double t);

	/**
	 * Vrijednost funkcije ogranièenja.
	 * 
	 * @param point
	 *            Toèka u kojoj raèunamo.
	 * @return Vrijednost funkcije.
	 */
	public double constraintValue(Matrix point);
}
