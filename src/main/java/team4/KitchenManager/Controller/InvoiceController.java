package team4.KitchenManager.Controller;

import java.text.SimpleDateFormat;
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
                    _target.setID(_result.getString("id"));
                    _target.setCreatedDay(_result.getDate("created_day"));
                    _target.setCreatedTime(_result.getTime("created_time"));
                    _target.setCustomer(new CustomerController(this.conn).getCustomer(_result.getString("customer_id")));
                    _target.setCustomerFeedback(_result.getString("customer_feedback"));
                    _target.setTotalPrice(_result.getInt("total_price"));
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
                    _target.setID(_result.getString("id"));
                    _target.setCreatedDay(_result.getDate("created_day"));
                    _target.setCreatedTime(_result.getTime("created_time"));
                    _target.setCustomer(new CustomerController(this.conn).getCustomer(_result.getString("customer_id")));
                    _target.setCustomerFeedback(_result.getString("customer_feedback"));
                    _target.setTotalPrice(_result.getInt("total_price"));
                    _invoices.add(_target);
                    }
            } 
        catch (SQLException e) {
            e.printStackTrace();
            }
        return _invoices;
        }
    public void addInvoice(Customer customer, HashMap<Dish,Integer> list) {
        // Tạo hóa đơn và thêm vào cơ sở dữ liệu
        String sql = "INSERT INTO invoices (id, customer_id, created_day, created_time) VALUES (?, ?, ?, ?)";

        try (var statement = conn.getConnector().prepareStatement(sql)) {
            Date currentDate = new Date(System.currentTimeMillis());
            Time currentTime = new Time(System.currentTimeMillis());

            // Thêm thông tin hóa đơn vào câu lệnh SQL
            var id = idGenerator(); // tự động tạo id đơn hàng theo id đã có trong db
            statement.setString(1, id);
            statement.setString(2, customer.getId());
            statement.setDate(3, currentDate);
            statement.setTime(4, currentTime);

            // Thực thi câu lệnh SQL
            statement.executeUpdate();

            addDishesToInvoice(id, list);
            var total_price = calculateTotalAmount(id);
            String sql2 = "UPDATE `invoices` SET `total_price` = "+total_price+" WHERE id = "+id;
            conn.getConnector().prepareStatement(sql2).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDishesToInvoice(String invoiceID, HashMap<Dish,Integer> list) {
        String sql = "INSERT INTO invoice_detail (invoice_id, dishes_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (var statement = conn.getConnector().prepareStatement(sql)) {
            statement.setString(1, invoiceID);
            for (Dish dish : list.keySet()) { // duyệt từng dish trong hashmap (dish, số lượng)
                statement.setString(2, dish.getID());
                statement.setInt(3,list.get(dish)); //lấy số lượng dish theo index
                statement.setInt(4,dish.getPrice() * list.get(dish));
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int calculateTotalAmount(String invoice_id) {
        int TotalAmount = 0;

        // Truy vấn cơ sở dữ liệu để tính tổng giá tiền
        String sql = "SELECT SUM(price) FROM invoice_detail WHERE `invoice_id` = ?";

        try {
            var statement = conn.getConnector().prepareStatement(sql);
            statement.setString(1,invoice_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                TotalAmount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TotalAmount;
    }

//    public void recordPayment(String customerID, int amountPaid) {
//        // Cập nhật thông tin thanh toán trong bảng customers
//        String sql = "UPDATE customers SET amount_paid = ? WHERE id = ?";
//
//        try (var statement = conn.getConnector().prepareStatement(sql)) {
//            statement.setDouble(1, amountPaid);
//            statement.setString(2, customerID);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            }
//        }
    
//    public void addCustomer(Customer customer) {
//        // Thêm khách hàng vào cơ sở dữ liệu
//        String sql = "INSERT INTO customers (id,first_Name, last_Name) VALUES (?, ?, ?)";
//
//        try (var statement = conn.getConnector().prepareStatement(sql)) {
//            statement.setString(1, customer.getId());
//            statement.setString(2, customer.getFirstName());
//            statement.setString(3, customer.getLastName());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
    
    public List<Invoice> sortInvoicesByDateTime(boolean desc) {
        List<Invoice> sortedInvoices = new ArrayList<>();
    
        // Truy vấn cơ sở dữ liệu để lấy hóa đơn và sắp xếp theo ngày giờ
        String sortby = "";
        if (desc) {
            sortby = "DESC";
        } else {
            sortby = "ASC";
        }
        String sql = "SELECT * FROM `kitchen`.`invoices` ORDER BY `created_day` "+sortby+", `created_time`";
    
        try (var statement = conn.getConnector().createStatement();
            ResultSet _result = statement.executeQuery(sql)) {
            while (_result.next()) {
                var _target = new Invoice();
                _target.setID(_result.getString("id"));
                _target.setCreatedDay(_result.getDate("created_day"));
                _target.setCreatedTime(_result.getTime("created_time"));
                _target.setCustomer(new CustomerController(this.conn).getCustomer(_result.getString("customer_id")));
                _target.setCustomerFeedback(_result.getString("customer_feedback"));
                _target.setTotalPrice(_result.getInt("total_price"));
                sortedInvoices.add(_target);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedInvoices;
    }
    private String idGenerator() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datePart = dateFormat.format(currentDate);
        String sql = "SELECT `id` FROM `invoices` ORDER BY `id` DESC LIMIT 1;";
        String id = "";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            var rs = ps.executeQuery();
            if (rs.first()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id == "") {
            id = datePart+"0001";
        } else {
            var _invoice_no = Integer.parseInt(id.substring(8,12))+1;
            id = datePart + String.format("%04d", _invoice_no);
        }
        return id;

    }
    
}
