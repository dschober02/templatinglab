import java.util.ArrayList;


interface Ingredient{
    String getName();
    float getQuantity();
}
class SolidIngredient implements Ingredient{
    private String name;
    private float quantity;
    public SolidIngredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public float getQuantity(){
        return quantity;
    }
    public String toString(){
        return name + ": " + quantity;
    }
}

class LiquidIngredient implements Ingredient{
    private String name;
    private float quantity;
    public LiquidIngredient(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public float getQuantity(){
        return quantity;
    }
    public String toString(){
        return name + ": " + quantity;
    }
}

class Recipe<T extends Ingredient> {
    private ArrayList<T> ingredients;
    private String name;
    public Recipe(String name){
        this.name = name;
        ingredients = new ArrayList<>();
    }
    public Recipe(String name, ArrayList<T> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public void addIngredient(T ingredient){
        ingredients.add(ingredient);
    }

    public void print(){
        System.out.println("Recipe for " + name);
        for (T ingredient : ingredients){
            System.out.println(ingredient);
        }
    }
}
public class Main {

    public static void menu(){

    }
    public static void main(String[] args) {
        Recipe<Ingredient> r = new Recipe<>("Cake");
        Ingredient si1 = new LiquidIngredient("Milk", 20);
        Ingredient si2 = new SolidIngredient("Egg", 2);
        r.addIngredient(si1);
        r.addIngredient(si2);
        r.print();
    }
}