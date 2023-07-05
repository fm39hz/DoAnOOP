
package team4.KitchenManager.Controller;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private DatabaseConnector Connector;

    public EmployeeController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        Connector = new DatabaseConnector(); 
        }
    public EmployeeController(DatabaseConnector connector){
        this.Connector = connector;
        }
    public List<Employee> getAll() {
        List<Employee> _employees = new ArrayList<>();
        try {
            Statement _statement = Connector.getConnector().createStatement();
            ResultSet _result = _statement.executeQuery("SELECT * FROM employees");
            while (_result.next()) {
                var _target = new Employee();
                    _target.setId(_result.getString(1));
                    _target.setFirstName(_result.getString(2));
                    _target.setLastName(_result.getString(3));
                    _target.setPhoneNumber(_result.getString(4));
                    _target.setSalary(_result.getInt(5));
                    _target.setPosition(_result.getString(6));
                    _target.setImagePath(_result.getString(7));
                    _target.setAttendanceHistory(new AttendanceController(Connector).GetAll(_target));
                    _employees.add(_target);
                }
            _result.close();
            _statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _employees;
        }
    public Employee GetEmployee(String id){
        var _query = "SELECT * FROM employees WHERE Id = ?";
        var _target = new Employee();
        try {
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setString(1, id);
            var _result = _statement.executeQuery();
                while(_result.next()){
                    _target.setId(_result.getString(1));
                    _target.setFirstName(_result.getString(2));
                    _target.setLastName(_result.getString(3));
                    _target.setPhoneNumber(_result.getString(4));
                    _target.setSalary(_result.getInt(5));
                    _target.setPosition(_result.getString(6));
                    _target.setImagePath(_result.getString(7));
                    _target.setAttendanceHistory(new AttendanceController(Connector).GetAll(_target));
                    }
            }
        catch (SQLException e) {
            e.printStackTrace();
            }
        return _target;
        }
    public void addEmployee(Employee newEmployee) {
        var _query = "INSERT INTO employees (first_name, last_name, phone, salary, position, image_path) VALUES (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement(_query);
            statement.setString(1, newEmployee.getFirstName());
            statement.setString(2, newEmployee.getLastName());
            statement.setString(3, newEmployee.getPhoneNumber());
            statement.setInt(4, newEmployee.getSalary());
            statement.setString(5, newEmployee.getPosition());
            statement.setString(6, newEmployee.getImagePath());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEmployee(int employeeId, int salary) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("UPDATE employees SET salary = ? WHERE id = ?");
            statement.setInt(1, salary);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteEmployee(int employeeId) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("DELETE FROM employees WHERE id = ?");
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchEmployeeByName(String name) {
        List<Employee> searchResults = new ArrayList<>();
        var _query = "SELECT * FROM employees WHERE employees.last_name LIKE '%" + name + "%' ORDER BY id ASC";
        try {
            var statement = Connector.getConnector().prepareStatement(_query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var employee = new Employee();
                employee.setId(resultSet.getString("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setPhoneNumber(resultSet.getString("phone"));
                employee.setSalary(resultSet.getInt("salary"));
                employee.setPosition(resultSet.getString("position"));
                employee.setImagePath(resultSet.getString("image_path"));
                employee.setAttendanceHistory(new AttendanceController(Connector).GetAll(employee));
                searchResults.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
        }
    }