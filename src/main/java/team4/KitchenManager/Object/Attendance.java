package team4.KitchenManager.Object;

import java.sql.Time;
import java.util.Date;
import lombok.Data;

@Data
public class Attendance {
    private int ID;
    private int Employee;
    private Date Day;
    private Time CheckIn;
}
