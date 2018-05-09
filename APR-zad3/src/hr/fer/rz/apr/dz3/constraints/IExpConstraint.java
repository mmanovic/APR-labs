package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

/**
 * Suèelje koje definira eksplicitno organièenje.
 * 
 * @author Mato
 *
 */
public interface IExpConstraint {
	/**
	 * Zadovoljava li toèka explicitno ogranièenje.
	 * 
	 * @param point
	 *            toèka.
	 * @return Boolean vrijednost.
	 */
	public boolean satisfy(Matrix point);

	/**
	 * Minimum varijable.
	 * 
	 * @return minimum varijable.
	 */
	public double getMinValue();

	/**
	 * Maksimum varijable.
	 * 
	 * @return Maksimum varijable.
	 */
	public double getMaxValue();
}
