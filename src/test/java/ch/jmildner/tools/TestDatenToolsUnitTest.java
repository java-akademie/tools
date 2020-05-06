package ch.jmildner.tools;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for TestDaten.
 */
public class TestDatenToolsUnitTest extends TestCase
{

    private final int MAX = 100;
    private final boolean DEBUG = true;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestDatenToolsUnitTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(TestDatenToolsUnitTest.class);
    }

    public void testDatensatz()
    {
        for (int i = 1; i <= MAX; i++)
        {
            int geschlecht = (int) (Math.random() * 2)+1;

            String rec = String.format("%4s: %10s %15s",
                    (geschlecht == 2 ? "masc" : "fem"),
                    TestDatenTools.getVorname(geschlecht),
                    TestDatenTools.getNachname());

            ausgeben(rec, DEBUG);
        }

        int geschlecht = (int) (Math.random() * 2);
        String vorname = TestDatenTools.getVorname(geschlecht);
        String nachname = TestDatenTools.getNachname();

        assertNotNull(vorname);
        assertNotNull(nachname);
    }

    private void ausgeben(String rec, boolean debug)
    {
        if (debug)
        {
            System.out.println(rec);
        }

    }

}
