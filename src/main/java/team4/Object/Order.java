package team4.Object;

import java.sql.Date;
import java.sql.Time;
import lombok.Data;

@Data
public class Order {
    private int ID;
    private Date CreatedDay;
    private Time CreatedTime;
    private int OrderCount;
}
