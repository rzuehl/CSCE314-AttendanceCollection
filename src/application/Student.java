package application;

public class Student {
	private String studentName;
	
	public Student(String name) {
		studentName = name;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String name) {
		studentName = name;
	}
	
	public int compareTo(Student other) {
		return studentName.compareTo(other.getStudentName());
	}
	//equals
	public boolean equals(Student other) {return studentName.equals(other.getStudentName());}
	//toString
	public String toString() {return studentName;}
}
