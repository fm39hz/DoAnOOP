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
    private String ID;
    private Date Day;
    private Time CheckIn;
    @Override
    public String toString(){
        return "attendances";
        }
    }
