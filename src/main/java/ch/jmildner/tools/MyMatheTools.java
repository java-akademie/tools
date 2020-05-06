package ch.jmildner.tools;


/**
 * @author johann
 *
 */
public final class MyMatheTools
{

	/**
	 * MyMatheTools darf nicht instantiiert werden
	 */
	private MyMatheTools()
	{
	}


	public static int kgvn(int a, int... b)
	{
		if (a == 0)
			throw new RuntimeException("Arg darf nicht 0 sein");
		for (int i = 0; i < b.length; i++)
			if (b[i] == 0)
				throw new RuntimeException("Arg darf nicht 0 sein");

		int kgv = 0;

		if (b.length == 0)
			return a;

		kgv = kgv2(a, b[0]);

		for (int i = 1; i < b.length; i++)
		{
			kgv = kgv2(kgv, b[i]);
		}
		return kgv;
	}


	public static int kgv2(int a, int b)
	{
		if (a <= b)
		{
			int z = a;
			a = b;
			b = z;
		}

		int m = a;

		while (a % b != 0)
		{
			a += m;
		}

		return a;
	}


	public static int ggtn(int a, int... b)
	{
		if (a == 0)
			throw new RuntimeException("Arg darf nicht 0 sein");
		for (int i = 0; i < b.length; i++)
			if (b[i] == 0)
				throw new RuntimeException("Arg darf nicht 0 sein");

		int zwggt = 0;
		int ggt = 0;

		if (b.length == 0)
			return a;

		ggt = ggt2(a, b[0]);

		if (ggt == 1)
			return 1;

		for (int i = 1; i < b.length; i++)
		{
			zwggt = ggt2(a, b[i]);
			if (zwggt == 1)
				return 1;
			else
				if (zwggt != ggt)
				{
					zwggt = ggt = ggt2(ggt, zwggt);
					if (ggt == 1)
						return 1;
				}
		}
		return ggt;
	}


	public static int ggt2(int a, int b)
	{
		do
		{
			if (a <= b)
			{
				int z = a;
				a = b;
				b = z;
			}

			a -= b;
		}
		while (a != b);

		return a;
	}
}
