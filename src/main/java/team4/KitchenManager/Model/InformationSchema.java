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
        return "\tID: " + this.Id + "\n\tHọ và tên: " + this.FirstName + " " + this.LastName + "\n\tSố điện thoại: " + this.PhoneNumber;
        }
    }
