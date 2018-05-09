package hr.fer.rz.apr.dz4.mutations;

import java.util.Random;

import hr.fer.rz.apr.dz4.solutions.BitVectorSolution;

public class BitVectorMutation implements IMutation<BitVectorSolution> {
	private Random random = new Random();
	private double mutationProbability;

	public BitVectorMutation(double mutationProbability) {
		super();
		this.mutationProbability = mutationProbability;
	}

	@Override
	public void mutate(BitVectorSolution neighbor) {
		byte[] bits = neighbor.bits;
		for (int i = 0; i < bits.length; i++) {
			if (random.nextDouble() < mutationProbability) {
				bits[i] ^= 1;
			}
		}

		// int j = random.nextInt(bits.length);
		// bits[i] ^= j;
	}

}
