// /*
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