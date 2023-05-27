package team4.KitchenManager.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Data;
import team4.KitchenManager.Model.Order;

@Data
public class OrderController {
    public static List<Order> _orderList = new ArrayList<>();
    
    public static void AddOrder(Order order) {
        _orderList.add(order);
    }
    
    public static void UpdateOrder(Order updatedOrder) {
        for (Order order : _orderList) {
            if (order.getID() == updatedOrder.getID()) {
                order.setCreatedDay(updatedOrder.getCreatedDay());
                order.setCreatedTime(updatedOrder.getCreatedTime());
                order.setOrderCount(updatedOrder.getOrderCount());
                break;
            }
        }
    }
    
    public static void DeleteOrder(int orderId) {
        Iterator<Order> iterator = _orderList.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getID() == orderId) {
                iterator.remove();
                break;
            }
        }
    }
}
