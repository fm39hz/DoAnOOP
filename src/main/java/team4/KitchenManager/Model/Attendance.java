package team4.KitchenManager.Model;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance{
    private int ID;
    private int Employee;
    private Date Day;
    private Time CheckIn;
}
