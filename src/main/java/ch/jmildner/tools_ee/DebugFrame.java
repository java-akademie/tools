package ch.jmildner.tools_ee;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DebugFrame extends Frame
{
	private static final long serialVersionUID = 1L;


	public static void main(String[] args)
	{
		DebugFrame df = new DebugFrame();
		for (i = 1; i <= 1000; i++)
		{
			df.setMessage("hallo  " + i);
			df.setCounters(i * 2, i * 3, i * 4, i * 5);
		}
	}

	Panel mainPanel;
	Button b1 = new Button("b1");
	TextField tf1 = new TextField("", 20);
	TextField message = new TextField("", 40);
	TextField empty = new TextField("", 40);
	TextField used = new TextField("", 40);
	TextField minEmpty = new TextField("", 40);

	TextField maxUsed = new TextField("", 40);

	static int i = 0;


	public DebugFrame()
	{
		super("awtTemplate");
		makeMainPanel();
		setLocation(200, 100);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}


	private void addTheListener()
	{
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				b1Verarbeiten();
			}
		});
	}


	private void b1Verarbeiten()
	{
		tf1.setText("b1 gedrueckt " + i);
	}


	private void makeMainPanel()
	{
		makeTheLayout();
		addTheListener();
	}


	private void makeTheLayout()
	{
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(14, 1));
		p1.add(b1);
		p1.add(tf1);
		p1.add(new Label("Message"));
		p1.add(message);
		p1.add(new Label("emptyCounter"));
		p1.add(empty);
		p1.add(new Label("usedCounter"));
		p1.add(used);
		p1.add(new Label("minEmptyCounter"));
		p1.add(minEmpty);
		p1.add(new Label("maxUsedCounter"));
		p1.add(maxUsed);
		setLayout(new BorderLayout());
		add("Center", p1);
	}


	public void setCounters(int e, int u, int me, int mu)
	{
		used.setText("" + u);
		empty.setText("" + e);
		maxUsed.setText("" + mu);
		minEmpty.setText("" + me);

		if (e + u == me + mu)
			;
		else
			System.out.println("fehler ................. " + (me + mu));
	}


	public void setMessage(String x)
	{
		message.setText(x);
	}
}
