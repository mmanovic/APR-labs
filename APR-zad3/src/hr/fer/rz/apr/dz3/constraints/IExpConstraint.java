package hr.fer.rz.apr.dz3.constraints;

import hr.fer.rz.apr.dz3.Matrix;

/**
 * Su�elje koje definira eksplicitno organi�enje.
 * 
 * @author Mato
 *
 */
public interface IExpConstraint {
	/**
	 * Zadovoljava li to�ka explicitno ograni�enje.
	 * 
	 * @param point
	 *            to�ka.
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
