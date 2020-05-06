package ch.jmildner.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * Die finale Klasse <code>BigDecimalTool</code> beinhaltet allgemein benoetigte
 * <code>static</code> Methoden zum Rechnen mit <code>BigDecimals</code>
 *
 * @author Johann Mildner, Basel
 *
 */
public final class BigDecimalTools
{

    /**
     * max = +decimal(31,2)
     */
    static final BigDecimal MAX = new BigDecimal("99999999999999999999999999999.99");

    /**
     * min = -decimal(31,2)
     */
    static final BigDecimal MIN = new BigDecimal("-99999999999999999999999999999.99");

    private BigDecimalTools()
    {
    }

    /**
     * DIVIDIEREN
     *
     * @param a Dividend
     * @param b Divisor
     * @return -
     * @throws Exception - xxx
     */
    public static BigDecimal divide(BigDecimal a, String b) throws Exception
    {
        return divide(2, a, make(2, b));
    }

    public static BigDecimal divide(int dezimalStellen, BigDecimal a, String b) throws Exception
    {
        return divide(dezimalStellen, a, make(2, b));
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) throws Exception
    {
        return divide(2, a, b);
    }

    public static BigDecimal divide(int dezimalStellen, BigDecimal a, BigDecimal b) throws Exception
    {
        BigDecimal bd = a.divide(b, dezimalStellen,
                RoundingMode.HALF_UP);
        check(bd);
        return bd;
    }


    /*
	 * MULTIPLIZIEREN
     */
    public static BigDecimal multiply(BigDecimal a, String b) throws Exception
    {
        return multiply(2, a, make(2, b));
    }

    public static BigDecimal multiply(int dezimalStellen, BigDecimal a, String b) throws Exception
    {
        return multiply(dezimalStellen, a, make(dezimalStellen, b));
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) throws Exception
    {
        return multiply(2, a, b);
    }

    public static BigDecimal multiply(int dezimalStellen, BigDecimal a, BigDecimal b) throws Exception
    {
        BigDecimal bd = a.multiply(b);

        bd = bd.setScale(dezimalStellen, RoundingMode.HALF_UP);
        check(bd);
        return bd;
    }


    /*
	 * SUBTRAHIEREN
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) throws Exception
    {
        return subtract(2, a, b);
    }

    public static BigDecimal subtract(int dezimalStellen, BigDecimal a, BigDecimal b) throws Exception
    {
        BigDecimal bd = new BigDecimal("0");
        bd = bd.add(a);
        bd = bd.subtract(b);
        bd = bd.setScale(dezimalStellen, RoundingMode.HALF_UP);
        check(bd);
        return bd;
    }


    /*
	 * ADDIEREN
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) throws Exception
    {
        return add(2, a, b);
    }

    public static BigDecimal add(int dezimalStellen, BigDecimal a, BigDecimal b) throws Exception
    {
        BigDecimal bd = new BigDecimal("0");
        bd = bd.add(a);
        bd = bd.add(b);
        bd = bd.setScale(dezimalStellen, RoundingMode.HALF_UP);
        check(bd);
        return bd;
    }


    /*
	 * MAKE A BIG_DECIMAL
     */
    public static BigDecimal makeRandom() throws Exception
    {
        return makeRandom(31, 2);
    }

    public static BigDecimal makeRandom(int laenge, int dezimalStellen) throws Exception
    {
        int vorkommaStellen = (int) (Math.random()
                * (laenge - dezimalStellen + 1));

        if (vorkommaStellen < 1 && dezimalStellen == 0)
        {
            vorkommaStellen = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= vorkommaStellen; i++)
        {
            sb.append((int) (Math.random() * 10));
        }
        sb.append(".");
        for (int i = 1; i <= dezimalStellen; i++)
        {
            sb.append((int) (Math.random() * 10));
        }

        BigDecimal bd = new BigDecimal(sb.toString());

        if (bd.compareTo(BigDecimal.ZERO) == 0)
        {
            return BigDecimal.ONE;
        }
        else
        {
            return bd;
        }
    }

    public static BigDecimal make(String wert) throws Exception
    {
        return make(2, wert);
    }

    public static BigDecimal make(int dezimalStellen, String wert)
            throws Exception
    {
        BigDecimal bd = new BigDecimal(wert);

        bd = bd.setScale(dezimalStellen, RoundingMode.HALF_UP);
        check(bd);
        return bd;
    }


    /*
	 * CHECK THE BIG_DECIMAL
     */
    private static void check(BigDecimal bd) throws Exception
    {
        if (bd.compareTo(MAX) > 0)
        {
            throw new Exception("BigDecimal zu gross [" + bd + "]");
        }
        if (bd.compareTo(MIN) < 0)
        {
            throw new Exception("BigDecimal zu klein [" + bd + "]");
        }
    }


    /*
	 * FORMAT THE BIG_DECIMAL
     */
    public static String format(BigDecimal n, int laenge)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String s = df.format(n);
        s = leerstellen(laenge - s.length()) + s;
        return s;
    }

    private static String leerstellen(int anzahl)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= anzahl; i++)
        {
            sb.append(" ");
        }
        return sb.toString();
    }
}
