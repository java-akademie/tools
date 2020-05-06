package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;

public class TrigonometricToolsTest1
{

    public static void main(String[] args)
    {
        checkSeitenlaengen();
        getWinkelSSS();
        checkWinkel();
    }

    private static void checkWinkel()
    {
        MyTools.uebOut("checkWinkel", 2);

        checkWinkel(33, 47, 100);
        checkWinkel(0, 2, 180);
        checkWinkel(-1, 0, 181);
        checkWinkel(1, 1, 178);
    }

    private static void getWinkelSSS()
    {
        MyTools.uebOut("getWinkelSSS", 2);

        getWinkelSSS(3, 4, 5);
        getWinkelSSS(3, 5, 3);
        getWinkelSSS(4, 5, 3);
        getWinkelSSS(1, 1, 1);
        getWinkelSSS(1, 1, 1);
        getWinkelSSS(5.1, 5.2, 10);
        getWinkelSSS(10, 5.1, 5.2);
        getWinkelSSS(10, 5.2, 5.1);
    }

    private static void checkSeitenlaengen()
    {
        MyTools.uebOut("checkSeitenlaengen", 2);

        checkSeitenlaengen(1, 2, 3);
        checkSeitenlaengen(1, 1, 2);
        checkSeitenlaengen(3, 4, 5);
    }

    private static void checkWinkel(double i, double j, double k)
    {
        System.out.printf("%n%f + %f + %f = %f %n", i, j, k, i + j + k);
        try
        {
            TrigonometricTools.checkWinkel(i, j, k);
            System.out.println("OK");
        }
        catch (Exception e)
        {
            System.out.println("Fehler checkWinkel: " + e.getMessage());
        }
    }

    private static void getWinkelSSS(double a, double b, double c)
    {
        try
        {
            double gamma = TrigonometricTools.getW3_S1S2S3(a, b, c);
            System.out.println(gamma);
        }
        catch (Exception e)
        {
            System.out.println("Fehler getWinkelSSS: " + e.getMessage());

        }
    }

    private static void checkSeitenlaengen(double a, double b, double c)
    {
        System.out.printf("%n%f, %f, %f %n", a, b, c);
        try
        {
            TrigonometricTools.checkSeitenlaengen(a, b, c);
            System.out.println("OK");
        }
        catch (Exception e)
        {
            System.out.println("Fehler checkSeitenlaengen: " + e.getMessage());
        }
    }

}
