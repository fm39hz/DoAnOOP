package team4.KitchenManager.DAO;

import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import team4.KitchenManager.Model.HasID;

public class MainDAO{
    private DatabaseConnector Connector;
    public MainDAO(){
        this.Connector = new DatabaseConnector();
        }
    public MainDAO(String url, String userName, String password){
        this.Connector = new DatabaseConnector(url, userName, password);
        }
    public MainDAO(DatabaseConnector connector) {
        this.Connector = connector;
        }
    public DatabaseConnector getConnector(){
        return this.Connector;
        }
    public Boolean getConnectionState(){
        return this.Connector.getConnectionState();
        }
    public void Add(Object object) throws SQLException {
        var _class = object.getClass();
        var _property = _class.getDeclaredFields();
        var _query  = "INSERT INTO " + object + " (" + PostStringBuilder(_property);
        var _statement = Connector.getConnector().prepareStatement(_query);
            for (int i = 0; i < _property.length; i++) {
                try {
                    var _value = _class.getMethod(GetGetterMethod(_property[i].getName())).invoke(object);
                    _statement = SetValue(_value, _statement, i + 1);
                    } catch (Exception e) {
                        System.out.print("Exception occur at " + _property[i].getName());
                        e.printStackTrace();
                        }
                }
            _statement.executeQuery();
            System.out.println("Added 1 item to table " + object);
        }
    public void Remove(Object object) throws SQLException {
        var _id = 0;
        try{
            var _query  = "DELETE FROM " + object + " WHERE id = ?";
            var _statement = Connector.getConnector().prepareStatement(_query);
                if (object instanceof HasID){
                    var _hasIDInstance = (HasID)object;
                        _id = _hasIDInstance.GetInstanceID();
                        _statement.setInt(1, _id);
                    }
                _statement.executeQuery();
            }
        catch (Exception exception){
            exception.printStackTrace();
            }
            System.out.println("deleted item with id " + _id + " at " + object);
        }
    public void Remove(String tableName, int id) throws SQLException{
        try{
            var _query = "DELETE FROM " + tableName + "WHERE id = ?";
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setInt(1, id);
                _statement.executeQuery();
            }
        catch (Exception exception){
            exception.printStackTrace();
            return;
            }
            System.out.println("deleted item with id " + id + "at" + tableName);
        }
    public Object Get(Object tempObject, int id) throws SQLException{
        var _class = tempObject.getClass();
        var _property = _class.getDeclaredFields();
        var _query  = "SELECT * FROM " + tempObject + " WHERE id = ?";
        var _statement = Connector.getConnector().prepareStatement(_query);
            _statement.setInt(1, id);
        var _resultSet = _statement.executeQuery();
            while(_resultSet.next()){
                for (int i = 0; i < _property.length; i++){
                    try{
                        var _result = GetValue(_property[i].getType(), _resultSet, i + 1);
                        if ((HasID.class.isAssignableFrom(_property[i].getType())) && _result.getClass() == Integer.class){
                            var _tempType = _property[i].getType().getDeclaredConstructor();
                            _class.getMethod(GetSetterMethod(_property[i].getName()), _property[i].getType())
                                .invoke(tempObject, this.Get(_tempType.newInstance(), (int)_result));
                            }
                        else{
                            _class.getMethod(GetSetterMethod(_property[i].getName()), _property[i].getType())
                                .invoke(tempObject, _result);
                            }
                        } catch (Exception exception){
                            exception.printStackTrace();
                            return null;
                            }
                    }
                System.out.println("Got 1 item satisfied with id " + id + " at table " + tempObject);
                }
        return tempObject;
        }
    //Các hàm static để sử dụng nội bộ trong class
    private static PreparedStatement SetValue(Object value, PreparedStatement statement, int index) throws SQLException {
        var _propertyType = value.getClass();
            if (_propertyType == String.class){
                statement.setString(index, (String)value);
                }
            else if (_propertyType == Integer.class){
                statement.setInt(index, (int)value);
                }
            else if (value instanceof HasID){
                var _hasIDInstance = (HasID)value;
                    statement.setInt(index, _hasIDInstance.GetInstanceID());
                }
        return statement;
        }
    private Object GetValue(Class<?> propertyType, ResultSet resultSet, int index) throws SQLException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (HasID.class.isAssignableFrom(propertyType)){
            var _value = resultSet.getInt(index);
            var _tempObject = propertyType.getDeclaredConstructor();
            var _result = this.Get(_tempObject.newInstance(), _value);
            var _id = propertyType.getMethod("getID").invoke(_result);
            return _id;
            }
        else if (propertyType == String.class){
            return (Object)resultSet.getString(index);
            }
        else if (propertyType == int.class){
            Integer _value = resultSet.getInt(index);
            return (Object)_value;
            }
        return null;
        }
    private static String PostStringBuilder(Field[] property){
        var _quantity = property.length;
        var _sqlString = "";
            for (int i = 0 ; i < _quantity; i++){
                _sqlString += property[i].getName() + RepeatedStringBuilder(_quantity - 1, i, ", ", ") VALUES (");
                }
            for (int i = 0 ; i < _quantity; i++){
                _sqlString += "?" + RepeatedStringBuilder(_quantity - 1, i, ", ", ");");
                }
        return _sqlString;
        }
    private static String RepeatedStringBuilder(int frequency, int index, String repeated, String end){
        if (frequency > index){
            return repeated;
            }
        else{
            return end;
            }
        }
    private static String GetGetterMethod(String fieldName) {
        return "get" + MatchNamingRules(fieldName);
        }
    private static String GetSetterMethod(String fieldName) {
        return "set" + MatchNamingRules(fieldName);
        }
    private static String MatchNamingRules(String fieldName){
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
    }
