package by.oguchok.pgscript;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BYPeopleGenerator extends PeopleGenerator {

	private final String pathToCSV = "/home/oleguchok/git/PeopleGeneratorScript/" + 
			"PeopleGeneratorScript/src/by/oguchok/pgscript/by.csv";
	
	public BYPeopleGenerator(int numberOfRecords, int numberOfErrors) {

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
				result[i] = renameRussianWords(result[i]);
				i++;
				if (i % 1082 == 0) {
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
		
		return record[0] + " " + getIndexOfCity() + " " + record[1] 
				+ " " +	record[2] + " д." + getHouseNumber() + " " 
				+ getPhoneNumber();
	}
	
	private String renameSomeLetters(String string) {
		
		if(string.contains("и"))
			string = string.replace("и", "і");
		if(string.contains("щ"))
			string = string.replace("щ", "шч");
		return string;
	}
	
	private String renameRussianWords(String string) {
		
		if (string.contains("Пер."))
			string = string.replace("Пер.", "Зав.");
		if (string.contains("Ул."))
			string = string.replace("Ул.", "Вул.");
		return renameSomeLetters(string);
	}
	
	private String getIndexOfCity(){
		
		int index = random.nextInt(3000)*10 + 210000;
		return String.valueOf(index);
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
}
