package ch.jmildner.tools;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Die Klasse <code>MyPanel</code> dient der Vereinfachung der Erzeugung eines
 * Panels unter Zuhilfenahme von SWING und des Layoutmanagers GridBagLayout.
 *
 * <pre>
 *
 *   ein Beispiel
 *
 *   +---------------------------------------------------------------+
 *   !   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |
 *   +---------------------------------------------------------------+
 *  1!                                                               |
 *  2!            U E B E R S C H R I F T                            |
 *  3!                                                               |
 *  4!name:           _______________________                        |
 *  5!eingagabewert:  ________________________________________       |
 *  6!                                                               |
 *  7!       ------------------------------------------------------- |
 *  8!       |                                                     | |
 *  9!erg:   |                                                     | |
 * 10!       |                                                     | |
 * 11!       ------------------------------------------------------- |
 * 12!                                                               |
 * 13!BUTTON1 BUTTON2 BUTTON3                             BUTTONN    |
 * 14!                                                               |
 *   +---------------------------------------------------------------+
 *
 *   int spalten       = 8;
 *   int spaltenbreite = 7:
 *   boolean test      = true/false;
 *
 *   int zeile         = 0;
 *   int spalte        = 0;
 *   int breite        = 0;
 *   int hoehe         = 0;
 *
 *   MyPanel mp = new MyPanel(spalten,spaltenbreite,test);
 *
 *   zeile =2;
 *   spalte=1;
 *   breite=8;
 *   mp.addCaptionCenter("UEBERSCHRIFT", zeile, spalte, breite);
 *
 *   zeile =4;
 *   spalte=1;
 *   breite=1;
 *   mp.add(labelName, zeile, 1, 1);
 *   mp.add(tfName, zeile, 3, 3);
 *
 *   mp.add(labelEingabewert, 5, 1, 2);
 *   mp.add(tfEingabewert   , 5, 3, 5);
 *
 *   hoehe = 3;
 *   mp.add(labelErg, 8, 1, 1);
 *   mp.add(taEerg  , 7, 2, 7, hoehe);
 *
 *   mp.add(bt1, 13, 1, 1);
 *   mp.add(bt2, 13, 2, 1);
 *   mp.add(bt3, 13, 3, 1);
 *   mp.add(btn, 13, 8, 1);
 *
 *   add(mp);
 *   ----------------------------------------------------------------
 * </pre>
 *
 *
 * @author Johann Mildner, Basel - &copy; java-akademie.ch
 *
 */
public  class MyPanel extends JPanel
{

    private static final long serialVersionUID = 1L;
    private Insets insets;

    public MyPanel()
    {

    }

    public MyPanel(int spalten, int spaltenbreite, boolean test)
    {
        init(spalten, spaltenbreite, test);
    }

    public MyPanel(int spalten, int spaltenbreite)
    {
        init(spalten, spaltenbreite, false);
    }

    /**
     * init
     */
    public final void init()
    {
        this.insets = new Insets(4, 4, 4, 4);
        this.setLayout(new GridBagLayout());
    }

    /**
     * init.
     *
     * @param spalten - Anzahl der Splaten
     * @param spaltenbreite - Breite einer Spalte
     * @param test - true=Test, false=Prodiction
     */
    public final void init(int spalten, int spaltenbreite, boolean test)
    {
        this.init();
        this.addEmptyRow(spaltenbreite, (test ? '.' : ' '), 1, spalten);
    }

    /**
     * init.
     *
     * @param spalten - Anzahl der Splaten
     * @param spaltenbreite - Breite einer Spalte
     */
    public final void init(int spalten, int spaltenbreite)
    {
        this.init(spalten, spaltenbreite, false);
    }

    /**
     * Fuegt eine Komponente links ausgerichtet ein.
     *
     * @param component Komponente
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void add(JComponent component, int row, int column, int width)
    {
        add(component, row, column, width, 1, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
    }

    /**
     * Fuegt eine Komponente ein.
     *
     * @param component Komponente
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     * @param height Hoehe
     */
    public void add(JComponent component, int row, int column, int width, int height)
    {
        add(component, row, column, width, height, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
    }


    /*
     * Fuegt eine Komponente ein.
     */
    private void add(JComponent component, int row, int column, int width, int height, int alignment, int fill)
    {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = insets;

        gbc.gridx = column - 1;
        gbc.gridy = row - 1;
        gbc.gridwidth = width;
        gbc.gridheight = height;

        gbc.anchor = alignment;
        gbc.fill = fill;
        gbc.weightx = 0;
        gbc.weighty = 0;

        ((GridBagLayout) this.getLayout()).setConstraints(component, gbc);

        add(component);
    }

    /**
     * Fuegt einen Text als zentrierte Ueberschrift ein.
     *
     * @param text der Text
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addCaptionCenter(String text, int row, int column, int width)
    {
        JLabel l = mkJLabel(text, new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(l, row, column, width, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    /**
     * Fuegt eine Komponente zentiert ein.
     *
     * @param component Komponente
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addCenter(JComponent component, int row, int column, int width)
    {
        add(component, row, column, width, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    /**
     * Fuellt den gesamten Grid mit gleichen Texten. Ermoeglicht die Breite der
     * einzelnen Spalten festzulegen. Schlussendlich soll der Text aus
     * Leerzeichen bestehen.
     *
     * @param breite Anzahl Fuellzeichen je Textfeld
     * @param filler Fuellzeichen
     * @param rows Anzahl Zeilen
     * @param columns Anzahl Spalten
     */
    public void addEmptyGrid(int breite, char filler, int rows, int columns)
    {
        String text = "";

        for (int i = 1; i <= breite; i++)
        {
            text += filler;
        }

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

        for (int row = 1; row <= rows; row++)
        {
            for (int column = 1; column <= columns; column++)
            {
                add(mkJLabel(text, font), row * 10, column, 1);
            }
        }

    }

    /**
     * Fuegt eine Zeile mit gleichen Texten ein. Ermoeglicht die Breite der
     * einzelnen Spalten festzulegen. Schlussendlich soll der Text aus
     * Leerzeichen bestehen.
     *
     * @param breite Anzahl Fuellzeichen je Textfeld
     * @param filler Fuellzeichen
     * @param row Zeile
     * @param columns Anzahl Spalten
     */
    public void addEmptyRow(int breite, char filler, int row, int columns)
    {
        String text = "";

        for (int i = 1; i <= breite; i++)
        {
            text += filler;
        }

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

        for (int column = 1; column <= columns; column++)
        {
            add(mkJLabel(text, font), row, column, 1);
        }
    }

    /**
     * Fuegt einen Text als links ausgerichteten Label ein.
     *
     * @param text der Text
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addLabel(String text, int row, int column, int width)
    {
        addLabelLeft(text, row, column, width);
    }

    /**
     * Fuegt einen Text als zentrierten Label ein.
     *
     * @param text der Text
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addLabelCenter(String text, int row, int column, int width)
    {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        add(l, row, column, width);
    }

    /**
     * Fuegt einen Text als links ausgerichteten Label ein.
     *
     * @param text der Text
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addLabelLeft(String text, int row, int column, int width)
    {
        JLabel l = new JLabel(text, SwingConstants.LEFT);
        add(l, row, column, width);
    }

    /**
     * Fuegt einen Text als rechts ausgerichteten Label ein.
     *
     * @param text der Text
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addLabelRight(String text, int row, int column, int width)
    {
        JLabel l = new JLabel(text, SwingConstants.RIGHT);
        add(l, row, column, width);
    }

    /**
     * Fuegt eine Komponente links ausgerichtet ein.
     *
     * @param component die Komponente
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addLeft(JComponent component, int row, int column, int width)
    {
        add(component, row, column, width, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
    }

    /**
     * Fuegt eine Komponente rechts ausgerichtet ein.
     *
     * @param component die Komponente
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     */
    public void addRight(JComponent component, int row, int column, int width)
    {
        add(component, row, column, width, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
    }

    /**
     * Fuegt eine scrollable TextArea ein.
     *
     * @param textArea die TextArea
     * @param row Zeile
     * @param column Spalte
     * @param width Breite
     * @param height Hoehe
     */
    public void addTextArea(JTextArea textArea, int row, int column, int width, int height)
    {
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        JScrollPane sp = new JScrollPane(textArea);
        add(sp, row, column, width, height);
    }


    /*
     * Erzeugt einen Label.
     */
    private JLabel mkJLabel(String text, Font font)
    {
        JLabel lb = new JLabel(text);
        lb.setFont(font);
        return lb;
    }
}
