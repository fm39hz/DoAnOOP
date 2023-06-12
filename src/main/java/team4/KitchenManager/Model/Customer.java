package team4.KitchenManager.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Customer extends InformationSchema{
    private List<Invoice> OrderHistory;
    @Override
    public String toString(){
        return "customers";
        }
    }
