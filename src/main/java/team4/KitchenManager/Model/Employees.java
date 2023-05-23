package team4.KitchenManager.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Employees extends BaseModel{
    private String Name;
    private String Phone;
    private String Position;
    private int Salary;
}
