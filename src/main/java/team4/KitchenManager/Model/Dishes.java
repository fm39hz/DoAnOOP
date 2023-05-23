package team4.KitchenManager.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dishes{
    private int ID;
    private String Name;
    private int Cost;
    private int Price;
    private List<Quantity> Quantities;
    @Override
    public String toString(){
        return "dishes";
        }
    }
