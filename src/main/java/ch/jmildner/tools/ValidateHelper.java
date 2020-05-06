package ch.jmildner.tools;

/**
 * @author johann
 *
 */
public final class ValidateHelper
{

    /**
     * Konstruktor.
     *
     * Darf nicht instantiiert werden
     */
    private ValidateHelper()
    {
    }

    /**
     * @param string der zu pruefende String
     *
     * @return einBoolean
     */
    public static boolean isEmpty(String string)
    {
        if (string == null)
        {
            return true;
        }

        return string.trim().equals("");
    }

    /**
     * @param string der zu pruefende String
     *
     * @return einBoolean
     */
    public static boolean isInteger(String string)
    {
        return isNumeric(string, "int");
    }

    /**
     * @param string der zu pruefende String
     *
     * @return einBoolean
     */
    public static boolean isDouble(String string)
    {
        return isNumeric(string, "double");
    }

    /**
     * @param string der zu pruefende String
     *
     * @return einBoolean
     */
    public static boolean isLong(String string)
    {
        return isNumeric(string, "long");
    }

    /**
     * @param string der zu pruefende String
     *
     * @return einBoolean
     */
    public static boolean isFloat(String string)
    {
        return isNumeric(string, "float");
    }

    /**
     * @param string der zu pruefende String
     *
     * @param type der Datentyp (int, double, long, float)
     *
     * @return true oder false
     */
    public static boolean isNumeric(String string, String type)
    {
        if (isEmpty(string))
        {
            return false;
        }

        try
        {
            switch (type)
            {
                case "long":
                    Long.parseLong(string);
                    break;
                case "double":
                    double d = Double.parseDouble(string);
                    if (Double.isInfinite(d))
                    {
                        return false;
                    }
                    break;
                case "float":
                    float f = Float.parseFloat(string);
                    if (Float.isInfinite(f))
                    {
                        return false;
                    }
                    break;
                case "int":
                default:
                    Integer.parseInt(string);
                    break;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

}
