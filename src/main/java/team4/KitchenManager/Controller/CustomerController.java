package team4.KitchenManager.Controller;
import java.sql.Connection;
import team4.KitchenManager.DAO.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerController {
    private Connection connection;
    DatabaseConnector conn = null;

    public CustomerController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
        
    }

    public void displayCustomers() {
        System.out.println("ID | Name | Phone |");

        List<Customer> customer = getCustomers();

        for (Customer Customer : customer) {
            System.out.print(Customer.getID() + " | ");
            System.out.print(Customer.getName() + " | ");
            System.out.print(Customer.getPhone() + " | ");
            System.out.println(getDishesUsingCustomer(Customer.getID()));
        }
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"));
                customer.setUsedQuantity(resultSet.getInt("used_quantity"));
                customers.add(customer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<String> getDishesUsingCustomer(int customerId) {
        List<String> dishes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT dish_name FROM Customer_dish WHERE customer_id = ?");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dishes.add(resultSet.getString("dish_name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishes;
    }

    public void addCustomer(Customer newCustomer) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer (id, name, phone) VALUES (?, ?, ?)");
            statement.setInt(1, newCustomer.getID());
            statement.setString(2, newCustomer.getName());
            statement.setString(3, newCustomer.getPhone());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerByID(int customerId, int quantity) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customer SET used_quantity = ? WHERE id = ?");
            statement.setInt(1, quantity);
            statement.setInt(2, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerByName(String customerName, int quantity) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customer SET used_quantity = ? WHERE name = ?");
            statement.setInt(1, quantity);
            statement.setString(2, customerName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomerByPhone(String customerPhone, int quantity) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE customer SET used_quantity = ? WHERE phone = ?");
            statement.setInt(1, quantity);
            statement.setString(2, customerPhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByID(int customerId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
            statement.setInt(1, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByName(String customerName) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE name = ?");
            statement.setString(1, customerName);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerByPhone(String customerPhone) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE phone = ?");
            statement.setString(1, customerPhone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> searchCustomerByName(String name) {
        List<Customer> searchResults = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phone"));
                customer.setUsedQuantity(resultSet.getInt("used_quantity"));
                searchResults.add(customer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    class Customer {
        private int ID;
        private String name;
        private String phone;
        private int usedQuantity;

        public Customer(int ID, String name, String phone) {
            this.ID = ID;
            this.name = name;
            this.phone = phone;
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

        public int getUsedQuantity() {
            return usedQuantity;
        }

        public void setUsedQuantity(int usedQuantity) {
            this.usedQuantity = usedQuantity;
        }
    }
}
