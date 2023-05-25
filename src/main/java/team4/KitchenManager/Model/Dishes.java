package team4.KitchenManager.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dishes implements HasID{
    private int ID;
    private String Name;
    private int Cost;
    private int Price;
    private List<IngredientQuantity> Quantities;
    @Override
    public String toString(){
        return "dishes";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
