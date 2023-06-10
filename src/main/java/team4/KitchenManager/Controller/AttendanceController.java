package team4.KitchenManager.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Attendance;
import team4.KitchenManager.Model.Employee;

public class AttendanceController {
    
    private Map<Date, List<Employee>> attendanceMap;
    DatabaseConnector conn = null;
    public AttendanceController() {
        conn = new DatabaseConnector();
        attendanceMap = new HashMap<>();
    }

    public void addAttendance(Employee employee, Date Day, Time CheckIn) {
        Attendance attendance = new Attendance(employee.getID(), Day, CheckIn);
        List<Employee> employeeList = attendanceMap.getOrDefault(Day, new ArrayList<>());
        employeeList.add(employee);
        attendanceMap.put(Day, employeeList);

        // Lưu thông tin chấm công vào cơ sở dữ liệu
        saveAttendanceToDatabase(attendance);
    }

    private void saveAttendanceToDatabase(Attendance attendance) {
        try {
            String sql = "INSERT INTO attendance (ID, Day, CheckIn) VALUES (?, ?, ?)";
            var statement = conn.getConnector().prepareStatement(sql);
            statement.setString(1, attendance.getID());
            statement.setDate(2, new java.sql.Date(attendance.getDay().getTime()));
            statement.setTime(3, attendance.getCheckIn());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error saving attendance to the database: " + e.getMessage());
        }
    }

    public List<Employee> getEmployeesByDay(Date Day) {
        if (attendanceMap.containsKey(Day)) {
            return attendanceMap.get(Day);
        } else {
            // Truy vấn cơ sở dữ liệu để lấy danh sách nhân viên đã chấm công trong ngày
            return getEmployeesFromDatabase(Day);
        }
    }

    private List<Employee> getEmployeesFromDatabase(Date Day) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employee WHERE ID IN (SELECT ID FROM attendance WHERE Day = ?)";
            var statement = conn.getConnector().prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(Day.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String employeeID = resultSet.getString("ID");
                String imagePath = resultSet.getString("ImagePath");
                String position = resultSet.getString("Position");
                int salary = resultSet.getInt("Salary");

                Employee employee = new Employee();
                employee.setID(employeeID);
                employee.setImagePath(imagePath);
                employee.setPosition(position);
                employee.setSalary(salary);

                employees.add(employee);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving employees from the database: " + e.getMessage());
        }
        return employees;
    }

    public double calculateTotalSalary(Date Day) {
        List<Employee> employees = getEmployeesByDay(Day);
        double TotalSalary = 0;
        for (Employee employee : employees) {
            TotalSalary += employee.getSalary();
        }
        return TotalSalary;
    }

    public void displayAttendanceCalendar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Date day : attendanceMap.keySet()) {
            System.out.println("Date: " + dateFormat.format(day));
            List<Employee> employees = getEmployeesByDay(day);
            for (Employee employee : employees) {
                System.out.println("Employee ID: " + employee.getID());
                System.out.println("Employee Name: " + employee.getName());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("---------------------");
            }
        }
    }
}
