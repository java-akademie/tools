package ch.jmildner.mathe_tools;

import javax.swing.JFrame;

public class Vector2Frame extends JFrame
{

    private static final long serialVersionUID = 1L;

    Vector2Frame(Vector2D... v)
    {
        setTitle("Vector 2 Dimensional");

        Vector2Panel panel = new Vector2Panel(v);

        add(panel);

        setSize(800, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

}
