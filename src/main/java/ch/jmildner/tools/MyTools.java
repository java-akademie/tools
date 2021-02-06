package ch.jmildner.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Die finale Klasse <code>MyTools</code> beinhaltet allgemein benoetigte
 * <code>static</code> Methoden.
 *
 * @author Johann Mildner, Basel
 */
public final class MyTools {
	/**
	 * privater Konstruktor da <code>MyTools</code> nicht instantiiert werden darf
	 */
	private MyTools() {
	}

	/**
	 * Liest von der Tastatur einen beliebigen <code>String</code> ein und stellt
	 * ihn getrimmt zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>String str = MyTools.getString("bitte String eingeben &gt; ");</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @return string
	 */
	public static String getString(String prompt) {
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print(prompt);
		System.out.flush();

		try {
			return br.readLine().trim();
		} catch (IOException ioException) {
			return "IOException in br.readLine() ....";
		}

	}

	/**
	 * Liest von der Tastatur einen <code>String</code> ein. Der eingegebene String
	 * darf wenn emptyAllowed false ist nicht leer sein.
	 * <p>
	 * Beispiel:
	 * <code>String s = MyTools.getString("bitte String eingeben &gt; ",false);</code>
	 *
	 * @param prompt       die Eingabeaufforderung
	 * @param emptyAllowed true oder false
	 * @return stringWert
	 */
	public static String getString(String prompt, boolean emptyAllowed) {
		while (true) {
			String stringWert = getString(prompt);

			if (emptyAllowed) {
				System.out.flush();
				return stringWert;
			} else {
				if (!stringWert.equals("")) {
					System.out.flush();
					return stringWert;
				} else {
					System.out.print("empty String not allowed .... ");
					System.out.flush();
				}
			}
		}
	}

	/**
	 * Liest von der Tastatur einen beliebigen <code>double</code> ein und stellt
	 * ihn zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getDouble("bitte Double eingeben &gt; ");</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @return doubleWert
	 */
	public static double getDouble(String prompt) {
		while (true) {
			try {
				System.out.flush();
				return Double.parseDouble(getString(prompt));
			} catch (NumberFormatException e) {
				System.out.print("not a double .... ");
				System.out.flush();
			}
		}
	}

	/**
	 * Liest von der Tastatur einen <code>double</code> groesser Null (GN=greater
	 * null) ein und stellt ihn zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getDoubleGN("bitte Double eingeben &gt; ");</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @return doubleWert
	 */
	public static double getDoubleGN(String prompt) {
		while (true) {
			double doubleWert = getDouble(prompt);

			if (doubleWert > 0.0) {
				System.out.flush();
				return doubleWert;
			} else {
				System.out.print("must be greater 0 ... ");
				System.out.flush();
			}
		}
	}

	/**
	 * Liest von der Tastatur einen <code>double</code> ungleich Null (NN=not null)
	 * ein und stellt ihn zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getDoubleNN("bitte Double eingeben &gt; ");</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @return doubleWert
	 */
	public static double getDoubleNN(String prompt) {
		while (true) {
			double doubleWert = getDouble(prompt);

			if (doubleWert != 0.0) {
				System.out.flush();
				return doubleWert;
			} else {
				System.out.print("must not be 0 ... ");
				System.out.flush();
			}
		}
	}

	/**
	 * Liest von der Tastatur einen beliebigen <code>int</code> ein und stellt ihn
	 * zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getInteger("bitte Integer eingeben &gt; ");</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @return intWert
	 */
	public static int getInteger(String prompt) {
		while (true) {
			try {
				return Integer.parseInt(getString(prompt));
			} catch (NumberFormatException e) {
				System.out.print("not an integer .... ");
				System.out.flush();
			}
		}
	}

	/**
	 * Liest von der Tastatur einen <code>int</code> aus der Domaene
	 * <code>min - max</code> ein und stellt ihn zur Verfuegung.
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getInteger("bitte Integer (1-5) eingeben &gt; ",1,5);</code>
	 *
	 * @param prompt die Eingabeaufforderung
	 * @param min    Min-Wert
	 * @param max    Max-Wert
	 * @return intWert
	 */
	public static int getInteger(String prompt, int min, int max) {
		while (true) {
			int intWert = getInteger(prompt);

			if (intWert >= min && intWert <= max) {
				System.out.flush();
				return intWert;
			} else {
				System.out.print("out of range .... ");
				System.out.flush();
			}
		}
	}

	/**
	 * Liefert einen zufaelligen <code>String</code> in der Laenge
	 * <code>length</code> aus der Domaene <code>0-9, a-z, A-Z</code>.
	 * <p>
	 * Beispiel: <code>String s = MyTools.getRandomString(32);</code>
	 *
	 * @param length Laenge
	 * @return randomWert
	 */
	public static String getRandomString(int length) {
		final char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'l', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z' };

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= length; i++) {
			sb.append(chars[(int) (Math.random() * chars.length)]);
		}

		return sb.toString();
	}

	/**
	 * Liefert einen zufaelligen <code>int</code> aus der Domaene
	 * <code>1 - 0x7fffffff</code>.
	 * <p>
	 * Beispiel: <code>int i = MyTools.getRandom();</code>
	 *
	 * @return randomWert
	 */
	public static int getRandom() {
		return getRandom(1, Integer.MAX_VALUE);
	}

	/**
	 * Liefert einen zufaelligen <code>int</code> aus der Domaene
	 * <code>1L - 0x7fffffffffffffffL</code>.
	 * <p>
	 * Beispiel: <code>long i = MyTools.getRandomLong();</code>
	 *
	 * @return randomWert
	 */
	public static long getRandomLong() {
		return getRandom(1L, Long.MAX_VALUE);
	}

	/**
	 * Liefert einen zufaelligen <code>int</code> aus der Domaene
	 * <code>min - max</code>. <br>
	 * <p>
	 * min muss groesser 0 sein. <br>
	 * max muss groesser min sein. <br>
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getRandom(1,46); // liefert eine Lottozahl</code>
	 *
	 * @param min - kleinsterr Wert
	 * @param max - groesster Wert
	 * @return randomWert
	 */
	public static int getRandom(int min, int max) {
		if (min < 1 || max <= min) {
			min = 1;
			max = Integer.MAX_VALUE;
		}

		return (int) (min + (Math.random() * (max - min + 1)));
	}

	/**
	 * Liefert einen zufaelligen <code>long</code> aus der Domaene
	 * <code>min - max</code>. <br>
	 * <p>
	 * min muss groesser 0 sein. <br>
	 * max muss groesser min sein. <br>
	 * <p>
	 * Beispiel:
	 * <code>int i = MyTools.getRandom(1,46); // liefert eine Lottozahl</code>
	 *
	 * @param min - kleinsterr Wert
	 * @param max - groesster Wert
	 * @return randomWert
	 */
	public static long getRandom(long min, long max) {
		if (min < 1 || max < min) {
			min = 1;
			max = Long.MAX_VALUE;
		}

		return (long) (min + (Math.random() * (max - min + 1)));
	}

	/**
	 * Liefert eine zufaellige Primzahl <code>(long)</code> aus der Domaene
	 * <code>1L - 0xfffffffffffffffL</code>.
	 * <p>
	 * Beispiel: <code>int i = MyTools.getRandomPrime();</code>
	 *
	 * @return randomWert
	 */
	public static long getRandomPrime() {
		return getRandomPrime(1L, Long.MAX_VALUE);
	}

	/**
	 * Liefert eine zufaellige Primzahl <code>(long)</code> aus der Domaene
	 * <code>min - max</code>. min muss groesser 0 sein. <br>
	 * max muss groesser min sein. <br>
	 * <p>
	 * Beispiel: <code>int i = MyTools.getRandomPrime(1,100000000);</code>
	 * <p>
	 * Die Funktion ermittelt eine Zufallszahl zwischen min und max und addiert
	 * solange 1 auf die ermittelte Zufallszahl, bis die Zahl eine Primzahl ist. Es
	 * kann passieren, dass zwischen der zuerst ermittelten Zufallszahl und dem
	 * Maximum keine Primzahl liegt. Es wird dann die naechsthoehere Zahl die eine
	 * Primzahl ist, zurueckgegeben. <br>
	 * <p>
	 * Der Aufruf <code>MyTools.getRandomPrime(21,28);</code> gibt entweder 23 oder
	 * 29 zurueck.<br>
	 *
	 * @param min - kleinster Wert
	 * @param max - groesstr Wert
	 * @return randomWert
	 */
	public static long getRandomPrime(long min, long max) {
		if (min < 1 || max <= min) {
			min = 1L;
			max = Long.MAX_VALUE;
		}

		long randomWert = getRandom(min, max);

		while (true) {
			if (new BigInteger(randomWert + "").isProbablePrime(999999999)) {
				return randomWert;
			}

			randomWert++;
		}
	}

	/**
	 * @param text       Ueberschriftstext
	 * @param leerzeilen Anzahl Leerzeilen davor
	 */
	public static void h1(String text, int leerzeilen) {
		text = text.trim();
		String fs = "/";
		String bs = "\\";
		
		hUeberschrift(text,leerzeilen,fs,bs);
	}
	
	/**
	 * @param text       Ueberschriftstext
	 * @param leerzeilen Anzahl Leerzeilen davor
	 */
	public static void h2(String text, int leerzeilen) {
		text = text.trim();
		String fs = "+";
		String bs = "+";
		
		hUeberschrift(text,leerzeilen,fs,bs);
	}


	private static void hUeberschrift(String text, int leerzeilen, String fs, String bs) {
		
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= leerzeilen; i++) {
			sb.append("\n");
		}

		StringBuilder striche = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			striche.append("-");
		}
		
		String nl = "\n";
		sb.append(fs).append("-").append(striche).append("-").append(bs).append(nl);
		sb.append("! ").append(text).append(" !").append(nl);
		sb.append(bs).append("-").append(striche).append("-").append(fs).append(nl);

		System.out.println(sb.toString());
	}

	/**
	 * @param text       Ueberschriftstext
	 * @param leerzeilen Anzahl Leerzeilen davor
	 * @return String
	 */
	@Deprecated
	public static String makeUeberschrift(String text, int leerzeilen) {
		text = text.trim();

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= leerzeilen; i++) {
			sb.append("\n");
		}

		StringBuilder striche = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			striche.append("-");
		}

		sb.append("+-").append(striche).append("-+\n");
		sb.append("! ").append(text).append(" !\n");
		sb.append("+-").append(striche).append("-+\n");

		return sb.toString();
	}

	/**
	 * @param text       Unterschriftstext
	 * @param leerzeilen Anzahl Leerzeilen danach
	 * @return String
	 */
	@Deprecated
	public static String makeUnterschrift(String text, int leerzeilen) {
		text = text.trim();

		StringBuilder sb = new StringBuilder();

		StringBuilder striche = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			striche.append("-");
		}

		sb.append("+-").append(striche).append("-+\n");
		sb.append("! ").append(text).append(" !\n");
		sb.append("+-").append(striche).append("-+\n");

		for (int i = 1; i <= leerzeilen; i++) {
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * Gibt drei Sternchen <code>(***)</code> aus und wartet darauf, dass der
	 * Benutzer die Eingabtaste drueckt.
	 * <p>
	 * Beispiel: <code>MyTools.pause();</code>
	 */
	public static void pause() {
		pause("\n*** ");
	}

	/**
	 * Gibt eine beliebigen <code>String</code> aus und wartet darauf, dass der
	 * Benutzer die Eingabtaste drueckt.
	 * <p>
	 * Beispiel: <code>MyTools.pause("weiter mit Eingabetaste ...");</code>
	 *
	 * @param str beliebiger String der an der Konsole ausgegeben werden soll
	 */
	public static void pause(String str) {
		getString(str);
		System.out.println();
		System.out.flush();
	}

	/**
	 * Legt den Thread fuer einige Millisekunden schlafen.
	 * <p>
	 * Beispiel:
	 * <code>MyTools.sleep(1000); // Thread soll eine Sekunde schlafen</code>
	 *
	 * @param milliSeconds Anzahl der Millisekunden die der Thread schlafen soll
	 */
	public static void sleep(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in Thread.sleep()\n" + e);
			System.out.flush();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * @param uebText Ueberschrift
	 */
	@Deprecated
	public static void uebOut(String uebText) {
		uebOut(uebText, 0);
	}

	/**
	 * @param uebText    Ueberschriftstext
	 * @param leerzeilen Anzahl Leerzeilen davor
	 */
	@Deprecated
	public static void uebOut(String uebText, int leerzeilen) {
		System.out.print(makeUeberschrift(uebText, leerzeilen));
	}

	/**
	 * @param text Unterschriftstext
	 */
	@Deprecated
	public static void untOut(String text) {
		untOut(text, 0);
	}

	/**
	 * @param text       Unterschriftstext
	 * @param leerzeilen Anzahl Leerzeilen danach
	 */
	@Deprecated
	public static void untOut(String text, int leerzeilen) {
		System.out.print(makeUnterschrift(text, leerzeilen));
	}

}
