package ch.jmildner.tools_ee;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChannelFactory extends Thread
{
	private static Debug d = new Debug("ChannelFactory ... ", true);

	public final static int DEFAULT_PORT = 4711;


	public static SocketChannel getChannel(String h, int p)
	{
		if (p <= 0)
		{
			p = DEFAULT_PORT;
		}

		SocketChannel channel = null;

		while (channel == null)
		{
			try
			{
				Socket s = new Socket(h, p);
				System.out.println(s);
				channel = new SocketChannel(s);
				// channel = new SocketChannel(new Socket(h, p));
			}
			catch (IOException e)
			{
				System.out.println(e);
				System.out
						.println("server on " + h + " not ready ... ");

				try
				{
					Thread.sleep((5000));
				}
				catch (InterruptedException e1)
				{
				}
			}
		}

		System.out.println("connected with " + h);

		return channel;
	}

	private int port;
	private Queue buffer = new BoundedBuffer(10);

	private ServerSocket serverSocket = null;


	public ChannelFactory()
	{
		this(DEFAULT_PORT);
	}


	public ChannelFactory(int p)
	{
		if (p <= 0)
		{
			throw new IllegalArgumentException();
		}

		d.debug("start");

		this.port = p;

		try
		{
			serverSocket = new ServerSocket(port);
			waitForClients();
			System.out.println("server startet on port: " + port);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}


	public SocketChannel getChannel() throws InterruptedException
	{
		return (SocketChannel) buffer.get();
	}


	public void waitForClients()
	{
		new Thread()
		{
			@Override
			public void run()
			{

				while (true)
				{
					try
					{
						System.out.println("wait for connection port: "
								+ port + " ...");

						Socket s = serverSocket.accept();

						System.out.println("connection accepted ...");

						SocketChannel channel = new SocketChannel(s);

						buffer.put(channel);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
