package team4.KitchenManager.Model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Order extends BaseModel{
    private Date CreatedDay;
    private Time CreatedTime;
    private int OrderCount;
}
