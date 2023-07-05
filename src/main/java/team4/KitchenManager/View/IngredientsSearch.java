package team4.KitchenManager.View;

import team4.KitchenManager.Controller.IngredientController;

public class IngredientsSearch extends SearchBar{
    private IngredientController Controller = new IngredientController();
    @Override
    public void search(String searchPrompt){
        this.ClearSearchResult();
        var ingredients = Controller.findByName(searchPrompt);
        if ((searchPrompt.equals("") && this.SearchResult.isEmpty()) || !searchPrompt.equals("")){
            for (var ingredient : ingredients){
                this.SearchResult.add(new Ingredients(ingredient));
                }
            }
        }
    @Override
    public String GetIdName(){
        return "Ingredient";
        }
    }
