package ch.jmildner.mathe_tools;

import java.util.Arrays;

import ch.jmildner.tools.MyTools;

/**
 * @author johann
 *
 */
public final class TrigonometricTools
{

    /**
     * TrigonometricTools darf nicht instantiiert werden
     */
    private TrigonometricTools()
    {
    }

    /**
     *
     * Berechnung der Dreiecksflaeche
     *
     * @param a die Seite a
     * @param gamma der eingeschlossene Winkel gamma
     * @param b die Seite b
     *
     * @return die Flaeche des Dreiecks
     *
     */
    public static double flaecheSWS(double a, double gamma, double b)
    {
        return a * b * TrigonometricTools.getSinFromDegrees(gamma) / 2;
    }

    /**
     *
     * Ueberpruefung der drei Winkel eines Dreiecks
     *
     * jeder einzelne Winkel muss groesser 0 und kleiner 180 sein
     *
     * die Winkelsumme muss 180 ergeben
     *
     * Bei einem Fehler wird eine Runtime Exception geworfen
     *
     * @param w1 alpha
     * @param w2 beta
     * @param w3 gamma
     *
     */
    public static void checkWinkel(double w1, double w2, double w3)
    {
        checkWinkel(w1);
        checkWinkel(w2);
        checkWinkel(w3);

        double ws = w1 + w2 + w3;

        if (!TrigonometricTools.equals(ws, 180.0))
        {
            throw new RuntimeException(String.format(
                    "Winkelsumme ist nicht 180 [%f + %f + %f = %f]", w1, w2, w3, ws));
        }
    }

    /**
     *
     * Ueberpruefung eines Winkel im Dreieck
     *
     * der Winkel muss groesser 0 und kleiner 180 sein
     *
     * Bei einem Fehler wird eine Runtime Exception geworfen
     *
     * @param w ein Winkel
     */
    public static void checkWinkel(double w)
    {
        if (w <= 0 || w >= 180)
        {
            throw new RuntimeException(String.format(
                    "Winkel muss > 0 und kleiner 180 sein [%f]", w));
        }
    }

    public static void checkSsW(double s1, double s2, double w1)
    {
        if (s1 < s2)
        {
            throw new RuntimeException(String.format(
                    "die dem Winkel gegenueberliegende Seite (erster Parameter)"
                    + " muss die laengere der beiden Seiten sein. "
                    + "Erster Parameter %f, zweiter Parameter %f",
                    s1, s2));
        }
    }

    public static void checkSeitenlaengen(double a, double b, double c)
    {
        double[] s =
        {
            a, b, c
        };

        Arrays.sort(s);

        if (s[0] + s[1] <= s[2])
        {
            throw new RuntimeException(String.format(
                    "die zwei kuerzeren Seiten %f + %f (%f)  "
                    + "muessen laenger als die laengere Seite %f sein",
                    s[0], s[1], (s[0] + s[1]), s[2]));
        }
    }

    /**
     *
     * ermitteln eines Winkels aus drei Seiten mittels Cosinussatz SSS
     *
     * <code>
     * 		Cosinussatz	a^2 + b^2        = c^2 - 2*a*b*(cos(gamma))   | - c2
     * 				a^2 + b^2 - c^2  = - 2*a*b"(cos(gamma))       | * -1
     *                         -a^2 - b^2 + c^2  = 2ab(cos(gamma))            | / 2ab
     *
     * 				c^2 - a^2 - b^2
     * 				---------------   = cos(gamma)                | arccos
     *                                  2ab
     *
     *                                   c^2-a^2-b^2
     * 				arccos ( ----------- ) = gamma
     *                                        2ab
     * 				==============================
     * </code>
     *
     * @param s1 Seite1 (a)
     * @param s2 Seite2 (b)
     * @param s3 Seite3 (c)
     *
     * @return w3 - Winkel zwischen s1 (a) und s2 (b) = (gamma) in Grad
     *
     */
    public static double getW3_S1S2S3(final double s1, final double s2, final double s3)
    {
        checkSeitenlaengen(s1, s2, s3);

        double a = s1, b = s2, c = s3, cosinusGamma, gamma;

        cosinusGamma = (c * c - a * a - b * b) / -(2 * a * b);
        gamma = getDegreeFromCosinus(cosinusGamma);
        return gamma;
    }

    /**
     *
     * ermitteln der dritten Seite mittels Cosinussatz SWS
     *
     * <code>
     * 		Cosinussatz	a2 + b2 = c2 - 2*a*b*(cos(gamma))
     * </code>
     *
     * @param s1 Seite1
     * @param w3 der von s1 und s2 eingeschlossene Winkel
     * @param s2 Seite2
     *
     * @return s3 - die dem Winkel w1 gegenueberliegende Seite
     *
     */
    public static double getS3_S1W3S2(double s1, double w3, double s2)
    {
        if (s1 <= 0 || s2 <= 0)
        {
            throw new RuntimeException(String.format(
                    "s1(%f) und s2(%f) muessen groesser 0 sein ", s1, s2));
        }

        if (w3 <= 0 || w3 >= 180)
        {
            throw new RuntimeException(String.format(
                    "w3(%f) muss groesser 0 und kleiner 180 sein", w3));
        }

        double s3 = Math.sqrt(s1 * s1 + s2 * s2 - 2 * s1 * s2 * (getCosFromDegrees(w3)));

        return s3;
    }

    /**
     *
     * ermitteln eines Winkels mittel Sinussatz SsW oder WsS
     *
     * <code>
     * 		Sinussatz   a/sin(a) = b/sin(b) = c/sin(c)
     *		oder        sin(a)/a = sin(b)/b = sin(c)/c
     * </code>
     *
     * Achtung: s1 muss groesser s2 sein
     *
     * @param s1 Seite1
     * @param w1 der Seite1 gegenueberliegende Winkel
     * @param s2 Seite2
     *
     * @return w2 - der Winkel welcher der Seite2 gegenueberliegt
     *
     */
    public static double getW2_S1W1S2(double s1, double w1, double s2)
    {
        checkSsW(s1, s2, w1);

        double sin1 = getSinFromDegrees(w1);

        double sin2 = sin1 * s2 / s1;

        double w2 = getDegreesFromSin(sin2);

        return w2;
    }

    /**
     *
     * ermitteln der beiden fehlenen Seiten
     *
     * mittels Sinussatz WWS oder WSW oder SWW
     *
     * <code>
     * 		Sinussatz   a/sin(a) = b/sin(b) = c/sin(c)
     *		oder        sin(a)/a = sin(b)/b = sin(c)/c
     * </code>
     *
     * @param s3 Seite3
     * @param w1 der Seite1 gegenueberliegende Winkel
     * @param w2 der Seite2 gegenueberliegende Winkel
     *
     * @return s[] - die Seiten 1 un 2
     *
     */
    public static double[] getS2_W1S3W2(double w1, double s3, double w2)
    {
        double w3 = 180 - w1 - w2;

        checkWinkel(w1, w2, w3);

        double[] s = new double[2];

        s[0] = s3 * getSinFromDegrees(w1) / getSinFromDegrees(w3);
        s[1] = s3 * getSinFromDegrees(w2) / getSinFromDegrees(w3);

        return s;
    }

    /**
     *
     * ermitteln eines Winkels in Grad zu einem Sinuswert
     *
     * @param funktionsWert Sinus
     *
     * @return Grad zu dem Sinuswert
     *
     */
    public static double getDegreesFromSin(double funktionsWert)
    {
        return Math.toDegrees(Math.asin(funktionsWert));
    }

    /**
     *
     * ermitteln eines Winkels in Grad zu einem Cosinuswert
     *
     * @param funktionsWert Cosinus
     *
     * @return Grad zu dem Cosinuswert
     *
     */
    public static double getDegreeFromCosinus(double funktionsWert)
    {
        return Math.toDegrees(Math.acos(funktionsWert));
    }

    /**
     *
     * ermitteln eines Winkels in Grad zu einem Tangenswert
     *
     * @param funktionsWert Tangens
     *
     * @return Grad zu dem Tangenswert
     *
     */
    public static double getDegreesFromTan(double funktionsWert)
    {
        return Math.toDegrees(Math.atan(funktionsWert));
    }

    /**
     *
     * ermitteln eines Sinus zu einem Winkel in Grad
     *
     * @param grad Winkelgrad
     *
     * @return sinus zu dem Winkel
     *
     */
    public static double getSinFromDegrees(double grad)
    {
        return Math.sin(Math.toRadians(grad));
    }

    /**
     *
     * ermitteln eines Cosinus zu einem Winkel in Grad
     *
     * @param grad Winkelgrad
     *
     * @return cosinus zu dem Winkel
     *
     */
    public static double getCosFromDegrees(double grad)
    {
        return Math.cos(Math.toRadians(grad));
    }

    /**
     *
     * ermitteln eines Tangens zu einem Winkel in Grad
     *
     * @param grad Winkelgrad
     *
     * @return tangens zu dem Winkel
     *
     */
    public static double getTanFromDegrees(double grad)
    {
        if (grad == 90)
        {
            return Double.POSITIVE_INFINITY;
        }
        if (grad == 270)
        {
            return Double.NEGATIVE_INFINITY;
        }

        return Math.tan(Math.toRadians(grad));
    }

    /**
     *
     * ueberpruefen ob zwei Doubles gleich sind
     *
     * Toleranz ist 1 / 1'000'000'000 (1 Milliardstel)
     *
     * @param a ein Double
     * @param b ein Double
     *
     * @return (a==b)
     *
     */
    public static boolean equals(double a, double b)
    {
        return Math.abs(a - b) < (1.0 / 1_000_000_000);
    }

    /**
     *
     * runden eines doubles
     *
     * @param wert zu rundender DoubleWert
     * @param stellen Anzahl der Stellen auf die gerundet werden soll
     *
     * @return der gerundete DoubleWert
     *
     */
    public static double runden(double wert, int stellen)
    {
        return (Math.round(wert * Math.pow(10, stellen)))
                / Math.pow(10, stellen);
    }

    /**
     *
     * Erstellen eines Arrays mit den Seiten (a,b,c) eines rechtwinkeligen
     * Dreicks
     *
     * @return ein int-Array mit den Seiten (Kathete,Kathete,Hypothenuse) eines
     * rechtwinkeligen Dreiecks
     *
     */
    public static int[] getPythagoraeischeDreieckszahlen()
    {
        int i = MyTools.getRandom(1, 100);
        int j = MyTools.getRandom(1, 100);

        while (j == i)
        {
            j = MyTools.getRandom(1, 100);
        }

        try
        {
            return getPythagoraeischeDreieckszahlen(i, j);
        }
        catch (Exception e)
        {
            // kann nicht vorkommen!
        }
        return null; // wird nie durchlaufen!
    }

    /**
     *
     * Erstellen eines Arrays mit den Seiten (a,b,c) eines rechtwinkeligen
     * Dreicks
     *
     * @param i eine beliebige Zahl groesser 0 und ungleich j
     * @param j eine beliebige Zahl groesser 0 und ungleich i
     *
     * @return ein int-Array mit den drei Seiten (Kathete,Kathete,Hypothenuse) 
     *
     * @throws RuntimeException i und j duerfen nicht gleich sein
     * @throws RuntimeException sowohl i als auch j muessen groesser 0 sein
     *
     */
    public static int[] getPythagoraeischeDreieckszahlen(int i, int j)
    {
        if (i == j)
        {
            throw (new RuntimeException(
                    String.format(
                            "getPythagoraeischeDreieckszahlen(%d,%d) "
                            + "- i darf nicht gleich j sein", i, j)));
        }

        if (i == 0 || j == 0)
        {
            throw (new RuntimeException(String.format(
                    "getPythagoraeischeDreieckszahlen(%d,%d) "
                    + "- sowohl i als auch j muessen groesser 0 sein", i, j)));
        }

        int[] zahlen = new int[3];

        zahlen[0] = 2 * i * j;
        zahlen[1] = (j * j + i * i);
        zahlen[2] = (Math.abs(j * j - i * i));

        Arrays.sort(zahlen);

        return zahlen;
    }
}
