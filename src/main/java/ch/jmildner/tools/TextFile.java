/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.jmildner.tools;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * Die Klasse <code>TextFile</code> dient dem Lesen und Schreiben von
 * Text-Dateien.
 *
 * <pre>
 *   ---------------------------------------------------------------
 *   Anwendungsbeispiel:
 *   ---------------------------------------------------------------
 *
 *   try
 *   {
 *     TextFile tf = new TextFile(&quot;adressen.txt&quot;, 'o');
 *     tf.printLine(&quot;zeile1&quot;);
 *     tf.printLine(&quot;zeile2&quot;);
 *     tf.printLine(&quot;zeile3&quot;);
 *     tf.close();
 *   }
 *   catch (Exception e)
 *   {
 *   }
 *
 *
 *   try
 *   {
 *     TextFile tf = new TextFile(&quot;adressen.txt&quot;, 'i');
 *     String s = tf.readLine();
 *     while(s!=null)
 *     {
 *       System.out.println(s);
 *       s = tf.readLine();
 *     }
 *   }
 *   catch (Exception e)
 *   {
 *   }
 *
 *   ---------------------------------------------------------------
 * </pre>
 *
 * @author Johann Mildner
 */
public class TextFile implements Closeable
{
	private String fileName;
	private char mode;
	private BufferedReader br;
	private PrintWriter pw;


	/**
	 * Erzeugt eine TextFile-Referenz zum Lesen oder Schreiben einer
	 * Datei
	 *
	 * @param fileName
	 *            z.B.: adressen.txt
	 * @param mode
	 *            i=Input, o=Output
	 * @throws FileNotFoundException
	 *             x
	 */
	public TextFile(String fileName, char mode) throws FileNotFoundException
	{
		this.fileName = fileName;
		this.mode = mode;

		switch (mode)
		{
			case 'I':
			case 'i':
				openInput();
				break;
			case 'O':
			case 'o':
				openOutput();
				break;

			default:
				break;
		}
	}


	/**
	 * Schliesst die Datei.
	 */
	@Override
	public void close()
	{
		try
		{
			switch (mode)
			{
				case 'I':
				case 'i':
					br.close();
					break;

				case 'O':
				case 'o':
					pw.close();
					break;

				default:
					break;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
		}

		fileName = null;
		br = null;
		pw = null;
		mode = 0;
	}


	/**
	 * Erstellt den Buffered Reader.
	 *
	 * @throws FileNotFoundException
	 *             File not found
	 */
	private void openInput() throws FileNotFoundException
	{
		InputStream is = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
	}


	/**
	 * Erstellt den Print Writer.
	 *
	 * @throws FileNotFoundException
	 *             file not found
	 */
	private void openOutput() throws FileNotFoundException
	{
		OutputStream os = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		pw = new PrintWriter(osw);
	}


	/**
	 * Schreibt eine Zeile in die Ausgabedatei.
	 *
	 * @param line
	 *            die zu schreibende Zeile
	 */
	public void printLine(String line)
	{
		if (mode == 'i' || mode == 'I')
		{
			throw (new RuntimeException("schreiben bei mode 'i' nicht moeglich"));
		}

		pw.println(line);
		pw.flush();
	}


	/**
	 * Liefert eine Zeile aus der Eingabedatei.
	 *
	 * @return eineZeile
	 */
	public String readLine()
	{
		if (mode == 'o' || mode == 'O')
		{
			throw (new RuntimeException("lesen bei mode 'o' nicht moeglich"));
		}

		String zeile = null;

		try
		{
			zeile = br.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
		}

		return zeile;
	}
}
