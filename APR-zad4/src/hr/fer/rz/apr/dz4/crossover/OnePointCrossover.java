package hr.fer.rz.apr.dz4.crossover;

import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;

public class OnePointCrossover implements ICrossover<BitVectorSolution> {
	private Random random = new Random();

	@Override
	public BitVectorSolution cross(BitVectorSolution x, BitVectorSolution y) {
		byte[] xBits = x.bits;
		byte[] yBits = y.bits;
		byte[] childBits = new byte[xBits.length];
		int crossPoint = random.nextInt(xBits.length);

		for (int i = 0; i < crossPoint; i++) {
			childBits[i] = xBits[i];
		}

		for (int i = crossPoint; i < childBits.length; i++) {
			childBits[i] = yBits[i];
		}

		return new BitVectorSolution(childBits);

	}

}
