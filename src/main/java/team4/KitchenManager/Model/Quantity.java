package team4.KitchenManager.Model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quantity{
    private int ID;
    private HashMap<Ingredient, Integer> Table;
    @Override
    public String toString(){
        return "quantities";
        }
    }
