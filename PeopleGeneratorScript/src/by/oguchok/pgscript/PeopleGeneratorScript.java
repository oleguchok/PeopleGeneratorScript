package by.oguchok.pgscript;

import java.util.Arrays;
import java.util.Random;

public class PeopleGeneratorScript {
	
	private static String currentLocale;
	private static int currentNumberOfRecords,currentNumberOfErrors;

	public static void main(String[] args) {
		
		checkInput(args);
		
		PeopleGenerator generator = getGenerator(currentLocale, currentNumberOfRecords,
				currentNumberOfErrors);
		
		String[] records = generator.getRecords();
		shuffleArray(records);
		for(String rec : records)
			System.out.println(rec);
		
	}
	
	public static void shuffleArray(String[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
          int change = i + random.nextInt(n - i);
          swap(a, i, change);
        }
      }

      private static void swap(String[] a, int i, int change) {
        String temp = a[i];
        a[i] = a[change];
        a[change] = temp;
      }
	
	private static boolean correctLocaleInput(String enteredLocale){
		
		String[] localeNames = new String[] { "ru", "us", "by" };
		return (Arrays.asList(localeNames).contains(enteredLocale.toLowerCase()));
	}
	
	private static void initializeFields(String locale, String numberOfRecords,
			String numberOfErrors) {
		
		currentLocale = locale;
		currentNumberOfRecords = Integer.parseInt(numberOfRecords);
		currentNumberOfErrors = Integer.parseInt(numberOfErrors);
	}
	
	private static void checkInput(String[] args) {
		
		try {
			if (args.length != 3)
				throw new Exception("Enter 3 arguments");
			initializeFields(args[0], args[1], args[2]);
			if (!correctLocaleInput(currentLocale))
				throw new Exception("Enter correct locale: RU, EN, BY");			
			if (currentNumberOfRecords < 0 || currentNumberOfRecords > 1000000)
				throw new Exception("Number of records must be from 0 to 1000000");
			if (currentNumberOfErrors < 0)
				throw new Exception("Number of errors must be grater than 0");			
		}
		catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	private static PeopleGenerator getGenerator(String locale, int numberOfRecords,
			int numberOfErrors) {
		
		if (locale.equalsIgnoreCase("us"))
			return new USPeopleGenerator(numberOfRecords, numberOfErrors);
		if (locale.equalsIgnoreCase("ru"))
			return new RUPeopleGenerator(numberOfRecords, numberOfErrors);
		return new BYPeopleGenerator(numberOfRecords, numberOfErrors);
	}
}
