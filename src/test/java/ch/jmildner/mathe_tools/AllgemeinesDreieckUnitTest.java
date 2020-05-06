package ch.jmildner.mathe_tools;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AllgemeinesDreieckUnitTest extends TestCase
{
	public AllgemeinesDreieckUnitTest(String testName)
	{
		super(testName);
	}


	public static Test suite()
	{
		return new TestSuite(AllgemeinesDreieckUnitTest.class);
	}


	public void testSSS()
	{
		AllgemeinesDreieck.outKopf("SSS - drei Seiten");

		tstSSS(3, 4, 5);
		tstSSS(21, 23, 3);
		tstSSS(3, 4, 4);
		tstSSS(1, 3, 3);
		tstSSS(12, 13, 6);
		tstSSS(5, 12, 13);
		tstSSS(1000, 1001, 2000);
		tstSSS(7, 5, 11.96739582);
	}


	public void testSWS() throws Exception
	{
		AllgemeinesDreieck.outKopf(
				"SWS - zwei Seiten und der eingeschlossene Winkel");
		tstSWS(3, 90, 4);
		tstSWS(5, 55, 13);
		tstSWS(33, 90, 4);
		tstSWS(7, 5, 9);
		tstSWS(7, 140, 9);
	}



	public void testSsW() throws Exception
	{
		AllgemeinesDreieck.outKopf(
				"SsW - zwei Seiten und der der groesseren Seite gegenueberliegende Winkel");
		tstSsW(5, 4, 90);
		tstSsW(5, 3, 90);
		tstSsW(15, 3, 97);
		tstSsW(12, 3, 1);
		tstSsW(7, 5, 5);
	}


	private void tstSSS(double s1, double s2, double s3)
	{
		try
		{
			AllgemeinesDreieck ad = new AllgemeinesDreieck("SSS", s1,
					s2, s3);
			ad.outDaten();
		}
		catch (Exception e)
		{
			System.out.printf("%ns1=%f, s2=%f, s3=%f%n", s1, s2, s3);
			System.out.println(e.getMessage());
			System.out.println();
		}

	}


	private void tstSWS(double s1, double w3, double s2)
	{
		AllgemeinesDreieck ad;
		try
		{
			ad = new AllgemeinesDreieck("SWS", s1, w3, s2);
			ad.outDaten();
		}
		catch (Exception e)
		{
			System.out.printf("%ns1=%f, w3=%f, s2=%f%n", s1, w3, s2);
			System.out.println(e.getMessage());
			System.out.println();
		}

	}



	private void tstSsW(double s1, double s2, double w1)
	{
		try
		{
			AllgemeinesDreieck ad = new AllgemeinesDreieck("SsW", s1,
					s2, w1);
			ad.outDaten();
		}
		catch (Exception e)
		{
			System.out.printf("%ns1=%f, s2=%f, w1=%f%n", s1, s2, w1);
			System.out.println(e.getMessage());
			System.out.println();
		}
	}

}
