package application;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.*;
import java.io.*;

public class SampleController {
	
	@FXML Button menu_0;
	@FXML Button menu_A;
	@FXML Button menu_B;
	@FXML Button menu_C;
	@FXML Button menu_D;
	@FXML Button menu_E;
	@FXML Button menu_F;
	@FXML Button menu_G;
	@FXML Button menu_H;
	@FXML Button menu_I;
	@FXML Button menu_J;
	@FXML Button menu_K;
	@FXML Button menu_L;
	@FXML Button menu_M;
	@FXML Button menu_N;
	@FXML Button menu_O;
	@FXML Button menu_P;
	@FXML Button menu_Q;
	@FXML Button menu_R;
	@FXML Button menu_S;
	@FXML Button swipeFile;
	@FXML Button rosterFile;
	@FXML TextArea textOut;
	@FXML Label nameInputLabel;
	@FXML TextField nameInput;
	@FXML Label timeInputLabel;
	@FXML Label dateInputLabel;
	@FXML Label intInputLabel;
	@FXML AnchorPane anchor;
	@FXML Label idLabel;
	
	String studentName;
	String out;
	String timestamp;
	String date;
	String roster;
	String log;
	AttendanceLog atl = new AttendanceLog();
	StudentRoster str = new StudentRoster();
	AttendanceApp atp = new AttendanceApp();
	Integer daysAttended;
	boolean swipeLoaded = false;
	boolean rosterLoaded = false;
	
	String style = "-fx-background-color: #500000; -fx-text-fill: white;\r\n";

	public void initialize() {
		updateButtons();
		textOut.setEditable(false);
		nameInput.setVisible(false);
		nameInputLabel.setVisible(false);
		nameInputLabel.setStyle("-fx-background-color:White");
		timeInputLabel.setVisible(false);
		timeInputLabel.setStyle("-fx-background-color:White");
		dateInputLabel.setVisible(false);
		dateInputLabel.setStyle("-fx-background-color:White");
		intInputLabel.setVisible(false);
		intInputLabel.setStyle("-fx-background-color:White");
		menu_0.setStyle(style);menu_A.setStyle(style);menu_B.setStyle(style);menu_C.setStyle(style);menu_D.setStyle(style);menu_E.setStyle(style);menu_F.setStyle(style);menu_G.setStyle(style);
		menu_H.setStyle(style);menu_I.setStyle(style);menu_J.setStyle(style);menu_K.setStyle(style);menu_L.setStyle(style);menu_M.setStyle(style);menu_N.setStyle(style);menu_O.setStyle(style);
		menu_P.setStyle(style);menu_Q.setStyle(style);menu_R.setStyle(style);menu_S.setStyle(style);swipeFile.setStyle(style);rosterFile.setStyle(style);
		anchor.setStyle("-fx-background-color: #262626");
		idLabel.setStyle("-fx-text-fill: white;");
		idLabel.setText("Ryan Zuehl\n231007308\nrzuehl@tamu.edu");
	}
	
	public void openName(){
		textOut.clear();
		nameInputLabel.setVisible(true);
		nameInput.setText("");
		nameInput.setVisible(true);
	}
	
	public void closeName(ActionEvent e) {
		studentName = nameInput.getText();
		nameInput.setVisible(false);
		nameInputLabel.setVisible(false);
	}
	
	public void openDate() {
		textOut.clear();
		dateInputLabel.setVisible(true);
		nameInput.setText("");
		nameInput.setVisible(true);
	}
	
	public void closeDate(ActionEvent e) {
		date = nameInput.getText();
		nameInput.setVisible(false);
		dateInputLabel.setVisible(false);
	}
	
	public void openTimestamp() {
		textOut.clear();
		timeInputLabel.setVisible(true);
		nameInput.setText("");
		nameInput.setVisible(true);
	}
	
	public void closeTimestamp(ActionEvent e) {
		timestamp = nameInput.getText();
		nameInput.setVisible(false);
		timeInputLabel.setVisible(false);
	}
	
	public void openInt() {
		textOut.clear();
		intInputLabel.setVisible(true);
		nameInput.setText("");
		nameInput.setVisible(true);
	}
	
	public void closeInt(ActionEvent e) {
		daysAttended = Integer.parseInt(nameInput.getText());
		nameInput.setVisible(false);
		intInputLabel.setVisible(false);
	}
	
	public void exitProgram(ActionEvent e) {
		Platform.exit();
	}
	
	public void updateButtons() {
		if (swipeLoaded && rosterLoaded) {
			menu_A.setDisable(false);menu_B.setDisable(false);menu_C.setDisable(false);menu_D.setDisable(false);menu_E.setDisable(false);menu_F.setDisable(false);menu_G.setDisable(false);menu_H.setDisable(false);
			menu_I.setDisable(false);menu_J.setDisable(false);menu_K.setDisable(false);menu_L.setDisable(false);menu_M.setDisable(false);menu_N.setDisable(false);menu_O.setDisable(false);menu_P.setDisable(false);
			menu_Q.setDisable(false);menu_R.setDisable(false);menu_S.setDisable(false);
		}
		else {
			menu_A.setDisable(true);menu_B.setDisable(true);menu_C.setDisable(true);menu_D.setDisable(true);menu_E.setDisable(true);menu_F.setDisable(true);menu_G.setDisable(true);menu_H.setDisable(true);
			menu_I.setDisable(true);menu_J.setDisable(true);menu_K.setDisable(true);menu_L.setDisable(true);menu_M.setDisable(true);menu_N.setDisable(true);menu_O.setDisable(true);menu_P.setDisable(true);
			menu_Q.setDisable(true);menu_R.setDisable(true);menu_S.setDisable(true);
		}
	}
	
	public void openLogFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if (selectedFile != null) {log = selectedFile.getName(); textOut.setText("Swipe File Loaded"); swipeLoaded = true;}
		else {textOut.setText("Error loading the log file"); swipeLoaded = false;}
		updateButtons();
	}
	
	public void openRosterFile(ActionEvent e) {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if (selectedFile != null) {roster = selectedFile.getName(); textOut.setText("Roster File Loaded");rosterLoaded = true;}
		else {textOut.setText("Error loading the swipe file");rosterLoaded = false;}
		updateButtons();
	}
	
	public static boolean loadATP(String log, String roster, AttendanceApp atp) {
		if (!atp.getRoster().load_roster(roster)) {
			return false;
		}
		if (!atp.getLog().load_log(log)) {
			return false;
		}
		return true;
	}
	
	public void buttonB(ActionEvent e) {
		if (!atl.load_log(log)) {
			textOut.setText("Error loading the log file");
		}
		else {
			textOut.setText(atl.print_collection());
		}
	}
	
	public void buttonC(ActionEvent e) {
		if (!atl.load_log(log)) {
			textOut.setText("Error loading the log file");
		}
		else {
			textOut.setText(atl.print_count());
		}
	}
	
	public void buttonE(ActionEvent e) {
		if (!str.load_roster(roster)) {
			textOut.setText("Error loading the log file");
		}
		else {
			textOut.setText(str.print_collection());
		}
	}
	
	public void buttonF(ActionEvent e) {
		if (!str.load_roster(roster)) {
			textOut.setText("Error loading the log file");
		}
		else {
			textOut.setText(str.print_count());
		}
	}
	
	public void buttonG(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			String out = "****** Students missing in class *************\n";
			out += atp.print_query_list(atp.list_students_not_in_class());
			textOut.setText(out);
		}
	}
	
	public void buttonH(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openName();
			
			nameInput.setOnAction(event -> {
				closeName(event);
				out = "****** List all swipe in and out for a student *******\n";
				out += atp.print_query_list(atp.list_all_times_checking_in_and_out(studentName));
				textOut.setText(out);
			});
		}
	}
	
	public void buttonI(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			out = "****** Check in times for all students who attended***\n";
			out += atp.print_query_list(atp.list_all_times_checked_in());
			textOut.setText(out);
		}
	}
	
	public void buttonJ(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openTimestamp();
			nameInput.setOnAction(event -> {
				closeTimestamp(event);
				openDate();
				nameInput.setOnAction(event2 -> {
					closeDate(event2);
					out = "****** Students that arrived late ******\n";
					out += atp.print_query_list(atp.list_students_late_to_class(timestamp, date));
					textOut.setText(out);
				});
			});
		}
	}
	
	public void buttonK(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openDate();
			nameInput.setOnAction(event -> {
				closeDate(event);
				out = "****** First student to enter on " + date + " *******\n";
				out += atp.get_first_student_to_enter(date);
				textOut.setText(out);
			});
			
		}
	}
	
	public void buttonL(ActionEvent e) {
		//print_attendance_data_for_student()
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openName();
			
			nameInput.setOnAction(event -> {
				closeName(event);
				out = "********* Looking up Student Attendance Data ***********\n";
				out += atp.print_attendance_data_for_student(studentName);
				textOut.setText(out);
			});
		}
	}
	
	public void buttonM(ActionEvent e) {
		//is_present
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openName();
			nameInput.setOnAction(event -> {
				closeName(event);
				openDate();
				nameInput.setOnAction(event2 -> {
					closeDate(event2);
					out = "**** Looking to see if Student was present on date ****\n";
					out += atp.is_present(studentName, date);
					textOut.setText(out);
				});
			});
		}
	}
	
	public void buttonN(ActionEvent e) {
		//list_all_students_checked_in
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openDate();
			nameInput.setOnAction(event -> {
				closeDate(event);
				out = "**** Students present on this date ****\n";
				out += atp.print_query_list(atp.list_all_students_checked_in(date));
				textOut.setText(out);
			});
			
		}
	}
	
	public void buttonO(ActionEvent e) {
		//list_all_students_checked_in_before
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openTimestamp();
			nameInput.setOnAction(event -> {
				closeTimestamp(event);
				openDate();
				nameInput.setOnAction(event2 -> {
					closeDate(event2);
					out = "**** Those present on date & before a time assigned ****\n";
					out += atp.print_query_list(atp.list_all_students_checked_in_before(timestamp, date));
					textOut.setText(out);
				});
			});
		}
	}
	
	public void buttonP(ActionEvent e) {
		//P - list_students_attendance_count()
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openInt();
			nameInput.setOnAction(event -> {
				closeInt(event);
				out = "******** Those who attended " + daysAttended + " day(s) ********\n";
				out += atp.print_query_list(atp.list_students_attendance_count(daysAttended));
				textOut.setText(out);
			});
			
		}
	}

	public void buttonQ(ActionEvent e) {
		//Q - get_first_student_to_enter()
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			openDate();
			nameInput.setOnAction(event -> {
				closeDate(event);
				out = "**** First student to enter on " + date + " ****\n";
				out += atp.get_first_student_to_enter(date);
				textOut.setText(out);
			});
			
		}
	}

	public void buttonR(ActionEvent e) {
		//R - print_query_list()
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			out = "**** Printing example query list_students_not_in_class ****\n";
		
			out += "****** Students missing in class *************\n";
			out += atp.print_query_list(atp.list_students_not_in_class());
			textOut.setText(out);
		}
	}

	public void buttonS(ActionEvent e) {
		if (!loadATP(log, roster, atp)) {
			textOut.setText("Error loading the AttendanceApp\nTry reloading the swipe and log file");
		}
		else {
			out = "**** Printing example query count list_students_not_in_class ****\n";
		
			out += "****** Students missing in class *************\n";
			out += atp.print_count(atp.list_students_not_in_class());
			textOut.setText(out);
		}
	}
}
