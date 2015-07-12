package by.oguchok.pgscript;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.util.Random;
import java.lang.StringBuilder;

public class ENPeopleGenerator implements PeopleGenerator {

	private int numberOfRecords, numberOfErrors;
	private final String pathToCSV = "/home/oleguchok/git/PeopleGeneratorScript/" + 
			"PeopleGeneratorScript/src/by/oguchok/pgscript/FakeNameGenerator.com_367b2554.csv";
	
	private final Random random = new Random();
	
	@Override
	public String[] getRecords() {
		
		String[] result = new String[numberOfRecords];
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(pathToCSV));
			int i = 0;
			while (i < numberOfRecords) {
	 
				line = br.readLine();
			        
				String[] record = line.split(cvsSplitBy);
				
				int j = 0, k = 0;
				while (j < numberOfErrors) {
					
					record[k] = generateError(record[k]);
					k++;
					if (k == 1){
						k = 2;
					}
					if (k == 7){
						k = 0;
					}
					j++;
				}
				
				result[i++] = record[0] + " " + record[1] + " " + record[2] + " " +
						record[5] + " " + record[4] + " " + record[3] + " " + record[6];
				
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

	public ENPeopleGenerator(int numberOfRecords, int numberOfErrors) {
		
		this.numberOfRecords = numberOfRecords;
		this.numberOfErrors = numberOfErrors;
	}
	
	private String generateError(String element) {
		
		int action = random.nextInt(3);
		if (action == 0) {
			return deleteSymbol(element);
		}
		if (action == 1) {
			return addSymbol(element);
		}
		return exchangeSymbols(element);
	}
	
	private String deleteSymbol(String string) {
		
		StringBuilder sb = new StringBuilder(string);
		int index = random.nextInt(string.length() - 1);
		sb.deleteCharAt(index + 1);
		return sb.toString();
	}
	
	private String addSymbol(String string) {
		
		int index = random.nextInt(string.length() - 1);
		StringBuilder sb = new StringBuilder(string);
		char symbol = string.charAt(index + 1);
		sb.append(symbol);
		return sb.toString();
	}
	
	private String exchangeSymbols(String string) {
		
		if (string.length() == 2){
			return string;
		}
		else {
			int firstIndex = random.nextInt(string.length()-1) + 1,
					secondIndex = firstIndex;
			while(secondIndex == firstIndex){
				secondIndex = random.nextInt(string.length() - 1) + 1;
			}
			
			char[] symbols = string.toCharArray();
			char temp = symbols[firstIndex];
			symbols[firstIndex] = symbols[secondIndex];
			symbols[secondIndex] = temp;
			
			return new String(symbols);
		}
	}
}
