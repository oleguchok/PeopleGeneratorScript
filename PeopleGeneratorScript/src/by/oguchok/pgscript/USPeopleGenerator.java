package by.oguchok.pgscript;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;

public class USPeopleGenerator extends PeopleGenerator {

	
	private final String pathToCSV = "/home/oleguchok/git/PeopleGeneratorScript/" + 
			"PeopleGeneratorScript/src/by/oguchok/pgscript/us.csv";
	
	
	@Override
	public String[] getRecords() {
		
		String[] result = new String[numberOfRecords];
		BufferedReader br = null;
		String line = "";	 
		try {	 
			br = new BufferedReader(new FileReader(pathToCSV));
			int i = 0;
			while (i < numberOfRecords) {
	 
				line = br.readLine();
				result[i] = getRecordFromLine(line);
				result[i] = errorGenerator.getErrors(line, numberOfErrors);
				result[i] = result[i].replace(',', ' ');
				i++;
				if (i % 50000 == 0) {
					br.close();
					br = new BufferedReader(new FileReader(pathToCSV));
				}
			}	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		return result;
	}
	
	private String getRecordFromLine(String line){
		
		String[] record = line.split(cvsSplitBy);							
		return record[0] + " " + record[1] + " " + record[2] + " " +
				record[3] + " " + record[4] + " " + record[5] + " " + record[6];
	}

	public USPeopleGenerator(int numberOfRecords, int numberOfErrors) {
		
		super(numberOfRecords,numberOfErrors);
	}
}
