package ch.jmildner.mathe_tools;

/**
 * Complexe Zahlen.
 *
 * Die komplexen Zahlen erweitern den Zahlenbereich der reellen Zahlen derart,
 * dass die Gleichung x^2 + 1 = 0 lösbar wird.
 *
 * Dies gelingt durch Einführung einer neuen imaginären Zahl i mit der
 * Eigenschaft i^2 = -1.
 *
 * Diese Zahl i wird als imaginäre Einheit bezeichnet.
 *
 * Komplexe Zahlen können in der Form a + bi dargestellt werden, wobei a und b
 * reelle Zahlen sind und i die imaginäre Einheit ist.
 *
 * Auf die so dargestellten komplexen Zahlen lassen sich die ueblichen
 * Rechenregeln für reelle Zahlen anwenden, wobei i^2 stets durch -1 ersetzt
 * werden kann und umgekehrt.
 *
 * Fuer die Menge der komplexen Zahlen wird das Symbol C (Unicode U+2102)
 * verwendet.
 *
 * @author johann
 */
public class ComplexNumber
{

    /**
     * Real Teil
     */
    private final double x;

    /**
     * Imaginaer Teil
     */
    private final double y;

    public ComplexNumber(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b)
    {
        return new ComplexNumber(a.x + b.x, a.y + b.y);
    }

    public static ComplexNumber sub(ComplexNumber a, ComplexNumber b)
    {
        return new ComplexNumber(a.x - b.x, a.y - b.y);
    }

    public static ComplexNumber mult(ComplexNumber a, ComplexNumber b)
    {
        /**
         * <code>
         *
         * z1 * z2 = (a+bi) * (c+di)
         * = (ac + bdii) + (ad + bc)i
         * = (ac - bd) + (ad + bc)i
         *
         * </code>
         */

        double m1 = a.x * b.x - a.y * b.y;
        double m2 = a.x * b.y + a.y * b.x;
        return new ComplexNumber(m1, m2);
    }

    public static ComplexNumber div(ComplexNumber a, ComplexNumber b)
    {
        /**
         * <code>
         *
         * z1   a+bi   (a+bi)(c-di)   (ac+bc)   (bc-ad)
         * -- = ---- = ------------ = ------- + -------- i
         * z2   c+di   (c+di)(c-di)   c2 + d2   c2 + d2
         *
         * </code>
         */
        double m1 = a.x * b.x + a.y * b.y; // ac + bd
        double m2 = a.y * b.x - a.x * b.y; // bc - ad
        double m3 = b.x * b.x + b.y * b.y; // c2 + d2

        return new ComplexNumber(m1 / m3, m2 / m3);
    }

    public void show()
    {
        System.out.println(this);

    }

    @Override
    public String toString()
    {
        return x + (y < 0 ? " - " : " + ") + Math.abs(y) + " i";
    }
}
