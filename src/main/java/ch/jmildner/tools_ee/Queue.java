package ch.jmildner.tools_ee;

/**
 * Das Interface <code>Queue</code> deklariert die abstrakten Methoden
 * <code>get</code> und <code>put</code>.
 * 
 * @author johann
 */
public interface Queue
{
	/**
	 * @return anObject the Object
	 * 
	 * @throws InterruptedException
	 *             ???
	 */
	Object get() throws InterruptedException;


	/**
	 * @param anObject
	 *            object to put
	 * 
	 * @throws InterruptedException
	 *             ???
	 */
	void put(Object anObject) throws InterruptedException;
}
