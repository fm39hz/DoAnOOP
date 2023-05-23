package team4.KitchenManager.Model;

import java.sql.Time;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Attendance extends BaseModel{
    private int Employee;
    private Date Day;
    private Time CheckIn;
}
