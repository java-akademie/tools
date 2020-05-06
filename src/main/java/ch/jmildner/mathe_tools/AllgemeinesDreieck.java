package ch.jmildner.mathe_tools;

public final class AllgemeinesDreieck
{
	private static boolean DEBUG = true;

	private static String f14 = "%14.8f  ";
	private static String s14 = "%14s  ";
 
	private static String dFormat = f14 + f14 + f14 + f14 + f14 + f14
			+ f14 + f14 + f14 + f14 + f14;

	private static String kFormat = s14 + s14 + s14 + s14 + s14 + s14
			+ s14 + s14 + s14 + s14 + s14;

  
	// Flaechen
	private static double A1s, A2s, A3s, A1h, A2h, A3h;
	private double flaeche;


	// Hoehen
	private double h1, h2, h3;


	// Seiten
	private double s1, s2, s3;

	// Winkel
	private double w1, w2, w3, wSumme;



	private void checkWinkel() throws Exception
	{
		double ws = w1 + w2 + w3;

		if (TrigonometricTools.equals(ws, 180.0))
		{
			return;
		}


		for (int i = 0; i < 3; i++)
		{
			double[] w = { w1, w2, w3 };

			w[i] = 180 - w[i];

			ws = w[0] + w[1] + w[2];

			if (TrigonometricTools.equals(ws, 180.0))
			{
				switch (i)
				{
					case 0:
						w1 = w[i];
						break;
					case 1:
						w2 = w[i];
						break;
					case 2:
						w3 = w[i];
						break;
					default:
						break;
				}
			}
		}

		ws = w1 + w2 + w3;

		if (!TrigonometricTools.equals(ws, 180.0))
		{
			if (DEBUG)
			{
				System.out.println();
				outDaten();
			}
			throw new Exception("Winkelsumme ist nicht 180 [" + w1 + "+"
					+ w2 + " + " + w3 + " = " + ws + "]");
		}
	}


	private static void out(String zeile)
	{
		System.out.println(zeile);
	}


	public static void outKopf(String typ)
	{
		if (!DEBUG)
			return;

		String striche = "-------------------------------------------------"
				+ "--------------------------------------------------------"
				+ "--------------------------------------------------------"
				+ "-------------";

		String kopf = String.format(kFormat, "Seite_1", "Seite_2",
				"Seite_3", "Hoehe_1", "Hoehe_2", "Hoehe_3", "Winkel_1",
				"Winkel_2", "Winkel_3", "WinkelSumme", "Flaeche");

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(striche);
		System.out.println("                                "
				+ "------------   " + typ + "   ------------");
		System.out.println(striche);
		out(kopf);
		System.out.println(striche);
	}



	private static double qu(double basis)
	{
		return basis * basis;
	}


	public AllgemeinesDreieck(String typ, double x, double y, double z)
			throws Exception
	{
		switch (typ)
		{
			case "SSS":

				s1 = x;
				s2 = y;
				s3 = z;

				TrigonometricTools.checkSeitenlaengen(s1, s2, s3);

				w1 = winkelCosinussatz(s1, s2, s3);
				w2 = winkelCosinussatz(s2, s1, s3);
				w3 = winkelCosinussatz(s3, s1, s2);

				checkWinkel();

				break;

			case "SWS":

				s1 = x;
				w3 = y;
				s2 = z;

				s3 = seiteCosinussatz(s1, w3, s2);

				w1 = winkelCosinussatz(s1, s2, s3);
				w2 = winkelCosinussatz(s2, s1, s3);

				checkWinkel();

				TrigonometricTools.checkSeitenlaengen(s1, s2, s3);

				break;

			case "SsW":

				s1 = x;
				s2 = y;
				w1 = z;

				TrigonometricTools.checkSsW(s1, s2, w1);

				w2 = winkelSinussatz(s1, w1, s2);
				s3 = seiteSinussatz(s1, w1, 180 - w1 - w2);
				w3 = winkelSinussatz(s1, w1, s3);
				checkWinkel();
				TrigonometricTools.checkSeitenlaengen(s1, s2, s3);

				break;
		}

		wSumme = w1 + w2 + w3;

		h1 = s2 * TrigonometricTools.getSinFromDegrees(w3);
		h2 = s3 * TrigonometricTools.getSinFromDegrees(w1);
		h3 = s1 * TrigonometricTools.getSinFromDegrees(w2);

		A1s = flaecheSS(s1, s2, w3);
		A2s = flaecheSS(s1, s3, w2);
		A3s = flaecheSS(s2, s3, w1);
		A1h = flaecheSH(s1, h1);
		A2h = flaecheSH(s2, h2);
		A3h = flaecheSH(s3, h3);

		if (TrigonometricTools.equals(A1s, A2s)
				&& TrigonometricTools.equals(A2s, A3s)
				&& TrigonometricTools.equals(A3s, A1h)
				&& TrigonometricTools.equals(A1h, A2h)
				&& TrigonometricTools.equals(A2h, A3h))
		{
			flaeche = A1s;
		}
		else
		{
			throw new Exception
			// System.out.println
			("die Flaechen stimmen nicht ueberein [" + A1s + ",  " + A2s
					+ ",  " + A3s + ",  " + A1h + ",  " + A2h + ",  "
					+ A3h + "]");
		}
	}



	private double flaecheSH(double s, double h)
	{
		return s * h / 2;
	}


	private double flaecheSS(double s1, double s2, double w3)
	{
		return s1 * s2 * TrigonometricTools.getSinFromDegrees(w3) / 2;
	}


	public double getH1()
	{
		return h1;
	}


	public double getH2()
	{
		return h2;
	}



	public double getH3()
	{
		return h3;
	}



	public double getS1()
	{
		return s1;
	}



	public double getS2()
	{
		return s2;
	}



	public double getS3()
	{
		return s3;
	}



	public double getW1()
	{
		return w1;
	}



	public double getW2()
	{
		return w2;
	}



	public double getW3()
	{
		return w3;
	}


	public void outDaten()
	{
		String zeile = String.format(dFormat, s1, s2, s3, h1, h2, h3,
				w1, w2, w3, wSumme, flaeche);

		out(zeile);
	}



	public double seiteCosinussatz(double s1, double w3, double s2)
	{
		return Math.sqrt(qu(s1) + qu(s2) - (-2 * s1 * s2
				* TrigonometricTools.getCosFromDegrees(w3)));
	}


	private double seiteSinussatz(double s1, double w1, double w2)
	{
		return s1 * TrigonometricTools.getSinFromDegrees(w2)
				/ TrigonometricTools.getSinFromDegrees(w1);
	}


	public void show()
	{
		System.out.println(this);
	}



	@Override
	public String toString()
	{
		return "AllgemeinesDreieck [s1=" + s1 + ", s2=" + s2 + ", s3="
				+ s3 + ", h1=" + h1 + ", h2=" + h2 + ", h3=" + h3
				+ ", w1=" + w1 + ", w2=" + w2 + ", w3=" + w3 + "]";
	}


	private double winkelCosinussatz(double s1, double s2, double s3)
	{
		double cos = ((qu(s1) - qu(s2) - qu(s3)) / (-2 * s2 * s3));
		return TrigonometricTools.getDegreeFromCosinus(cos);
	}


	private double winkelSinussatz(double s1, double w1, double s2)
	{
		return TrigonometricTools.getDegreesFromSin(
				TrigonometricTools.getSinFromDegrees(w1) * s2 / s1);
	}
}
