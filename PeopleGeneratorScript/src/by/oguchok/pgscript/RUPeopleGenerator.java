package by.oguchok.pgscript;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RUPeopleGenerator extends PeopleGenerator {

	private final String pathToCSV = "/home/oleguchok/git/PeopleGeneratorScript/" + 
			"PeopleGeneratorScript/src/by/oguchok/pgscript/ru.csv";
	
	private final Random random = new Random();
	
	public RUPeopleGenerator(int numberOfRecords, int numberOfErrors) {
		
		super(numberOfRecords, numberOfErrors);
	}
	
	
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
				result[i] = errorGenerator.getErrors(result[i], numberOfErrors);
				i++;								
				if (i % 3807 == 0) {
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
		
		return record[0] + " " + getIndexOfCity() + " " + record[2] + " " +
				record[3] + " " + record[4] + " д." + getHouseNumber() + " " 
				+ getPhoneNumber();
	}
	
	private String getHouseNumber() {
		
		int house = random.nextInt(250) + 1;
		return String.valueOf(house);
	}
	
	private String getPhoneNumber() {
		
		int num = random.nextInt(899)+101;
		String phone = "+7(495)" + String.valueOf(num);
		num = random.nextInt(89)+11;
		phone += "-"+String.valueOf(num);
		num = random.nextInt(89)+11;
		phone += "-"+String.valueOf(num);
		return phone;
	}
	
	private String getIndexOfCity(){
		
		int index = random.nextInt(999999)+1;
		return String.valueOf(index);
	}
}
