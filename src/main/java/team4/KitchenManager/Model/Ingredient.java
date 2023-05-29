package team4.KitchenManager.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements HasID{
    private int ID;
    private String Name;
    private Date InDate;
    private int InStock;
    private int Cost;
    @Override
    public String toString(){
        return "ingredients";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
