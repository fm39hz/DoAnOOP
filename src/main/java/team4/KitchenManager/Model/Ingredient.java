package team4.KitchenManager.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Ingredient extends BaseModel{
    private String Name;
    private int InStock;
    private int Cost;
}
