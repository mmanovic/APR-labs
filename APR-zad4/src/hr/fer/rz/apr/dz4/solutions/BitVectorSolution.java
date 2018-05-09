package hr.fer.rz.apr.dz4.solutions;

import java.util.Arrays;
import java.util.Random;

public class BitVectorSolution extends SingleObjectiveSolution {
	public byte[] bits;

	public BitVectorSolution(int n) {
		bits = new byte[n];
	}

	public BitVectorSolution(byte[] bits) {
		super();
		this.bits = bits;
	}

	public BitVectorSolution newLikeThis() {
		return new BitVectorSolution(bits.length);
	}

	public BitVectorSolution duplicate() {
		return new BitVectorSolution(Arrays.copyOf(bits, bits.length));
	}

	public void randomize(Random random) {
		for (int i = 0; i < bits.length; ++i) {
			bits[i] = (byte) (random.nextInt(2));
		}
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		for (byte value : bits) {
			string.append(value + " ");
		}
		string.append("]");
		return string.toString();
	}
}
