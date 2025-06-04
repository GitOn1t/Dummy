package lms.zainab.dao;

import java.sql.SQLException;

import lms.zainab.model.LeaveHistory;

public interface EmployeeDao {
	

	String showAllEmployDao() throws SQLException, ClassNotFoundException;

	String searchEmployDao(int empno) throws ClassNotFoundException, SQLException;

	String applyLeaveDao(LeaveHistory leaveHistroy,int managerId) throws ClassNotFoundException, SQLException;

	// String leaveHistory (select * from LeaveHistory where empId=1000)

	String showleaveHistoryDao(int empId) throws ClassNotFoundException, SQLException;

	// 3) Pending Leave History (DIsplay Managers Subordinates pending leaves
	// history)

	String showPendingLeaveHistoryDao(int mangerId) throws SQLException, ClassNotFoundException;

    //	Accept/Reject (leaveId,ManagerId,Yes/No,ManagerComments)

	String leaveApproveByManagerDao(int managerId) throws SQLException, ClassNotFoundException;
}
