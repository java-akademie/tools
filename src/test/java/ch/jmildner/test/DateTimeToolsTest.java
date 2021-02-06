package ch.jmildner.test;


import ch.jmildner.tools.DateTimeTools;
import ch.jmildner.tools.MyTools;
import org.junit.Test;

public class DateTimeToolsTest
{
	private static void out(Object obj)
	{
		System.out.println(obj);
	}

	@Test
	public void test()
	{
		MyTools.h2("DateTimeToolsMain", 2);

		MyTools.h2("Current Date und Time", 2);
		out(DateTimeTools.getCurrentDate());
		out(DateTimeTools.getCurrentTime());

		MyTools.h2("Current Timestamps", 2);
		for (int i = 1; i <= 10; i++)
			out(DateTimeTools.getCurrentTimestamp(true));
		System.out.println();
		for (int i = 1; i <= 10; i++)
			out(DateTimeTools.getCurrentTimestamp(false));

		MyTools.h2("Random Age um 70 und Geburtsjahr", 2);
		for (int i = 1; i <= 10; i++)
		{
			int age = DateTimeTools.getRandomAge(70);
			int year = DateTimeTools.getGeburtsjahr(age);
			System.out.printf("Alter: %d, Geburtsjahr: %d %n", age, year);
		}

		MyTools.h2("Random Geburtsdatum um 40", 2);
		try
		{
			for (int i = 1; i <= 10; i++)
			{
				out(DateTimeTools.makeRandomGeburtsdatum(40));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		MyTools.h2("makeDate", 2);
		try
		{
			for (int i = 1; i <= 10; i++)
			{
				out(DateTimeTools.makeDate(2019, 2 + i, 10 + i, 0, 0, 0));
				out(DateTimeTools.makeDate(2019, 1 + i, 1 + i));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		MyTools.h2("makeRandomDate", 2);
		try
		{
			for (int i = 1; i <= 10; i++)
			{
				out(DateTimeTools.makeRandomDate());
				out(DateTimeTools.makeRandomDate(2100));
				out(DateTimeTools.makeRandomDate(2019, 2020));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		MyTools.h2("makeTime", 2);
		try
		{
			out(DateTimeTools.makeTime(15, 30, 20));
			for (int i = 1; i <= 10; i++)
			{
				out(DateTimeTools.makeRandomTime());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		MyTools.h2("makeTimeStamp", 2);
		try
		{
			out(DateTimeTools.makeTimestamp(2019, 3, 20));
			out(DateTimeTools.makeTimestamp(2010, 4, 20, 11, 15, 30));
			out(DateTimeTools.makeTimestamp(2015, 5, 20, 11, 15, 30, 200_000));
			for (int i = 1; i <= 10; i++)
			{
				out(DateTimeTools.makeRandomTimestamp());
				out(DateTimeTools.makeRandomTimestamp(2019));
				out(DateTimeTools.makeRandomTimestamp(2015, 2017));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
