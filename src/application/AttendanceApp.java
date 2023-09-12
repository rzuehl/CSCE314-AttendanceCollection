package application;
import java.util.*;

public class AttendanceApp {
	
	private StudentRoster studentRoster;
	private AttendanceLog attendanceLog;
	
	public StudentRoster getRoster() {
		return studentRoster;
	}
	
	public AttendanceLog getLog() {
		return attendanceLog;
	}
	
	public AttendanceApp() {
		studentRoster = new StudentRoster();
		attendanceLog = new AttendanceLog();
	}
	
	public AttendanceApp(String rosterFile, String swipeFile) {
		studentRoster = new StudentRoster();
		studentRoster.load_roster(rosterFile);
		attendanceLog = new AttendanceLog();
		attendanceLog.load_log(swipeFile);
	}
	
	public Vector<Student> list_students_not_in_class() {
		Vector<Student> missingStudents = new Vector<Student>();
		Vector<Student> students = studentRoster.getList();
		Vector<String> presentStudents = attendanceLog.getPresentStudents();

		//iterate through the student roster
		Iterator<Student> itr = students.iterator();
		while (itr.hasNext()) {
			//currStudent is the current student from the roster being checked
			Student currStudent = itr.next();
			if (presentStudents.indexOf(currStudent.getStudentName()) == -1) {
				//add them to the missingStudents list
				missingStudents.add(currStudent);
			}
		}
		return missingStudents;
	}

	public Vector<Log> list_all_times_checking_in_and_out(String studentName) {
		Vector<Log> checkIn = new Vector<Log>();
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currLog = itr.next();
			if (currLog.getName().equals(studentName)) {
				checkIn.add(currLog);
			}
		}
		return checkIn;
	}
	
	public Vector<Log> list_all_times_checked_in() {
		Vector<Log> checkInList = new Vector<Log>();
		Vector<String> checkedIn = new Vector<String>();
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currLog = itr.next();
			//if the current student is not checked in
			if (checkedIn.indexOf(currLog.getName()) == -1) {
				//add the student's name to the checked in list
				checkedIn.add(currLog.getName());
				//add the log to the list
				checkInList.add(currLog);
			}
		}
		return checkInList;
	}
	
	public Vector<Log> list_students_late_to_class(String timestamp, String date) {
		Vector<Log> lateList = new Vector<Log>();
		Vector<Log> presentList = new Vector<Log>();
		String hourMin;
		String hourMax;
		
		//parse the hour, minute and second values of the timestamp
		String hour = timestamp.substring(0, 2);
		String minute = timestamp.substring(3, 5);
		String second = timestamp.substring(6, 8);
		//an estimate of the start of next class
		Integer hourMinInt = Integer.parseInt(hour) - 1;
		Integer hourMaxInt = Integer.parseInt(hour) + 1;
		
		if (hourMinInt < 10) {
			hourMin = "0" + Integer.toString(hourMinInt);
		}
		else {
			hourMin = Integer.toString(hourMinInt);
		}
		
		if (hourMaxInt < 10) {
			hourMax = "0" + Integer.toString(hourMaxInt);
		}
		else {
			hourMax = Integer.toString(hourMaxInt);
		}
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currLog = itr.next();
			//check if the time is within constraints
			if ((currLog.getHour().compareTo(hourMin) > 0) && (currLog.getHour().compareTo(hourMax) < 0) && (date.equals(currLog.getDate()))) {
				//check that the student is not already counted
				if (presentList.contains(currLog)) {
					continue;
				}
				//check that the student checks in during or before the hour
				//if they are not equal
				if (hour.compareTo(currLog.getHour()) > 0) {
					lateList.add(currLog);
					continue;
				}
				//if the student's minute is equal to that allowed
				else if (minute.compareTo(currLog.getMinute()) == 0) {
					//check if the second is late
					if (second.compareTo(currLog.getSecond()) < 0) {
						lateList.add(currLog);
						continue;
					}
				}
				else if (minute.compareTo(currLog.getMinute()) < 0) {
					lateList.add(currLog);
					continue;
				}
				
				//the students passes
				presentList.add(currLog);
			}
		}
		
		return lateList;
		
	}
	
	public String print_attendance_data_for_student(String studentName) {
		String output = "";
		Vector<Log> studentAttendance = new Vector<Log>();
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		while (itr.hasNext()) {
			Log currLog = itr.next();
			
			if (currLog.getName().equals(studentName)) {
				studentAttendance.add(currLog);
			}
		}
		
		if (studentAttendance.size() == 0) {
			output = "No student of this name in the attendance log";
			return output;
		}
		
		output += studentAttendance.get(0).getName() + " ['" + studentAttendance.get(0).getTime();
		output += ", " + studentAttendance.get(0).getDate() + "'";
		
		Iterator<Log> itr2 = studentAttendance.iterator();
		
		Log prevLog = itr2.next();
		
		while (itr2.hasNext()) {
			Log nextLog = itr2.next();
			if (prevLog.getDate().equals(nextLog.getDate())) {
				continue;
			}
			else {
				output += ", '" + nextLog.getTime() + ", " + nextLog.getDate() + "'";
				prevLog = nextLog;
			}
		}
		output += "]\n";
		return output;
	}
	
	public boolean is_present(String studentName, String studentDate) {
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currStudent = itr.next();
			
			if (currStudent.getName().equals(studentName) && currStudent.getDate().equals(studentDate)) {
				return true;
			}
		}
		return false;
	}
	
	public Vector<Student> list_all_students_checked_in(String date) {
		Vector<Student> presentList = new Vector<Student>();
		Vector<String> presentNames = new Vector<String>();
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currStudent = itr.next();
			if (currStudent.getDate().equals(date)) {
				//check that the student has not already been counted as present
				Student presentStudent = new Student(currStudent.getName());
				if (!presentNames.contains(presentStudent.getStudentName())) {
					presentNames.add(presentStudent.getStudentName());
					presentList.add(presentStudent);
				}
			}
		}
		
		
		return presentList;
	}
	
	public Vector<Student> list_all_students_checked_in_before(String timestamp, String date) {
		Vector<Log> onTime = new Vector<Log>();
		Vector<Student> presentList = new Vector<Student>();
		Vector<String> presentNames = new Vector<String>();
		
		String hour = timestamp.substring(0, 2);
		String minute = timestamp.substring(3, 5);
		String second = timestamp.substring(6, 8);
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currStudent = itr.next();
			if (currStudent.getHour().compareTo(hour) < 0) {
				onTime.add(currStudent);
				continue;
			}
			else if (currStudent.getHour().equals(hour)) {
				if (currStudent.getMinute().compareTo(minute) < 0) {
					onTime.add(currStudent);
					continue;
				}
				else if (currStudent.getMinute().equals(minute)) {
					if (currStudent.getSecond().compareTo(second) < 0) {
						onTime.add(currStudent);
						continue;
					}
				}
			}
		}

		Iterator<Log> itr2 = onTime.iterator();
		
		while (itr2.hasNext()) {
			Log currPresentStudent = itr2.next();
			if (currPresentStudent.getDate().equals(date)) {
				//check that the student has not already been counted as present
				Student presentStudent = new Student(currPresentStudent.getName());
				if (!presentNames.contains(presentStudent.getStudentName())) {
					presentNames.add(presentStudent.getStudentName());
					presentList.add(presentStudent);
				}
			}
		}
		
		return presentList;
	}
	
	public Vector<Student> list_students_attendance_count(int attendanceDays) {
		Vector<Student> presentList = new Vector<Student>();
		HashMap<String, Integer> presentCount = new HashMap<String, Integer>();
		HashMap<String, String> currentDate = new HashMap<String, String>();
		
		Iterator<Student> stuItr = studentRoster.getList().iterator();
		
		//initialize hash map for every student, and 0 attendance
		while (stuItr.hasNext()) {
			Student currStudent = stuItr.next();
			presentCount.put(currStudent.getStudentName(), 0);
			currentDate.put(currStudent.getStudentName(), "");
		}
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currLog = itr.next();
			
			//if the last login date of the student is the same as the current log, continue
			//as that student has already been accounted for
			if (currentDate.get(currLog.getName()).equals(currLog.getDate())) {
				continue;
			}
			//if not, update the current date of that student, and add 1 to their attendance count
			else {
				currentDate.put(currLog.getName(), currLog.getDate());
				presentCount.put(currLog.getName(), presentCount.get(currLog.getName()) + 1);
			}
		}
		
		stuItr = studentRoster.getList().iterator();
		
		while (stuItr.hasNext()) {
			Student currStudent = stuItr.next();
			if (presentCount.get(currStudent.getStudentName()) >= attendanceDays) {
				presentList.add(currStudent);
			}
		}

		return presentList;
	}
	
	public Integer calculate_time(Log log) {
		Integer totalTime = 0;
		totalTime += Integer.parseInt(log.getSecond());
		totalTime += (Integer.parseInt(log.getMinute()) * 60);
		totalTime += (Integer.parseInt(log.getHour()) * 3600);
		return totalTime;
	}
	
	public Student get_first_student_to_enter(String date) {
		Student firstStudent = new Student("");
		
		Integer minTime = -1;
		
		Iterator<Log> itr = attendanceLog.getList().iterator();
		
		while (itr.hasNext()) {
			Log currLog = itr.next();
			
			if (currLog.getDate().equals(date)) {
				if (minTime == -1) {
					firstStudent.setStudentName(currLog.getName());
					minTime = calculate_time(currLog);
				}
				else if (minTime > calculate_time(currLog)) {
					firstStudent.setStudentName(currLog.getName());
					minTime = calculate_time(currLog);
				}
				continue;
			}
		}
		
		return firstStudent;
	}
	
	public String print_query_list(Vector<?> list) {
		String output = "";
		Iterator<?> itr = list.iterator();
		while(itr.hasNext()) {
			output += itr.next() + "\n";
		}
		return output;
	}
	
	public String print_count(Vector<?> list) {
		return "There were " + list.size() + " record(s) for this query";
	}
}
