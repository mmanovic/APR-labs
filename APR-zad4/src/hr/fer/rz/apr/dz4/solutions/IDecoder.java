package hr.fer.rz.apr.dz4.solutions;

public interface IDecoder<T> {
	public double[] decode(T solution);

	public void decode(T solution, double[] values);
}
