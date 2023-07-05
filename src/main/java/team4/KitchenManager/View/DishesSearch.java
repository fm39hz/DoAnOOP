package team4.KitchenManager.View;

import team4.KitchenManager.Controller.DishesController;

public class DishesSearch extends SearchBar{
    private DishesController Controller = new DishesController();
    @Override
    public void search(String searchPrompt){
        this.ClearSearchResult();
        var dishes = Controller.findByName(searchPrompt);
        if ((searchPrompt.equals("") && this.SearchResult.isEmpty()) || !searchPrompt.equals("")){
            for (var dish : dishes){
                this.SearchResult.add(new Dishes(dish));
                }
            }
        }
    @Override
    public String GetIdName(){
        return "Dish";
        }
    }
