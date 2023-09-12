package application;
import java.io.*;
import java.util.*;

public class StudentRoster {
	
	private Vector<Student> studentList = new Vector<Student>();
	private int studentCounter;
	
	public boolean load_roster(String filename) {
		studentList.clear();
		Scanner infile = null;
		try { infile = new Scanner(new File(filename));}
		catch (FileNotFoundException e) {
			System.out.println("Roster file not found");
			return false;
		}
		studentCounter = 0;
		while (infile.hasNextLine()) {
			studentCounter++;
			Student line = new Student(infile.nextLine());
			studentList.add(line);
		}
		return true;
	}
	
	public String print_collection() {
		String output = "";
		Iterator<Student> itr = studentList.iterator();
		while (itr.hasNext()) {
			Student temp = itr.next();
			output += temp.getStudentName() + "\n";
		}
		return output;
	}
	
	public String print_count() { return Integer.toString(studentCounter);}
	
	public Vector<Student> getList() {
		return studentList;
	}
	
	public void setList(Vector<Student> List) {
		studentList = List;
	}
	
}
