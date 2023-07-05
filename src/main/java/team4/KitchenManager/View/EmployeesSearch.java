package team4.KitchenManager.View;

import team4.KitchenManager.Controller.EmployeeController;

public class EmployeesSearch extends SearchBar{
    private EmployeeController Controller = new EmployeeController();
    @Override
    public void search(String searchPrompt){
        this.ClearSearchResult();
        var employees = Controller.searchEmployeeByName(searchPrompt);
        int i = 0;
        if ((searchPrompt.equals("") && this.SearchResult.isEmpty()) || !searchPrompt.equals("")){
            for (var employee : employees){
                this.SearchResult.add(new Employees(employee));
                i++;
                }
            }
        System.out.println(i);
        }
    @Override
    public String GetIdName(){
        return "Employee";
        }
    }
