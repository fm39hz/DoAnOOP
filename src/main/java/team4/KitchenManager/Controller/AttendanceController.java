package team4.KitchenManager.Controller;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Attendance;
import team4.KitchenManager.Model.Employee;

public class AttendanceController {
    private List<Attendance> Attendances;
    private Map<Date, List<Employee>> attendanceMap;
    private DatabaseConnector Connector = null;
    public AttendanceController() {
        Connector = new DatabaseConnector();
        attendanceMap = new HashMap<>();
    }

    public AttendanceController(DatabaseConnector connector) {
        this.Connector = connector;
        this.Attendances = new ArrayList<>();
        attendanceMap = new HashMap<>();
        }
    public void addAttendance(Employee employee, Date day, Time checkIn){
        var _attendance = new Attendance(null, employee, day, checkIn);
        var _query = "INSERT INTO attendances (Id, emp_id, day, checkin_at) VALUES (?, ?, ?, ?)";
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setString(1, _attendance.getID());
                _statement.setString(2, _attendance.getEmployee().getId());
                _statement.setDate(3, _attendance.getDay());
                _statement.setTime(4, _attendance.getCheckIn());
                _statement.executeQuery();
                _statement.close();
            } 
        catch (SQLException e) {
            e.printStackTrace();;
            }
        }
    public void addAttendance(Employee employee){
        addAttendance(employee, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
        }
    public int CountAttendances(){
        var _query = "SELECT COUNT(Id) as counter FROM attendances;";
            try {
                var _statement = Connector.getConnector().prepareStatement(_query);
                var _result = _statement.executeQuery();
                    _result.next();
                    return _result.getInt("counter");
                }
            catch (SQLException e) {
                e.printStackTrace();
                }
            return 0;
        }

    public List<Employee> getEmployeesByDay(Date Day) {
        if (attendanceMap.containsKey(Day)){
            return attendanceMap.get(Day);
            } 
        else{
            // Truy vấn cơ sở dữ liệu để lấy danh sách nhân viên đã chấm công trong ngày
            return getEmployeesFromDatabase(Day);
            }
        }
    public List<Employee> GetEmployeeOnDay(Date day){
        var _getIdQuery = "select emp_id from attendances where day = ?;";
        List<String> _ids = new ArrayList<>();
        try {
            var _statement = Connector.getConnector().prepareStatement(_getIdQuery);
                _statement.setDate(1, day);
            var _result = _statement.executeQuery();
                while (_result.next()){
                    _ids.add(_result.getString(1));
                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        }
    private List<Employee> getEmployeesFromDatabase(Date Day) {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM employees WHERE ID IN (SELECT ID FROM attendances WHERE Day = ?)";
            var statement = Connector.getConnector().prepareStatement(sql);
            statement.setDate(1, Day);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String employeeID = resultSet.getString("Id");
                String phone = resultSet.getString("phone");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String imagePath = resultSet.getString("image_path");
                String position = resultSet.getString("position");
                int salary = resultSet.getInt("salary");

                Employee employee = new Employee();
                employee.setId(employeeID);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setPhoneNumber(phone);
                employee.setImagePath(imagePath);
                employee.setPosition(position);
                employee.setSalary(salary);

                employees.add(employee);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
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
    }
