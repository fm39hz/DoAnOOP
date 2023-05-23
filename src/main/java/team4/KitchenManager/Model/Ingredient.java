package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient{
    private int ID;
    private String Name;
    private int InStock;
    private int Cost;
    @Override
    public String toString(){
        return "ingredients";
        }
    }
