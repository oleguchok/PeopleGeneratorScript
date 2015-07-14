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
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(pathToCSV));
			int i = 0;
			while (i < numberOfRecords) {
	 
				line = br.readLine();
			        
				String[] record = line.split(cvsSplitBy);
				
				result[i] = record[0] + " " + getIndexOfCity() + " " + record[1] 
						+ " " +	record[2] + " д." + getHouseNumber() + " " 
						+ getPhoneNumber();
				result[i] = errorGenerator.getErrors(result[i], numberOfErrors);
				
				if (result[i].contains("Пер.")){
					result[i] = result[i].replace("Пер.", "Зав.");
				}
				if (result[i].contains("Ул."))
					result[i] = result[i].replace("Ул.", "Вул.");
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
