package ch.jmildner.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CryptJ
{

    /**
     * Klasse darf nicht erweitert und nicht instantiiert werden.
     */
    private CryptJ()
    {
    }

    /**
     * <pre>
     * 		Verschluesselungsalgorithmus
     *      ----------------------------
     * 		MD5     Laenge des verschluesselten Werts  32
     *	 	SHA-1   Laenge des verschluesselten Werts  40
     *	 	SHA-256 Laenge des verschluesselten Werts  64
     *	 	SHA-512 Laenge des verschluesselten Werts 128
     * </pre>
     */
    private static final String DEFAULT_ALGORITHM = "SHA-1";

    /**
     * Es wird ein Originaltwert (z.B. ein Passwort) mittels dem
     * defaultAlgorithmus als einmaliger Hash (Fingerprint) verschluesselt.
     *
     * @param original der zu verschluesselnde Wert
     *
     * @return der verschluesselte Wert
     *
     * @throws NoSuchAlgorithmException bei ungueltigem
     * Verschluesselungsalgorithmus
     */
    public static String crypt(String original)
            throws NoSuchAlgorithmException
    {
        return crypt(original, DEFAULT_ALGORITHM);
    }

    /**
     * Es wird ein Originaltwert (z.B. ein Passwort) mittels einem gegebenen
     * Algorithmus als einmaliger Hash (Fingerprint) verschluesselt.
     *
     * @param original der zu verschluesselnde Wert
     *
     * @param algorithm der Verschluesselungsalgorithmus
     *
     * @return der verchluesselte Wert
     *
     * @throws NoSuchAlgorithmException bei ungueltigem
     * Verschluesselungsalgorithmus
     */
    public static String crypt(String original, String algorithm)
            throws NoSuchAlgorithmException
    {
        original = original.trim();
        MessageDigest md;
        md = MessageDigest.getInstance(algorithm);
        md.update(original.getBytes());
        byte[] mb = md.digest();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < mb.length; i++)
        {
            byte temp = mb[i];
            String s = Integer.toHexString(temp);
            while (s.length() < 2)
            {
                s = "0" + s;
            }
            s = s.substring(s.length() - 2);
            buffer.append(s);
        }
        return buffer.toString();
    }

    /**
     * Es wird ein Originaltwert (z.B. ein Passwort) mittels dem
     * defaultAlgorithmus als einmaliger Hash (Fingerprint) verschluesselt und
     * mit einem einem verschluesselten Wert verglichen.
     *
     * @param crypted der verschluesselte Wert
     *
     * @param original der zu verschluesselnde Wert
     *
     * @return (crypted==original)
     *
     * @throws NoSuchAlgorithmException bei ungueltigem
     * Verschluesselungsalgorithmus
     *
     */
    public static final boolean compare(String crypted, String original)
            throws IllegalArgumentException, NoSuchAlgorithmException
    {
        return compare(crypted, original, DEFAULT_ALGORITHM);
    }

    /**
     * Es wird ein Originaltwert (z.B. ein Passwort) mittels einem angegebenen
     * Algorithmus als einmaliger Hash (Fingerprint) verschluesselt und mit
     * einem einem verschluesselten Wert verglichen.
     *
     * @param crypted der verschluesselte Wert
     *
     * @param original der zu verschluesselnde Wert
     *
     * @param algorithm der Verschluesselungsalgorithmus
     *
     * @return (crypted==original)
     *
     * @throws NoSuchAlgorithmException bei ungueltigem
     * Verschluesselungsalgorithmus
     *
     *
     */
    public static final boolean compare(String crypted, String original,
            String algorithm)
            throws IllegalArgumentException, NoSuchAlgorithmException
    {
        String encoded = crypt(original, algorithm);
        return encoded.equals(crypted);
    }
}
