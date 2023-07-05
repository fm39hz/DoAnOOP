package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;
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
    private int TotalPrice;
    private String CustomerFeedback;
    @Override
    public String toString(){
        return "orders";
        }
    }
