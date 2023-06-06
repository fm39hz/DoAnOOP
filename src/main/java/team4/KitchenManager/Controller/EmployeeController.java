package team4.KitchenManager.Controller;
import team4.KitchenManager.DAO.DatabaseConnector;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class EmployeesController {
    private List<Employee> employees;
    
    public EmployeesController() {
        employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void updateEmployee(int employeeId, Employee updatedEmployee) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getID() == employeeId) {
                employees.set(i, updatedEmployee);
                break;
            }
        }
    }
    
    public void deleteEmployee(int employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getID() == employeeId) {
                employees.remove(i);
                break;
            }
        }
    }
    
    public void displayEmployeeList() {
        if (employees.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng.");
        } else {
            System.out.println("Danh sách nhân viên:");
            System.out.format("%-5s %-20s %-15s %-20s %-10s\n", "ID", "Name", "Phone", "Position", "Salary");
            for (Employee employee : employees) {
                System.out.format("%-5d %-20s %-15s %-20s %-10d\n", employee.getID(), employee.getName(), employee.getPhone(), employee.getPosition(), employee.getSalary());
            }
        }
    }
    
    public void sortBySalaryDescending() {
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                return Integer.compare(emp2.getSalary(), emp1.getSalary());
            }
        });
    }
    
    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                result.add(employee);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeesController controller = new EmployeesController();
        
        int choice;
        do {
            System.out.println("----- Quản lý nhân viên -----");
            System.out.print("Nhập lựa chọn của bạn: ");
            System.out.println("0. Thoát");
            System.out.println("1. Nhập thông tin nhân viên");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Sửa thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Hiển thị danh sách nhân viên");
            System.out.println("6. Sắp xếp theo mức lương giảm dần");
            System.out.println("7. Tìm kiếm nhân viên theo tên");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự newline (\n)
            
            switch (choice) {
                case 1:
                    Employee newEmployee = inputEmployeeInfo(scanner);
                    controller.addEmployee(newEmployee);
                    break;
                case 2:
                    Employee employeeToAdd = inputEmployeeInfo(scanner);
                    controller.addEmployee(employeeToAdd);
                    System.out.println("Thêm nhân viên thành công.");
                    break;
                case 3:
                    System.out.print("Nhập ID nhân viên cần sửa: ");
                    int employeeIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); 
                    Employee updatedEmployee = inputEmployeeInfo(scanner);
                    updatedEmployee.setID(employeeIdToUpdate);
                    controller.updateEmployee(employeeIdToUpdate, updatedEmployee);
                    System.out.println("Sửa thông tin nhân viên thành công.");
                    break;
                case 4:
                    System.out.print("Nhập ID nhân viên cần xóa: ");
                    int employeeIdToDelete = scanner.nextInt();
                    scanner.nextLine(); 
                    controller.deleteEmployee(employeeIdToDelete);
                    System.out.println("Xóa nhân viên thành công.");
                    break;
                case 5:
                    controller.displayEmployeeList();
                    break;
                case 6:
                    controller.sortBySalaryDescending();
                    System.out.println("Danh sách nhân viên đã được sắp xếp theo mức lương giảm dần.");
                    break;
                case 7:
                    System.out.print("Nhập tên nhân viên cần tìm: ");
                    String searchName = scanner.nextLine();
                    List<Employee> searchResults = controller.searchEmployeesByName(searchName);
                    if (searchResults.isEmpty()) {
                        System.out.println("Không tìm thấy nhân viên có tên '" + searchName + "'.");
                    } else {
                        System.out.println("Kết quả tìm kiếm:");
                        System.out.format("", "ID", "Name", "Phone", "Position", "Salary");
                        for (Employee employee : searchResults) {
                            System.out.format("", employee.getID(), employee.getName(), employee.getPhone(), employee.getPosition(), employee.getSalary());
                        }
                    }
                    break;
                case 8:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
            
            System.out.println();
        } while (choice != 0);
    }
    
    private static Employee inputEmployeeInfo(Scanner scanner) {
        System.out.print("Nhập ID nhân viên: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nhập tên nhân viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        System.out.print("Nhập vị trí công việc: ");
        String position = scanner.nextLine();

        System.out.print("Nhập mức lương: ");
        int salary = scanner.nextInt();

        return new Employee(id, name, phone, position, salary);
    }
}

public class Employee {
    private int ID;
    private String Name;
    private String Phone;
    private String Position;
    private int Salary;

    public Employee(int ID, String name, String phone, String position, int salary) {
        this.ID = ID;
        this.Name = name;
        this.Phone = phone;
        this.Position = position;
        this.Salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        this.Position = position;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        this.Salary = salary;
    }
}
