package backupVisitors.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import backupVisitors.util.FileProcessor;
import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;
import backupVisitors.visitor.FullTimeStatusVisitorImpl;
import backupVisitors.visitor.IdenticalRecordsVisitorImpl;
import backupVisitors.visitor.SortedRecordsVisitorImpl;
import backupVisitors.visitor.StatsVisitorImpl;

public class Driver {

	static String outputString;
	static int i;
	static int ind;
	static String[] tempArr = new String[100];
	static String[] delArr = new String[100];
	static String fw[] = new String[3];
	static int bnumber;
	static String course;
	static String delString;
    /***
     * This is the main method that drives the program
     * @param args Input and delete files along with the names of output files is provided with command line arguments.
     * return none
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length == 5 || !args[0].equals("${arg0}")) {

			try {
				FileProcessor fp = new FileProcessor(args[0]);
				FileProcessor fileProcessor = new FileProcessor(args[0]);
				FileProcessor delFile = new FileProcessor(args[1]);
				FileProcessor outFile2 = new FileProcessor(args[2]);
				FileProcessor outFile3 = new FileProcessor(args[3]);
				FileProcessor outFile4 = new FileProcessor(args[4]);
				TreeBuilder myBST = new TreeBuilder();
				FullTimeStatusVisitorImpl visitor1 = new FullTimeStatusVisitorImpl();
				StatsVisitorImpl visitor2 = new StatsVisitorImpl();
				SortedRecordsVisitorImpl visitor3 = new SortedRecordsVisitorImpl();
				IdenticalRecordsVisitorImpl visitor4 = new IdenticalRecordsVisitorImpl();
				Results res2 = new Results(outFile2);
				Results res3 = new Results(outFile3);
				Results res4 = new Results(outFile4);

				if (!fp.isEmpty()) {

					outputString = fileProcessor.readLine();
					delString = delFile.readLine();

					tempArr = outputString.split("\n");
					for (i = 0; i < fileProcessor.line_count; i++) {
						tempArr[i] = tempArr[i].trim();
						fw = tempArr[i].split(":");
						bnumber = Integer.parseInt(fw[0]);
						course = fw[1];
						if (course.equals("A") || course.equals("B")
								|| course.equals("C") || course.equals("D")
								|| course.equals("E") || course.equals("F")
								|| course.equals("G") || course.equals("H")
								|| course.equals("I") || course.equals("J")
								|| course.equals("K"))
							myBST.insert(bnumber, course);
					}
					// myBST.accept(visitor1);
					// for deletion
					delArr = delString.split("\n");
					for (i = 0; i < delFile.line_count; i++) {
						delArr[i] = delArr[i].trim();
						fw = delArr[i].split(":");
						bnumber = Integer.parseInt(fw[0]);
						course = fw[1];
						if (myBST.getRoot() != null)
							myBST.delete(bnumber, course);

					}
					myBST.accept(visitor1);
					if (myBST.getRoot() != null)
						myBST.accept(visitor2, res2);
					if (myBST.getRoot() != null)
						myBST.accept(visitor3, res3);
					if (myBST.getRoot() != null)
						myBST.accept(visitor4, res4);
				}
			} catch (IllegalArgumentException ex) {
				System.err.println("Invalid number of arguments.");
				ex.printStackTrace();
				System.exit(1);
			} catch (FileNotFoundException ex) {
				System.err.println("Input file not Found.");
				ex.printStackTrace();
				System.exit(1);
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.err.println("Invalid debug value");
				ex.printStackTrace();
				System.exit(1);
			} catch (Exception e) {
				System.err.println("Invalid input");
				e.printStackTrace();
			}
			finally {

			}
		} else {
			System.out.println("Invalid number of arguments");
		}

	}
}
