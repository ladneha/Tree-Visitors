package backupVisitors.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
	String filename;
	public int line_count;
	String everything;
	int cnt;

	public FileProcessor() {
		line_count = 0;
	}

	public FileProcessor(String inputFile) {
		filename = inputFile;
		line_count = 0;
	}

	public boolean isEmpty() throws FileNotFoundException {
		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(fr);
			while ((reader.readLine()) != null) {
				cnt++;
			}
			if (cnt == 0) {
				return true;
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Input file not Found.");
			ex.printStackTrace();
			System.exit(1);
		} catch (IOException io) {
			io.printStackTrace();
			System.err.println("I/O Exception: " + io);
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index Out of bound \n");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String readLine() {
		try {
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder sb = new StringBuilder();
			String line = bufferedReader.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufferedReader.readLine();
				line_count++;
			}

			everything = sb.toString();
			// System.out.println(everything);
			// System.out.println(line_count);

		} catch (FileNotFoundException ex) {
			System.err.println("Input file not Found.");
			ex.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return everything;
	}

	public void writeFile(String out) throws Exception {
		FileWriter writer = null;
		try {
			File file = new File(filename);
			file.createNewFile();
			writer = new FileWriter(file.getAbsoluteFile(), true);
			writer.write("\n");
			writer.write(out);
			writer.close();

		} catch (IOException io) {
			io.printStackTrace();
			System.err.println("I/O Exception: " + io);
			System.exit(1);
		} catch (NullPointerException nullpointerException) {
			System.err.println("Input file may be empty: "
					+ nullpointerException);
			System.exit(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index Out of bound \n");
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public int getLength() {
		// TODO Auto-generated method stub
		return line_count++;
	}

}
