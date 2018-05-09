package hr.fer.rz.apr.dz5.algebra;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Razred koji predstavlja matricu realnih brojeva.
 * 
 * @author Mato
 *
 */
public class Matrix {
	/**
	 * Broj redaka matrice.
	 */
	private int rows;
	/**
	 * Broj stupaca matrice.
	 */
	private int columns;
	/**
	 * Dvodimenzionalno polje realnih vrijednosti za ovu matricu.
	 */
	private double[][] values;
	/**
	 * Konstanta koja predstavlja mali broj za brojeve èija je apsolutna
	 * vrijednost manja od ove konstante.Za one brojeve koje je ovo ispunjeno,
	 * taj broj æemo usporediti s nulom.
	 */
	private static final double EPSILON = 1E-9;

	/**
	 * Osnovni konstruktor koji stvara matricu zadanih dimenzija sa svim
	 * vrijednostima inicijalno postavljenim na nulu.
	 * 
	 * @param rows
	 *            Broj redaka.
	 * @param columns
	 *            Broj stupaca.
	 */
	public Matrix(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		values = new double[rows][columns];
	}

	/**
	 * Konstruktor koji za zadano dvodimnezionalno polje realnih brojeva stvara
	 * instancu ovog razreda.
	 * 
	 * @param values
	 *            Dvodimenzionalno polje realnih brojeva.
	 */
	public Matrix(double[][] values) {
		this.rows = values.length;
		this.columns = values[0].length;
		this.values = values;
	}

	public Matrix(double[] vector) {
		this.rows = 1;
		this.columns = vector.length;
		this.values = new double[1][columns];
		for (int i = 0; i < columns; i++) {
			this.values[0][i] = vector[i];
		}
	}

	/**
	 * Konstruktor koji stvara matricu dimenzija 1*1.
	 * 
	 * @param value
	 *            Realni broj od matrice.
	 */
	public Matrix(double value) {
		this.rows = this.columns = 1;
		this.values = new double[1][1];
		values[0][0] = value;
	}

	/**
	 * Konstruktor koji na osnovu zadane matrice stvara novu matricu koja je
	 * kopija zadane tj. i samo polje realnih brojeva takoðer kopiramo.
	 * 
	 * @param other
	 */
	public Matrix(Matrix other) {
		this.rows = other.rows;
		this.columns = other.columns;
		this.values = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] = other.values[i][j];
			}
		}
	}

	public Matrix inverse() {
		Matrix LU = this.copy();
		Matrix P = LU.decompose(true);
		System.out.println(LU);

		if (P == null) {
			System.err.println("Inverz matrice se ne moze izracunati!");
			return null;
		}

		Matrix inverse = new Matrix(rows, columns);

		for (int j = 0; j < columns; j++) {
			Matrix y = LU.forwardSubstitution(P.columnAsMatrix(j));
			Matrix x = LU.backSubstitution(y);
			for (int i = 0; i < rows; i++) {
				inverse.values[i][j] = x.values[i][0];
			}
		}

		return inverse;
	}

	public Matrix columnAsMatrix(int col) {
		checkColumnIndex(col);
		Matrix m = new Matrix(rows, 1);
		for (int i = 0; i < rows; i++) {
			m.values[i][0] = values[i][col];
		}
		return m;
	}

	/**
	 * Frobeniusova norma tj. korijen sume kvadrata svih elemenata matrice.
	 * 
	 * @return Frobeniusovu normu.
	 */
	public double normF() {
		double sum = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				sum += Math.pow(values[i][j], 2);
			}
		}
		return Math.sqrt(sum);
	}

	/**
	 * Metoda koja obavlja in-place algoritam dekompozicije nad ovom matricom
	 * dijeleæi ovu matricu na gornju i donju trokutastu.
	 * 
	 * @param LUP
	 *            Parametar koji oznaèava da li radimo varijantu LU-algoritma s
	 *            pivotiranjem.
	 * @return Vraæa permutiranu jediniènu matricu koja predstavlja zamijene
	 *         redaka koje je algoritam napravio nad ovom matricom. Ukoliko
	 *         imamo obiènu varijantu LU-algoritma vraæena matrica æe biti
	 *         jedinièna.Jedinièna matrica je jednakih dimenzija ove matrice nad
	 *         kojom obavljamo algoritam dekompozicije. Takoðer metoda vraæa
	 *         null vrijednost ukoliko tijekom algoritma naiðemo na vrijednost
	 *         na glavnoj dijagonali srazmjerna vrijednosti nula.
	 */
	public Matrix decompose(boolean LUP) {
		if (rows != columns) {
			throw new IllegalArgumentException("Matrica nije kvadratna.");
		}
		Matrix p = Matrix.getUnitMatrix(rows);
		for (int i = 0; i < rows - 1; i++) {
			// da li je potrebna zamjena redaka, jedina razlika izmeðu LU-a i
			// LUP-a.
			if (LUP) {
				int tmp = i;
				for (int j = i + 1; j < rows; j++) {
					if (Math.abs(values[j][i]) > Math.abs(values[tmp][i])) {
						tmp = j;
					}
				}
				p.swapRows(i, tmp);
				this.swapRows(i, tmp);
			}
			double pivot = values[i][i];
			if (Math.abs(pivot) < EPSILON) {
				System.out.println("Greška.Pivot s vrijednoscu " + pivot + " identican nuli.");
				return null;
			}

			for (int j = i + 1; j < rows; j++) {
				values[j][i] /= pivot;
				for (int k = i + 1; k < columns; k++) {
					values[j][k] -= values[j][i] * values[i][k];
				}
			}
		}
		if (Math.abs(values[rows - 1][rows - 1]) < EPSILON) {
			System.out.println("Greška.Pivot s vrijednoscu " + values[rows - 1][rows - 1] + " identican nuli.");
			return null;
		}
		return p;
	}

	/**
	 * Metoda konstruktor koja vraæa kvadratnu jediniènu matricu sa zadanom
	 * dimenzijom n.
	 * 
	 * @param n
	 *            Dimenzija kvadratne jediniène matrice.
	 * @return Kvadratnu jediniènu matricu.
	 */
	public static Matrix getUnitMatrix(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Dimenzija mora biti veca od nule.");
		}
		double[][] values = new double[n][n];
		for (int i = 0; i < n; i++) {
			values[i][i] = 1;
		}
		return new Matrix(values);
	}

	/**
	 * Metoda koja obavlja algoritam unaprijedne supstitucije tj. raèuna
	 * elemente jednostupèane matrice y na osnovu ove dekomponirane matrice i
	 * ulaznog vektora b.
	 * 
	 * @param vector
	 *            Ulazni vektor b.
	 * @return Vektor y.
	 */
	public Matrix forwardSubstitution(Matrix vector) {
		if (vector.rows != rows || vector.columns != 1) {
			throw new IllegalArgumentException("Vektor nema prave dimenzije.");
		}
		if (rows != columns) {
			throw new IllegalArgumentException("Matrica nije kvadratna.");
		}

		double[][] y = new double[columns][1];

		for (int i = 0; i < rows; i++) {
			y[i][0] = vector.get(i, 0);
			for (int j = 0; j < i; j++) {
				y[i][0] -= y[j][0] * values[i][j];
			}
		}
		return new Matrix(y);
	}

	/**
	 * Metoda koja provodi algoritam unazadne supstitucije tj. raèuna
	 * jednostupèanu matricu x na osnovu ove dekoponirane matrice i ulaznog
	 * vektora y kojeg smo dobili metodom unaprijedne supstitucije. Matrica x
	 * predstavlja konaèno rješenje linearnog sustava jednadzbi.
	 * 
	 * @param y
	 *            Ulazni vektor y.
	 * @return Vektor x.
	 */
	public Matrix backSubstitution(Matrix y) {
		if (y.rows != rows || y.columns != 1) {
			throw new IllegalArgumentException("Vektor nema prave dimenzije.");
		}
		if (rows != columns) {
			throw new IllegalArgumentException("Matrica nije kvadratna.");
		}
		double[][] x = new double[rows][1];

		for (int i = rows - 1; i >= 0; i--) {
			x[i][0] = y.get(i, 0);

			for (int j = i + 1; j < rows; j++) {
				x[i][0] -= x[j][0] * values[i][j];
			}
			x[i][0] /= values[i][i];
		}

		return new Matrix(x);
	}

	/**
	 * Metoda koja raèuna umnožak ove i zadane matrice. Umnožak je moguæ ukoliko
	 * su matrice ulanèane inaèe ova metoda daje iznimku.
	 * 
	 * @param other
	 *            Matrica kojom množimo ovu matricu.
	 * @return Umnožak dviju matrica.
	 */
	public Matrix multiply(Matrix other) {
		if (columns != other.rows) {
			throw new IllegalArgumentException("Matrice nisu kompatibilne");
		}
		Matrix m = new Matrix(rows, other.columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < other.columns; j++) {
				m.values[i][j] = 0.0;
				for (int k = 0; k < columns; k++) {
					m.values[i][j] += values[i][k] * other.values[k][j];
				}
			}
		}
		return m;
	}

	/**
	 * Metoda koja obavlja množenje matrice sa skalarom.
	 * 
	 * @param scalar
	 *            Skalarna vrijednost kojom množimo matricu.
	 * @return Umnožak matrice sa skalarom.
	 */
	public Matrix multiply(double scalar) {
		double[][] newValues = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				newValues[i][j] = values[i][j] * scalar;
			}
		}
		return new Matrix(newValues);
	}

	public void scaleThis(double scalar) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] *= scalar;
			}
		}
	}

	/**
	 * Metoda koja vraæa novu matricu koja je jednaku transponiranoj matrici.
	 * 
	 * @return Transponiranu matricu.
	 */
	public Matrix transpose() {
		Matrix m = new Matrix(columns, rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				m.values[j][i] = values[i][j];
			}
		}
		return m;
	}

	/**
	 * Pomoæna metoda koja obavlja zamjenu dvaju redaka zadanih indeksima.
	 * 
	 * @param first
	 *            Indeks prvog retka.
	 * @param second
	 *            Indeks drugog retka.
	 */
	private void swapRows(int first, int second) {
		checkRowIndex(first);
		checkRowIndex(second);

		double[] tmp = values[first];
		values[first] = values[second];
		values[second] = tmp;
	}

	/**
	 * Metoda koja obavlja promjenu velièine matrice. Ukoliko su nove dimenzije
	 * manje matrica æe efektivno biti izrezana u suprotnom ostatak æe dobiti
	 * vrijednost nula.
	 * 
	 * @param rows
	 *            Novi broj redaka.
	 * @param cols
	 *            Novi broj stupaca.
	 */
	public void resize(int rows, int cols) {
		if (rows < 1 || cols < 1) {
			throw new IllegalArgumentException("Broj redaka i stupaca mora biti veci od 1.");
		}
		double[][] values = new double[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				values[i][j] = this.values[i][j];
			}
		}

		this.values = values;
	}

	/**
	 * Metoda koja umanjuje ovu matricu za zadanu matricu tj. oduzimanje mijenja
	 * vrijednost trenutne matrice.
	 * 
	 * @param other
	 *            Matrica s kojom oduzimamo.
	 * @return Vraæamo matricu nakon oduzimanja.
	 */
	public Matrix subtractToThis(Matrix other) {
		if (rows != other.rows || columns != other.columns) {
			throw new IllegalArgumentException("Matrice nisu kompatibilne.");
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] -= other.values[i][j];
			}
		}
		return this;
	}

	/**
	 * Metoda koja poveæava ovu matricu za zadanu matricu tj. zbrajanje mijenja
	 * vrijednost trenutne matrice.
	 * 
	 * @param other
	 *            Matrica s kojom zbrajamo.
	 * @return Vraæamo matricu nakon zbrajanja.
	 */
	public Matrix addToThis(Matrix other) {
		if (rows != other.rows || columns != other.columns) {
			throw new IllegalArgumentException("Matrice nisu kompatibilne.");
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				values[i][j] += other.values[i][j];
			}
		}
		return this;
	}

	/**
	 * Metoda koja obavlja zbrajanje matrica i vraæa novu matricu kao rezultat.
	 * 
	 * @param other
	 *            Matrica kojom zbrajamo.
	 * @return Rezultat zbrajanja.
	 */
	public Matrix add(Matrix other) {
		return this.copy().addToThis(other);
	}

	/**
	 * Metoda koja obavlja oduzimanje matrica i vraæa novu matricu kao rezultat.
	 * 
	 * @param other
	 *            Matrica kojom oduzimamo.
	 * @return Rezultat oduzimanja.
	 */
	public Matrix subtract(Matrix other) {
		return this.copy().subtractToThis(other);
	}

	/**
	 * Metoda koja vraæa kopiju trenutne matrice.
	 * 
	 * @return Kopiju trenutne matrice.
	 */
	public Matrix copy() {
		return new Matrix(this);
	}

	/**
	 * Vraæa realnu vrijednost elementa na zadanim indeksima.
	 * 
	 * @param row
	 *            Redak elementa.
	 * @param column
	 *            Stupac elementa.
	 * @return Element na zadanim indeksima.
	 */
	public double get(int row, int column) {
		checkColumnIndex(column);
		checkRowIndex(row);
		return values[row][column];
	}

	/**
	 * Metoda koja postavlja novu zadanu vrijednost na zadanoj poziciji.
	 * 
	 * @param row
	 *            Indeks retka.
	 * @param column
	 *            Indeks stupca.
	 * @param value
	 *            Nova vrijednost.
	 */
	public void set(int row, int column, double value) {
		checkColumnIndex(column);
		checkRowIndex(row);
		values[row][column] = value;
	}

	/**
	 * Metoda koja vraæa broj redaka.
	 * 
	 * @return Broj redaka.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Metoda koja vraæa broj stupaca.
	 * 
	 * @return Broj stupaca.
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Metoda koja vraæa polje realnih vrijednosti ove matrice.
	 * 
	 * @return Dvodimenzionalno polje realnih brojeva.
	 */
	public double[][] getValues() {
		return values;
	}

	/**
	 * Statièka javna metoda koja za zadanu putanju datoteke èita i stvara novu
	 * matricu.
	 * 
	 * @param path
	 *            Putanja do datoteke.
	 * @return Instancu ovog razreda.
	 * @throws IOException
	 *             Ukoliko postoji problemi pri otvaranju datoteke.
	 */
	public static Matrix loadFromFile(String path) throws IOException {
		Path pathToFile = Paths.get(path);
		if (!Files.exists(pathToFile)) {
			throw new IllegalArgumentException("Ne postoji datoteka s tom stazom.");
		}
		List<String> lines = Files.readAllLines(pathToFile);
		if (lines.size() == 0) {
			throw new IllegalArgumentException("Datoteka je prazna");
		}
		int columns = lines.get(0).trim().split("\\s+").length;
		int rows = lines.size();
		double[][] matrix = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			String[] tmp = lines.get(i).split("\\s+");
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = Double.parseDouble(tmp[j]);
			}
		}
		return new Matrix(matrix);
	}

	/**
	 * MEtoda koja obavlja spremanje zapisa ove matrice u prikladnom obliku tj.
	 * zapisuje ju u datoteku zadanom putanjom.
	 * 
	 * @param path
	 *            Putanja do datoteke.
	 * @throws IOException
	 *             Ukoliko postoje problemi pri otvaranju datoteke.
	 */
	public void saveToFile(String path) throws IOException {
		Path pathToFile = Paths.get(path);
		PrintWriter writer = new PrintWriter(new FileWriter(pathToFile.toFile()));
		writer.write(toString());
		writer.flush();
		writer.close();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				string.append(values[i][j] + " ");
			}
			string = string.deleteCharAt(string.length() - 1);
			string.append("\n");
		}
		return string.substring(0, string.length() - 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columns;
		result = prime * result + rows;
		result = prime * result + Arrays.deepHashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		if (columns != other.columns)
			return false;
		if (rows != other.rows)
			return false;
		if (!Arrays.deepEquals(values, other.values))
			return false;
		return true;
	}

	/**
	 * Privatna metoda za provjeru ispravnosti indeksa retka.
	 * 
	 * @param row
	 *            Indeks retka.
	 */
	private void checkRowIndex(int row) {
		if (row < 0 || row >= rows) {
			throw new IllegalArgumentException("Nepostojeci indeks retka!");
		}
	}

	/**
	 * Privatna metoda za provjeru ispravnosti indeksa stupca.
	 * 
	 * @param row
	 *            Indeks stupca.
	 */
	private void checkColumnIndex(int column) {
		if (column < 0 || column >= columns) {
			throw new IllegalArgumentException("Nepostojeci indeks stupca!");
		}
	}
}
