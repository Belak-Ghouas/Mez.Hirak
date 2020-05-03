package Outils;

import java.util.ArrayList;

public class CLI {

	public static ArrayList<Double> C = new ArrayList<Double>();
	public static ArrayList<Integer> L = new ArrayList<Integer>();
	public static ArrayList<Integer> I = new ArrayList<Integer>();

	public static double[] produitTransposeBis(double[] vect) {

		double[] result = new double[L.size() - 1];

		int i = 0;
		while (i < L.size() - 1) {

			int s = L.get(i);

			while (s >= L.get(i) && s < L.get(i + 1)) {

				{

					result[I.get(s)] += C.get(s) * vect[i];

					//System.out.println(" " + I.get(s) + "  ");
					// res.set(i,( c.get(s) * vect[i]));
					s++;

				}
			}

			i++;
		}
		for (int j = 0; j < result.length; j++) {
			result[j] = 0.15 + 0.85 * result[j];
		}
		return result;
	}

	public static double[] produitTranspose(double[] vect) {

		double[] result = new double[L.size() - 1];

		int i = 0;
		while (i < L.size() - 1) {

			int s = L.get(i);

			while (s >= L.get(i) && s < L.get(i + 1)) {

				{

					result[I.get(s)] += C.get(s) * vect[i];

					// System.out.println("T["+i+"]" +"= "+C.get(s)+" "+vect[i] +" "+i);
					// res.set(i,( c.get(s) * vect[i]));
					s++;

				}
			}

			i++;
		}
		return result;
	}

	public static double[] produitSimple(double[] vect) {

		double[] result = new double[L.size() - 1];

		int i = 0;
		while (i < L.size() - 1) {

			int s = L.get(i);

			while (s >= L.get(i) && s < L.get(i + 1)) {

				result[I.get(s)] += C.get(s) * vect[I.get(s)];
				s++;

			}

			i++;
		}

		return result;
	}

}
