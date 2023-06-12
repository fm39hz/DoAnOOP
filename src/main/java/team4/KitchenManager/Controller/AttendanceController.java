package team4.KitchenManager.Controller;

import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.util.*;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Attendance;
import team4.KitchenManager.Model.Employee;

public class AttendanceController {
    private DatabaseConnector Connector = null;
    public AttendanceController() {
        Connector = new DatabaseConnector();
        }
    public AttendanceController(DatabaseConnector connector) {
        this.Connector = connector;
        }
    public List<Attendance> GetAll(Employee employee){
        var _query = "SELECT * from attendances WHERE emp_id = ?";
        List<Attendance> _target = new ArrayList<>();
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setString(1, employee.getId());
            var _result = _statement.executeQuery();
                while(_result.next()){
                    var _employeeController = new EmployeeController(this.Connector);
                    _target.add(new Attendance(_result.getString(1), _employeeController.GetEmployee(_result.getString(2)), _result.getDate(3), _result.getTime(4)));
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _target;
        }
    public List<Attendance> GetAll(){
        var _query = "SELECT * FROM attendances";
        List<Attendance> _target = new ArrayList<>();
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
            var _result = _statement.executeQuery();
                while(_result.next()){
                    var _employeeController = new EmployeeController(this.Connector);
                    _target.add(new Attendance(_result.getString(1), _employeeController.GetEmployee(_result.getString(2)), _result.getDate(3), _result.getTime(4)));
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _target;
        }
    public void AddAttendance(Employee employee, Date day, Time checkIn){
        var _attendance = new Attendance(null, employee, day, checkIn);
        var _query = "INSERT INTO attendances (Id, emp_id, day, checkin_at) VALUES (?, ?, ?, ?)";
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setString(1, _attendance.getID());
                _statement.setString(2, _attendance.getEmployee().getId());
                _statement.setDate(3, _attendance.getDay());
                _statement.setTime(4, _attendance.getCheckIn());
                _statement.executeUpdate();
                _statement.close();
            } 
        catch (SQLException e) {
            e.printStackTrace();;
            }
        }
    public void AddAttendance(Employee employee){
        AddAttendance(employee, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
        }
    public int CountAttendances(Date day){
        var _query = "SELECT COUNT(Id) as counter FROM attendances WHERE = ?;";
            try {
                var _statement = Connector.getConnector().prepareStatement(_query);
                    _statement.setDate(1, day);
                var _result = _statement.executeQuery();
                    _result.next();
                    return _result.getInt("counter");
                }
            catch (SQLException e) {
                e.printStackTrace();
                }
            return 0;
        }
    public List<Employee> GetEmployeeOnDay(Date day){
        var _query = "SELECT emp_id FROM attendances WHERE day = ?;";
        List<Employee> _employees = new ArrayList<>();
        var _employeeController = new EmployeeController(this.Connector);
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setDate(1, day);
            var _result = _statement.executeQuery();
                while (_result.next()){
                    _employees.add(_employeeController.GetEmployee(_result.getString(1)));
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _employees;
        }
    // public double CalculateTotalSalary(Date Day) {
    //     var _employees = GetEmployeeOnDay(Day);
    //     var _salaryOnDay = 0;
    //         for (var _employee : _employees) {
    //             _salaryOnDay += _employee.getSalary();
    //             }
    //     return _salaryOnDay;
    //     }
    }
