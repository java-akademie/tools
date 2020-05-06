package ch.jmildner.tools;

import java.util.Date;

/**
 * Die finale Klasse <code>TestDaten</code> dient dem Erstellen von
 * Testdaten fuer Datenbanktabellen.
 *
 * @author Johann Mildner, Basel
 */
public final class TestDatenTools
{
	private static final String[] vnameWeiblich = { "brigita", "frieda", "adele", "beate", "elisabeth", "linda",
			"karin", "heidi", "maria", "ute", "martina", "margarete", "ludowika", "marianne", "susanne", "gerlinde",
			"gabriele", "eva", "ullrike" };

	private static final String[] vnameMaennlich = { "fritz", "urs", "oli", "albert", "mario", "hans-peter", "heinz",
			"peter", "max", "oliver", "josef", "johann", "friedrich", "georg", "gerhard", "robert", "johannes",
			"bruno" };

	private static final String[] nname = { "mueller", "schuster", "meier", "allmen", "von arx", "friedrichs", "mauler",
			"gruber", "bamberger", "holterer", "german", "lugner", "strache", "havlik", "nigl", "meier", "maier",
			"strobl", "huber", "einstein", "vogel", "fink", "sperling" };

	private static final String[] ort = { "basel", "genf", "zuerich", "wien", "berlin", "paris", "brugg", "bruck",
			"wiel", "graz", "klagenfurt", "krems", "muenchen", "koeln", "berlin" };

	private static final String[] strasse = { "hauptstrasse", "hauptplatz", "gablenzgasse", "lehenmattstrasse",
			"gueterstrasse", "gerlgasse", "rheingasse", "wurmstrasse", "blumenrain", "zuercherstrasse", "im gehoeft",
			"rosenackerstrasse" };

	private static final String[] email = { "mm@xxx.aa", "nn@yyy.bb" };

	private static final String[] password = { "geheim", "soginet", "1234", "tiger", "london", "dulcinea", "rosinante",
			"password" };

	private static final String[] vname;

	static
	{
		vname = new String[vnameWeiblich.length + vnameMaennlich.length];

		int vnameIndex = 0;

		for (String n : vnameWeiblich)
		{
			vname[vnameIndex++] = n;
		}
		for (String n : vnameMaennlich)
		{
			vname[vnameIndex++] = n;
		}
	}


	/**
	 * <code>TestDaten</code> darf nicht instantiiert werden.
	 */
	private TestDatenTools()
	{
	}


	/**
	 * @return name (vorname + " " + nachname)
	 */
	public static String getName()
	{
		return getVorname() + " " + getNachname();
	}


	/**
	 * @return adresse
	 */
	public static String getAdresse()
	{
		return getPlz() + " " + getOrt() + " " + getStrasse() + " " + " " + getHausnummer();
	}


	/**
	 * @param geschlecht
	 *            - optional 1=weiblich, 2=maennlich
	 * @return vorname
	 */
	public static String getVorname(int... geschlecht)
	{
		if (geschlecht.length == 1)
		{
			switch (geschlecht[0])
			{
				case 1:
					return vnameWeiblich[(int) (Math.random() * vnameWeiblich.length)];
				case 2:
					return vnameMaennlich[(int) (Math.random() * vnameMaennlich.length)];
			}
		}

		return vname[(int) (Math.random() * vname.length)];
	}


	/**
	 * @return nachname
	 */
	public static String getNachname()
	{
		return nname[(int) (Math.random() * nname.length)];
	}


	/**
	 * @return plz (1000-8000)
	 */
	public static int getPlz()
	{
		return (int) (1000 + Math.random() * 8000);
	}


	/**
	 * @return ort
	 */
	public static String getOrt()
	{
		return ort[(int) (Math.random() * ort.length)];
	}


	/**
	 * @return email
	 */
	public static String getEMail()
	{
		return email[(int) (Math.random() * email.length)];
	}


	/**
	 * @return password
	 */
	public static String getPassword()
	{
		return password[(int) (Math.random() * password.length)];
	}


	/**
	 * @return strasse
	 */
	public static String getStrasse()
	{
		return strasse[(int) (Math.random() * strasse.length)];
	}


	/**
	 * @return hausnummer(1 - 64)
	 */
	public static int getHausnummer()
	{
		return (int) (1 + Math.random() * 64);
	}


	/**
	 * @param spitze
	 *            - Alter fuer Gauss'sche Verteilung
	 * @return Geburtsdatum
	 * @throws Exception
	 *             - Fehler bei Berechnung des Datums (sollte nicht
	 *             auftreten)
	 */
	public static Date getGeburtsdatum(int spitze) throws Exception
	{
		int alter = DateTimeTools.getRandomAge(spitze);
		int jahr = DateTimeTools.getGeburtsjahr(alter);
		return DateTimeTools.makeRandomDate(jahr);
	}

}
