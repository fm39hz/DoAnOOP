
package team4.KitchenManager.Controller;
import team4.KitchenManager.DAO.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private Connection connection;
    DatabaseConnector conn = null;

    public EmployeeController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
        
    }

public List<Employee> getEmployees() {
    List<Employee> employees = new ArrayList<>();
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("position"), resultSet.getInt("salary"));
            employees.add(employee);
        }
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employees;
}

public void addEmployee(Employee newEmployee) {
    try {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO employee (id, name, phone, position, salary) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, newEmployee.getID());
        statement.setString(2, newEmployee.getName());
        statement.setString(3, newEmployee.getPhone());
        statement.setString(4, newEmployee.getPosition());
        statement.setInt(5, newEmployee.getSalary());
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void editEmployeeByID(int employeeId, int salary) {
    try {
        PreparedStatement statement = connection.prepareStatement("UPDATE employee SET salary = ? WHERE id = ?");
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
        PreparedStatement statement = connection.prepareStatement("UPDATE employee SET salary = ? WHERE name = ?");
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
        PreparedStatement statement = connection.prepareStatement("UPDATE employee SET salary = ? WHERE phone = ?");
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
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
        statement.setInt(1, employeeId);
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteEmployeeByName(String employeeName) {
    try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE name = ?");
        statement.setString(1, employeeName);
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteEmployeeByPhone(String employeePhone) {
    try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE phone = ?");
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
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE name LIKE ?");
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("position"), resultSet.getInt("salary"));
            searchResults.add(employee);
        }
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return searchResults;
}

class Employee {
    private int ID;
    private String name;
    private String phone;
    private String position;
    private int salary;

    public Employee(int ID, String name, String phone, String position, int salary) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

}