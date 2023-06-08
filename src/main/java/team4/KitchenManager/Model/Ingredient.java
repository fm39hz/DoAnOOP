package team4.KitchenManager.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String ID;
    private String Name;
    private Date InDate;
    private int InStock;
    private int Cost;

    public static <T> int getUsedQuantity(T t) {
        return 0;
    }

    public void setUsedQuantity(int usedQuantity) {
    }

    public boolean getUsedQuantity() {
        return false;
    }
}
