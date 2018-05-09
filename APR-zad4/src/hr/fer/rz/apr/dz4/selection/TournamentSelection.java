package hr.fer.rz.apr.dz4.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TournamentSelection<T> implements ISelection<T> {
	private Random random = new Random();
	private int n;

	public TournamentSelection(int n) {
		super();
		if (n < 2) {
			throw new IllegalArgumentException("Parameter n must be greater than two.");
		}
		this.n = n;
	}

	@Override
	public T select(List<T> population) {
		List<T> tournir = new ArrayList<>();
		int size = population.size();
		for (int i = 0; i < n; i++) {
			int index = random.nextInt(size);
			tournir.add(population.get(index));
		}
		return tournir.stream().sorted().findFirst().get();
	}

}
