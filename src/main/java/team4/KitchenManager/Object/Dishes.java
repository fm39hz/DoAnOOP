package team4.KitchenManager.Object;

import java.util.List;
import lombok.Data;

@Data
public class Dishes {
    private int ID;
    private String Name;
    private int Cost;
    private int Price;
    private List<Quantity> Quantities;
}
