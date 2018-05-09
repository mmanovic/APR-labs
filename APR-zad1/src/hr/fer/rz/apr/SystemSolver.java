package hr.fer.rz.apr;

/**
 * Razred koji sadr�i stati�ku metodu koja rje�ava zadani sustav linearnih
 * jednad�bi.
 * 
 * @author Mato
 *
 */
public class SystemSolver {

	/**
	 * JAvna stati�ka metoda koja ra�una rje�enje sustava linearnih jednad�bi.
	 * 
	 * @param A
	 *            Koeficijenti sustava linearnih jednad�bi.
	 * @param b
	 *            Vrijednosti linearnih jednad�bi.
	 * @param LUP
	 *            Zastavica koja ozna�ava da li sustav rje�avamo
	 *            LU-dekompozicijom ili LUP-dekompozicijom.
	 * @param shallPrint
	 *            Zastavica koja ozna�ava treba li ispisivati me�urezultate
	 *            postupka na standardni izlaz.
	 * @return Rje�enje sustava jednad�bi u obliku vektora x.
	 */
	public static Matrix solve(Matrix A, Matrix b, boolean LUP, boolean shallPrint) {
		if (shallPrint) {
			System.out.println("Ulazne matrice A i b za sustav jednad�bi su:");
			System.out.println(A);
			System.out.println();
			System.out.println(b);
		}
		Matrix Acopy = A.copy();
		Matrix P = Acopy.decompose(LUP);
		if (P == null) {
			System.out.println("Dekompozicija nije uspjela.");
			return null;
		}

		if (shallPrint) {
			System.out.println("Dekomponirana matrica A, pri �emu elementi glavne dijagonale i iznad pripadaju "
					+ "gornjoj matrici tj. matrici U\n,a elementi ispod glavne dijagonale pripadaju donjoj matrici L pri"
					+ "\n�emu su elementi na glavnoj dijagonali jednaki jedinici.\n");
			System.out.println(Acopy);
		}

		if (LUP) {
			b = P.multiply(b);
			if (shallPrint) {
				System.out.println("Matrice permutacije P i nova permutirana matrica b su:\n");
				System.out.println(P);
				System.out.println();
				System.out.println(b);
			}
		}

		Matrix y = Acopy.forwardSubstitution(b);
		Matrix x = Acopy.backSubstitution(y);

		if (shallPrint) {
			System.out.println("Matrice y i x su:\n");
			System.out.println(y);
			System.out.println();
			System.out.println(x);
		}
		return x;
	}
}
