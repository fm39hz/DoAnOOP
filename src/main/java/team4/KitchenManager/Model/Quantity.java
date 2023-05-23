package team4.KitchenManager.Model;

import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Quantity extends BaseModel{
    private HashMap<Ingredient, Integer> Table;
}
