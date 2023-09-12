package application;
import java.util.*;

public class Driver {
	
	public static boolean loadATP(String log, String roster, AttendanceApp atp, Scanner sc) {
		System.out.println("Please Enter the roster you wish to load: ");
		roster = sc.next();
		System.out.println("Please Enter the log you wish to load: ");
		log = sc.next();
		if (!atp.getRoster().load_roster(roster)) {
			return false;
		}
		if (!atp.getLog().load_log(log)) {
			return false;
		}
		System.out.println();
		return true;
	}
	
	//AttendanceApp atp = new AttendanceApp("rosters.txt", "dataAllShow1stClass.txt");
	public static void main(String[] args) {
		String studentName;
		String timestamp;
		String date;
		String roster = "";
		String log = "";
		AttendanceLog atl = new AttendanceLog();
		StudentRoster str = new StudentRoster();
		AttendanceApp atp = new AttendanceApp();
		
		Scanner sc = new Scanner(System.in);
		
		String choice = "";
		do {
			System.out.println("Please choose an option from the menu listed below: ");
			System.out.println("0 - Exit program");
			System.out.println("A - load_log()");
			System.out.println("B - print_collection()");
			System.out.println("C - print_count()");
			System.out.println("D - load_log()");
			System.out.println("E - print_collection()");
			System.out.println("F - print_count()");
			System.out.println("G - list_students_not_in_class()");
			System.out.println("H - list_all_times_checking_in_and_out()");
			System.out.println("I - list_all_times_checked_in()");
			System.out.println("J - list_students_late_to_class()");
			System.out.println("K - get_first_student_to_enter()");
			System.out.println("L - print_attendance_data_for_student()");
			System.out.println("M - is_present()");
			System.out.println("N - list_all_students_checked_in()");
			System.out.println("O - list_all_students_checked_in_before()");
			System.out.println("P - list_students_attendance_count()");
			System.out.println("Q - get_first_student_to_enter()");
			System.out.println("R - print_query_list()");
			System.out.println("S - print_count()");
			
			choice = sc.next();
			
			switch(choice) {
			
			case "0":
				break;
			
			case "A":
				System.out.println("Please enter the file you wish to load.");
				log = sc.next();
				atl.load_log(log);
				break;
				
			case "B":
				System.out.println("Printing the collection: ");
				atl.print_collection();	
				break;
				
			case "C":
				System.out.println("Printing the AttendanceLog count: ");
				atl.print_count();
				break;
				
			case "D":
				System.out.println("Please enter the file you wish to load.");
				roster = sc.next();
				str.load_roster(roster);
				break;
				
			case "E":
				System.out.println("Printing the collection: ");
				str.print_collection();	
				break;
				
			case "F":
				System.out.println("Printing the StudentRoster count: ");
				str.print_count();
				break;
				
			case "G":
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("****** Students missing in class *************");
				atp.print_query_list(atp.list_students_not_in_class());
				break;
				
			case "H":
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				System.out.println("Please enter the students name:");
				sc.nextLine();
				studentName = sc.nextLine();
				
				System.out.println("****** List all swipe in and out for a student *******");
				atp.print_query_list(atp.list_all_times_checking_in_and_out(studentName));
				break;
				
			case "I":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("****** Check in times for all students who attended***");
				
				atp.print_query_list(atp.list_all_times_checked_in());
				
				break;
				
			case "J":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the timestamp:");
				sc.nextLine();
				timestamp = sc.nextLine();
				System.out.println("Please enter the date:");
				date = sc.nextLine();
				
				System.out.println("****** Students that arrived late ********************");
				
				atp.print_query_list(atp.list_students_late_to_class(timestamp, date));
				
				break;
				
			case "K":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the date:");
				sc.nextLine();
				date = sc.nextLine();
				
				System.out.println("**** First student to enter on " + date + " ****");
				
				System.out.println(atp.get_first_student_to_enter(date));
				break;
				
			case "L":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the students name:");
				sc.nextLine();
				studentName = sc.nextLine();
				
				atp.print_attendance_data_for_student(studentName);
				
				break;
				
			case "M":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the students name:");
				sc.nextLine();
				studentName = sc.nextLine();
				System.out.println("Please enter the date:");
				date = sc.nextLine();
				
				System.out.println("**** Looking to see if Student was present on date ****");
				
				System.out.println(atp.is_present(studentName, date));
				
				break;
				
			case "N":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the date:");
				sc.nextLine();
				date = sc.nextLine();
				
				System.out.println("**** Students present on this date ****");
				atp.print_query_list(atp.list_all_students_checked_in(date));
				
				break;
				
			case "O":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the date:");
				sc.nextLine();
				date = sc.nextLine();
				System.out.println("Please enter the timestamp");
				timestamp = sc.nextLine();
				
				System.out.println("**** Those present on date & before a time assigned ****");
				
				atp.print_query_list(atp.list_all_students_checked_in_before(timestamp, date));
				
				break;
				
			case "P":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the amount of days:");
				sc.nextLine();
				Integer days = sc.nextInt();
				
				System.out.println("**** Those who attended " + days + " days ****");
				
				atp.print_query_list(atp.list_students_attendance_count(days));
				atp.print_count(atp.list_students_attendance_count(days));
				
				break;
				
			case "Q":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("Please enter the date:");
				sc.nextLine();
				date = sc.nextLine();
				
				System.out.println("**** First student to enter on " + date + " ****");
				
				System.out.println(atp.get_first_student_to_enter(date));
				
				break;
				
			case "R":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("**** Printing example query list_students_not_in_class ****");
				
				System.out.println("****** Students missing in class *************");
				atp.print_query_list(atp.list_students_not_in_class());
				
				break;
				
			case "S":
				
				if (!loadATP(log, roster, atp, sc)) {
					continue;
				}
				
				System.out.println("**** Printing example query count list_students_not_in_class ****");
				
				System.out.println("****** Students missing in class *************");
				atp.print_count(atp.list_students_not_in_class());
				
				break;
				
			default:
				System.out.println("Please Enter a correct option.");
			}

			
			
				
			if (!choice.equals("0")) {
				System.out.println("Enter 0 to exit, or 1 to continue");
				choice = sc.next();
			}
			
		}
		while(!choice.equals("0"));
		
		System.out.println("Exiting Program...");
		sc.close();
	}
	
}

