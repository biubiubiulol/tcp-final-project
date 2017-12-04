package tcp;

import java.io.*;

import java.net.ServerSocket;

import java.net.Socket;

import java.net.SocketException;

import java.nio.file.Files;

import java.nio.file.Paths;

public class TCPServer

{

	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private OutputStream outStream = null;
	private String fileData;

	public TCPServer()

	{

	}

	public void createSocket(int port,String fileName)

	{
		Thread thread = new Thread(new Runnable() {
		public void run() {
		try
		{
		
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			outStream = socket.getOutputStream();
			System.out.println("Connected");
			System.out.println(socket.getPort());
			System.out.println(socket.getLocalPort());
			//createWriteThread(fileName);
			String recvedMessage = fileName;
			//FileInputStream FileData = new FileInputStream();
			//fileData = new String(Files.readAllBytes(Paths.get(recvedMessage)));
			//outStream.write(fileData.getBytes());
			outStream.write(Files.readAllBytes(Paths.get(recvedMessage)));

		}
		

		catch (IOException io)

		{

			io.printStackTrace();

				}
			}
		});
		thread.start();
	}



	public static void main(String[] args)

	{
		TCPServer[] chatServers = new TCPServer[] {new TCPServer(),new TCPServer(),new TCPServer(),new TCPServer(),new TCPServer()};
		int port = 3334;
		String[] fileNames = {"/home/kai/workspace/cs260workspace/workspace/Project Part-1/100Mb File (1).txt"
				,"/home/kai/workspace/cs260workspace/workspace/Project Part-1/100Mb File (2).txt",
				"/home/kai/workspace/cs260workspace/workspace/Project Part-1/100Mb File (3).txt",
				"/home/kai/workspace/cs260workspace/workspace/Project Part-1/100Mb File (4).txt",
				"/home/kai/workspace/cs260workspace/workspace/Project Part-1/100Mb File (5).txt"};
		for (int i = 0; i < 5; i++)
		{
			chatServers[i].createSocket(port,fileNames[i]);
			port++;
		}

	}

}