package application;
import java.util.StringTokenizer;

public class Log {
	//data members
	private String logEntry;
	private String name;
	private String time;
	private String date;
	
	public Log(String log) {
		logEntry = log;
		
		StringTokenizer st = new StringTokenizer(log);
		String lastName = st.nextToken();
		String firstName = st.nextToken();
		String nameToSet = lastName + " " + firstName; nameToSet = nameToSet.substring(0, nameToSet.length() - 1);
		name = nameToSet;
		
		String logTime = st.nextToken();
		time = logTime.substring(0, logTime.length() - 1);
		
		date = st.nextToken();	
	}
	
	//getters
	public String getEntry() {
		return logEntry;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getHour() {
		return time.substring(0, 2);
	}
	
	public String getMinute() {
		return time.substring(3, 5);
	}
	
	public String getSecond() {
		return time.substring(6, 8);
	}
	
	public String getName() {
		StringTokenizer st = new StringTokenizer(logEntry);
		String lastName = st.nextToken();
		String firstName = st.nextToken();
		String nameToSet = lastName + " " + firstName; nameToSet = nameToSet.substring(0, nameToSet.length() - 1);
		this.setName(nameToSet);
		return name;
	}
	
	
	public void setName(String nameSet) {
		name = nameSet;
	}
	
	//setters
	public void setEntry(String entry) {
		logEntry = entry;
	}
	//compareTo
	public int compareTo(Log other) {
		return this.getEntry().compareTo(other.getEntry());
	}
	//equals
	public boolean equals(Log other) {return this.getEntry().equals(other.getEntry());}
	//toString
	public String toString() {return this.logEntry;}
}
