package team4.KitchenManager.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish{
    private String ID;
    private String Name;
    private int Price;
    private String ImagePath;
    private String Description;
    private List<IngredientQuantity> Recipe;
    @Override
    public String toString(){
        return "dishes";
        }
    }
