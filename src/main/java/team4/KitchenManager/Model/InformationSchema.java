package team4.KitchenManager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationSchema {
    private String Id;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    public String getInformation(){
        return "ID: " + this.Id + "\nHọ và tên: " + this.FirstName + " " + this.LastName + "\nSố điện thoại: " + this.PhoneNumber;
        }
    }
