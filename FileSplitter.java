package tcp;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSplitter {

	private OutputStream output;

	/*
	* 
	* This function serves to split the files into 5 equal parts
	*/
	public void splitFiles() throws IOException
	{
		File file100Mb = new File("100Mb File.txt");
		InputStream file100MbInput = new BufferedInputStream(new FileInputStream(file100Mb));
		File file250Mb = new File("250Mb File.txt");
		InputStream file250MbInput = new BufferedInputStream(new FileInputStream(file250Mb));
		File file500Mb = new File("500Mb File.txt");
		InputStream file500MbInput = new BufferedInputStream(new FileInputStream(file500Mb));
		File file1Gb = new File("1Gb File.txt");
		InputStream file1GbInput = new BufferedInputStream(new FileInputStream(file1Gb));
		File file2Gb = new File("2Gb File.txt");
		InputStream file2GbInput = new BufferedInputStream(new FileInputStream(file2Gb));
		File file3Gb = new File("3Gb File.txt");
		InputStream file3GbInput = new BufferedInputStream(new FileInputStream(file3Gb));

		long fileSize100Mb = file100Mb.length();
		long fileSize250Mb = file250Mb.length();
		long fileSize500Mb = file500Mb.length();
		long fileSize1Gb = file1Gb.length();
		long fileSize2Gb = file2Gb.length();
		long fileSize3Gb = file3Gb.length();
		long length = 0;
		int input;
		int fileCounter = 1;

		long splitLength = fileSize100Mb / 5;
		input = file100MbInput.read();

		while(input != -1 && fileCounter <= 5)
		{
			file100Mb = new File("100Mb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file100Mb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file100MbInput.read();
			}

			length = 0;
			fileCounter++;
		}
		file100MbInput.close();

		splitLength = fileSize250Mb / 5;
		input = file250MbInput.read();
		length = 0;
		fileCounter = 1;

		while(input != - 1 && fileCounter <= 5)
		{
			file250Mb = new File("250Mb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file250Mb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file250MbInput.read();
			}
			length = 0;
			fileCounter++;
		}
		file250MbInput.close();

		splitLength = fileSize500Mb / 5;
		input = file500MbInput.read();
		length = 0;
		fileCounter = 1;

		while(input != -1 && fileCounter <= 5)
		{
			file500Mb = new File("500Mb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file500Mb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file500MbInput.read();
			}
			length = 0;

			fileCounter++;
		}
		file500MbInput.close();

		splitLength = fileSize1Gb / 5;
		input = file1GbInput.read();
		length = 0;
		fileCounter = 1;

		while(input != -1 && fileCounter <= 5)
		{
			file1Gb = new File("1Gb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file1Gb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file1GbInput.read();
			}
			length = 0;
			fileCounter++;
		}
		file1GbInput.close();

		splitLength = fileSize2Gb / 5;
		input = file2GbInput.read();
		length = 0;
		fileCounter = 1;

		while(input != -1 && fileCounter <= 5)
		{
			file2Gb = new File("2Gb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file2Gb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file2GbInput.read();
			}
			length = 0;

			fileCounter++;
		}
		file2GbInput.close();

		splitLength = fileSize3Gb / 5;
		input = file3GbInput.read();
		length = 0;
		fileCounter = 1;

		while(input != -1 && fileCounter <= 5)
		{
			file3Gb = new File("3Gb File (" + fileCounter + ").txt");

			output = new BufferedOutputStream(new FileOutputStream(file3Gb));

			while(input != -1 && length < splitLength)
			{
				output.write(input);
				length++;

				input = file3GbInput.read();
			}
			length = 0;
			fileCounter++;
		}
		file3GbInput.close();
	}

	/*
	* 
	* This function serves to fill in the splited files
	*/
	public void write() throws IOException
	{
		File file100Mb = new File("100Mb File.txt");
		InputStream file100MbInput = new BufferedInputStream(new FileInputStream(file100Mb));
		File file250Mb = new File("250Mb File.txt");
		InputStream file250MbInput = new BufferedInputStream(new FileInputStream(file250Mb));
		File file500Mb = new File("500Mb File.txt");
		InputStream file500MbInput = new BufferedInputStream(new FileInputStream(file500Mb));
		File file1Gb = new File("1Gb File.txt");
		InputStream file1GbInput = new BufferedInputStream(new FileInputStream(file1Gb));
		File file2Gb = new File("2Gb File.txt");
		InputStream file2GbInput = new BufferedInputStream(new FileInputStream(file2Gb));
		File file3Gb = new File("3Gb File.txt");
		InputStream file3GbInput = new BufferedInputStream(new FileInputStream(file3Gb));

		int fileCounter = 1;
		int input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file100Mb));

			file100Mb = new File(fileCounter + ".txt");

			if(file100Mb.exists())
			{
				input = file100MbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file100MbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file100MbInput.close();

		fileCounter = 1;
		input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file250Mb));

			file250Mb = new File(fileCounter + ".txt");

			if(file250Mb.exists())
			{
				input = file250MbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file250MbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file250MbInput.close();
		fileCounter = 1;
		input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file500Mb));

			file500Mb = new File(fileCounter + ".txt");

			if(file500Mb.exists())
			{
				input = file500MbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file500MbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file500MbInput.close();
		fileCounter = 1;
		input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file1Gb));

			file1Gb = new File(fileCounter + ".txt");

			if(file1Gb.exists())
			{
				input = file1GbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file1GbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file1GbInput.close();
		fileCounter = 1;
		input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file2Gb));

			file2Gb = new File(fileCounter + ".txt");

			if(file2Gb.exists())
			{
				input = file2GbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file2GbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file2GbInput.close();
		fileCounter = 1;
		input = 0;

		while(true && fileCounter <= 5)
		{
			output = new BufferedOutputStream(new FileOutputStream(file3Gb));

			file3Gb = new File(fileCounter + ".txt");

			if(file3Gb.exists())
			{
				input = file3GbInput.read();

				while(input != -1)
				{
					output.write(input);
					input = file3GbInput.read();
				}
				fileCounter++;
			}
			else
			{
				break;
			}
		}
		file3GbInput.close();
	}
}
