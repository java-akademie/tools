package ch.jmildner.tools;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Die finale Klasse <code>DateTime</code> beinhaltet allgemein
 * benoetigte <code>static</code> Date/Time Methoden fuer die
 * Datenbankprogrammierung.
 *
 * @author Johann Mildner, Basel
 */
public final class DateTimeTools
{
	/**
	 * <code>DateTimeTools</code> darf nicht instantiiert werden.
	 */
	private DateTimeTools()
	{
	}


	/**
	 * Liefert das momentane Datum.
	 *
	 * @return theCurrentDate
	 */
	public static Date getCurrentDate()
	{
		Calendar cal = GregorianCalendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); // zero based
		int day = cal.get(Calendar.DAY_OF_MONTH);

		return new Date(new GregorianCalendar(year, month, day, 0, 0, 0).getTimeInMillis());
	}


	/**
	 * Liefert die momentane Zeit.
	 *
	 * @return theCurrentTime (1970/01/01 + current time)
	 */
	static public Time getCurrentTime()
	{
		int eineStunde = 1000 * 60 * 60; // Sommerzeit ???
		return new Time(GregorianCalendar.getInstance().getTimeInMillis() - getCurrentDate().getTime() - eineStunde);

	}


	/**
	 * Liefert den momentanen Zeitstempel.
	 *
	 * @param gleicheErlaubt
	 *            false wenn gleiche Zeitstempel nicht erlaubt sind,
	 *            true oder kein Parameter wenn gleiche erlaubt sind
	 * @return theCurrentTimestamp
	 */
	public static Timestamp getCurrentTimestamp(boolean... gleicheErlaubt)
	{
		if (gleicheErlaubt.length == 1 && !gleicheErlaubt[0])
		{
			/*
			 * wartet eine Millisekunde damit es keine gleichen
			 * Timestamps geben kann
			 */
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				System.out.println("exception kommt wahrscheinlich nie vor, " + "würde aber keine Auswirkung haben");
			}
		}

		return new Timestamp(GregorianCalendar.getInstance().getTimeInMillis());

	}


	/**
	 * Liefert ein zufaelliges Alter. Die Haeufigkeit richtet sich nach
	 * der Gauss'schen Normalverteilung (Glockenkurve).
	 *
	 * @param spitze
	 *            Spitze der Gauss'schen Glocke
	 * @return aRandomAge
	 */
	static public int getRandomAge(int spitze)
	{
		Random r = new Random();
		int mult = spitze / 5;
		int min = (int) (spitze * 0.75);
		int max = (int) (spitze * 1.25);

		while (true)
		{
			double age = (r.nextGaussian() * mult);

			int ageRounded = spitze + Math.round((float) age);

			if (ageRounded >= min && ageRounded <= max)
			{
				return ageRounded;
			}
		}
	}


	/**
	 * Liefert das Geburtsjahr zu einem momentanen Alter.
	 *
	 * @param age
	 *            Alter aus dem das Geburtsjahr ermittelt werden soll
	 * @return geburtsjahr
	 */
	static public int getGeburtsjahr(int age)
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);

		return year - age;
	}


	/**
	 * @param spitze
	 *            Alter an der Spitze der Gauss'schen Glockenkurve
	 * @return das Geburtsdatum
	 * @throws Exception
	 *             falsches Datum (kann eigentlich nicht vorkommen)
	 */
	static public Date makeRandomGeburtsdatum(int spitze) throws Exception
	{
		return makeRandomDate(getGeburtsjahr(getRandomAge(spitze)));
	}


	/**
	 * Liefert das Date aus year, month, day, hr, min, sec.
	 *
	 * @param year
	 *            the Year
	 * @param month
	 *            the Month
	 * @param day
	 *            the Day
	 * @param hr
	 *            the Hour
	 * @param min
	 *            the Minute
	 * @param sec
	 *            the Second
	 * @return Date
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Date makeDate(int year, int month, int day, int hr, int min, int sec) throws Exception
	{
		checkDate(year, month, day, hr, min, sec, 0);

		return new Date(makeGregorianCalendar(year, month, day, hr, min, sec).getTimeInMillis());
	}


	/**
	 * Liefert das Date aus year, month, day.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @param day
	 *            the day
	 * @return aDate the Date
	 * @throws Exception
	 *             illegal Date/Time
	 */
	public static Date makeDate(int year, int month, int day) throws Exception
	{
		return makeDate(year, month, day, 0, 0, 0);
	}


	/**
	 * Liefert ein zufaelliges Date im Bereich fromYear - toYear.
	 *
	 * @param yearFrom
	 *            the year from
	 * @param yearTo
	 *            the year to
	 * @return Date the Date between from and to
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Date makeRandomDate(int yearFrom, int yearTo) throws Exception
	{
		int year = getRandom(yearFrom, yearTo);
		int month = getRandom(1, 12);
		int day = getRandom(1, lastDayOfMonth(year, month));
		return makeDate(year, month, day);
	}


	/**
	 * Liefert ein zufaelliges Date im Bereich 1950 - 2010.
	 *
	 * @return Date in the range from 1950 to 2010
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Date makeRandomDate() throws Exception
	{
		return makeRandomDate(1950, 2010);
	}


	/**
	 * Liefert ein zufaelliges Date im Jahr year.
	 *
	 * @param year
	 *            the year
	 * @return a random date in that year
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Date makeRandomDate(int year) throws Exception
	{
		return makeRandomDate(year, year);
	}


	/**
	 * Liefert die Time aus hr, min, sec.
	 *
	 * @param hr
	 *            the hour
	 * @param min
	 *            the minute
	 * @param sec
	 *            the second
	 * @return aTime (1970/01/01 hr:min:sec)
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Time makeTime(int hr, int min, int sec) throws Exception
	{
		checkDate(1970, 1, 1, hr, min, sec, 0);

		return new Time(makeGregorianCalendar(1970, 1, 1, hr, min, sec).getTimeInMillis());
	}


	/**
	 * Liefert eine zufaellige Time.
	 *
	 * @return a random time
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Time makeRandomTime() throws Exception
	{
		return new Time(makeGregorianCalendar(1970, 1, 1, getRandom(0, 23), getRandom(0, 58), getRandom(0, 59))
				.getTimeInMillis());
	}


	/**
	 * Liefert den Timestamp aus year, month, day, hr, min, sec.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @param day
	 *            the day
	 * @param hr
	 *            the hour
	 * @param min
	 *            the minute
	 * @param sec
	 *            the second
	 * @return aTimestamp
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Timestamp makeTimestamp(int year, int month, int day, int hr, int min, int sec) throws Exception
	{
		return makeTimestamp(year, month, day, hr, min, sec, 0);
	}


	/**
	 * Liefert den Timestamp aus year, month, day.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @param day
	 *            the day
	 * @return aTimestamp
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Timestamp makeTimestamp(int year, int month, int day) throws Exception
	{
		return makeTimestamp(year, month, day, 0, 0, 0, 0);
	}


	/**
	 * Liefert den Timestamp aus year, month, day, hr, min, sec,
	 * microSec.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @param day
	 *            the day
	 * @param hr
	 *            the hour
	 * @param min
	 *            the minute
	 * @param sec
	 *            the second
	 * @param nanoSecond
	 *            the nanoSecond
	 * @return aTimestamp
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Timestamp makeTimestamp(int year, int month, int day, int hr, int min, int sec, int nanoSecond)
			throws Exception
	{
		checkDate(year, month, day, hr, min, sec, nanoSecond);

		Timestamp ts = new Timestamp(makeGregorianCalendar(year, month, day, hr, min, sec).getTimeInMillis());
		ts.setNanos(nanoSecond);

		return ts;
	}


	/**
	 * Liefert einen zufaelligen Timestamp.
	 *
	 * @return aTimestamp
	 */
	static public Timestamp makeRandomTimestamp()
	{
		return new Timestamp(MyTools.getRandom(1L, System.currentTimeMillis()));
	}


	/**
	 * Liefert einen zufaelligen Timestamp im Bereich fromYear - toYear.
	 *
	 * @param yearFrom
	 *            from year
	 * @param yearTo
	 *            to year
	 * @return aTimestamp
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Timestamp makeRandomTimestamp(int yearFrom, int yearTo) throws Exception
	{
		int year = getRandom(yearFrom, yearTo);
		int month = getRandom(1, 12);
		int day = getRandom(1, lastDayOfMonth(year, month));
		int hour = getRandom(1, 23);
		int min = getRandom(1, 59);
		int sec = getRandom(1, 59);

		Timestamp ts = makeTimestamp(year, month, day, hour, min, sec);
		ts.setNanos(getRandom(0, 999) * 1000000);
		return ts;
	}


	/**
	 * Liefert einen zufaelligen Timestamp im Jahr year.
	 *
	 * @param year
	 *            the year
	 * @return aTimestamp in that year
	 * @throws Exception
	 *             illegal Date/Time
	 */
	static public Timestamp makeRandomTimestamp(int year) throws Exception
	{
		return makeRandomTimestamp(year, year);
	}


	//
	// PRIVATE METHODEN
	//
	static private GregorianCalendar makeGregorianCalendar(int year, int month, int day, int hr, int min, int sec)
			throws Exception
	{
		checkDate(year, month, day, hr, min, sec, 0);

		int mon;

		switch (month)
		{
			default:
			case 1:
				mon = Calendar.JANUARY;
				break;
			case 2:
				mon = Calendar.FEBRUARY;
				break;
			case 3:
				mon = Calendar.MARCH;
				break;
			case 4:
				mon = Calendar.APRIL;
				break;
			case 5:
				mon = Calendar.MAY;
				break;
			case 6:
				mon = Calendar.JUNE;
				break;
			case 7:
				mon = Calendar.JULY;
				break;
			case 8:
				mon = Calendar.AUGUST;
				break;
			case 9:
				mon = Calendar.SEPTEMBER;
				break;
			case 10:
				mon = Calendar.OCTOBER;
				break;
			case 11:
				mon = Calendar.NOVEMBER;
				break;
			case 12:
				mon = Calendar.DECEMBER;
				break;
		}


		return new GregorianCalendar(year, mon, day, hr, min, sec);
	}


	static private void checkDate(int year, int month, int day, int hr, int min, int sec, int msec) throws Exception
	{
		boolean ok = true;

		if (hr < 0 || hr > 23 || min < 0 || min > 59 || sec < 0 || sec > 59 || msec < 0 || msec > 999999)
		{
			ok = false;
		}
		else
			if (year < 1 || year > 9999 || month < 1 || month > 12)
			{
				ok = false;
			}
			else
				if (day < 1 || day > lastDayOfMonth(year, month))
				{
					ok = false;
				}

		if (!ok)
		{
			throw new Exception("illegal Date/Time (" + year + "-" + month + "-" + day + " " + hr + ":" + min + ":"
					+ sec + "." + msec + ")");
		}
	}


	static private int lastDayOfMonth(int year, int month)
	{
		switch (month)
		{
			case 2:
				if (year % 400 == 0)
				{
					return 29;
				}

				if (year % 100 == 0)
				{
					return 28;
				}

				if (year % 4 == 0)
				{
					return 29;
				}

				return 28;

			case 4:
			case 6:
			case 9:
			case 11:
				return 30;

			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
			default:
				return 31;
		}
	}


	static private int getRandom(int min, int max)
	{
		return (int) (Math.random() * (max - min + 1)) + min;
	}


}
