package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Order extends BaseModel{
    private Date CreatedDay;
    private Time CreatedTime;
    private int OrderCount;
}
