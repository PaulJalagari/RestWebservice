package webservice.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import webservice.dao.EmployeeDaoImpl;
import webservice.dto.DateOrder;
import webservice.dto.Employee;

@Path("/employee")
public class EmployeeService {

	@GET
	@Path("/employeedetails")
	@Produces("application/json")
	public String getProductDetails() {
		String employeeDetails = null;
		List<Employee> employeeList = null;

		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

		employeeList = employeeDaoImpl.getProductDetails();

		Gson gson = new Gson();
		employeeDetails = gson.toJson(employeeList);
		return employeeDetails;
	}

	@GET
	@Path("{empid}")
	@Produces("application/json")
	public String getEmployeeAttend(@PathParam("empid") String empid) {
		String employeeAttendance = null;
		List<DateOrder> attendanceList = null;
		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
		attendanceList = employeeDaoImpl.getAttendance(empid);
		Gson gson = new Gson();
		employeeAttendance = gson.toJson(attendanceList);
		return employeeAttendance;
	}
}
