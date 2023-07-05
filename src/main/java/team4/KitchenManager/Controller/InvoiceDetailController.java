package team4.KitchenManager.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.Invoice;
import team4.KitchenManager.Model.InvoiceDetail;

public class InvoiceDetailController {
    private DatabaseConnector Connector;

    public InvoiceDetailController() {
        Connector = new DatabaseConnector(); 
        }
    public InvoiceDetailController(DatabaseConnector connector){
        this.Connector = connector;
        }
    public List<InvoiceDetail> GetAll(Invoice invoice){
        var _targetList = new ArrayList<InvoiceDetail>();
        var _dishesController = new DishesController();
        var _invoicesController = new InvoiceController();
        try {
            Statement _statement = Connector.getConnector().createStatement();
            ResultSet _result = _statement.executeQuery("SELECT * FROM invoices_detail WHERE invoice_id = " + invoice.getID());
            while (_result.next()) {
                var _targetInvoice = new InvoiceDetail();
                    _targetInvoice.setTargetInvoice((_invoicesController.getAll(_result.getString("invoice_id"))));
                    _targetInvoice.setTargetDish(_dishesController.getAll(_result.getString("dishes_id")));
                    _targetInvoice.setTargetQuantity(_result.getInt("quantity"));
                    _targetList.add(_targetInvoice);
                }
            _result.close();
            _statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _targetList;
        }
    
    }
