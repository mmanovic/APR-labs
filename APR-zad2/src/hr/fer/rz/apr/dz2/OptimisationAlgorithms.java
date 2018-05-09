package hr.fer.rz.apr.dz2;

import java.util.ArrayList;
import java.util.List;

import hr.fer.rz.apr.dz2.functions.AbstractFunction;
import hr.fer.rz.apr.dz2.functions.IFunction;

/**
 * Razred koji definira algoritme pretraživanja funkcije jedne ili više
 * varijabli tj. nudi njihove implementacije kao statièke metode.
 * 
 * @author Mato
 *
 */
public class OptimisationAlgorithms {
	/** Konstanta u postupku zlatnog reza */
	public static double K = (Math.sqrt(5) - 1) * 0.5;
	/** Preciznost kojom raèunamo rješenja. */
	public static double EPSILON = 1E-6;
	/** Konstanta za refleksiju. */
	public static double ALPHA = 1;
	/** Konstanta za kontrakciju */
	public static double BETA = 0.5;
	/** Konstanta za ekspanziju */
	public static double GAMMA = 2;
	/** Konstanta za pomicanje toèaka simpleksa prema najboljoj toèki. */
	public static double SIGMA = 0.5;
	/** Velièina koraka kojom stvaramo poèetni simpleks */
	public static double MOVE_SIMPLEX = 1;
	/** Poèetni pomak u algoritmu Hooke-Jeeves. */
	public static double DX = 0.5;

	/**
	 * Metoda koja provodi Hooke-Jeeves algoritam za zadanu funkciju i poèetnu
	 * toèku.
	 * 
	 * @param function
	 *            Funkciju koju minimiziramo.
	 * @param startPoint
	 *            Poèetna toèka algoritma.
	 * @return Pronaðeni minimum.
	 */
	public static Matrix hookeJeeves(IFunction function, Matrix startPoint) {
		Matrix x0 = startPoint;
		Matrix xp = startPoint;
		Matrix xb = startPoint;
		double dx = DX;
		while (true) {
			Matrix xn = explore(function, xp, dx);
			if (function.valueAt(xn) < function.valueAt(xb)) {
				xp = xn.multiply(2).subtract(xb);
				xb = xn;
			} else {
				dx *= 0.5;
				xp = xb;
			}
			// System.out.println(xb);
			if (dx < EPSILON) {
				return xb;
			}
		}
	}

	/**
	 * Pomoæna metoda koja obavlja pretragu oko poèetne toèke.
	 * 
	 * @param function
	 *            Funkcija koju minimiziramo.
	 * @param xp
	 *            Poèetna toèka.
	 * @param dx
	 *            Pomak.
	 * @return Sljedeæa toèka.
	 */
	private static Matrix explore(IFunction function, Matrix xp, double dx) {
		Matrix xn = xp.copy();
		int n = xp.getColumns();
		for (int i = 0; i < n; i++) {
			double fp = function.valueAt(xn);
			xn.set(0, i, xn.get(0, i) + dx);
			double fn = function.valueAt(xn);

			if (fn > fp) {
				xn.set(0, i, xn.get(0, i) - 2 * dx);
				fn = function.valueAt(xn);
				if (fn > fp) {
					xn.set(0, i, xn.get(0, i) + dx);
				}
			}
		}
		return xn;
	}

	/**
	 * Metoda koja provodi simplex algoritam za zadanu funkciju i poèetnu toèku.
	 * 
	 * @param function
	 *            Funkciju koju minimiziramo.
	 * @param startPoint
	 *            Poèetna toèka algoritma.
	 * @return Pronaðeni minimum.
	 */
	public static Matrix simplexAlgorithm(IFunction function, Matrix startPoint) {
		List<Matrix> simplex = new ArrayList<>();
		simplex.add(startPoint);
		int n = startPoint.getColumns();
		for (int i = 0; i < n; i++) {
			Matrix copy = startPoint.copy();
			copy.set(0, i, startPoint.get(0, i) + MOVE_SIMPLEX);
			simplex.add(copy);
		}

		while (true) {
			double[] functionValues = new double[n + 1];
			double tmpMin = function.valueAt(simplex.get(0));
			double tmpMax = tmpMin;
			functionValues[0] = tmpMin;
			int l = 0;
			int h = 0;
			for (int i = 1; i <= n; i++) {
				Matrix x = simplex.get(i);
				double value = function.valueAt(x);
				functionValues[i] = value;
				if (value - tmpMax > EPSILON) {
					tmpMax = value;
					h = i;
				}
				if (tmpMin - value > EPSILON) {
					tmpMin = value;
					l = i;
				}
			}
			Matrix xc = getCentroid(simplex, h);
			Matrix xr = reflection(simplex.get(h), xc);
			double valueAtXR = function.valueAt(xr);
			if (valueAtXR < tmpMin) {
				Matrix xe = expansion(xr, xc);
				double xeValue = function.valueAt(xe);
				if (xeValue < tmpMin) {
					simplex.set(h, xe);
					functionValues[h] = xeValue;
				} else {
					simplex.set(h, xr);
					functionValues[h] = valueAtXR;
				}
			} else {
				if (checkCondition(functionValues, valueAtXR, h)) {
					if (valueAtXR < tmpMax) {
						simplex.set(h, xr);
						functionValues[h] = valueAtXR;
					}
					Matrix xk = contraction(simplex.get(h), xc);
					double xkValue = function.valueAt(xk);
					if (xkValue < function.valueAt(simplex.get(h))) {
						simplex.set(h, xk);
						functionValues[h] = xkValue;
					} else {
						simplex = movePoints(simplex, simplex.get(l));
					}
				} else {
					simplex.set(h, xr);
					functionValues[h] = valueAtXR;
				}
			}

			int length = functionValues.length;
			double stopValue = 0;
			double centroidValue = function.valueAt(xc);

			for (int i = 0; i < length; i++) {
				stopValue += Math.pow(centroidValue - functionValues[i], 2);
			}
			if (Math.sqrt(stopValue / length) < EPSILON) {
				return xc;
			}
			// System.out.println(Math.sqrt(stopValue / length));

		}

	}

	/**
	 * Pomoæna metoda koja pomièe toèke simpleksa prema trenutno najpovoljnijoj
	 * toèki.
	 * 
	 * @param simplex
	 *            Simpleks.
	 * @param matrix
	 *            Toèka prema kojoj pomièemo toèke simpleksa.
	 * @return Nove toèke simpleksa.
	 */
	private static List<Matrix> movePoints(List<Matrix> simplex, Matrix matrix) {
		List<Matrix> movedPoints = new ArrayList<>(simplex.size());
		for (Matrix point : simplex) {
			movedPoints.add(matrix.add(point).multiply(SIGMA));
		}
		return movedPoints;
	}

	private static boolean checkCondition(double[] functionValues, double valueAtXR, int h) {
		int n = functionValues.length;
		for (int i = 0; i < n; i++) {
			if (i == h) {
				continue;
			}
			if (valueAtXR <= functionValues[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metoda koja obavlja refleksiju toèke xh s obzirom na centroidu.
	 * 
	 * @param xh
	 *            Toèku koju reflketiramo.
	 * @param xc
	 *            Toèko oko koje reflektiramo.
	 * @return Reflektirana toèka.
	 */
	private static Matrix reflection(Matrix xh, Matrix xc) {
		Matrix b = xh.multiply(ALPHA);
		Matrix a = xc.multiply(1 + ALPHA);
		return a.subtract(b);
	}

	/**
	 * Metoda koja obavlja ekspanziju reflektirane toèke s obzirom na centroidu.
	 * 
	 * @param xr
	 *            Reflektirana toèka.
	 * @param xc
	 *            Centroid.
	 * @return Nova ekspanzirana toèka.
	 */
	private static Matrix expansion(Matrix xr, Matrix xc) {
		Matrix a = xc.multiply(1 - GAMMA);
		Matrix b = xr.multiply(GAMMA);

		return a.add(b);
	}

	/**
	 * Metoda koja obavlja kontrakciju toèke xh s obzirom na centroid.
	 * 
	 * @param xh
	 *            Toèku koju kontrakiramo.
	 * @param xc
	 *            Centroid.
	 * @return Nova kontraktirana toèka.
	 */
	private static Matrix contraction(Matrix xh, Matrix xc) {
		Matrix a = xc.multiply(1 - BETA);
		Matrix b = xh.multiply(BETA);

		return a.add(b);
	}

	/**
	 * Metoda koja raèuna centroid iz zadanog simpleksa.
	 * 
	 * @param simplex
	 *            Simpleks.
	 * @param h
	 *            Indeks toèke iz simpleksa koju ne koristimo.
	 * @return Centroid.
	 */
	private static Matrix getCentroid(List<Matrix> simplex, int h) {
		int n = simplex.size();
		double[][] values = new double[1][n - 1];
		for (int i = 0; i < n; i++) {
			if (i == h) {
				continue;
			}
			double[] simValues = simplex.get(i).getValues()[0];
			for (int j = 0; j < n - 1; j++) {
				values[0][j] += simValues[j];
			}
		}
		return new Matrix(values).multiply(1.0 / (n - 1));
	}

	/**
	 * Metoda koja pronalazi unimodalni interval funkcije u okolini zadane
	 * toèke.
	 * 
	 * @param function
	 *            Funkcija za koju tražimo unimodalni interval.
	 * @param point
	 *            toèka oko koje tražimo unimodalni interval.
	 * @param h
	 *            Poèetni pomak kojim pretražujemo okolinu.
	 * @return Unimodalni interval.
	 */
	public static Interval<Double, Double> unimodalInterval(IFunction function, double point, double h) {
		double l = point - h;
		double r = point + h;
		double m = point;
		double step = 1;

		double fm = function.valueAt(new Matrix(point));
		double fl = function.valueAt(new Matrix(l));
		double fr = function.valueAt(new Matrix(r));

		if (fm < fr && fm < fl) {
			return new Interval<>(l, r);
		} else if (fm > fr) {
			while (true) {
				l = m;
				m = r;
				fm = fr;
				step *= 2;
				r = point + h * step;
				fr = function.valueAt(new Matrix(r));
				if (!(fm > fr)) {
					break;
				}
			}
		} else {
			while (true) {
				r = m;
				m = l;
				fm = fl;
				step *= 2;
				l = point - h * step;
				fl = function.valueAt(new Matrix(l));
				if (!(fm > fl)) {
					break;
				}
			}

		}
		return new Interval<>(l, r);
	}

	/**
	 * Metoda zlatnog reza koja pronalazi minimum funkcije za zadani unimodalni
	 * interval.
	 * 
	 * @param function
	 *            Funkciju za koju tražimo minimum.
	 * @param a
	 *            Lijeva strana unimodalnog intervala.
	 * @param b
	 *            Desna granica unimodalnog intervala.
	 * @return Optimum.
	 */
	public static double goldenSection(IFunction function, double a, double b) {
		double c = b - K * (b - a);
		double d = a + K * (b - a);
		double fc = function.valueAt(new Matrix(c));
		double fd = function.valueAt(new Matrix(d));

		while ((b - a) > EPSILON) {
			if (fc < fd) {
				b = d;
				d = c;
				c = b - K * (b - a);
				fd = fc;
				fc = function.valueAt(new Matrix(c));
			} else {
				a = c;
				c = d;
				d = a + K * (b - a);
				fc = fd;
				fd = function.valueAt(new Matrix(d));
			}

			// System.out.println(Arrays.toString(new double[] { a, b, c, d }));
			// System.out.println(
			// Arrays.toString(new double[] { function.valueAt(new Matrix(a)),
			// function.valueAt(new Matrix(b)),
			// function.valueAt(new Matrix(c)), function.valueAt(new Matrix(d))
			// }));

		}
		return (b + a) / 2;
	}

	/**
	 * Metoda koja provodi Hpretraživanje po koordinatnim osima za zadanu
	 * funkciju i poèetnu toèku.
	 * 
	 * @param function
	 *            Funkciju koju minimiziramo.
	 * @param startPoint
	 *            Poèetna toèka algoritma.
	 * @return Pronaðeni minimum.
	 */
	public static Matrix coordinateSearch(IFunction f, Matrix startPoint) {
		Matrix x = startPoint.copy();
		int n = startPoint.getColumns();
		Matrix unit = Matrix.getUnitMatrix(n);
		double[][] unitValues = unit.getValues();
		while (true) {
			// System.out.println(x);
			Matrix xs = x.copy();
			for (int i = 0; i < n; i++) {
				double[] vector = unitValues[i];
				IFunction function = new AbstractFunction() {

					@Override
					protected double functionValue(Matrix point) {
						double[][] values = new double[1][n];
						for (int j = 0; j < n; j++) {
							values[0][j] = vector[j] * point.get(0, 0) + x.get(0, j);
						}
						return f.valueAt(new Matrix(values));
					}
				};

				Interval<Double, Double> interval = unimodalInterval(function, x.get(0, i), 1);

				double lambda = goldenSection(function, interval.getLeft(), interval.getRight());
				x.set(0, i, lambda + x.get(0, i));

			}
			double norm = 0;
			for (int k = 0; k < n; k++) {
				norm += Math.pow(xs.get(0, k) - x.get(0, k), 2);
			}
			if (Math.sqrt(norm) < EPSILON) {
				break;
			}
		}
		return x;
	}
}
