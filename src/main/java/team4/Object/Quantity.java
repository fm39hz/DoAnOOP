package team4.Object;

import java.util.HashMap;
import lombok.Data;

@Data
public class Quantity {
    private int ID;
    private HashMap<Ingredient, Integer> Table;
}
