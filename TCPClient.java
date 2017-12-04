package tcp;

import java.io.*;

import java.net.Socket;

import java.net.SocketException;

import java.net.UnknownHostException;


public class TCPClient

{

private Socket socket = null;

private InputStream inStream = null;

private OutputStream outStream = null;


public TCPClient()

{


}


public void createSocket(int port,String fileName)

{

Thread thread = new Thread(new Runnable()

{

public void run()

{

try

{

socket = new Socket("localHost", port);

// socket.

System.out.println("Connected");

inStream = socket.getInputStream();

outStream = socket.getOutputStream();

createReadThread(fileName);

//createWriteThread();

} catch (UnknownHostException u)

{

u.printStackTrace();

} catch (IOException io)

{

io.printStackTrace();

}

}

});

thread.start();

}


public void createReadThread(String fileName)

{

Thread readThread = new Thread()

{

public void run()

{

while (socket.isConnected())

{

try

{

byte[] readBuffer = new byte[200];
int num = inStream.read(readBuffer);



if (num > 0)

{

byte[] arrayBytes = new byte[num];

System.arraycopy(readBuffer, 0, arrayBytes, 0, num);

FileOutputStream output = new FileOutputStream(new File(fileName));

output.write(arrayBytes);
output.close();
// String recvedMessage = new String(arrayBytes, "UTF-8");

// System.out.println("Received message :" + recvedMessage);

} /*

* else { ? // notify(); ? }

*/

;

// System.arraycopy();

} catch (SocketException se)

{

System.exit(0);

} catch (IOException i)

{

i.printStackTrace();

}

}

}

};

readThread.setPriority(Thread.MAX_PRIORITY);

readThread.start();

}


public void createWriteThread()

{

Thread writeThread = new Thread()

{

public void run()

{

while (socket.isConnected())

{

try

{

BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

// sleep(100);

String typedMessage = inputReader.readLine();

if (typedMessage != null && typedMessage.length() > 0)

{

synchronized (socket)

{

outStream.write(typedMessage.getBytes());


}

}

//System.arraycopy();

} catch (IOException i)

{

i.printStackTrace();

} 

}

}

};

writeThread.setPriority(Thread.MAX_PRIORITY);

writeThread.start();

}


public static void main(String[] args) throws Exception

{

TCPClient[] myChatClient = new TCPClient[] {new TCPClient(),new TCPClient(),new TCPClient(),new TCPClient(),new TCPClient()};

int port = 3334;

String[] fileNames = {"file1/newp1","file1/newp2","file1/newp3",
		"file1/newp4","file1/newp5"};

for (int i = 0; i < 5; i++)

{

myChatClient[i].createSocket(port,fileNames[i]);

port++;

}

}

}