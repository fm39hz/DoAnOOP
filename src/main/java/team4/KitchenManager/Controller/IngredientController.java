package team4.KitchenManager.Controller;

import team4.KitchenManager.Model.*;
import team4.KitchenManager.DAO.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IngredientController {
    DatabaseConnector conn = null;
    public IngredientController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
    }
    public IngredientController(DatabaseConnector con){
        this.conn = con;
        }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            Statement statement = conn.getConnector().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ingredients");
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setID(resultSet.getString("id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setInDate(resultSet.getDate("date_in"));
                ingredient.setInStock(resultSet.getInt("in_stock"));
                ingredient.setCost(resultSet.getInt("cost"));
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
            PreparedStatement statement = conn.getConnector().prepareStatement("SELECT dish_name FROM dishes_ingredients WHERE ingredient_id = ?");
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
            PreparedStatement statement = conn.getConnector().prepareStatement("UPDATE ingredients SET name = ?, cost = ? WHERE id = ?");
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
            PreparedStatement statement = conn.getConnector().prepareStatement("INSERT INTO ingredients (name, in_date, in_stock, cost) VALUES (?, ?, ?, ?)");
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
            PreparedStatement statement = conn.getConnector().prepareStatement("UPDATE ingredients SET in_stock = in_stock + ? WHERE id = ?");
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
            try (PreparedStatement statement = conn.getConnector().prepareStatement("DELETE FROM ingredients WHERE id = ?")) {
                statement.setString(1, ingredientId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }

    // public void sortIngredientsByUsage() {
    //     List<Ingredient> ingredients = getAllIngredients();
    //     ingredients.sort(Comparator.comparingInt(Ingredient::getUsedQuantity).reversed());
    // }

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
            PreparedStatement statement = conn.getConnector().prepareStatement("SELECT * FROM ingredients WHERE name LIKE ?");
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setID(resultSet.getString("id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setInDate(resultSet.getDate("in_date"));
                ingredient.setInStock(resultSet.getInt("in_stock"));
                ingredient.setCost(resultSet.getInt("cost"));
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