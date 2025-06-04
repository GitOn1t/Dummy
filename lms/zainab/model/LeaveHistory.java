package lms.zainab.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveHistory {
	 private int leaveId;
	    private int leaveNoOfDays;
	    private String leaveMngrComments;
	    private int empId;
	    private Date leaveStartDate;
	    private Date leaveEndDate;
	    private LeaveType leaveType;
	    private LeaveStatus leaveStatus;
	    private String leaveReason;
}
