package finalproject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPClientFile
{
	private Socket socket = null;
	private DataInputStream inStream = null;
	private DataOutputStream outStream = null;

	public TCPClientFile()
	{

	}

	public void createSocket(String fileName,int port)
	{
		Thread thread = new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					// connect to localHost at given port #
					socket = new Socket("localHost", port);
					System.out.println("Connected");
					// fetch the streams
					inStream = new DataInputStream(socket.getInputStream());
					outStream = new DataOutputStream(socket.getOutputStream());
					receiveFile(fileName);
				} catch (Exception u)
				{
					u.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public void receiveFile(String fileName)
	{
		byte[] data = null;
		// decide the max buffer size in bytes
		// a typical value for a tcp payload is 1000 bytes, this is because of
		// the common MTU of the underlying ethernet of 1500 bytes
		// HOWEVER their is no optimal value for tcp payload, just a best guess i.e.
		// 1000 bytes
		final int MAX_BUFFER = 1000;
		try
		{
			// read the size of the file <- coming from Server
			long fileSize = inStream.readLong();
			int bufferSize = 0;

			// decide the data reading bufferSize
			if (fileSize > MAX_BUFFER)
				bufferSize = MAX_BUFFER;
			else
				bufferSize = (int) fileSize;

			data = new byte[bufferSize];

			// insert the path/name of your target file
			FileOutputStream fileOut = new FileOutputStream(fileName, true);

			// now read the file coming from Server & save it onto disk

			long totalBytesRead = 0;
			while (true)
			{
				// read bufferSize number of bytes from Server
				int readBytes = inStream.read(data, 0, bufferSize);

				byte[] arrayBytes = new byte[readBytes];
				System.arraycopy(data, 0, arrayBytes, 0, readBytes);
				totalBytesRead = totalBytesRead + readBytes;

				if (readBytes > 0)
				{
					// write the data to the file
					fileOut.write(arrayBytes);
					fileOut.flush();
				}

				// stop if fileSize number of bytes are read
				if (totalBytesRead == fileSize)
					break;

				// update fileSize for the last remaining block of data
				if ((fileSize - totalBytesRead) < MAX_BUFFER)
					bufferSize = (int) (fileSize - totalBytesRead);

				// reinitialize the data buffer
				data = new byte[bufferSize];
			}
			System.out.println("File Size is: " + fileSize + ", number of bytes read are: " + totalBytesRead);

			socket.close();
			fileOut.close();
			inStream.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		TCPClientFile[] myChatClient = new TCPClientFile[] {new TCPClientFile(),new TCPClientFile(),new TCPClientFile(),new TCPClientFile(),new TCPClientFile()};
		

		String[] fileNames = { "file1/newp1", "file1/newp2", "file1/newp3", "file1/newp4", "file1/newp5" };
		Thread thread = new Thread(new Runnable() {
			int port = 3334;
			public void run() {
		for (int i = 0; i < 5; i++)

		{
			myChatClient[i].createSocket(fileNames[i], port);
			port++;

		}}});
		thread.start();
		thread.join();
		
		FileMerger f = new FileMerger(new File[] { new File("file1/newp1"), new File("file1/newp2"),
				new File("file1/newp3"), new File("file1/newp4"), new File("file1/newp5"), },
				new File("file1/100Mb File"));
		
		
		f.start();
		f.join();
	}

}
