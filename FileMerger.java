package tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileMerger {

	/*
	* @param File array with all the file particles, merged file.
	* 
	* The purpose of this method is to merge file particles into a big file using threads
	* running one after another.
	*/
	public void mergeFiles(File[] files, File mergedFile) throws IOException, InterruptedException {

		OutputStream out = new FileOutputStream(mergedFile);

		for (File file : files) {

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					long progress = 0;

					try {
						int length;
						byte[] buffer = new byte[1024];
						InputStream in = new FileInputStream(file);
						while((length = in.read(buffer)) != -1)
						{
							out.write(buffer, 0, length);
							progress += length;
							System.out.println("Progress: " + 100.0* progress / (double) file.length());
						}

						in.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

			thread.start();
			thread.join();
		}

		out.close();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		new FileMerger().mergeFiles(new File[] { 
				new File("100Mb File (1).txt"),
				new File("100Mb File (2).txt"), 
				new File("100Mb File (3).txt"),
				new File("100Mb File (4).txt"),
				new File("100Mb File (5).txt"),
		}, new File("100Mb File.txt")
				);

		new FileMerger().mergeFiles(new File[] { 
				new File("250Mb File (1).txt"),
				new File("250Mb File (2).txt"), 
				new File("250Mb File (3).txt"),
				new File("250Mb File (4).txt"),
				new File("250Mb File (5).txt"),
		}, new File("250Mb File.txt")
				);

		new FileMerger().mergeFiles(new File[] { 
				new File("500Mb File (1).txt"),
				new File("500Mb File (2).txt"), 
				new File("500Mb File (3).txt"),
				new File("500Mb File (4).txt"),
				new File("500Mb File (5).txt"),
		}, new File("500Mb File.txt")
				);

		new FileMerger().mergeFiles(new File[] { 
				new File("2Gb File (1).txt"),
				new File("2Gb File (2).txt"), 
				new File("2Gb File (3).txt"),
				new File("2Gb File (4).txt"),
				new File("2Gb File (5).txt"),
		}, new File("2Gb File.txt")
				);

		new FileMerger().mergeFiles(new File[] { 
				new File("3Gb File (1).txt"),
				new File("3Gb File (2).txt"), 
				new File("3Gb File (3).txt"),
				new File("3Gb File (4).txt"),
				new File("3Gb File (5).txt"),
		}, new File("3Gb File.txt")
				);
		 
	}
}