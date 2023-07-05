package team4.KitchenManager.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {
    private Invoice TargetInvoice;
    private Dish TargetDish;
    private int TargetQuantity;
    }
