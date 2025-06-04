package lms.zainab.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Predicate;

import lms.zainab.dao.*;
import lms.zainab.model.LeaveHistory;
import lms.zainab.util.ConnectionHelper;
import lms.zainab.validate.EmployeeValidate;

public class Main {
	
	static EmployeeValidate vlayer;
	static Scanner sc;

	static {
		vlayer = new EmployeeValidate();
		sc = new Scanner(System.in);

	}

	public static void main(String[] args) {
		
		
		
			
			
			int choice;

			do {
				System.out.println("\n--- Leave Management System ---");
				System.out.println("1. Show All Employees");
				System.out.println("2. Search Employee by ID");
				System.out.println("3. Apply Leave");
				System.out.println("4. Show Leave History");
				System.out.println("5. Show Pending Leave History");
				System.out.println("6. Approve/Reject Leave");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				choice = sc.nextInt();

				try {
					switch (choice) {
					case 1:
						showAllEmployeeDaoValMain();
						break;
					case 2:
						searchEmployeeByIdMain();
						break;
					case 3:
						applyLeaveDaoValMain();
						break;
					case 4:
						showleaveHistoryByEmpIdMain();
						break;
						
					case 5:
						showPendingLeaveHistoryMain();
						break;
					case 6:
						leaveApproveByManagerMain();
						break;
					case 7:
						System.out.println("Exiting...");
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				} catch (Exception e) {
					System.out.println("An error occurred: " + e.getMessage());
				}

			} while (choice != 7);
		}

		
	 
		public static void showAllEmployeeDaoValMain() throws ClassNotFoundException, SQLException {
			System.out.println("=======show all employee====");
			System.out.println(vlayer.showAllEmployDaoVal());
			
		}
	 	public static void showleaveHistoryByEmpIdMain() throws ClassNotFoundException, SQLException {
			System.out.println("========show the leave history======");
			System.out.println("Enter the empId:");
			Scanner sc = new Scanner(System.in);
			int empid = sc.nextInt();
			System.out.println(vlayer.leaveHistroyByIdDaoVal(empid));
		}

		

		// Search employee by ID
		public static void searchEmployeeByIdMain() throws SQLException, ClassNotFoundException {
			System.out.println("======== Search Employee ========");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Employee ID: ");
			int empId = sc.nextInt();
			
			System.out.println(vlayer.searchEmployDaoVal(empId));
		}

		// Apply leave
		public static void applyLeaveDaoValMain() throws SQLException, ClassNotFoundException {
			System.out.println("======== Apply Leave ========");
			  LeaveHistory leave=new LeaveHistory();
			
//		            System.out.print("Enter Leave ID: ");
//		            leave.setLeaveId(sc.nextInt());

		            System.out.print("Enter Employee ID: ");
		            leave.setEmpId(sc.nextInt());

		            System.out.print("Enter Number of Leave Days: ");
		            leave.setLeaveNoOfDays(sc.nextInt());
		            sc.nextLine(); // consume newline

		            System.out.print("Enter Leave Reason: ");
		            leave.setLeaveReason(sc.nextLine());

		            System.out.print("Enter Manager Comments: ");
		            leave.setLeaveMngrComments(sc.nextLine());

//		            System.out.print("Enter Leave Type (e.g., EL): ");
//		            leave.setLeaveType(LeaveType.valueOf(sc.nextLine().toUpperCase()));
	//
//		            System.out.print("Enter Leave Status (PENDING, APPROVED, DENIED): ");
//		            leave.setLeaveStatus(LeaveStatus.valueOf(sc.nextLine().toUpperCase()));

		            System.out.print("Enter Leave Start Date (yyyy-MM-dd): ");
		            String startDateStr = sc.nextLine();
		            leave.setLeaveStartDate(Date.valueOf(startDateStr));  // using java.sql.Date

		            System.out.print("Enter Leave End Date (yyyy-MM-dd): ");
		            String endDateStr = sc.nextLine();
		            leave.setLeaveEndDate(Date.valueOf(endDateStr));
		            
	                System.out.println("Enter the mangerId");
	                int managerId=sc.nextInt();
	                
	                 System.out.println(leave);
	                
		            System.out.println("\nLeave Details Entered:");
		            System.out.println(leave);
		            System.out.println(vlayer.applyLeaveDaoVal(leave,managerId));
			        
			 

			
	  	}



		// Show pending leave history for manager
		public static void showPendingLeaveHistoryMain() throws SQLException, ClassNotFoundException {
			System.out.println("======== Show Pending Leave History ========");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Manager ID: ");
			int managerId = sc.nextInt();
		    
			System.out.println(vlayer.showPendingLeaveHistoryDaoVal(managerId));
		}

		// Approve or reject leave
		public static void leaveApproveByManagerMain() throws SQLException, ClassNotFoundException {
			System.out.println("======== Approve/Reject Leave ========");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Manager ID: ");
			int managerId = sc.nextInt();
			
			System.out.println(vlayer.leaveApproveByManagerDaoVal(managerId));
		}
}