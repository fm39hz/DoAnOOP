package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order{
    private int ID;
    private Date CreatedDay;
    private Time CreatedTime;
    private int OrderCount;
}
