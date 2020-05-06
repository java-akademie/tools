package ch.jmildner.mathe_tools;

public class Vector2D
{
	private Point schaft;
	private Point spitze;


	private double x;


	private double y;


	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.schaft = new Point(0, 0);
		this.spitze = new Point(x, y);
	}



	public Vector2D(Point schaft, double x, double y)
	{
		this.x = x;
		this.y = y;

		this.schaft = schaft;
		this.spitze = new Point(schaft.x + x, schaft.y + y);
	}


	public static double getWinkel(Vector2D v1, Vector2D v2)
	{
		double z=mult(v1,v2);
		double n=v1.getLength()*v2.getLength();
		double cosinus=z/n;
		return TrigonometricTools.getDegreeFromCosinus(cosinus);
	}

	public static Vector2D add(Vector2D v1, Vector2D v2)
	{
		return new Vector2D(v1.x + v2.x, v1.y + v2.y);
	}


	public static Vector2D div(Vector2D v1, double w)
	{
		return new Vector2D(v1.x / w, v1.y / w);
	}


	public static Vector2D einheitsVector(Vector2D v)
	{
		return new Vector2D(v.x / v.getLength(), v.y / v.getLength());
	}


	public static double mult(Vector2D v1, Vector2D v2)
	{
		return v1.x * v2.x + v1.y * v2.y;
	}


	public static Vector2D mult(Vector2D v1, double w)
	{
		return new Vector2D(v1.x * w, v1.y * w);
	}


	public static Vector2D sub(Vector2D v1, Vector2D v2)
	{
		return new Vector2D(v1.x - v2.x, v1.y - v2.y);
	}


	public double getLength()
	{
		return Math.pow(x * x + y * y, 0.5);
	}


	public Point getSchaft()
	{
		return schaft;
	}



	public Point getSpitze()
	{
		return spitze;
	}



	public double getX()
	{
		return x;
	}


	public double getY()
	{
		return y;
	}


	public void setSchaft(Point schaft)
	{
		this.schaft = schaft;
		this.spitze = new Point(schaft.x + x, schaft.y + y);
	}



	public void setSpitze(Point spitze)
	{
		this.spitze = spitze;
	}



	public void show()
	{
		System.out.println(this);
	}


	@Override
	public String toString()
	{
		if (schaft == null || schaft.x == 0 && schaft.y == 0)
		{
			return String.format("[%.2f/%.2f,%.2f]", x, y, getLength());
		}
		else
		{
			return String.format("[%.2f/%.2f,%.2f,%s,%s]", x, y,
					getLength(), schaft, spitze);
		}
	}



	public void show(String s)
	{
		System.out.printf("%5s: %s %n", s, this);

	}
}
