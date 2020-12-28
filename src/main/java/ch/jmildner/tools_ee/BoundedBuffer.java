package ch.jmildner.tools_ee;

/**
 * Die Klasse <code>BoundedBuffer</code> stellt die Funktionalitaet
 * eines 'RingBuffers' zur Verfuegung.
 * 
 * Die Klasse implementiert das Interface <code>Queue</code> mit den
 * Methoden <code>get</code> und <code>put</code>.
 * 
 * @author johann
 * 
 */
public final class BoundedBuffer implements Queue
{
<<<<<<< HEAD
	// eine aenderung...
	
=======
	// eine aenderung

>>>>>>> branch 'master' of https://github.com/java-akademie/tools.git
	private boolean test = false;

	private DebugFrame dframe;
	private Debug d;

	{
		if (test)
		{
			dframe = new DebugFrame();
			d = new Debug("BoundedBuffer ... ", true);
		}
	}

	/**
	 * der Buffer zur Aufnahme der Objekte
	 */
	private final Object buffer[];

	/**
	 * die Locks fuer get und put Operationen
	 */
	private final Object putlock = new Object();
	private final Object getlock = new Object();

	/**
	 * die Indizes fuer get und put Operationen
	 */
	private int getIndex = 0;
	private int putIndex = 0;

	/**
	 * die leeren und benutzten Plaetze im buffer
	 */
	private int emptyCounter;
	private int usedCounter;

	/**
	 * minima und maxima
	 */
	private int minEmptyCounter;
	private int maxUsedCounter;


	public BoundedBuffer()
	{
		this(10, false);
	}


	public BoundedBuffer(boolean test)
	{
		this(10, test);
	}


	public BoundedBuffer(int size)
	{
		this(size, false);
	}


	/**
	 * Erstellt einen 'RingBuffer' mit 'size' Elementen, setzt den
	 * emptyCounter auf size und den usedCounter auf 0.
	 * 
	 * @param size
	 *            default 10
	 * @param test
	 *            default false
	 */
	public BoundedBuffer(int size, boolean test)
	{
		this.test = test;

		if (size <= 0)
		{
			throw new IllegalArgumentException();
		}

		buffer = new Object[size];
		emptyCounter = minEmptyCounter = size;
		usedCounter = maxUsedCounter = 0;
	}


	private void debug(String m)
	{
		if (test)
		{
			d.debug(m);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.jmb.eetools.Queue#get()
	 */
	public final Object get() throws InterruptedException
	{
		if (Thread.interrupted())
		{
			throw new InterruptedException();
		}

		Object old = null;

		synchronized (getlock)
		{
			// wenn keine Plaetze in der Queue belegt sind, die Queue
			// also leer ist (usedCounter==0), muss gewartet werden, bis
			// die put-Methode ein Objekt eingefuegt hat und den
			// usedCounter wieder hochgesetzt hat.
			while (usedCounter <= 0)
			{
				try
				{
					debug("get: getlock.wait");
					getlock.wait();
				}
				catch (InterruptedException e)
				{
					debug("get: getlock.notify !");
					getlock.notify();
					throw e;
				}
			}

			// ein Object aus dem Buffer extrahieren, den usedCounter
			// vermindern und den getIndex erhoehen.
			old = buffer[getIndex];

			debug("get: belegte Plaetze vor dem Entnehmen: " + usedCounter + ", entnommen vom Platz: " + getIndex
					+ ", objekt: " + old); // test

			showMessage("objekt entnehmen: " + old); // test

			buffer[getIndex] = null;
			--usedCounter;
			getIndex = (getIndex + 1) % buffer.length;
		}

		// den emptyCounter erhoehen und putlock benachrichtigen dass
		// es wieder Platz hat zum Einfuegen eines neuen Objekts.
		synchronized (putlock)
		{
			++emptyCounter;
			debug("get: putlock.notify");
			putlock.notify();
		}

		showCounters();

		return old;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.jmb.eetools.Queue#put(java.lang.Object)
	 */
	public void put(Object anObject) throws InterruptedException
	{
		if (anObject == null)
		{
			throw new IllegalArgumentException();
		}

		if (Thread.interrupted())
		{
			throw new InterruptedException();
		}

		synchronized (putlock)
		{
			// wenn alle Plaetze in der Queue belegt sind, es also keine
			// leeren Plaetze in der Queue gibt (emptyCounter==0), muss
			// gewartet werden, bis die get-Methode ein Objekt entnommen
			// hat und den emptyCounter wieder hochgesetzt hat.
			while (emptyCounter <= 0)
			{
				try
				{
					debug("put: putlock.wait");
					putlock.wait();
				}
				catch (InterruptedException e)
				{
					debug("put: putlock.notify !");
					putlock.notify();
					throw e;
				}
			}

			// ein Object in den Buffer einfuegen, den emptyCounter
			// vermindern und den putIndex erhoehen.

			debug("put: freie   Plaetze vor dem Einfuegen: " + emptyCounter + ", eingefuegt an Platz: " + putIndex
					+ ", objekt: " + anObject); // test

			buffer[putIndex] = anObject;

			--emptyCounter;

			minEmptyCounter = Math.min(emptyCounter, minEmptyCounter); // test

			showMessage("objekt einfuegen: " + anObject);

			putIndex = (putIndex + 1) % buffer.length;
		}

		// den usedCounter erhoehen und getlock benachrichtigen dass es
		// wieder ein Objekt zu holen gibt.
		synchronized (getlock)
		{
			++usedCounter;
			maxUsedCounter = Math.max(usedCounter, maxUsedCounter); // test
			debug("put: getlock.notify");
			getlock.notify();
		}

		showCounters(); // test
	}


	private void showCounters()
	{
		if (test)
		{
			dframe.setCounters(emptyCounter, usedCounter, minEmptyCounter, maxUsedCounter);
		}
	}


	private void showMessage(String m)
	{
		if (test)
		{
			dframe.setMessage(m);
		}
	}


	@Override
	public String toString()
	{
		String s = "";
		s += "\n putIndex        : " + putIndex;
		s += "\n getIndex        : " + getIndex;
		s += "\n emptyCounter    : " + emptyCounter;
		s += "\n usedCounter     : " + usedCounter;
		s += "\n minEmptyCounter : " + minEmptyCounter;
		s += "\n maxUsedCounter  : " + maxUsedCounter;

		int gefuellt = 0;

		for (int i = 0; i < buffer.length; i++)
		{
			if (buffer[i] != null)
				gefuellt++;
		}

		s += "\n elemente        : " + gefuellt;

		return s;
	}
}
