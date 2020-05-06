package ch.jmildner.mathe_tools;

public class Point
{
	double x;
	double y;
	double z;


	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.z = 0;
	}


	public Point(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString()
	{
		return String.format("(%.2f/%.2f/%.2f)", x, y, z);
	}

}
