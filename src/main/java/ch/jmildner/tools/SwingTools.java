package ch.jmildner.tools;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextArea;

/**
 * Die finale Klasse SwingTools beinhaltet allgemein benoetigte Methoden fuer
 * die Erzeugung von GUI's mittels GridBagLayout.
 *
 * <pre>
 *
 * ---------------------------------------------------------------
 * Anwendungsbeispiel:
 * ---------------------------------------------------------------
 * Container container = new JPanel();
 * GridBagLayout gbl = new GridBagLayout();
 * container.setLayout (gbl);
 *
 * SwingTools.addComponent
 *   (container
 *   ,gbl
 *   ,componente
 *   ,zeile,spalte
 *   ,breite,hoehe
 *   ,anpassung
 *   ,alignment);
 *
 * container: JPanel, Panel
 * gbl: Instanz der Klasse GridBagLayout
 * componente: JButton JLabel JTextField JTextArea ....
 *                                                       default
 * zeile/spalte: 1..n                                      (1)
 * breite/hoehe: 1..n                                      (1)
 *
 * anpassung: 0=NONE, 1=HORIZONTAL, 2=VERTICAL, 3=BOTH     (1)
 *
 * alignment: 1-9 -&gt; nw,n,ne;w,c,e;sw,s,se              (4)
 * - 123 NORTHWEST    NORTH      NORTHEAST
 * - 456 WEST         CENTER     EAST
 * - 789 SOUTHWEST    SOUTH      SOUTHEAST
 * ----------------------------------------------------------------
 * </pre>
 *
 * @deprecated diese Klasse soll nicht mehr verwendet werden Sie wird durch die
 * Klasse MyPanel ersetzt.
 *
 * @author Johann Mildner
 *
 */
@Deprecated
public final class SwingTools
{

    /**
     * <code>addComponent</code> fuegt eine Komponente in einen Container ein.
     * <p>
     * Fuer fehlende Parameter werden default-Werte genommen: <br>
     * breite=1, hoehe=1, anpassung=1, alingment=4
     *
     * @param cont Container
     * @param gbl GridBagLayout
     * @param comp Component
     * @param zeile Zeile
     * @param spalte Spalte
     */
    public static void addComponent(Container cont, GridBagLayout gbl,
            Component comp, int zeile, int spalte)
    {
        addComponent(cont, gbl, comp, zeile, spalte, 1, 1, 1, 4);
    }

    /**
     * <code>addComponent</code> fuegt eine Komponente in einen Container ein.
     * <p>
     * Fuer fehlende Parameter werden default-Werte genommen: <br>
     * hoehe=1, anpassung=1, alingment=4
     *
     * @param cont Container
     * @param gbl GridBagLayout
     * @param comp Component
     * @param zeile Zeile
     * @param spalte Spalte
     * @param breite Breite
     */
    public static void addComponent(Container cont, GridBagLayout gbl,
            Component comp, int zeile, int spalte, int breite)
    {
        addComponent(cont, gbl, comp, zeile, spalte, breite, 1, 1, 4);
    }

    /**
     * <code>addComponent</code> fuegt eine Komponente in einen Container ein.
     * <p>
     * Fuer fehlende Parameter werden default-Werte genommen: <br>
     * anpassung=1, alingment=4
     *
     * @param cont Container
     * @param gbl GridBagLayout
     * @param comp Component
     * @param zeile Zeile
     * @param spalte Spalte
     * @param breite Breite
     * @param hoehe Hoehe
     */
    public static void addComponent(Container cont, GridBagLayout gbl,
            Component comp, int zeile, int spalte, int breite,
            int hoehe)
    {
        addComponent(cont, gbl, comp, zeile, spalte, breite, hoehe, 1,
                4);
    }

    /**
     * <code>addComponent</code> fuegt eine Komponente in einen Container ein.
     * <p>
     * Fuer fehlende Parameter werden default-Werte genommen: <br>
     * alingment=4
     *
     * @param cont Container
     * @param gbl GridBagLayout
     * @param comp Component
     * @param zeile Zeile
     * @param spalte Spalte
     * @param breite Breite
     * @param hoehe Hoehe
     * @param anpassung Anpassung
     */
    public static void addComponent(Container cont, GridBagLayout gbl,
            Component comp, int zeile, int spalte, int breite,
            int hoehe, int anpassung)
    {
        addComponent(cont, gbl, comp, zeile, spalte, breite, hoehe,
                anpassung, 4);
    }

    /**
     * <code>addComponent</code> fuegt eine Komponente in einen Container ein.
     *
     * @param cont Container
     * @param gbl GridBagLayout
     * @param comp Component
     * @param zeile Zeile
     * @param spalte Spalte
     * @param breite Breite
     * @param hoehe Hoehe
     * @param anpassung Anpassung
     * @param alignment Alignment
     */
    public static void addComponent(Container cont, GridBagLayout gbl,
            Component comp, int zeile, int spalte, int breite,
            int hoehe, int anpassung, int alignment)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = setGbcInsets(comp);
        gbc.anchor = setGbcAnchor(alignment);
        setGbcWeight(gbc, anpassung);
        gbc.gridx = spalte - 1;
        gbc.gridy = zeile - 1;
        gbc.gridwidth = breite;
        gbc.gridheight = hoehe;

        gbl.setConstraints(comp, gbc);

        cont.add(comp);
    }

    /**
     * <code>setGbcAnchor</code> (set GridBagConstraints Anchor) setzt den
     * Anchor abhaengig vom uebergebenen Wert des alignments.
     * <p>
     *
     * <pre>
     *        1: NorthWest    2: North      3: NorthEast
     *        4: West         5: Center     6: East
     *        7: SouthWest    8: South      9: SouthEast
     * </pre>
     *
     * @param align Alignment
     *
     * @return wertFuerAnchor
     */
    private static int setGbcAnchor(int align)
    {
        switch (align)
        {
            case 1:
                return GridBagConstraints.NORTHWEST;
            case 2:
                return GridBagConstraints.NORTH;
            case 3:
                return GridBagConstraints.NORTHEAST;
            case 4:
                return GridBagConstraints.WEST;
            case 5:
                return GridBagConstraints.CENTER;
            case 6:
                return GridBagConstraints.EAST;
            case 7:
                return GridBagConstraints.SOUTHWEST;
            case 8:
                return GridBagConstraints.SOUTH;
            case 9:
                return GridBagConstraints.SOUTHEAST;
            default:
                return GridBagConstraints.WEST;
        }
    }

    /**
     * <code>setGbcInsets</code> (set GridBagConstraints Insets) setzt die
     * Insets fuer JTextAreas auf (4,4,4,4), fuer alle anderen Components auf
     * (2,2,2,2)
     *
     * @param comp Component
     *
     * @return Insets
     */
    private static Insets setGbcInsets(Component comp)
    {
        if (comp instanceof JTextArea)
        {
            return new Insets(4, 4, 4, 4);
        }
        else
        {
            return new Insets(2, 2, 2, 2);
        }
    }

    /**
     * <code>setGbcWeight</code> (set GridBagConstrains Fill and Weightx/y)
     * setzt fill, weightx und weighty abhaengig von 'Anpassung'.
     *
     * @param gbc GridBagConstraint
     * @param anpassung Anpassung
     */
    private static void setGbcWeight(GridBagConstraints gbc,
            int anpassung)
    {
        switch (anpassung)
        {
            case 0:
                gbc.fill = GridBagConstraints.NONE;
                gbc.weightx = 0;
                gbc.weighty = 0;
                break;
            case 1:
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1;
                gbc.weighty = 0;
                break;
            case 2:
                gbc.fill = GridBagConstraints.VERTICAL;
                gbc.weightx = 0;
                gbc.weighty = 1;
                break;
            default:
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1;
                gbc.weighty = 1;
                break;
        }
    }

    /**
     * privater Konstruktor da SwingTools nicht instantiiert werden darf
     */
    private SwingTools()
    {
    }

}
