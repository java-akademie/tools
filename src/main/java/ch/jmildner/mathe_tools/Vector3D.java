package ch.jmildner.mathe_tools;

public class Vector3D
{
	private double x;
	private double y;
	private double z;


	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}


	public double getLength()
	{
		return Math.pow(x * x + y * y + z * z, 0.5);
	}


	@Override
	public String toString()
	{
		return String.format("x=%.2f, y=%.2f, z=%.2f, len=%.2f",
				x, y, z, getLength());
	}


	public void show()
	{
		System.out.println(this);
	}


	public static Vector3D add(Vector3D v1, Vector3D v2)
	{
		return new Vector3D(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}


	public static Vector3D sub(Vector3D v1, Vector3D v2)
	{
		return new Vector3D(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}


	public static Vector3D einheitsVector(Vector3D v)
	{
		return new Vector3D(v.x / v.getLength(), v.y / v.getLength(),
				v.z / v.getLength());
	}
}
