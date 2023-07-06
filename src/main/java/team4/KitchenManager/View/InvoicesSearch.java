package team4.KitchenManager.View;

import team4.KitchenManager.Controller.InvoiceController;

public class InvoicesSearch extends SearchBar{
    private InvoiceController Controller = new InvoiceController();
    @Override
    public void search(String searchPrompt){
        this.ClearSearchResult();
        // var employees = Controller.searchEmployeeByName(searchPrompt);
        // if ((searchPrompt.equals("") && this.SearchResult.isEmpty()) || !searchPrompt.equals("")){
        //     for (var employee : employees){
        //         this.SearchResult.add(new Employees(employee));
        //         }
        //     }
        }
    @Override
    public String GetIdName(){
        return "Invoice";
        }
    }
