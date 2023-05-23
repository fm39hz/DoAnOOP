package team4.KitchenManager.Model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Quantity extends BaseModel{
    private HashMap<Ingredient, Integer> Table;
}
