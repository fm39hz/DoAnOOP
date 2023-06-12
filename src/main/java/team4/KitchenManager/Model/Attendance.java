package team4.KitchenManager.Model;

import java.sql.Time;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance{
    private String ID;
    private Employee Employee;
    private Date Day;
    private Time CheckIn;
    @Override
    public String toString(){
        return "attendances";
        }
    }
