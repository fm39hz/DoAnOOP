package team4.KitchenManager.Controller;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import team4.KitchenManager.Model.Customer;
import team4.KitchenManager.Model.Dish;
import team4.KitchenManager.Model.Invoice;
import team4.KitchenManager.DAO.DatabaseConnector;

public class InvoiceController {
    DatabaseConnector conn = null;
    public InvoiceController(){
        conn = new DatabaseConnector();
    }
    public InvoiceController(DatabaseConnector connector){
        this.conn = connector;
        }
    public List<Invoice> getAll(){
        var _query = "SELECT * FROM invoices";
        List<Invoice> _invoices = new ArrayList<>();
        try {
            var _statement = conn.getConnector().prepareStatement(_query);
            var _result = _statement.executeQuery();
                while (_result.next()){
                    var _target = new Invoice();
                    _target.setID(_result.getString(1));
                    _target.setCreatedDay(_result.getDate(2));
                    _target.setCreatedTime(_result.getTime(3));
                    _target.setCustomer(new CustomerController(this.conn).getCustomer(_result.getString(4)));
                    _target.setCustomerFeedback(_result.getString(5));
                    _invoices.add(_target);
                    }
            } 
        catch (SQLException e) {
            e.printStackTrace();
            }
        return _invoices;
        }
    public List<Invoice> getAll(Customer customer){
        var _query = "SELECT * FROM invoices WHERE customer_id = ?";
        List<Invoice> _invoices = new ArrayList<>();
        try {
            var _statement = conn.getConnector().prepareStatement(_query);
                _statement.setString(1, customer.getId());
            var _result = _statement.executeQuery();
                while (_result.next()){
                    var _target = new Invoice();
                    _target.setID(_result.getString(1));
                    _target.setCreatedDay(_result.getDate(2));
                    _target.setCreatedTime(_result.getTime(3));
                    _target.setCustomer(customer);
                    _target.setCustomerFeedback(_result.getString(5));
                    _invoices.add(_target);
                    }
            } 
        catch (SQLException e) {
            e.printStackTrace();
            }
        return _invoices;
        }
    public void addInvoice(Customer customer, List<Dish> dishes) {
        // Tạo hóa đơn và thêm vào cơ sở dữ liệu
        String sql = "INSERT INTO invoices (customer_id, created_day, created_time) VALUES (?, ?, ?)";

        try (var statement = conn.getConnector().prepareStatement(sql)) {
            Date currentDate = new Date(System.currentTimeMillis());
            Time currentTime = new Time(System.currentTimeMillis());

            // Thêm thông tin hóa đơn vào câu lệnh SQL
            statement.setString(1, customer.getId());
            statement.setDate(2, currentDate);
            statement.setTime(3, currentTime);

            // Thực thi câu lệnh SQL
            statement.executeUpdate();

            // Lấy ID được tạo tự động cho hóa đơn mới
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int invoiceId = generatedKeys.getInt(1);

                // Thêm các món ăn trong hóa đơn vào bảng dishes_in_invoice
                addDishesToInvoice(invoiceId, dishes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDishesToInvoice(int invoiceID, List<Dish> dishes) {
        String sql = "INSERT INTO dishes_in_invoice (invoice_id, dish_id) VALUES (?, ?)";

        try (var statement = conn.getConnector().prepareStatement(sql)) {
            for (Dish dish : dishes) {
                statement.setInt(1, invoiceID);
                statement.setString(2, dish.getID());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalAmount() {
        double TotalAmount = 0;

        // Truy vấn cơ sở dữ liệu để tính tổng giá tiền
        String sql = "SELECT SUM(price) FROM dishes JOIN dishes_in_invoice ON dishes.id = dishes_in_invoice.dish_id";

        try (var statement = conn.getConnector().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                TotalAmount = resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TotalAmount;
    }

    public void recordPayment(String customerID, double amountPaid) {
        // Cập nhật thông tin thanh toán trong bảng customers
        String sql = "UPDATE customers SET amount_paid = ? WHERE id = ?";

        try (var statement = conn.getConnector().prepareStatement(sql)) {
            statement.setDouble(1, amountPaid);
            statement.setString(2, customerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            }
        }
    
    public void addCustomer(Customer customer) {
        // Thêm khách hàng vào cơ sở dữ liệu
        String sql = "INSERT INTO customers (id,first_Name, last_Name) VALUES (?, ?, ?)";
    
        try (var statement = conn.getConnector().prepareStatement(sql)) {
            statement.setString(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Truy vấn cơ sở dữ liệu để đếm số lượng hóa đơn trong ngày
    public int CountInvoicesByDate(Date date) {
        int Count = 0;
        String sql = "SELECT COUNT(*) FROM invoices WHERE created_day = ?";
    
        try (var statement = conn.getConnector().prepareStatement(sql)) {
            statement.setDate(1, date);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Count;
    }

    // Truy vấn cơ sở dữ liệu để lấy danh sách phản hồi
    public Map<String, String> listFeedback() {
        Map<String, String> feedbackMap = new HashMap<>();

        String sql = "SELECT customers.name, invoices.feedback FROM invoices JOIN customers ON invoices.customer_id = customers.id";
    
        try (var statement = conn.getConnector().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String customerName = resultSet.getString("name");
                String feedback = resultSet.getString("feedback");
                feedbackMap.put(customerName, feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackMap;
    }
    
    public List<Invoice> sortInvoicesByDateTime() {
        List<Invoice> sortedInvoices = new ArrayList<>();
    
        // Truy vấn cơ sở dữ liệu để lấy hóa đơn và sắp xếp theo ngày giờ
        String sql = "SELECT invoices.id, invoices.created_day, invoices.created_time, customers.first_name, customers.last_name " +
                     "FROM invoices JOIN customers ON invoices.customer_id = customers.id " +
                     "ORDER BY invoices.created_day DESC, invoices.created_time DESC";
    
        try (var statement = conn.getConnector().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String invoiceId = resultSet.getString(1);
                Date createdDay = resultSet.getDate(2);
                Time createdTime = resultSet.getTime(3);
                String customerFirstName = resultSet.getString(4);
                String customerLastName = resultSet.getString(5);
    
                Customer customer = new Customer();
                customer.setFirstName(customerFirstName);
                customer.setLastName(customerLastName);
    
                Invoice invoice = new Invoice(invoiceId, createdDay, createdTime, customer, null, null);
                sortedInvoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedInvoices;
    }
    
}
