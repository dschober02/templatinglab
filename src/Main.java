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
        return name + ": " + quantity + " cups";
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

class RecipeBook{
    private ArrayList<Recipe> recipes;
    public RecipeBook(){
        recipes = new ArrayList<>();
    }
    public RecipeBook(int size){
        recipes = new ArrayList<>(size);
    }
    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }
    public Recipe getRecipe(int i){
        return recipes.get(i);
    }
    public Recipe removeRecipe(int i){
        return recipes.remove(i);
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
    // quick method to check if a string is a float
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // for displaying menu
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

    // adds as many ingredients to recipe as user wants
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
                String quantity = keyboard.nextLine();
                while (!isFloat(quantity)) {
                    System.out.print("Please enter a valid number: ");
                    quantity = keyboard.nextLine();
                }
                recipe.addIngredient(new SolidIngredient(name, Float.parseFloat(quantity)));
            }
            // input will always equal 'l' at this point
            else {
                System.out.print("Name of ingredient: ");
                String name = keyboard.nextLine();
                System.out.print("Volume in cups: ");
                String quantity = keyboard.nextLine();
                while (!isFloat(quantity)) {
                    System.out.print("Please enter a valid number: ");
                    quantity = keyboard.nextLine();
                }
                recipe.addIngredient(new SolidIngredient(name, Float.parseFloat(quantity)));
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

    // instantiates new Recipe object and adds it to RecipeBook ArrayList
    public static void createRecipe(Scanner keyboard, RecipeBook recipes){
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
        recipes.addRecipe(recipe);
    }

    // Handles flow of program
    public static void runProgram(Scanner keyboard, RecipeBook recipes) {
        int choice = menu(keyboard);
        switch (choice){
            case 1 -> createRecipe(keyboard, recipes);
            // case 2 -> displayRecipes(recipes);
            // case 3 -> deleteRecipe(keyboard, recipes);
            // case 4 -> editRecipe(keyboard, recipes);
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