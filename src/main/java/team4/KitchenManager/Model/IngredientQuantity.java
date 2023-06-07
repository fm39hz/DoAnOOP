package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientQuantity{
    private String ID;
    private Ingredient Ingredient;
    private int Quantity;
    @Override
    public String toString(){
        return "quantities";
        }
    }
