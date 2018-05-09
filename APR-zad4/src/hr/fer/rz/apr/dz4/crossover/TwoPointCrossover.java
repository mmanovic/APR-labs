package hr.fer.rz.apr.dz4.crossover;

import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;

public class TwoPointCrossover implements ICrossover<BitVectorSolution> {
	private Random random = new Random();

	@Override
	public BitVectorSolution cross(BitVectorSolution x, BitVectorSolution y) {
		byte[] xBits = x.bits;
		byte[] yBits = y.bits;
		byte[] childBits = new byte[xBits.length];
		int crossPoint1 = random.nextInt(xBits.length);
		int crossPoint2 = random.nextInt(xBits.length);

		if (crossPoint1 > crossPoint2) {
			int tmp = crossPoint1;
			crossPoint1 = crossPoint2;
			crossPoint2 = tmp;
		}

		for (int i = 0; i < crossPoint1; i++) {
			childBits[i] = xBits[i];
		}

		for (int i = crossPoint1; i < crossPoint2; i++) {
			childBits[i] = yBits[i];
		}

		for (int i = crossPoint2; i < childBits.length; i++) {
			childBits[i] = xBits[i];
		}

		return new BitVectorSolution(childBits);
	}

}
