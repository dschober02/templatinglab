import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


interface Ingredient{
    String getName();
    float getQuantity();
}
class SolidIngredient implements Ingredient{
    private String name;
    private float quantity;
    public SolidIngredient(String name, float quantity) {
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
    public LiquidIngredient(String name, float quantity){
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
    /*
    Flow of the program:
        Print Menu => menu()
            1.) Create new Recipe => createRecipe()     --done--
                Ask and validate name for recipe
                Create Recipe Object
                Add Ingredients to the recipe and add to recipe's arrayList => ingredientHandler()
                Add Recipe to recipe arrayList

            2.) View all recipes
                Use toString and display to console => displayRecipes()

            3.) Delete an existing recipe
                Use toString and display to console with an index => displayRecipesIndexed()
                Prompt user to enter which recipe they would like to remove and have a cancel value
                Validate user input
                Cancel value exits or use valid index to remove from arraylist

            4.) Edit an existing Recipe
                Use toString and display to console with an index => displayRecipesIndexed()
                Prompt user to enter which recipe they would like to remove and have a cancel value
                Validate user input
                Cancel exits and valid index gets recipe:

                    1.) Remove Ingredient
                        List ingredients indexed
                        Prompt user which ingredient they would like to remove and have a cancel value
                        Validate user input
                        Cancel exits and valid index removes ingredient at the specified index

                    2.) Add ingredient => ingredientHandler()

            5.) Exit ends the program with a nice message

     */
    public static int menu(Scanner keyboard){
        System.out.println("            Recipe Program");
        System.out.println("________________________________________");
        System.out.println("1. Create new recipe");
        System.out.println("2. View all recipes");
        System.out.println("3. Delete an existing recipe");
        System.out.println("4. Edit an existing Recipe");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        String choice = keyboard.next();
        while (!choice.equals("5") && !choice.equals("4") && !choice.equals("3") && !choice.equals("2") && !choice.equals("1")){
            System.out.print("Please enter a valid integer: ");
            choice = keyboard.next();
        }
        return Integer.parseInt(choice);
    }

    public static void ingredientHandler(Scanner keyboard, Recipe<Ingredient> recipe){
        String yes = "y";
        while (yes.equals("y")) {
            System.out.print("Ingredient Solid or Liquid? (s/l): ");
            String input = keyboard.nextLine().toLowerCase();
            while (!input.equals("s") && !input.equals("l")) {
                System.out.print("Please input characters s or l: ");
                input = keyboard.nextLine();
            }
            if (input.equals("s")) {
                System.out.print("Name of ingredient: ");
                String name = keyboard.nextLine().toLowerCase();
                System.out.print("Quantity: ");
                float quantity = Float.parseFloat(keyboard.nextLine());
                recipe.addIngredient(new SolidIngredient(name, quantity));
            }
            // input will always equal 'l' at this point
            else {
                System.out.print("Name of ingredient: ");
                String name = keyboard.nextLine();
                System.out.print("Volume in cups: ");
                float quantity = Float.parseFloat(keyboard.nextLine());
                recipe.addIngredient(new SolidIngredient(name, quantity));
            }
            // Prompt user to add another ingredient
            System.out.print("Would you like to add an ingredient? (y/n): ");
            yes = keyboard.nextLine().toLowerCase();
            while (!yes.equals("y") && !yes.equals("n")) {
                System.out.print("Please input characters y or n: ");
                yes = keyboard.nextLine();
            }
        }
    }
    public static Recipe<Ingredient> createRecipe(Scanner keyboard){
        String yes = "n";
        String name = "";
        while (yes.equals("n")){
            System.out.print("Enter recipe name: ");
            name = keyboard.nextLine();
            System.out.print(name + " was entered, is this okay? (y/n): ");
            yes = keyboard.nextLine().toLowerCase();
            while (!yes.equals("y") && !yes.equals("n")){
                System.out.print("Please input characters y or n: ");
                yes = keyboard.nextLine();
            }
        }
        Recipe<Ingredient> recipe = new Recipe<>(name);
        System.out.print("Would you like to add an ingredient? (y/n): ");
        yes = keyboard.nextLine().toLowerCase();
        while (!yes.equals("y") && !yes.equals("n")){
            System.out.print("Please input characters y or n: ");
            yes = keyboard.nextLine();
        }
        ingredientHandler(keyboard, recipe);
        return recipe;
    }

    public static void runProgram(Scanner keyboard, Recipe<Ingredient> recipe) {
        int choice = menu(keyboard);
        switch (choice){
            case 1 -> createRecipe(keyboard);
            //case 2 -> displayRecipes();
            // case 3 -> deleteRecipe();
            // case 4 -> editRecipe();
            default -> System.out.println("\t\t Have a nice day :)");
        }
        System.out.println("Enter");
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