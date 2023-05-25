package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees implements HasID{
    private int ID;
    private String Name;
    private String Phone;
    private String Position;
    private int Salary;
    @Override
    public String toString(){
        return "employees";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
