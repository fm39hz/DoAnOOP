package team4.KitchenManager.Object;

import java.util.HashMap;
import lombok.Data;

@Data
public class Quantity {
    private int ID;
    private HashMap<Ingredient, Integer> Table;
}
