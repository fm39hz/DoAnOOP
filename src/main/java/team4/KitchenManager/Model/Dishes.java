package team4.KitchenManager.Model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Dishes extends BaseModel{
    private String Name;
    private int Cost;
    private int Price;
    private List<Quantity> Quantities;
    }
