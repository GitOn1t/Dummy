package lms.zainab.model;

import java.sql.DatabaseMetaData;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Employee {
	private	int empid;
	private  String empname;
	private String email;
	private String mobno;
	private DatabaseMetaData date;
	private String  dept;
	private int managerId;
	private int leaveAailable;

}
