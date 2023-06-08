package team4.KitchenManager.Controller;// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package team4.KitchenManager.Controller;
// import team4.KitchenManager.Model.Ingredient;
// import team4.KitchenManager.DAO.DatabaseConnector;
// import javax.swing.*;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import team4.KitchenManager.Object.Ingredient;
// /**
//  *
//  * @author ADMIN
//  */
// public class IngredientController {
//   DatabaseConnector conn = null;
//     public IngredientController() {
//         conn = new DatabaseConnector();   
// }
// public List<Ingredient> getAllIngredient() { 
//     List<Ingredient> _list = new ArrayList<>();
//     Ingredient _ingredient = new Ingredient();
//     _ingredient.setName("Ten nguyen lieu");
//         _ingredient.setCost(100000);
//         _ingredient.setInStock(100);
//         _ingredient.setID(300);
// return _list;
// }
// public void AddIngredient(MouseEvent){ 
//    // thao tác  

//    ingredient.setID(txtID.getText());
//    ingredient.setName(txtName.getName());
//    ingredient.setInStock(txtInStock.getInStock());
//    ingredient.setCost(txtCost.getCost);
//    list.add(ingredient);
// }
// public void UpdateIngredient(Mouse event){
//     for (Ingredient ingredient : list){
//         if (ingredient.getID().equal(txtID.getText())){
//            ingredient.setName(txtName.getName());
//            ingredient.setInStock(txtInStock.getInStock());
//            ingredient.setCost(txtCost.getCost());
//      break;
//         }
//     }
// }
// public void DeleteIngredient(Mouse event){
//     for (Ingredient ingredient : list){
//         if (ingredient.getID().equal(txtID.getText())){
//             list.remove(ingredient);
//             break;
//         }
//     }
// }
import team4.KitchenManager.Model.*;
import team4.KitchenManager.DAO.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IngredientController {
    private Connection connection;
    DatabaseConnector conn = null;
    public void IngredientController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
    }



    // Các phương thức khác của quản lý nguyên liệu...

    public void displayIngredients() {
        System.out.println("ID | Tên nguyên liệu | Cost | Số lượng còn lại | Số lượng đã sử dụng trong ngày | Món ăn sử dụng");

        List<Ingredient> ingredients = getIngredients();

        for (Ingredient ingredient : ingredients) {
            System.out.print(ingredient.getID() + " | ");
            System.out.print(ingredient.getName() + " | ");
            System.out.print(ingredient.getCost() + " | ");
            System.out.print(ingredient.getInStock() + " | ");
            System.out.print(ingredient.getUsedQuantity() + " | ");
            System.out.println(getDishesUsingIngredient(ingredient.getID()));
        }
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("StELECT * FROM ingrediens");
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setID(resultSet.getString("id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setInDate(resultSet.getDate("in_date"));
                ingredient.setInStock(resultSet.getInt("in_stock"));
                ingredient.setCost(resultSet.getInt("cost"));
                ingredient.setUsedQuantity(resultSet.getInt("used_quantity"));
                ingredients.add(ingredient);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public List<String> getDishesUsingIngredient(String ingredientId) {
        List<String> dishes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT dish_name FROM dishes_ingredients WHERE ingredient_id = ?");
            statement.setString(1, ingredientId);
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

    public void editIngredient(String ingredientId, String newName, int newCost) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ingredients SET name = ?, cost = ? WHERE id = ?");
            statement.setString(1, newName);
            statement.setInt(2, newCost);
            statement.setString(3, ingredientId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIngredient(Ingredient newIngredient) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingredients (name, in_date, in_stock, cost) VALUES (?, ?, ?, ?)");
            statement.setString(1, newIngredient.getName());
            statement.setDate(2, newIngredient.getInDate());
            statement.setInt(3, newIngredient.getInStock());
            statement.setInt(4, newIngredient.getCost());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refillIngredient(String ingredientId, int quantity) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ingredients SET in_stock = in_stock + ? WHERE id = ?");
            statement.setInt(1, quantity);
            statement.setString(2, ingredientId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredient(String ingredientId) {
        try {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM ingredients WHERE id = ?")) {
                statement.setString(1, ingredientId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }

    public void sortIngredientsByUsage() {
        List<Ingredient> ingredients = getAllIngredients();
        ingredients.sort(Comparator.comparingInt(Ingredient::getUsedQuantity).reversed());
    }

    private List<Ingredient> getAllIngredients() {
        return null;
    }

    public void sortIngredientsByCost() {
        List<Ingredient> ingredients = getAllIngredients();
        ingredients.sort(Comparator.comparingInt(Ingredient::getCost).reversed());
    }

    public void sortIngredientsByInDate() {
        List<Ingredient> ingredients = getAllIngredients();
        ingredients.sort(Comparator.comparing(Ingredient::getInDate));
    }

    public List<Ingredient> searchIngredientsByName(String name) {
        List<Ingredient> searchResults = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setID(resultSet.getString("id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setInDate(resultSet.getDate("in_date"));
                ingredient.setInStock(resultSet.getInt("in_stock"));
                ingredient.setCost(resultSet.getInt("cost"));
                ingredient.setUsedQuantity(resultSet.getInt("used_quantity"));
                searchResults.add(ingredient);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

}