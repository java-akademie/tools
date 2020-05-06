package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;

public class QuadratischeGleichung
{

    public static void main(String[] args)
    {

        MyTools.uebOut("Quadratische Gleichung");

        loeseQuadratischeGleichung(3, -2, -1);
        loeseQuadratischeGleichung(1, 2, 2);
        loeseQuadratischeGleichung(1, 7, 3);
        loeseQuadratischeGleichung(1, 8, 4);
        loeseQuadratischeGleichung(1, 11, 9);
        loeseQuadratischeGleichung(1, 11, 11);
        loeseQuadratischeGleichung(1, 14, 12);

        MyTools.uebOut("ABC Form ", 3);
        for (int i = 1; i <= 100; i++)
        {
            int a = MyTools.getRandom(1, 100);
            int b = MyTools.getRandom(1, 100);
            int c = MyTools.getRandom(1, 100);
            a = MyTools.getRandom(1, 2) == 1 ? -a : a;
            b = MyTools.getRandom(1, 2) == 1 ? -b : b;
            c = MyTools.getRandom(1, 2) == 1 ? -c : c;
            loeseQuadratischeGleichung(a, b, c);
        }
 
        MyTools.uebOut("PQ Form ", 3);
        for (int i = 1; i <= 100; i++)
        {
            int p = MyTools.getRandom(1, 100);
            int q = MyTools.getRandom(1, 100);
            p = MyTools.getRandom(1, 2) == 1 ? -p : p;
            q = MyTools.getRandom(1, 2) == 1 ? -q : q;
            loeseQuadratischeGleichung(1, p, q);
        }

    }

    static void loeseQuadratischeGleichung(double a, double b, double c)
    {

        System.out.println("\n-------------");
        System.out.printf("%2.2fx2 + %2.2fx + %2.2f %n", a, b, c);

        // Normalform ermitteln p, q
        double p = b / a;
        double q = c / a;

        System.out.printf("p=%f, q=%f\n", p, q);

        // Diskriminante ermitteln
        double D = Math.pow((p / 2), 2) - q;
        System.out.printf("D=%f\n", D);

        if (D < 0.0)
        {
            System.out.println("es gibt kein Loesung oder L={}");
            return;
        }

        // x1,2 berechnen
        double x1 = -(p / 2) + Math.sqrt(D);
        double x2 = -(p / 2) - Math.sqrt(D);
        System.out.printf("x1=%f, x2=%f\n", x1, x2);

        // satz von vieta
        double qx = x1 * x2;
        double px = -(x1 + x2);
        System.out.printf("px=%f, qx=%f\n", px, qx);
    }
}
