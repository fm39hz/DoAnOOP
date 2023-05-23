package team4.KitchenManager.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Customers extends BaseModel{
    private String Name;
    private String Phone;
}
