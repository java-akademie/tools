package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;

public class ComplexNumberTest
{

    public static void main(String[] args)
    {
        test1();

        testEinerGleichung(-10, 40);
        testEinerGleichung(-10, 26);
        testEinerGleichung(-10, 25);
        testEinerGleichung(-10, 24);
    }

    private static void test1()
    {
        ComplexNumber c1;
        ComplexNumber c2;
        ComplexNumber cerg;

        MyTools.h2("Addition", 2);
        c1 = new ComplexNumber(3, 2);
        c2 = new ComplexNumber(5, 5);
        cerg = add(c1, c2); // 8+7i

        MyTools.h2("Subtraction", 2);
        sub(cerg, c2);
        sub(cerg, c1);

        MyTools.h2("Multiplikation", 2);
        c1 = new ComplexNumber(3, 5);
        c2 = new ComplexNumber(4, 11);
        cerg = mult(c1, c2); // -43 + 53i

        MyTools.h2("Division", 2);
        div(cerg, c2);
        div(cerg, c1);

        div(new ComplexNumber(2, 5), new ComplexNumber(3, 7));

    }

    private static void testEinerGleichung(int p, int q)
    {
        MyTools.h2("Test eines Gleichungssystems" + String.format(" [p=%d, q=%d]", p, q), 2);
        // x + y = 10
        // x * y = 40
        // x * (10 - x) = 40
        // x2 - 10x + 40 = 0
        // p=-10
        // q=40
        // D=quadrat(p/2) - q = 25-40 = -15
        // Loesung:
        // x1 = -5 + wurzel(-15)
        // x2 = -5 - wurzel(-15)

        int D = p * p / 4 - q;
        System.out.println("Diskriminante: " + D);
        if (D < 0)
        {
            ComplexNumber x1 = new ComplexNumber(-p / 2,
                    Math.pow(Math.abs(D), .5));
            System.out.print("x1 = ");
            x1.show();
            ComplexNumber x2 = new ComplexNumber(-p / 2,
                    -Math.pow(Math.abs(D), .5));
            System.out.print("x2 = ");
            x2.show();

            // Test
            System.out.print("y1 = ");
            ComplexNumber y1 = sub(new ComplexNumber(10, 0), x1);
            System.out.print("y2 = ");
            ComplexNumber y2 = sub(new ComplexNumber(10, 0), x2);

            System.out.print("x1 * y1 = ");
            mult(x1, y1);
            System.out.print("x2 * y2 = ");
            mult(x2, y2);
        }
        else
        {
            ComplexNumber x1 = new ComplexNumber(-p / 2 + Math.pow(D, .5), 0);
            System.out.print("x1 = ");
            x1.show();
            ComplexNumber x2 = new ComplexNumber(-p / 2 - Math.pow(D, .5), 0);
            System.out.print("x2 = ");
            x2.show();

            // Test
            System.out.print("y1 = ");
            ComplexNumber y1 = sub(new ComplexNumber(10, 0), x1);
            System.out.print("y2 = ");
            ComplexNumber y2 = sub(new ComplexNumber(10, 0), x2);

            System.out.print("x1 * y1 = ");
            mult(x1, y1);
            System.out.print("x2 * y2 = ");
            mult(x2, y2);
        }
    }

    private static ComplexNumber div(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber erg = ComplexNumber.div(a, b);

        System.out.printf("[%s] ./. [%s] = [%s] %n", a, b, erg);

        return erg;
    }

    private static ComplexNumber add(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber erg = ComplexNumber.add(a, b);

        System.out.printf("[%s] + [%s] = [%s] %n", a, b, erg);

        return erg;
    }

    private static ComplexNumber sub(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber erg = ComplexNumber.sub(a, b);

        System.out.printf("[%s] - [%s] = [%s] %n", a, b, erg);

        return erg;
    }

    private static ComplexNumber mult(ComplexNumber a, ComplexNumber b)
    {
        ComplexNumber erg = ComplexNumber.mult(a, b);

        System.out.printf("[%s] * [%s] = [%s] %n", a, b, erg);

        return erg;
    }

}
