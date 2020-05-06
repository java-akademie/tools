package ch.jmildner.tools;

import java.sql.Time;
import java.util.Date;

/**
 * Die finale Klasse <code>StoppUhr</code> dient der Zeitmessung. Es sind
 * mehrere Stoppuhren moeglich.
 *
 * <pre>
 *   ----------------------------------------------------------------
 *   Anwendungsbeispiel:
 *   ----------------------------------------------------------------
 *   StoppUhr stoppUhr = new StoppUhr();
 *
 *   stoppUhr.start("Name des zu stoppenden Codeteils");
 *
 *   // ...
 *   // code dessen Zeit gemessen werden soll
 *   // ...
 *
 *   stoppUhr.getZischenzeit();
 *   stoppUhr.getGesamtzeit();
 *
 *   nach getGesamtzeit() ist wieder ein start() erforderlich
 *
 *   anstatt
 *   getZwischenzeit() oder getGesamtzeit()
 *   ist auch getDauer() moeglich.
 *   ----------------------------------------------------------------
 * </pre>
 *
 *
 * @author Johann Mildner
 *
 */
public final class StoppUhr
{

    private static int anzahl = 0;
    private final int einrueckung;

    private Date start = null;
    private Date stopp = null;
    private String messung = null;

    public StoppUhr()
    {
        einrueckung = anzahl++;
    }

    public void delete()
    {
        anzahl--;
    }

    public void start(String messung)
    {
        this.messung = messung;
        start = new Date();

        einrueckung();
        System.out.printf("Zeitmessung Start : %s %n", this.messung);
    }

    private void einrueckung()
    {
        for (int i = 1; i <= einrueckung; i++)
        {
            System.out.print("      ");
        }
    }

    private void stopp()
    {
        stopp = new Date();
    }

    public double getZwischenzeit()
    {
        double zeit = getZeit("Zwischenzeit");

        return zeit;
    }

    public double getGesamtzeit()
    {
        double zeit = getZeit("Gesamtzeit");

        start = stopp = null;

        return zeit;
    }

    private double getZeit(String s)
    {
        if (start == null)
        {
            einrueckung();
            System.out.println("Stoppuhr laeuft nicht");
            return 0;
        }

        double zeitInSekunden = getDauer();
        double zeit = zeitInSekunden;
        int minuten = (int) (zeit / 60);
        zeit = zeit - minuten * 60;

        Time tstart = new Time(start.getTime());
        Time tstopp = new Time(stopp.getTime());

        einrueckung();
        System.out.printf(
                "%-12s %12s Start: %s Stopp: %s Dauer: %3d Minute(n) %7.3f Sekunden %n",
                messung, s, tstart, tstopp, minuten, zeit);

        return zeitInSekunden;
    }

    /**
     * macht einen Zwischenstopp und gibt die Gesamtzeit in Sekunden zurueck.
     *
     * @return dauer
     */
    public double getDauer()
    {
        if (start == null)
        {
            einrueckung();
            System.out.println("Stoppuhr laeuft nicht");
            return 0;
        }

        stopp();

        return (stopp.getTime() - start.getTime()) / 1000.0;
    }
}
