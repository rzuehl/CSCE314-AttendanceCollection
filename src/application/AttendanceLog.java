package application;
import java.io.*;
import java.util.*;

public class AttendanceLog {
	
	private Vector<Log> logList = new Vector<Log>();
	private Vector<String> presentList = new Vector<String>();
	private int attendanceCounter;
	
	public boolean load_log(String filename) {
		logList.clear();
		Scanner infile = null;
		try { infile = new Scanner(new File(filename));}
		catch (FileNotFoundException e) {
			System.out.println("Log file not found");
			return false;
		}
		
		while (infile.hasNextLine()) {
			attendanceCounter++;
			//set entire List
			Log line = new Log(infile.nextLine());
			logList.add(line);
			//set the students who are present
			presentList.add(line.getName());
		}
		return true;
	}
	
	public String print_collection() {
		String output = "";
		Iterator<Log> itr = logList.iterator();
		while (itr.hasNext()) {
			Log temp = itr.next();
			output += temp.getEntry() + "\n";
		}
		return output;
	}
	
	public String print_count() {
		return Integer.toString(attendanceCounter);
	}
	
	//getters
	public Vector<Log> getList() {
		return logList;
	}
	public Vector<String> getPresentStudents() {
		return presentList;
	}
	//setters
	public void setList(Vector<Log> list) {
		logList = list;
	}
	//compareTo
	//public int compareTo(AttendanceLog other) {	}
	//equals
	//public boolean equals(AttendanceLog other) {}
	//toString
	//public String toString() {}
}
