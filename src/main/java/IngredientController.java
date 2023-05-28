import java.util.ArrayList;
import java.util.List;
import team4.KitchenManager.Object.Ingredient;

public class IngredientController {
    private List<Ingredient> ingredients;

    public IngredientController() {
        ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }


    public void updateIngredient(Ingredient updatedIngredient) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getID() == updatedIngredient.getID()) {
                ingredient.setName(updatedIngredient.getName());
                ingredient.setInStock(updatedIngredient.getInStock());
                ingredient.setCost(updatedIngredient.getCost());
                break;
            }
        }
    }


    public void deleteIngredient(int ingredientID) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getID() == ingredientID) {
                ingredients.remove(ingredient);
                break;
            }
        }
    }


    public List<Ingredient> searchIngredientsByName(String name) {
        List<Ingredient> foundIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                foundIngredients.add(ingredient);
            }
        }
        return foundIngredients;
    }

    // Tìm kiếm nguyên liệu theo ID
    public Ingredient searchIngredientByID(int ingredientID) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getID() == ingredientID) {
                return ingredient;
            }
        }
        return null;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}