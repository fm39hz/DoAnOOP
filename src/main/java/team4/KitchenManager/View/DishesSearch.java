package team4.KitchenManager.View;

import javax.swing.JPanel;

import team4.KitchenManager.Controller.DishesController;

public class DishesSearch extends SearchBar{
    private DishesController Controller = new DishesController();
    public DishesSearch(JPanel target){
        super(target);
        }
    @Override
    protected void search(String searchPrompt){
        var dishes = Controller.findByName(searchPrompt);
        if ((searchPrompt.equals("") && this.SearchResult.isEmpty()) || !searchPrompt.equals("")){
            for (var dish : dishes){
                System.out.println("Found: " + dish.getName());
                this.SearchResult.add(new Dishes(dish));
                }
            }
        }
    }
