package hr.fer.rz.apr;

/**
 * Razred koji sadrži statièku metodu koja rješava zadani sustav linearnih
 * jednadžbi.
 * 
 * @author Mato
 *
 */
public class SystemSolver {

	/**
	 * JAvna statièka metoda koja raèuna rješenje sustava linearnih jednadžbi.
	 * 
	 * @param A
	 *            Koeficijenti sustava linearnih jednadžbi.
	 * @param b
	 *            Vrijednosti linearnih jednadžbi.
	 * @param LUP
	 *            Zastavica koja oznaèava da li sustav rješavamo
	 *            LU-dekompozicijom ili LUP-dekompozicijom.
	 * @param shallPrint
	 *            Zastavica koja oznaèava treba li ispisivati meðurezultate
	 *            postupka na standardni izlaz.
	 * @return Rješenje sustava jednadžbi u obliku vektora x.
	 */
	public static Matrix solve(Matrix A, Matrix b, boolean LUP, boolean shallPrint) {
		if (shallPrint) {
			System.out.println("Ulazne matrice A i b za sustav jednadžbi su:");
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
			System.out.println("Dekomponirana matrica A, pri èemu elementi glavne dijagonale i iznad pripadaju "
					+ "gornjoj matrici tj. matrici U\n,a elementi ispod glavne dijagonale pripadaju donjoj matrici L pri"
					+ "\nèemu su elementi na glavnoj dijagonali jednaki jedinici.\n");
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
