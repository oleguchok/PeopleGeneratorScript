package by.oguchok.pgscript;

public enum Locale {

	BY,
	RU,
	US;
	
	public static String[] localesInString() {
		
		Locale[] states = values();
		String[] localesNames = new String[states.length];
		for (int i = 0; i < states.length; i++){
			localesNames[i] = states[i].name();
		}
			
		return localesNames;
	}
}
