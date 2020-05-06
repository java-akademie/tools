package ch.jmildner.mathe_tools;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Vector2Panel extends JPanel
{

    private static final long serialVersionUID = 1L;

    Vector2D[] varr;

    public Vector2Panel(Vector2D... v)
    {
        this.varr = v;
    }

    public void paintComponent(Graphics g)
    {
        // zeichenflaeche vorbereiten
        g.setColor(Color.white);
        g.fillRect(0, 0, getSize().width, getSize().height);

        // das Koordinatensystem zeichnen
        g.setColor(Color.gray);
        drawLine(g, 400, 0, 400, 600);
        drawLine(g, 0, 300, 800, 300);

        // for (Vector2 v : varr)
        for (int i = 0; i < varr.length; i++)
        {
            if (i == 0)
            {
                g.setColor(Color.red);
            }
            else if (i == 1)
            {
                g.setColor(Color.blue);
            }
            else if (i == 2)
            {
                g.setColor(Color.black);
            }
            else
            {
                g.setColor(Color.MAGENTA);
            }

            // einen Vector zeichnen
            Vector2D v = varr[i];
            int schaftX = 400 + (int) v.getSchaft().x * 33;
            int schaftY = 300 - (int) v.getSchaft().y * 33;
            int spitzeX = schaftX + (int) v.getX() * 33;
            int spitzeY = schaftY - (int) v.getY() * 33;
            // v.show();
            drawLine(g, schaftX, schaftY, spitzeX, spitzeY);
        }

    }

    public void xxx(Graphics g)
    {
        // zeichenflaeche vorbereiten
        g.setColor(Color.white);
        g.fillRect(0, 0, getSize().width, getSize().height);

        // das Koordinatensystem zeichnen
        g.setColor(Color.green);
        drawLine(g, 100, 0, 100, 600);
        drawLine(g, 0, 500, 800, 500);

        // start links oben
        // 100 nach rechts
        // 10 nach unten
        // 200
        // g.drawLine()
        g.drawLine(100, 300, 700, 400);

        // rote linie zeichnen
        g.setColor(Color.red);
        g.drawLine(50, 10, 100, 200);
        // gruenes Rechteck gefuellt zeichnen
        g.setColor(Color.green);
        g.fillRect(170, 100, 70, 120);
        // blaues leeres Rechteck zeichnen
        g.setColor(Color.blue);
        g.drawLine(150, 10, 250, 10);
        g.drawLine(150, 60, 250, 60);
        g.drawLine(150, 10, 150, 60);
        g.drawLine(250, 10, 250, 60);
        // magenta Kreis zeichnen
        g.setColor(Color.magenta);
        g.drawOval(270, 100, 70, 70);
        // black Kreis gefuellt
        g.setColor(Color.black);
        g.fillOval(300, 10, 70, 70);
        // pink Kreisbogen
        g.setColor(Color.pink);
        g.drawArc(370, 100, 70, 70, 0, 270);
        // noch eine rote linie
        g.setColor(Color.red);
        g.drawLine(450, 10, 500, 200);
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2)
    {
        g.drawLine(x1, y1, x2, y2);
    }
}
