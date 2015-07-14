package by.oguchok.pgscript;

import java.util.Random;

public class ErrorGenerator {
	
	private final Random random = new Random();
	
	public String getErrors(String record, int numberOfErrors) {
		
		for(int i = 0, action; i < numberOfErrors; i++){
			int index = random.nextInt(record.length());
			while(record.charAt(index) == ' ' || record.charAt(index) == ',')
				index = random.nextInt(record.length());
			action = random.nextInt(2);
			record = generateError(record, action, index);
		}
		return record;
	}
	
	private String generateError(String element, int action, int index) {

		if (action == 0) {
			return deleteSymbol(element, index);
		}
			return addSymbol(element, index);
	}
	
	
	private String deleteSymbol(String string, int index) {
		
		StringBuilder sb = new StringBuilder(string);
		sb.deleteCharAt(index);
		return sb.toString();
	}
	
	private String addSymbol(String string, int index) {
		
		int symbolPosition = random.nextInt(string.length());
		char symbol = string.charAt(symbolPosition);
		while (symbol == ' ' || symbol == ','){
			symbolPosition = random.nextInt(string.length());
			symbol = string.charAt(symbolPosition);
		}
		string = string.substring(0,index) + symbol + 
				string.substring(index,string.length());
		return string;
	}
}
