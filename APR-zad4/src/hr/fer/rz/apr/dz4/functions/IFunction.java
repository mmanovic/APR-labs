package hr.fer.rz.apr.dz4.functions;

/**
 * Suèelje koje predstavlja funkciju.
 * 
 * @author Mato
 *
 */
public interface IFunction {

	double valueAt(double[] values);

	int getCallCounter();

	void resetCallCounter();
}
