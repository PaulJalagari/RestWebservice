package webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import webservice.dto.DateOrder;
import webservice.dto.Employee;

public class EmployeeDaoImpl {
	public List<Employee> getProductDetails() {

		List<Employee> employeeData = new ArrayList<>();

		EmployeeDao employeeDao = new EmployeeDao();

		Connection connection = employeeDao.getConnnection();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from EMPINFO");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpid(rs.getInt(1));
				employee.setEmp_timestamp(rs.getString(2));
				employeeData.add(employee);

			}
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeeData;
	}

	public List<DateOrder> getAttendance(String month) {
		String date1 = "'2017-" + month + "-%% %%:%%:%%'";
		List<DateOrder> datelist = new ArrayList<>();
		List<Employee> empdata = new ArrayList<>();
		EmployeeDao employeeDao = new EmployeeDao();

		Connection connection = employeeDao.getConnnection();

		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from EMPINFO where emp_timestamp like" + date1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpid(rs.getInt("empid"));
				employee.setEmp_timestamp(rs.getString("emp_timestamp"));
				empdata.add(employee);

			}
			datelist = getdatewise(empdata);
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datelist;

	}

	public List<DateOrder> getdatewise(List<Employee> empdata) {
		List<DateOrder> date1 = new ArrayList<DateOrder>();
		List<Employee> emp1;
		Map<String, Integer> countmap = new HashMap<String, Integer>();
		for (Employee empto : empdata) {
			String[] datearray = empto.getEmp_timestamp().split(" ");
			String date3 = datearray[0];
			int n = 0;
			for (Employee empto1 : empdata) {
				String[] datearrayin = empto1.getEmp_timestamp().split(" ");
				String date4 = datearrayin[0];
				if (date3.equals(date4))
					n = n + 1;

			}
			countmap.put(date3, n);

		}
		Iterator it = countmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry count1 = (Map.Entry) it.next();
			DateOrder do1 = new DateOrder();
			do1.setDate1(count1.getKey().toString());
			do1.setEMPCount(Integer.parseInt(count1.getValue().toString()));
			date1.add(do1);

		}
		return date1;

	}

}
