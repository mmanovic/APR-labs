package hr.fer.rz.apr.dz4.solutions;

public class SingleObjectiveSolution implements Comparable<SingleObjectiveSolution> {
	public double error;

	@Override
	public int compareTo(SingleObjectiveSolution o) {
		return Double.compare(this.error, o.error);
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

}
