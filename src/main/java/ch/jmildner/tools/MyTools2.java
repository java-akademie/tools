package ch.jmildner.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Die finale Klasse <code>MyTools</code>beinhaltet allgemein benoetigte
 * Methoden zum Lesen von der Tastatur, ....
 * 
 * @author johann
 * 
 */
public final class MyTools2
{
	public static void close(BufferedReader br)
	{
		try
		{
			br.close();
		}
		catch (IOException e)
		{
		}
	}


	public static final void close(PrintWriter pw)
	{
		pw.close();
	}


	public static void close(Socket socket) throws IOException
	{
		socket.close();
	}


	public static void flush(PrintWriter pw)
	{
		pw.flush();
	}


	/**
	 * fuer das Lesen von Strings aus Sockets und TextFiles erzeugen
	 * eines BufferedReader's, readLine und close
	 * 
	 * @param s
	 *            Socket
	 * 
	 * @throws IOException
	 *             x
	 * 
	 * @return BufferedReader
	 */
	public static BufferedReader getBufferedReader(Socket s)
			throws IOException
	{
		return new BufferedReader(
				new InputStreamReader(s.getInputStream()));
	}


	public static BufferedReader getBufferedReader(String fileName)
			throws FileNotFoundException
	{
		InputStreamReader isr = null;

		isr = new InputStreamReader(new FileInputStream(fileName));

		return new BufferedReader(isr);
	}


	/**
	 * fuer das Schreiben von Strings in Sockets und TextFiles erzeugen
	 * eines PrintWriter's, writeLine und close
	 * 
	 * @param s
	 *            Socket
	 * 
	 * @throws IOException
	 *             x
	 *             
	 * @return PrintWriter
	 */
	public static PrintWriter getPrintWriter(Socket s)
			throws IOException
	{
		OutputStreamWriter osw = null;

		osw = new OutputStreamWriter(s.getOutputStream());

		return new PrintWriter(osw);
	}


	public static PrintWriter getPrintWriter(String fileName)
			throws FileNotFoundException
	{
		OutputStreamWriter osw = null;

		osw = new OutputStreamWriter(new FileOutputStream(fileName));

		return new PrintWriter(osw);
	}


	/**
	 * Sockets (Server-Socket, Client-Socket)
	 * 
	 * @param port
	 *            Port
	 * 
	 * @throws IOException
	 *             x
	 *             
	 * @return ServerSocket
	 */
	public static ServerSocket getServerSocket(int port)
			throws IOException
	{
		ServerSocket serverSocket = null;

		serverSocket = new ServerSocket(port);

		return serverSocket;
	}


	public static Socket getSocket(ServerSocket serverSocket)
			throws IOException
	{
		return serverSocket.accept();
	}


	public static Socket getSocket(String url, int port)
			throws UnknownHostException, IOException
	{
		return new Socket(url, port);
	}


	public static String readLine(BufferedReader br) throws IOException
	{
		return br.readLine();
	}


	public static void writeLine(PrintWriter pw, String buff)
	{
		pw.println(buff);
	}


	/**
	 * privater Konstruktor da MyTools2 nicht instantiiert werden darf
	 */
	private MyTools2()
	{
	}
}
