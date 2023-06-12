
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
            Statement statement = Connector.getConnector().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                // Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("position"), resultSet.getInt("salary"));
                // employees.add(employee);
            }
            resultSet.close();
            statement.close();
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
                    _target.setLastName(_result.getString(2));
                    _target.setPhoneNumber(_result.getString(3));
                    _target.setPosition(_result.getString(4));
                    _target.setSalary(_result.getInt(5));
                    _target.setFirstName(_result.getString(6));
                    _target.setImagePath(_result.getString(7));
                    }
            }
        catch (SQLException e) {
            e.printStackTrace();
            }
        return _target;
        }
    // public void addEmployee(Employee newEmployee) {
    //     try {
    //         PreparedStatement statement = Connector.getConnector().prepareStatement("INSERT INTO employee (id, name, phone, position, salary) VALUES (?, ?, ?, ?, ?)");
    //         statement.setInt(1, newEmployee.getID());
    //         statement.setString(2, newEmployee.getName());
    //         statement.setString(3, newEmployee.getPhone());
    //         statement.setString(4, newEmployee.getPosition());
    //         statement.setInt(5, newEmployee.getSalary());
    //         statement.executeUpdate();
    //         statement.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void editEmployeeByID(int employeeId, int salary) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("UPDATE employee SET salary = ? WHERE id = ?");
            statement.setInt(1, salary);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEmployeeByName(String employeeName, int salary) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("UPDATE employee SET salary = ? WHERE name = ?");
            statement.setInt(1, salary);
            statement.setString(2, employeeName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEmployeeByPhone(String employeePhone, int salary) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("UPDATE employee SET salary = ? WHERE phone = ?");
            statement.setInt(1, salary);
            statement.setString(2, employeePhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeByID(int employeeId) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("DELETE FROM employee WHERE id = ?");
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeByName(String employeeName) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("DELETE FROM employee WHERE name = ?");
            statement.setString(1, employeeName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeByPhone(String employeePhone) {
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("DELETE FROM employee WHERE phone = ?");
            statement.setString(1, employeePhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchEmployeeByName(String name) {
        List<Employee> searchResults = new ArrayList<>();
        try {
            PreparedStatement statement = Connector.getConnector().prepareStatement("SELECT * FROM employee WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("position"), resultSet.getInt("salary"));
                // searchResults.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
        }
    }