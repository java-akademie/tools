package ch.jmildner.tools_ee;

import java.io.IOException;

/**
 * Das Interface <code>Channel</code> deklariert die abstrakten Methoden
 * <code>send</code> und <code>receive</code>.
 * 
 * @author johann
 * 
 */
public interface Channel
{

	public Object receive() throws IOException, ClassNotFoundException;


	public void send(Object anObject) throws IOException;
}
