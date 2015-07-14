package by.oguchok.pgscript;

import java.util.Random;

public abstract class PeopleGenerator {
	
	protected int numberOfRecords, numberOfErrors;
	protected final Random random = new Random();
	protected final ErrorGenerator errorGenerator = new ErrorGenerator();
	protected final String cvsSplitBy = ",";

	public abstract String[] getRecords();
	
	public PeopleGenerator(int numberOfRecords, int numberOfErrors) {
		this.numberOfRecords = numberOfRecords;
		this.numberOfErrors = numberOfErrors;
	}
	
	protected void isItEndOfColumn(){
		
	}
}
