package ch.jmildner.mathe_tools;

import java.util.Set;
import java.util.TreeSet;

import ch.jmildner.tools.MyTools;

public class PythagoraeischeDreiecke {

	private static Set<int[]> pythagoraeischeDreieckszahlen;

	public static void main(String[] args) throws Exception {
		new PythagoraeischeDreiecke().run();
	}

	private void run() throws Exception {
		MyTools.h2("Pythagoraeische Dreiecke - Start", 1);

		erstellen(1);
		ausgeben();

		erstellen(2);
		ausgeben();

		MyTools.h2("Pythagoraeische Dreiecke - Stopp", 1);
	}

	private void erstellen(int i) throws Exception {
		pythagoraeischeDreieckszahlen = new TreeSet<>((int[] o1, int[] o2) -> {
			String w1 = String.format("%10s %10s %10s", "" + o1[0], "" + o1[1], "" + o1[2]);
			String w2 = String.format("%10s %10s %10s", "" + o2[0], "" + o2[1], "" + o2[2]);

			return w1.compareTo(w2);
		});

		switch (i) {
		case 1:
			erstellen1();
			break;
		case 2:
			erstellen2();
			break;
		}

	}

	private void erstellen2() throws Exception {
		int MAX = 400;

		MyTools.h2("erstellen2 " + MAX, 1);

		for (int i = 1; i <= MAX; i++) {
			int[] abc = TrigonometricTools.getPythagoraeischeDreieckszahlen();

			boolean ok = pythagoraeischeDreieckszahlen.add(abc);

			if (!ok) {
				i--;
			}
		}
	}

	private void erstellen1() throws Exception {
		int MAX_I = 25;
		int MAX_J = 16;

		MyTools.h2("erstellen1 " + MAX_I * MAX_J, 1);

		for (int i = 1; i <= MAX_I; i++) {
			for (int j = i + 1; j <= MAX_J + i; j++) {
				pythagoraeischeDreieckszahlen.add(TrigonometricTools.getPythagoraeischeDreieckszahlen(i, j));
			}
		}
	}

	private void ausgeben() {
		MyTools.h2("ausgeben: " + pythagoraeischeDreieckszahlen.size(), 1);

		String s5 = "%5s  ", s14 = "%14s  ";
		String d5 = "%5d  ", f14 = "%14.8f  ";

		String striche = "-------------------------------------------------------------------------------------------------------------------";

		String kFormat = s5 + s5 + s5 + s14 + s14 + s14 + s14 + s14 + s14;
		String dFormat = d5 + d5 + d5 + f14 + f14 + f14 + f14 + f14 + f14;
		String kopf = String.format(kFormat, "a", "b", "c", "Alpha", "Beta", "Gamma", "q", "p", "h");

		System.out.println();

		System.out.println(striche);
		System.out.println(kopf);
		System.out.println(striche);

		pythagoraeischeDreieckszahlen.stream().map((abc) -> {
			int a = abc[0];
			int b = abc[1];
			int c = abc[2];
			double da = abc[0];
			double db = abc[1];
			double dc = abc[2];
			// GEGENKATHETE / HYPOTHENUSE == SIN(alpha)
			double alpha = TrigonometricTools.getDegreesFromSin(da / dc);
			// ANKATHETE / HYPOTHENUSE = COS(beta)
			double beta = TrigonometricTools.getDegreesFromSin(db / dc);
			// PYTGAGIRAEISCHES DREIECK MUSS EINEN RECHTEN WINKEL HABEN
			double gamma = alpha + beta;
			// P * C = A^2 Kathetensatz
			double p = da * da / dc;
			// Q * C = B'2 Kathetensatz
			double q = db * db / dc;
			// P * Q = H^2 Hoehensatz
			double h = Math.sqrt(p * q);
			String zeile = String.format(dFormat, a, b, c, alpha, beta, gamma, p, q, h);
			return zeile;
		}).forEachOrdered((zeile) -> {
			System.out.println(zeile);
		});
	}

}
