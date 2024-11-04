import java.util.ArrayList;

/*
Extend here does not relate to inheritance or interface, but in terms of templating, extends
indicates a constraint.
 */
public class Recipe<T extends Ingredient> {
    private final ArrayList<T> ingredients;
    private String name;
    private String instructions;

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public String getInstructions(){
        return instructions;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Recipe(String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = new ArrayList<>();
    }

    public T removeIngredient(int index) {
        return ingredients.remove(index);
    }
    public void addIngredient(T ingredient) {
        ingredients.add(ingredient);
    }

    public String getName(int i){
        return ingredients.get(i).getName();
    }

    public float getQuantity(int i){
        return ingredients.get(i).getQuantity();
    }

    public String toString() {
        System.out.println("Recipe for " + name);
        String oStr = "";
        for (T ingredient : ingredients) {
            oStr += "\t" + ingredient.toString() + "\n";
        }
        return oStr;
    }

    public int size() {
        return ingredients.size();
    }

    public ArrayList<T> getIngredients() {
        return ingredients;
    }
}