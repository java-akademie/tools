package ch.jmildner.tools;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for DateTime.
 */
public class DateTimeToolsUnitTest extends TestCase
{

    private static final int MAX = 10;
    private static final boolean DEBUG1 = true;
    private static final boolean DEBUG2 = true;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DateTimeToolsUnitTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(DateTimeToolsUnitTest.class);
    }

    public void testCurrentDate()
    {
        for (int i = 1; i <= MAX; i++)
        {
            Date cd = DateTimeTools.getCurrentDate();

            Calendar cal = GregorianCalendar.getInstance();
            Date xx = new Date(cal.getTimeInMillis());

            assertEquals(cd.toString(), xx.toString());
        }

        if (DEBUG2)
        {
            System.out.printf("%-25s %s%n", "getCurrentDate:", DateTimeTools.getCurrentDate());
        }
    }

    public void testCurrentTime()
    {
        for (int i = 1; i <= MAX; i++)
        {
            Time ct = DateTimeTools.getCurrentTime();

            Calendar cal = GregorianCalendar.getInstance();
            java.sql.Timestamp ts = new java.sql.Timestamp(cal.getTimeInMillis());
            Time xx = new java.sql.Time(ts.getTime());

            assertEquals(ct.toString(), xx.toString());
        }

        if (DEBUG2)
        {
            System.out.printf("%-25s %s%n", "getCurrentTime:", DateTimeTools.getCurrentTime());
        }
    }

    public void testCurrentTimestamp()
    {
        // millisekunden weichen manchesmal ab
        // deshalb werden die nanos auf 0 gesetzt
        for (int i = 1; i <= MAX; i++)
        {

            Timestamp ts = DateTimeTools.getCurrentTimestamp();

            Calendar cal = GregorianCalendar.getInstance();
            java.sql.Timestamp xx = new java.sql.Timestamp(cal.getTimeInMillis());

            ts.setNanos(0);
            xx.setNanos(0);

            assertEquals(ts, xx);
        }

        if (DEBUG2)
        {
            System.out.printf("%-25s %s%n", "getCurrentTimestamp:", DateTimeTools.getCurrentTimestamp());
        }
    }

    public void testRandomAge1()
    {
        if (DEBUG2)
        {
            System.out.printf("%-25s %s%n", "getRandomAge(30):", DateTimeTools.getRandomAge(30));
        }

        for (int i = 1; i <= MAX; i++)
        {
            testRA1(10);
            testRA1(20);
            testRA1(40);
            testRA1(60);
            testRA1(80);
            testRA1(90);
        }
    }

    private void testRA1(int spitze)
    {
        int age = DateTimeTools.getRandomAge(spitze);

        assertTrue(age >= (int) (spitze * 0.25) && age <= (int) (spitze * 1.75));
    }

    public void testRandomAge2()
    {
        testRA2(10);
        testRA2(24);
        testRA2(30);
        testRA2(50);
        testRA2(70);
        testRA2(90);
    }

    private void testRA2(int spitze)
    {
        int arr[] = new int[200];

        for (int i = 1; i <= MAX * spitze; i++)
        {
            arr[DateTimeTools.getRandomAge(spitze)]++;
        }

        if (DEBUG1)
        {
            System.out.println("\nSpitze: " + spitze);
        }

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] != 0)
            {
                if (DEBUG1)
                {
                    System.out.printf("Anzahl %3d jaehrig:  %d%n", i, arr[i]);
                }
            }
        }
    }
}
