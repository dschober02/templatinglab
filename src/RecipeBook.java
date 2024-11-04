import java.util.ArrayList;

public class RecipeBook {
    private final ArrayList<Recipe> recipes;

    public RecipeBook() {
        recipes = new ArrayList<>();
    }

    public RecipeBook(int size) {
        recipes = new ArrayList<>(size);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public Recipe getRecipe(int i) {
        return recipes.get(i);
    }

    public Recipe removeRecipe(int i) {
        return recipes.remove(i);
    }

    public int size() {
        return recipes.size();
    }

    public String toString() {
        String oStr = "";
        for (int i = 0; i < recipes.size(); i++) {
            oStr += i + 1 + ".) " + recipes.get(i).getName() + "\n";
        }
        return oStr;
    }
}
