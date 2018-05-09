package hr.fer.rz.apr.dz3;

/**
 * Pomo�na klasa koja predstavlja interval brojeva.Mo�e se koristiti i kao ure�eni par vrijednosti.
 * @author Mato
 *
 * @param <T>
 * @param <U>
 */
public class Interval<T, U> {
	private T left;
	private U right;

	public Interval(T left, U right) {
		super();
		this.left = left;
		this.right = right;
	}

	public T getLeft() {
		return left;
	}

	public U getRight() {
		return right;
	}

}
