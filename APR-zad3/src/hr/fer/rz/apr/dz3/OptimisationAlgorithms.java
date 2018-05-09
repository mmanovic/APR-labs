package hr.fer.rz.apr.dz3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.rz.apr.dz3.constraints.IConstraint;
import hr.fer.rz.apr.dz3.constraints.IExpConstraint;
import hr.fer.rz.apr.dz3.functions.AbstractFunction;
import hr.fer.rz.apr.dz3.functions.IFunction;
import hr.fer.rz.apr.dz3.functions.InnerPointFunction;
import hr.fer.rz.apr.dz3.functions.TransformedFunction;

/**
 * Razred koji definira algoritme pretraživanja funkcije jedne ili više
 * varijabli tj. nudi njihove implementacije kao statièke metode.
 * 
 * @author Mato
 *
 */
public class OptimisationAlgorithms {
	/**
	 * Konstanta koja oznaèava maksimalni broj iteraciji gdje rješenje
	 * divergira.
	 */
	public static int MAX_ITERATION = 100;
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
	public static Random random = new Random();

	/**
	 * Nalaženje toèke koja zadovoljava implicitna ogranièenja.
	 * 
	 * @param startPoint
	 *            Poèetna toèka od koje pretražujemo.
	 * @param constraints
	 *            Implicitna ogranièenja.
	 * @return Unutarnju toèku.
	 */
	public static Matrix innerPointSearch(Matrix startPoint, List<IConstraint> constraints) {
		return simplexAlgorithm(new InnerPointFunction(constraints), startPoint);
	}

	/**
	 * Algoritam koji traži minimum funkcije u zadana ogranièenja.
	 * 
	 * @param function
	 *            Poèetna funkcija.
	 * @param startPoint
	 *            Poèetna toèka.
	 * @param constraints
	 *            Zadana ogranièenja.
	 * @param t
	 *            Poèetni parametar.
	 * @return Minimum.
	 */
	public static Matrix transformation(IFunction function, Matrix startPoint, List<IConstraint> constraints,
			double t) {
		TransformedFunction transformedFunction = new TransformedFunction(function, t, constraints);
		Matrix previousSolution = startPoint;
		Matrix currentSolution = startPoint;

		double currentBest = function.valueAt(startPoint);
		double oldBest = currentBest;
		int counter = 0;
		while (true) {
			previousSolution = currentSolution;
			// currentSolution = hookeJeeves(transformedFunction,
			// previousSolution);
			currentSolution = hookeJeeves(transformedFunction, previousSolution);
			currentBest = transformedFunction.valueAt(currentSolution);
			//System.out.println(currentBest);
			t *= 10;
			transformedFunction.setT(t);
			//System.out.println(previousSolution + "===" + currentSolution);
			if (isSimilar(currentSolution, previousSolution) || counter > 100) {
				break;
			} else {
				if (Math.abs(oldBest - currentBest) < EPSILON || currentBest >= oldBest) {
					// if (oldBest != currentBest) {
					counter++;
				} else {
					counter = 0;
				}
			}

			if (oldBest >= currentBest) {
				oldBest = currentBest;
			}
		}
		return currentSolution;
	}

	private static boolean isSimilar(Matrix x, Matrix y) {
		double[] xValues = x.getValues()[0];
		double[] yValues = y.getValues()[0];
		for (int i = 0; i < xValues.length; i++) {
			if (Math.abs(xValues[i] - yValues[i]) > EPSILON) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Box algoritam koji rješava problem pronalaženja minimuma funkcije s
	 * ogranièenjima.
	 * 
	 * @param function
	 *            Poèetna funkcija.
	 * @param startPoint
	 *            poèetna toèka.
	 * @param impConstraints
	 *            Implicitna ogranièenja.
	 * @param expConstraints
	 *            Eksplicitna ogranièenja.
	 * @return Minimum
	 */
	public static Matrix box(IFunction function, Matrix startPoint, List<IConstraint> impConstraints,
			List<IExpConstraint> expConstraints) {
		ALPHA = 1.3;
		Matrix xc = startPoint.copy();
		if (!isSatisfyExplicitConstraints(expConstraints, xc)) {
			throw new IllegalArgumentException("Poèetna toèka ne zadovoljava eksplicitna ogranièenja.");
		}
		int n = xc.getColumns();
		List<Matrix> simplex = new ArrayList<>();
		for (int t = 0; t < 2 * n; t++) {
			double[] vector = new double[n];
			for (int i = 0; i < n; i++) {
				vector[i] = expConstraints.get(i).getMinValue() + random.nextDouble()
						* (expConstraints.get(i).getMaxValue() - expConstraints.get(i).getMinValue());
			}
			Matrix tmp = new Matrix(vector);
			while (!isSatisfyImplicitConstraints(impConstraints, tmp)) {
				tmp.addToThis(xc).scaleThis(0.5);
			}
			simplex.add(tmp);
			xc = getCentroid(simplex, -1, 2);
		}

		double currentBest = function.valueAt(startPoint);
		double oldBest = currentBest;
		int counter = 0;
		while (true) {
			double[] functionValues = new double[2 * n];
			double h1Value = function.valueAt(simplex.get(0));
			double h2Value = h1Value;
			int h1 = 0;
			for (int i = 0; i < 2 * n; i++) {
				Matrix x = simplex.get(i);
				double value = function.valueAt(x);
				functionValues[i] = value;
				if (value > h1Value) {
					h2Value = h1Value;
					h1Value = value;
					h1 = i;
					continue;
				}
				if (value > h2Value) {
					h2Value = value;
				}
			}
			xc = getCentroid(simplex, h1, 2);
			Matrix xr = reflection(simplex.get(h1), xc);
			double[] xrValues = xr.getValues()[0];
			for (int i = 0; i < n; i++) {
				xrValues[i] = Double.max(xrValues[i], expConstraints.get(i).getMinValue());
				xrValues[i] = Double.min(xrValues[i], expConstraints.get(i).getMaxValue());
			}
			xr = new Matrix(xrValues);
			while (!isSatisfyImplicitConstraints(impConstraints, xr)) {
				xr.addToThis(xc).scaleThis(0.5);
			}
			if (function.valueAt(xr) > h2Value) {
				xr.addToThis(xc).scaleThis(0.5);
			}
			simplex.set(h1, xr);
			int length = functionValues.length;
			double stopValue = 0;
			double centroidValue = function.valueAt(xc);

			currentBest = centroidValue;
			if (oldBest >= currentBest) {
				oldBest = currentBest;
			}

			for (int i = 0; i < length; i++) {
				stopValue += Math.pow(centroidValue - functionValues[i], 2);
			}

			if (Math.sqrt(stopValue / length) < EPSILON || counter >= 1000 || currentBest >= Double.MAX_VALUE) {
				break;
			} else {
				if (Math.abs(oldBest - currentBest) < EPSILON || currentBest >= oldBest) {
					// if (oldBest != currentBest) {
					counter++;
				} else {
					counter = 0;
				}
			}
		}
		ALPHA = 1.0;
		return xc;
	}

	private static boolean isSatisfyExplicitConstraints(List<IExpConstraint> expConstraints, Matrix point) {
		for (IExpConstraint constraint : expConstraints) {
			if (!constraint.satisfy(point)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isSatisfyImplicitConstraints(List<IConstraint> impConstraints, Matrix point) {
		for (IConstraint constraint : impConstraints) {
			if (!constraint.satisfy(point)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metoda gradijentnog spusta koja pronalazi lokalni optimum funkcije.
	 * 
	 * @param function
	 *            Poèetna funkcija.
	 * @param startPoint
	 *            Poèetna toèka.
	 * @param goldenSection
	 *            Treba li obaviti linijsko pretraživanje po gradijentu.
	 * @return Minimum.
	 */
	public static Matrix gradientDescent(IFunction function, Matrix startPoint, boolean goldenSection) {
		double currentBest = function.valueAt(startPoint);
		double oldBest = currentBest;
		int counter = 0;
		Matrix x = startPoint.copy();
		while (true) {
			//System.out.println(x);
			Matrix dir = function.getValueOfGradientAt(x);
			//System.out.println("smjer"+dir);
			if (dir.normF() < EPSILON || currentBest >= Double.MAX_VALUE) {
				break;
			} else {
				if (Math.abs(oldBest - currentBest) < EPSILON || currentBest >= oldBest) {
					// if (oldBest != currentBest) {
					counter++;
				} else {
					counter = 0;
				}
			}
			double lambda = -1;
			if (goldenSection) {
				Matrix dirCopy = dir.copy();
				IFunction function2 = new AbstractFunction() {

					@Override
					public Matrix valueOfGradientAt(Matrix matrix) {
						return null;
					}

					@Override
					public Matrix hesseMatrix(Matrix matrix) {
						return null;
					}

					@Override
					protected double functionValue(Matrix point) {
						return function.valueAt(x.add(dirCopy.multiply(point).transpose()));
					}
				};

				Interval<Double, Double> interval = unimodalInterval(function2, 0, 1);
				lambda = goldenSection(function2, interval.getLeft(), interval.getRight());
				//System.out.println(lambda);
			}
			x.addToThis(dir.multiply(lambda).transpose());
			currentBest = function.valueAt(x);
			if (oldBest >= currentBest) {
				oldBest = currentBest;
			}
		}
		return x;
	}

	/**
	 * Metoda koja pronalazi minimum funkcije.
	 * 
	 * @param function
	 *            Poèetna funkcija.
	 * @param startPoint
	 *            Poèetna toèka.
	 * @param goldenSection
	 *            Treba li linijsko pretraživanje.
	 * @return Minimum.
	 */
	public static Matrix newtonRaphson(IFunction function, Matrix startPoint, boolean goldenSection) {
		double currentBest = function.valueAt(startPoint);
		double oldBest = currentBest;
		int counter = 0;
		Matrix x = startPoint.copy();
		while (true) {
			Matrix gradient = function.getValueOfGradientAt(x);
			Matrix hessian = function.getHesseMatrix(x);
			Matrix shift = hessian.multiply(gradient);
			if (shift.normF() < EPSILON || counter >= 100 || currentBest >= Double.MAX_VALUE) {
				break;
			} else {
				if (Math.abs(oldBest - currentBest) < EPSILON || currentBest >= oldBest) {
					counter++;
				} else {
					counter = 0;
				}
			}
			double lambda = -1;
			if (goldenSection) {
				Matrix shiftCopy = shift.copy();
				IFunction function2 = new AbstractFunction() {

					@Override
					public Matrix valueOfGradientAt(Matrix matrix) {
						return null;
					}

					@Override
					public Matrix hesseMatrix(Matrix matrix) {
						return null;
					}

					@Override
					protected double functionValue(Matrix point) {
						return function.valueAt(x.add(shiftCopy.multiply(point).transpose()));
					}
				};

				Interval<Double, Double> interval = unimodalInterval(function2, 0, 1);
				lambda = goldenSection(function2, interval.getLeft(), interval.getRight());
			}
			x.addToThis(shift.multiply(lambda).transpose());
			// System.out.println(x);
			currentBest = function.valueAt(x);
			
			if (oldBest >= currentBest) {
				oldBest = currentBest;
			}
		}
		return x;
	}

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
		// Matrix x0 = startPoint;
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
			Matrix xc = getCentroid(simplex, h, simplex.size() - 1);
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
	private static Matrix getCentroid(List<Matrix> simplex, int h, int components) {
		int n = simplex.size();
		double[][] values = new double[1][components];
		for (int i = 0; i < n; i++) {
			if (i == h) {
				continue;
			}
			double[] simValues = simplex.get(i).getValues()[0];
			for (int j = 0; j < components; j++) {
				values[0][j] += simValues[j];
			}
		}
		if (h == -1) {
			return new Matrix(values).multiply(1.0 / n);
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

					@Override
					public Matrix valueOfGradientAt(Matrix matrix) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public Matrix hesseMatrix(Matrix matrix) {
						// TODO Auto-generated method stub
						return null;
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
