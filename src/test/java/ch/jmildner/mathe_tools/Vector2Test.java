package ch.jmildner.mathe_tools;

import ch.jmildner.tools.MyTools;

public class Vector2Test
{
	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
		test4();
	}


	private static void test4()
	{
		MyTools.h2("test4 - ScalarProdukt, Winkel", 2);
		Vector2D v1 = new Vector2D(new Point(2, 3), 0, 3);
		Vector2D v2 = new Vector2D(new Point(2, 3), 4, 0);
		Vector2D v3 = Vector2D.sub(v1, v2);
		v3.setSchaft(v2.getSpitze());
		Vector2D v3m = Vector2D.mult(v3, -1);
		v3m.setSchaft(new Point(2, 3));
		double winkel3 = Vector2D.getWinkel(v1, v2);
		double winkel2 = Vector2D.getWinkel(v1, v3);
		double winkel1 = Vector2D.getWinkel(v2, v3m);
		v1.show("v1");
		v2.show("v2");
		v3.show("v3");
		System.out.printf("%.2f + %.2f + %.2f = %.2f %n", winkel1,
				winkel2, winkel3, winkel1 + winkel2 + winkel3);

		showVector(v1, v2, v3, v3m);

	}


	private static void test3()
	{
		MyTools.h2("test3", 2);
		Vector2D v1 = new Vector2D(1, 3);
		v1.show("v1");
		Vector2D e1 = Vector2D.mult(v1, 2);
		e1.show("e1");

		Vector2D v2 = Vector2D.div(e1, 2);
		v2.show("v2");
		showVector(v1, e1, v2);
	}


	private static void show(String s, Vector2D v1, Vector2D erg)
	{
		System.out.printf("%s  %s  %s %n", v1, s, erg);
	}


	private static void show(String s, Vector2D v1, Vector2D v2,
			Vector2D erg)
	{
		System.out.printf("%s  %s  %s  =   %s %n", v1, s, v2, erg);
	}


	static void showVector(Vector2D... v)
	{
		new Vector2Frame(v);
	}


	static void test1()
	{
		MyTools.h2("test1", 2);
		Vector2D v1 = new Vector2D(1, 3);
		Vector2D v2 = new Vector2D(4, 3);


		Vector2D e1 = Vector2D.add(v1, v2);
		show("+", v1, v2, e1);

		Vector2D e2 = Vector2D.sub(e1, v2);
		show("-", e1, v2, e2);
		Vector2D e3 = Vector2D.sub(e2, v1);
		show("-", e2, v1, e3);

		e1 = Vector2D.einheitsVector(v1);
		show("einheitsVector", v1, e1);
	}


	static void test2()
	{
		MyTools.h2("test2", 2);

		Point startPoint = new Point(-1, -1);

		Vector2D a = new Vector2D(startPoint, 0, 3);
		Vector2D b = new Vector2D(startPoint, 4, 0);
		Vector2D c = new Vector2D(b.getSpitze(), 0, 3);
		Vector2D d = new Vector2D(c.getSpitze(), -4, 0);

		a.show("a");
		b.show("b");
		c.show("c");
		d.show("d");

		Vector2D AC = Vector2D.add(a, b);
		AC.setSchaft(a.getSchaft());
		AC.show("AC");

		Vector2D BD = Vector2D.sub(a, b);
		BD.setSchaft(b.getSpitze());
		BD.show("BD");
		// showVector(a, b, c, d, AC, BD);


		show("+", a, b, AC);
		show("-", a, b, BD);

		Vector2D AM = Vector2D.div(AC, 2);
		AM.show("AM");

		showVector(a, b, c, d, AC, BD, AM);
	}

}
