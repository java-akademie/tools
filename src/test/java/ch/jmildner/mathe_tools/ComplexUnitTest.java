package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;
import junit.framework.TestCase;

public class ComplexUnitTest extends TestCase
{

    public void test1()
    {
        MyTools.h2("Test 1", 3);

        Complex c;
        double r = 2;

        c = Complex.createPolar(r, 0);
        assertTrue(c.getX() == r);
        assertTrue(c.getY() == 0.0);
        c.show();

        c = Complex.createPolar(r, 90);
        assertTrue(c.getX() == 0.0);
        assertTrue(c.getY() == r);
        c.show();

        c = Complex.createPolar(r, 180);
        assertTrue(c.getX() == -r);
        assertTrue(c.getY() == 0.0);
        c.show();

        c = Complex.createPolar(r, 270);
        assertTrue(c.getX() == 0.0);
        assertTrue(c.getY() == -r);
        c.show();
        c = Complex.createPolar(r, 360);
        assertTrue(c.getX() == r);
        assertTrue(c.getY() == 0.0);
        c.show();

        c = Complex.createPolar(r, 720);
        assertTrue(c.getX() == r);
        assertTrue(c.getY() == 0.0);
        c.show();
    }

    public void test2()
    {
        MyTools.h2("Test 2", 3);

        Complex c;
        double r;// = 122.258987;
        r = 2;
        c = Complex.createPolar(r, 45);
        assertEquals(0.707106781 * r, c.getX(), 0.0000001);
        assertEquals(0.707106781 * r, c.getY(), 0.0000001);
        c.show();

        c = Complex.createPolar(r, 135);
        assertEquals(-0.707106781 * r, c.getX(), 0.0000001);
        assertEquals(0.707106781 * r, c.getY(), 0.0000001);
        c.show();

        c = Complex.createPolar(r, 225);
        assertEquals(-0.707106781 * r, c.getX(), 0.0000001);
        assertEquals(-0.707106781 * r, c.getY(), 0.0000001);
        c.show();

        c = Complex.createPolar(r, 315);
        assertEquals(0.707106781 * r, c.getX(), 0.0000001);
        assertEquals(-0.707106781 * r, c.getY(), 0.0000001);
        c.show();

        c = Complex.createPolar(r, 675);
        assertEquals(0.707106781 * r, c.getX(), 0.0000001);
        assertEquals(-0.707106781 * r, c.getY(), 0.0000001);
        c.show();

    }

    public void test3()
    {
        MyTools.h2("Test 3", 3);

        Complex p, c;
        double r ;//= 6.44;
        r = 2;
        for (int f = -1; f <= 361; f++)
        {
            p = Complex.createPolar(r, f);
            c = Complex.createKartesisch(p.getX(), p.getY());
            // p.show();
            // c.show();
            assertEquals(c.getR(), r, 0.000000001);
            assertEquals(c.getF(), Complex.radian(f), 0.000000001);
            c.show();

        }
    }

    public void test4()
    {
        MyTools.h2("Test 4", 3);

        Complex p, c;

        for (int x = -100; x <= 100; x += 15)
        {
            for (int y = -100; y <= 100; y += 15)
            {
                c = Complex.createKartesisch(x, y);
                c.show();
                p = Complex.createPolar(c.getR(),
                        180 / Math.PI * c.getF());
                p.show();
                assertEquals(p.getX(), x, 0.000000001);
                assertEquals(p.getY(), y, 0.000000001);
                assertEquals(c.getX(), x, 0.000000001);
                assertEquals(c.getY(), y, 0.000000001);
                System.out.println();
            }
        }
    }
}
