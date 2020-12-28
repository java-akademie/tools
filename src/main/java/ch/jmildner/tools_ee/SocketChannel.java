package ch.jmildner.tools_ee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketChannel implements Channel
{
	// private static Debug d = new Debug("SocketChannel ... ", true);

	private Socket socket;

	private ObjectInputStream in;
	private ObjectOutputStream out;


	public SocketChannel(Socket s) throws IOException
	{
		socket = s;

		out = new ObjectOutputStream(s.getOutputStream());
		System.out.println("sc " + 333 + "  " + socket + "  ");

		in = new ObjectInputStream(s.getInputStream());
		System.out.println("sc" + 444);
	}


	public void close()
	{
		try
		{
			if (in != null)
			{
				in.close();
			}

			if (out != null)
			{
				out.close();
			}

			if (socket != null)
			{
				socket.close();
			}
		}
		catch (IOException e)
		{
		}
	}


	public Object receive() throws IOException, ClassNotFoundException
	{
		Object object = null;

		synchronized (in)
		{
			object = in.readObject();
		}

		return object;
	}


	public void send(Object v) throws IOException
	{
		synchronized (out)
		{
			out.writeObject(v);
		}
	}


	@Override
	public String toString()
	{
		return new String(socket.getInetAddress().toString());
	}
}
