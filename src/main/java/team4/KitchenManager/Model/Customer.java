package team4.KitchenManager.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Customer extends InformationSchema{
    @Override
    public String toString(){
        return "customers";
        }
    }
