package ch.jmildner.tools;

import java.security.NoSuchAlgorithmException;

import junit.framework.TestCase;

public class CryptJUnitTest extends TestCase
{

    static final boolean DEBUG = false;

    static final char[] chars =
    {
        '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_', '$',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'l', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public String kurz = "1";
    public String lang = "";

    
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1_000_000; i++)
        {
            sb.append(chars[(int) (Math.random() * chars.length)]);
        }
        lang = sb.toString();
    }

    public void testMitAlgorithmus() throws NoSuchAlgorithmException
    {
        System.out.println("testMitAlgorithmus");
        test1("MD5");
        test1("SHA");
        test1("SHA-1");
        test1("SHA-256");
        test1("SHA-512");
    }

    public void testOhneAlgorithmus() throws NoSuchAlgorithmException
    {
        System.out.println("testOhneAlgorithmus");
        test1(null);
    }

    private void test1(String algorithm) throws NoSuchAlgorithmException
    {
        System.out.println("test1");

        if (algorithm != null)
        {
            test11(kurz, algorithm);
            test11(lang, algorithm);
            test11("hugo", algorithm);
            test11("max", algorithm);
            test11("moritz", algorithm);
            test11("ÄÄääÖÖööÜÜüü-1234$£ a...", algorithm);
        }
        else
        {
            test11(kurz);
            test11(lang);
            test11("hugo");
            test11("max");
            test11("moritz");
            test11("ÄÄääÖÖööÜÜüü-1234$£ a...");
        }
    }

    private void test11(String original, String algorithmus) throws NoSuchAlgorithmException
    {
        System.out.println("test1");

        String crypted = CryptJ.crypt(original, algorithmus);

        if (DEBUG)
        {
            System.out
                    .println(algorithmus + " " + crypted.length() + " " + crypted + " " + original);
        }

        test12(original, crypted, algorithmus);
    }

    private void test11(String original) throws NoSuchAlgorithmException
    {
        System.out.println("test1");
        String crypted = CryptJ.crypt(original);

        if (DEBUG)
        {
            System.out.println(
                    "defaultAlgorithm " + crypted.length() + " " + crypted + " " + original);
        }

        test12(original, crypted);
    }

    private void test12(String original, String crypted, String algorithm)
            throws NoSuchAlgorithmException
    {
        System.out.println("test2");
        assertEquals(CryptJ.crypt(original, algorithm), crypted);
    }

    private void test12(String original, String crypted) throws NoSuchAlgorithmException
    {
        System.out.println("test2");
        assertEquals(CryptJ.crypt(original), crypted);
    }

}
