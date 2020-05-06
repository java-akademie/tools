package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;

public class TrigonometricToolsTest2
{

    double gamma;
    double alpha;
    double beta;

    public static void main(String[] args)
    {
        testSWS();
        testWSW();
        testSSS();
    }

    public static void testSSS()
    {
        MyTools.uebOut("testSSS", 3);

        int MIN = 100;
        int MAX = 200;

        for (int i = 1; i <= 100; i++)
        {
            int a = MyTools.getRandom(MIN, MAX);
            int b = MyTools.getRandom(MIN, MAX);
            int c = a + b - MyTools.getRandom(1, MIN);
            testSSS(a, b, c);
        }

    }

    public static void testWSW()
    {
        MyTools.uebOut("testWSW", 3);
        testWSW(11, 44, 15);
        testWSW(30, 5, 50);
        testWSW(123, 9, 12);
        testWSW(179, 180, 179);
        testWSW(123, 9, 12);
    }

    public static void testSWS()
    {
        MyTools.uebOut("testSSW", 3);
        testSWS(1, 179.999, 41);
        testSWS(3, 90, 4);
        testSWS(5, 22, 12);
        testSWS(33, 120, 4);
        testSWS(3, 111, 4);
        testSWS(3, 4, 5);
    }

    public static void testSSS(final double a, final double b, final double c)
    {
        try
        {
            TrigonometricTools.checkSeitenlaengen(a, b, c);

            double gamma = TrigonometricTools.getW3_S1S2S3(a, b, c);
            double alpha = TrigonometricTools.getW3_S1S2S3(c, b, a);
            double beta = TrigonometricTools.getW3_S1S2S3(a, c, b);

            TrigonometricTools.checkWinkel(gamma, alpha, beta);

            double a1 = TrigonometricTools.flaecheSWS(c, alpha, b);
            double a2 = TrigonometricTools.flaecheSWS(a, beta, c);
            double a3 = TrigonometricTools.flaecheSWS(a, gamma, b);

            System.out.printf(
                    "a = %10f,  b = %10f,  c = %10f,  alpha = %10f,  beta = %10f,  gamma = %10f,  W'summe = %10f,  A1 = %15f,  A2 = %15f,  A3 = %15f  %n",
                    a, b, c, alpha, beta, gamma, (alpha + beta + gamma), a1, a2, a3);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
            MyTools.pause();
        }
    }

    public static void testWSW(double alpha, double c, double beta)
    {
        try
        {
            TrigonometricTools.checkWinkel(alpha);
            TrigonometricTools.checkWinkel(beta);

            double[] s = TrigonometricTools.getS2_W1S3W2(alpha, c, beta);

            if (s == null)
            {
                System.err.println("seiten sind null");
                return;
            }
            double a = s[0];
            double b = s[1];
            TrigonometricTools.checkSeitenlaengen(a, b, c);

            double gamma = 180 - alpha - beta;

            TrigonometricTools.checkWinkel(alpha, beta, gamma);

            double a1 = TrigonometricTools.flaecheSWS(c, alpha, b);
            double a2 = TrigonometricTools.flaecheSWS(a, beta, c);
            double a3 = TrigonometricTools.flaecheSWS(a, gamma, b);

            System.out.printf(
                    "a = %10f,  b = %10f,  c = %10f,  alpha = %10f,  beta = %10f, gamma = %10f,  W'summe = %10f,  A1 = %10f,  A2 = %10f,  A3 = %10f  %n",
                    a, b, c, alpha, beta, gamma, (alpha + beta + gamma), a1, a2, a3);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);

        }
    }

    public static void testSWS(double a, double gamma, double b)
    {
        try
        {
            double c = TrigonometricTools.getS3_S1W3S2(a, gamma, b);

            TrigonometricTools.checkSeitenlaengen(a, b, c);

            double alpha = TrigonometricTools.getW3_S1S2S3(b, c, a);
            double beta = TrigonometricTools.getW3_S1S2S3(a, c, b);

            TrigonometricTools.checkWinkel(alpha, beta, gamma);

            double a1 = TrigonometricTools.flaecheSWS(c, alpha, b);
            double a2 = TrigonometricTools.flaecheSWS(a, beta, c);
            double a3 = TrigonometricTools.flaecheSWS(a, gamma, b);

            System.out.printf(
                    "a = %10f,  b = %10f,  c = %10f,  alpha = %10f,  beta = %10f, gamma = %10f, W'summe = %10f,  A1 = %10f,  A2 = %10f,  A3 = %10f  %n",
                    a, b, c, alpha, beta, gamma, (alpha + beta + gamma), a1, a2, a3);
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
}
