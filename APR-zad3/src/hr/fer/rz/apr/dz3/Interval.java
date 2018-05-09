package hr.fer.rz.apr.dz3;

/**
 * Pomoæna klasa koja predstavlja interval brojeva.Može se koristiti i kao ureðeni par vrijednosti.
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
