package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientQuantity implements HasID{
    private int ID;
    private Ingredient IngredientName;
    private int Quantity;
    @Override
    public String toString(){
        return "quantities";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
