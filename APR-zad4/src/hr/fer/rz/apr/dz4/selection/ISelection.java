package hr.fer.rz.apr.dz4.selection;

import java.util.List;

public interface ISelection<T> {
	public T select(List<T> population);
}
