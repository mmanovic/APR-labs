package hr.fer.rz.apr.dz4.creator;

import java.util.List;

import hr.fer.rz.apr.dz4.solutions.SingleObjectiveSolution;

public interface ICreator<T extends SingleObjectiveSolution> {
	public List<T> create();
}
