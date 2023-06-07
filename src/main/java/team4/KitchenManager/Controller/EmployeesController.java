// package team4.KitchenManager.Controller;

// import team4.KitchenManager.Model.employee;
// import team4.KitchenManager.DAO.DatabaseConnector;
// import javax.swing.*;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import team4.KitchenManager.Object.Employee;

// public class EmployeeController {
//     DatabaseConnector conn = null;

//     public EmployeeController() {
//         conn = new DatabaseConnector();
//     }

//     public List<Employee> getAllEmployee() {
//         List<Employee> _list = new ArrayList<>();
//         Employee _Employee = new Employee();
//         _Employee.setName();
//         _Employee.setID();
//         _Employee.setPhone();
//         _Employee.setPosition();
//         _Employee.setSalary();
//         return _list;
//     }

//     public class EmployeeController {

//         private Map<Integer, Employee> employeeMap;

//         public EmployeeController() {
//             this.employeeMap = new HashMap<Integer, Employee>();
//         }

//         // Thêm mới Employee với thông tin được cung cấp
//         public Employee addEmployee(int id, String name, String position, String phone, double salary) {
//             Employee employee = new Employee(id, name, position, phone, salary);
//             this.employeeMap.put(id, employee);
//             return employee;
//         }

//         // Cập nhật Employee với các thông tin được cung cấp dựa trên ID
//         public Employee updateEmployee(int id, String name, String position, String phone, double salary) {
//             if (this.employeeMap.containsKey(id)) {
//                 Employee employee = this.employeeMap.get(id);
//                 if (name != null && !name.isEmpty()) {
//                     employee.setName(name);
//                 }

//                 if (position != null && !position.isEmpty()) {
//                     employee.setPosition(position);
//                 }

//                 if (phone != null && !phone.isEmpty()) {
//                     employee.setPhone(phone);
//                 }

//                 if (salary > 0) {
//                     employee.setSalary(salary);
//                 }

//                 this.employeeMap.put(id, employee);

//                 return employee;
//             }
//             return null;
//         }

//         // Xóa Employee dựa trên ID
//         public boolean deleteEmployee(int id) {
//             if (this.employeeMap.containsKey(id)) {
//                 this.employeeMap.remove(id);
//                 return true;
//             } else {
//                 return false;
//             }
//         }

//         // Lấy ra tất cả Employee
//         public List<Employee> getAllEmployees() {
//             List<Employee> list = new ArrayList<Employee>();
//             for (Employee employee : this.employeeMap.values()) {
//                 list.add(employee);
//             }
//             return list;
//         }

//     }
// }
