package Outils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class PageRank {

	public final double DAMPIN_FACTOR =0.85;
	/*
	 * 
	 * 
	 * 
	 * */
	public double[] produit(CLI cli, double[] vect) {

		double[] result = new double[vect.length];
		// ArrayList<Integer> res = new ArrayList<Integer>();

		int i = 0;
		while (i < cli.L.size() - 1) {

			int s = cli.L.get(i);

			while (s >= cli.L.get(i) && s < cli.L.get(i + 1)) {

				{

					result[cli.I.get(s)] += cli.C.get(s) * vect[i];

					System.out.println("T[" + i + "]" + "= " + cli.C.get(s) * vect[i]);
					// res.set(i,( c.get(s) * vect[i]));
					s++;

				}
			}

			i++;
		}

		return result;
	}

	/*
	 * 
	 * 
	 * 
	 * */
	double minus(double[] first, double[] second) {
		double result = 0.0;
		for (int i = 0; i < second.length; i++) {
			result += first[i] - second[i];
			// System.out.println(result +" = "+first[i]+" - "+second[i]);
		}
		// System.out.println(result);
		return result;
	}

	/*
	 * 
	 * 
	 * 
	 * */
	public double[] pageRank(double[] Z, double epsilon) {

		double zeta = 0.0;
		double beta = 0.0;
		double[] p0 = new double[Z.length];
		double[] p1 = new double[Z.length];
		p0 = Z;
		double w = 0;

		do {

			p1 = CLI.produitTranspose(p0);

			zeta = this.minus(p1, p0);

			p0 = p1;

			w = Math.abs(zeta - beta);
			beta = zeta;

		} while (w > epsilon);

		return p0;

	}
	
	
	/*
	 * 
	 * 
	 * 
	 * */
	public ArrayList<Pair<Integer, Double>> sort(double[] p0) {

		ArrayList<Pair<Integer, Double>> fiable = new ArrayList<Pair<Integer, Double>>();
		for (int i = 0; i < p0.length; i++) {
			Pair<Integer, Double> pair = new Pair<Integer, Double>(i, p0[i]);
			fiable.add(pair);
		}

		Collections.sort(fiable, new Comparator<Pair<Integer, Double>>() {

			@Override
			public int compare(Pair<Integer, Double> o1, Pair<Integer, Double> o2) {
				if (o1.getValue() < o2.getValue()) {
					return 1;
				} else {
					if (o1.getValue() > o2.getValue()) {
						return -1;
					} else
						return 0;
				}
			}
		});
		return fiable;
	}
	
	
	
	/*
	 * 
	 * 
	 * 
	 * */
	public double[] pageRankZap(double[] Z, double epsilon) {

		double zeta = 0.0;
		double beta = 0.0;
		double[] p0 = new double[Z.length];
		double[] p1 = new double[Z.length];
		p0 = Z;
		double w = 0;

		do {
			for (int i = 0; i < p1.length; i++) {

			}
			p1 = CLI.produitTransposeBis(p0);

			zeta = this.minus(p1, p0);

			p0 = p1;

			w = Math.abs(zeta - beta);

			beta = zeta;

		} while (w > epsilon);

		return p0;

	}
}
