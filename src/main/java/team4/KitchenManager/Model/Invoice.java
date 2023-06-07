package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice{
    private String ID;
    private Date CreatedDay;
    private Time CreatedTime;
    private Customer Customer;
    private List<Dish> ListDishes;
    private String CustomerFeedback;
    @Override
    public String toString(){
        return "orders";
        }
    }
