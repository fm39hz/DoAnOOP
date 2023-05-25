package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers implements HasID{
    private int ID;
    private String Name;
    private String Phone;
    @Override
    public String toString(){
        return "customers";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
