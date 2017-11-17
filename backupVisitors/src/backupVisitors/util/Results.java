package backupVisitors.util;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	FileProcessor f;
	public String[] store = new String[100];
	public int count = 0;

	public Results(FileProcessor outFile) {
		f = outFile;
	}

	public void writeToStdout(String s) {
		System.out.println(s);

	}

	public void writeToFile(String s) {
		try {
			f.writeFile(s);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void storeNewResults(String str) {
		// System.out.println(str);
		store[count] = str;
		count++;
	}

}
