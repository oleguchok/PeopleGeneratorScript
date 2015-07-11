package by.oguchok.pgscript;

import java.util.Arrays;

public class PeopleGeneratorScript {
	
	private static String currentLocale;
	private static int currentNumberOfRecords,currentNumberOfErrors;

	public static void main(String[] args) {
		
		try {
			if (args.length != 3)
				throw new Exception("Enter 3 arguments");
			initializeFields(args[0], args[1], args[2]);
			if (!correctLocaleInput(currentLocale))
				throw new Exception("Enter correct locale: RU, EN, BY");			
			if (currentNumberOfRecords < 0 || currentNumberOfRecords > 1000000)
				throw new Exception("Number of fields must be from 0 to 1000000");
			
		}
		catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static boolean correctLocaleInput(String enteredLocale){
		
		enteredLocale.toUpperCase();
		String[] localeNames = Locale.localesInString();
		return (Arrays.asList(localeNames).contains(enteredLocale));
	}
	
	private static void initializeFields(String locale, String numberOfRecords,
			String numberOfErrors) {
		
		currentLocale = locale;
		currentNumberOfRecords = Integer.parseInt(numberOfRecords);
		currentNumberOfErrors = Integer.parseInt(numberOfErrors);
	}
}
