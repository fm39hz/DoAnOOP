package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements HasID{
    private int ID;
    private Date CreatedDay;
    private Time CreatedTime;
    private int OrderCount;
    @Override
    public String toString(){
        return "orders";
        }
    @Override
    public int GetInstanceID() {
        return this.getID();
        }
    }
