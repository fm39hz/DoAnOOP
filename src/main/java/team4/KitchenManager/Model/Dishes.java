package team4.KitchenManager.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Dishes extends BaseModel{
    private String Name;
    private int Cost;
    private int Price;
    private List<Quantity> Quantities;
    }
