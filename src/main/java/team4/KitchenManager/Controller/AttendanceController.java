package team4.KitchenManager.Controller;

import java.util.ArrayList;
import java.util.List;
import team4.KitchenManager.Model.Attendance;
import team4.KitchenManager.Model.Employees;


public class AttendanceController {
    private List<Attendance> AttendanceList = new ArrayList<>();
    public Employees[] employeeList;
   
    public void AddAttendance(Attendance attendance) {
        AttendanceList.add(attendance);
    }

public class MonthlyAttendance {
    private List<Attendance> attendanceList = new ArrayList<>();;

    // Tính lương cho nhân viên trong tháng
    public double MonthlySalary(int Employee) {
        double DailySalary = 300.0; // Lương một ngày
        int WorkingDays = 26; // Số ngày làm việc trong tháng

        int AttendanceCount = 0;// Số ngày chấm công của nhân viên
        for (Attendance attendance : attendanceList) {
            if (attendance.getEmployee() == Employee) {
                AttendanceCount++;
            }
        }
        double MonthlySalary = DailySalary * AttendanceCount * WorkingDays;
        return MonthlySalary;
    }

    // Lấy thông tin nhân viên dựa vào ID
    public Employees getEmployee(int Employee) {
        for (Employees employee : employeeList) {
            if (employee.getID() == Employee) {
                return employee;
            }
        }
        return null;
    }
}

}
