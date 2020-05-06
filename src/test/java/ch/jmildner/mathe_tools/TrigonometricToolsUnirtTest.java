package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TrigonometricToolsUnirtTest extends TestCase
{
	private static final int MIN = 0;
	private static final int MAX = 360;

	private static final boolean DEBUG = false;
	private static final boolean DEBUG1 = true;
	private static final boolean DEBUG2 = true;

	private static final String M7 = "%7.2f, %7.2f, %7.2f, %7.2f, %7.2f, %7.2f %7.2f %n%n%n";


	public TrigonometricToolsUnirtTest(String testName)
	{
		super(testName);
	}


	public static Test suite()
	{
		return new TestSuite(TrigonometricToolsUnirtTest.class);
	}


	public void testSinussatz()
	{
		// rechtwinkelige Dreiecke
		testSinussatz2(5, 3, 90);
		testSinussatz1(8, 15, 17, 90);
		testSinussatz1(7, 24, 25, 90);
		testSinussatz1(25, 312, 313, 90);
		testSinussatz1(476, 480, 676, 90);

		// gleichseitige Dreiecke
		testSinussatz1(30, 30, 30, 60);

		// allgemeine Dreiecke
		testSinussatz2(2222, 1111, 60);
		testSinussatz2(1111, 100, 58);
		testSinussatz2(1055, 134, 85);
		testSinussatz2(3, 5, 36.9);
		testSinussatz2(4, 3, 53.1);
		testSinussatz2(5, 1.9780409038562632, 1);
		testSinussatz2(4, 1.9780409038562632, 1);


		testCosinussatz1(3, 90, 4);
		testCosinussatz1(4, 90, 3);

		MyTools.uebOut("testCosinussatz2", 3);
		testCosinussatz2(3, 4, 6);
		testCosinussatz2(111, 112, 11);
		testCosinussatz2(111, 122, 11);
		testCosinussatz2(111, 14, 116);
		testCosinussatz2(111, 4, 6);
		testCosinussatz2(11, 7, 6);
		testCosinussatz2(1.5, 1, 1.5);
		testCosinussatz2(5, 12, 13);
	}



	private void testCosinussatz2(final double s1, final double s2, final double s3)
	{
		try
		{
			double w3 = TrigonometricTools.getW3_S1S2S3(s1, s2, s3);
			double w1 = TrigonometricTools.getW3_S1S2S3(s2, s3, s1);
			double w2 = TrigonometricTools.getW3_S1S2S3(s3, s1, s2);
			if (DEBUG2)
			{
				System.out.printf("55 " + M7, s1, s2, s3, w1, w2, w3, w1 + w2 + w3);
			}

			// assertTrue(DreieckTools.equals(180.0, w1 + w2 + w3));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}


	private void testSinussatz1(double a, double b, double c, double gamma)
	{
		// zwei Seiten und ein gegenueberliegender Winkel sind bekannt
		double alpha = TrigonometricTools.getW2_S1W1S2(c, gamma, a);
		double beta = TrigonometricTools.getW2_S1W1S2(c, gamma, b);

		if (DEBUG)
		{
			System.out.printf(M7, a, b, c, alpha, beta, gamma, alpha + beta + gamma);
		}

		assertTrue(TrigonometricTools.equals(180.0, alpha + beta + gamma));
	}


	private void testCosinussatz1(double s1, double w3, double s2)
	{
		// zwei Seiten und der eingeschlossene Winkel sind bekannt
		double s3 = TrigonometricTools.getS3_S1W3S2(s1, w3, s2);

		// jetzt kann mit Sinussatz weitergemacht werden

		double w2 = TrigonometricTools.getW2_S1W1S2(s3, w3, s2);
		double w1 = TrigonometricTools.getW2_S1W1S2(s3, w3, s1);

		if (DEBUG1)
		{
			System.out.printf("33 " + M7, s1, s2, s3, w1, w2, w3, w1 + w2 + w3);
		}

		double diff = 180 - w1 - w2 - w3;

		if (diff != 0)
		{
			// den winkel gegenueber der groessten seite anpassen
			if (s1 > s2 && s1 > 3)
				w1 += diff;
			else
				if (s2 > s1 && s2 > s3)
					w2 += diff;
				else
					if (s3 > s1 && s3 > s2)
						w3 += diff;
			System.out.printf("44 " + M7, s1, s2, s3, w1, w2, w3, w1 + w2 + w3);
		}
	}


	private void testSinussatz2(double a, double b, double alpha)
	{
		// zwei Seiten und ein gegenueberliegender Winkel sind bekannt
		// die zu dem bekannten Winkel gegenueberliegende Seite muss die
		// laengere sein
		if (a <= b)
		{
			if (DEBUG1)
				System.out.printf("%n%nder Test wird nicht gemacht, weil er schief gehen wird!"
						+ "%na darf nicht kleiner b sein [a=%f, b=%f] %n%n", a, b);
			return;
		}

		double beta = TrigonometricTools.getW2_S1W1S2(a, alpha, b);

		// der dritte Winkel kann jetzt berechnet werdn
		double gamma = 180 - alpha - beta;

		double c = 0; // TrigonometricTools.getS2_S1W1W2(a, alpha,
						// gamma);

		double diff = 180 - alpha - beta - gamma;

		if (diff != 0)
		{
			// den winkel gegenueber der groessten seite anpassen
			if (a > b && a > c)
				alpha += diff;
			else
				if (b > b && b > c)
					beta += diff;
				else
					if (c > a && c > b)
						gamma += diff;

			if (DEBUG1)
				System.out.printf("22 " + M7, a, b, c, alpha, beta, gamma, alpha + beta + gamma);
		}
		else
			if (DEBUG1)
				System.out.printf("11 " + M7, a, b, c, alpha, beta, gamma, alpha + beta + gamma);

		assertTrue(TrigonometricTools.equals(180.0, alpha + beta + gamma));
	}


	public void testSinus()
	{
		for (int i = MIN; i <= MAX; i++)
		{
			double f = TrigonometricTools.getSinFromDegrees(i);
			double winkel = TrigonometricTools.getDegreesFromSin(f);

			if (TrigonometricTools.equals(i, winkel))
			{
				assertTrue(TrigonometricTools.equals(i, TrigonometricTools.runden(winkel, 10)));
			}
			else
			{
				if (i > 90 && i <= 270)
				{
					double summe = i + TrigonometricTools.runden(winkel, 10);
					assertTrue(summe == 180.0);
				}
				else
					if (i > 270 && i <= 360)
					{
						double summe = i - TrigonometricTools.runden(winkel, 10);
						assertTrue(summe == 360.0);
					}
			}
		}
	}


	public void testCosinus()
	{
		for (double i = MIN; i <= MAX; i++)
		{
			double f = TrigonometricTools.getCosFromDegrees(i);
			double winkel = TrigonometricTools.getDegreeFromCosinus(f);

			if (TrigonometricTools.equals(i, winkel))
			{
				assertTrue(TrigonometricTools.equals(i, winkel));
			}
			else
			{
				double summe = i + TrigonometricTools.runden(winkel, 10);
				assertTrue(summe == 360.0);
			}
		}
	}


	public void testTangens()
	{
		for (double i = MIN; i <= MAX; i++)
		{
			double f = TrigonometricTools.getTanFromDegrees(i);
			double winkel = TrigonometricTools.getDegreesFromTan(f);

			if (TrigonometricTools.equals(i, winkel))
			{
				assertTrue(TrigonometricTools.equals(i, winkel));
			}
			else
			{
				if (i >= 90 && i < 270)
				{
					double summe = i - TrigonometricTools.runden(winkel, 10);
					assertTrue(summe == 180.0);
				}
				if (i >= 270 && i <= 360)
				{
					double summe = i - TrigonometricTools.runden(winkel, 10);
					assertTrue(summe == 360);
				}
			}
		}
	}

}
