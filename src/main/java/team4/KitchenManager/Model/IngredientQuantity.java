package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientQuantity{
    private Dish TargetDish;
    private Ingredient TargetIngredient;
    private int Quantity;
    @Override
    public String toString(){
        return "quantities";
        }
    }
