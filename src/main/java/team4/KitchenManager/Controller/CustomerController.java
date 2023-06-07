package team4.KitchenManager.Controller;
import team4.KitchenManager.Controller.CustomerController;
import team4.KitchenManager.DAO.DatabaseConnector;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(int customerId, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getID() == customerId) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
    }

    public void deleteCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getID() == customerId) {
                customers.remove(i);
                break;
            }
        }
    }

    public List<Customer> searchCustomersByID(int customerId) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getID() == customerId) {
                result.add(customer);
            }
        }
        return result;
    }

    public List<Customer> searchCustomersByName(String name) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                result.add(customer);
            }
        }
        return result;
    }

    public List<Customer> searchCustomersByPhone(String phone) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getPhone().equalsIgnoreCase(phone)) {
                result.add(customer);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerController controller = new CustomerController();

        int choice;
        do {
            System.out.println("----- Quản lý khách hàng -----");
            System.out.print("Nhập lựa chọn của bạn: ");
            System.out.println("0. Thoát");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Sửa thông tin khách hàng");
            System.out.println("3. Xóa khách hàng");
            System.out.println("4. Tìm kiếm khách hàng theo ID");
            System.out.println("5. Tìm kiếm khách hàng theo Tên");
            System.out.println("6. Tìm kiếm khách hàng theo Số điện thoại");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    Customer newCustomer = inputCustomerInfo(scanner);
                    controller.addCustomer(newCustomer);
                    System.out.println("Thêm khách hàng thành công.");
                    break;
                case 2:
                    System.out.print("Nhập ID khách hàng cần sửa: ");
                    int customerIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); 
                    Customer updatedCustomer = inputCustomerInfo(scanner);
                    updatedCustomer.setID(customerIdToUpdate);
                    controller.updateCustomer(customerIdToUpdate, updatedCustomer);
                    System.out.println("Sửa thông tin khách hàng thành công.");
                    break;
                case 3:
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    int customerIdToDelete = scanner.nextInt();
                    scanner.nextLine(); 
                    controller.deleteCustomer(customerIdToDelete);
                    System.out.println("Xóa khách hàng thành công.");
                    break;
                case 4:
                    System.out.print("Nhập ID khách hàng cần tìm: ");
                    int searchCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    List<Customer> searchResultById = controller.searchCustomersByID(searchCustomerId);
                    displayCustomerList(searchResultById);
                    break;
                case 5:
                    System.out.print("Nhập Tên khách hàng cần tìm: ");
                    String searchName = scanner.nextLine();
                    List<Customer> searchResultByName = controller.searchCustomersByName(searchName);
                    displayCustomerList(searchResultByName);
                    break;
                case 6:
                    System.out.print("Nhập Số điện thoại khách hàng cần tìm: ");
                    String searchPhone = scanner.nextLine();
                    List<Customer> searchResultByPhone = controller.searchCustomersByPhone(searchPhone);
                    displayCustomerList(searchResultByPhone);
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }

            System.out.println();
        } while (choice != 0);
    }

    private static Customer inputCustomerInfo(Scanner scanner) {
        System.out.print("Nhập ID khách hàng: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nhập tên khách hàng: ");
        String name = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        return new Customer(id, name, phone);
    }

    private static void displayCustomerList(List<Customer> customers) {
        System.out.format("", "ID", "Name", "Phone");
        for (Customer customer : customers) {
            System.out.format("", customer.getID(), customer.getName(), customer.getPhone());
        }
    }
}

class Customer {
    private int ID;
    private String Name;
    private String Phone;

    public Customer(int ID, String name, String phone) {
        this.ID = ID;
        this.Name = name;
        this.Phone = phone;
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
}
