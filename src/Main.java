import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// todo: Make Solid Ingredient in grams
// todo: Set up instructions

interface Ingredient {
    String getName();
    void setQuantity(float quantity);
    float getQuantity();
}

class SolidIngredient implements Ingredient {
    private final String name;
    private float grams;

    public SolidIngredient(String name, float quantity) {
        this.name = name;
        this.grams = quantity;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(float quantity) {
        this.grams = quantity;
    }

    public float getQuantity() {
        return grams;
    }

    public String toString() {
        return name + ": " + grams;
    }
}

class LiquidIngredient implements Ingredient {
    private final String name;
    private float quantity;

    public LiquidIngredient(String name, float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return name + ": " + quantity + " cups";
    }
}
/*
Extend here does not relate to inheritance or interface, but in terms of templating, extends
indicates a constraint.
 */
class Recipe<T extends Ingredient> {
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
}

class RecipeBook {
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

public class Main {

    // quick method to check if a string is a float
    public static boolean isFloat(String str) {
        try {
            // in this case, we only want positive numbers
            return !(Float.parseFloat(str) < 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int validateNumber(int max, Scanner keyboard){
        boolean valid = false;
        int num = -1;
        int tries = 0;
        while (!valid) {
            if (tries > 0) {
                System.out.println("please enter a vlid ");
            }
            String number = keyboard.nextLine();
            try {
                num = Integer.parseInt(number);
                if (num >= 0 && num <= max)
                    valid = true;
            } catch (Exception e) {
                System.out.print("Invalid number, please try again: ");
            }
        }
        return num;
    }

    // for displaying menu
    public static int menu(Scanner keyboard) {
        System.out.println("\n\n            Recipe Program");
        System.out.println("________________________________________");
        System.out.println("1. Create new recipe");
        System.out.println("2. View a recipe");
        System.out.println("3. View all recipes");
        System.out.println("4. Delete an existing recipe");
        System.out.println("5. Edit an existing Recipe");
        System.out.println("6. Exit");
        System.out.print("\t\tEnter your choice: ");
        String choice = keyboard.nextLine();
        while (!choice.equals("6") && !choice.equals("5") && !choice.equals("4") && !choice.equals("3") && !choice.equals("2") && !choice.equals("1")) {
            System.out.print("Please enter a valid integer: ");
            choice = keyboard.nextLine();
        }
        return Integer.parseInt(choice);
    }

    public static String createInstructions(Scanner keyboard){
        String oStr = "";
        int i = 1;
        System.out.print("Enter instruction " + i + " or enter 0 to exit: ");
        String input = keyboard.nextLine();
        while (!input.equals("0")) {
            oStr += i++ + ".) " + input + "\n";
            System.out.print("Enter instruction " + i + " or enter 0 to exit: ");
            input = keyboard.nextLine();
        }
        return oStr;
    }
    // adds as many ingredients to recipe as user wants
    public static void ingredientHandler(Scanner keyboard, Recipe<Ingredient> recipe) {
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
                System.out.print("Quantity in grams: ");
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
    public static void createRecipe(Scanner keyboard, RecipeBook recipes) {
        String yes = "n";
        String name = "";
        while (yes.equals("n")) {
            System.out.print("Enter recipe name: ");
            name = keyboard.nextLine();
            System.out.print(name + " was entered, is this okay? (y/n): ");
            yes = keyboard.nextLine().toLowerCase();
            while (!yes.equals("y") && !yes.equals("n")) {
                System.out.print("Please input characters y or n: ");
                yes = keyboard.nextLine();
            }
        }
        Recipe<Ingredient> recipe = new Recipe<>(name);
        System.out.print("Would you like to add an ingredient? (y/n): ");
        yes = keyboard.nextLine().toLowerCase();
        while (!yes.equals("y") && !yes.equals("n")) {
            System.out.print("Please input characters y or n: ");
            yes = keyboard.nextLine();
        }
        if (yes.equals("y"))
            ingredientHandler(keyboard, recipe);
        recipe.setInstructions(createInstructions(keyboard));
        recipes.addRecipe(recipe);
    }
    public static void viewRecipe(Scanner keyboard, RecipeBook recipes){
        System.out.println("\t\t\nRecipe List:\n");
        System.out.println(recipes);
        System.out.println("----------------------------------------------");
        System.out.print("Enter the number of the recipe you would like to view or enter 0 to cancel: ");
        // max needs to be
        int num = validateNumber(recipes.size(), keyboard);
        System.out.println("\n");
        System.out.println("\t\tRecipe Ingredients\n");
        if (num != 0) {
            Recipe<Ingredient> recipe = recipes.getRecipe(num - 1);
            for (int i = 0; i < recipe.size(); i++) {
                System.out.println(i+1 + ".) "+ recipe.getIngredient(i));
            }
            System.out.println("\n\t\tRecipe instructions\n");
            System.out.println(recipe.getInstructions());
        }
    }

    public static void deleteRecipe(Scanner keyboard, RecipeBook recipes) {
        System.out.println(recipes);
        System.out.println("----------------------------------------------");
        System.out.print("Enter the number of the recipe you would like to delete or enter 0 to cancel: ");
        // max needs to be
        int num = validateNumber(recipes.size(), keyboard);
        if (num != 0) {
            recipes.removeRecipe(num - 1);
        }
    }

    public static void editRecipe(Scanner keyboard, RecipeBook recipes) {
        System.out.println(recipes);
        System.out.println("---------------------------------------------");
        System.out.print("Enter the number of the recipe you would like to edit or enter 0 to cancel: ");
        int num = validateNumber(recipes.size(), keyboard);
        if (num != 0) {
            Recipe<Ingredient> recipe = recipes.getRecipe(num - 1);
            System.out.println("\n" +recipe.toString());
            System.out.println("\nRecipe Instructions:\n" + recipe.getInstructions());
            System.out.println("\n\t\tEdit Menu");
            System.out.println("---------------------------------------------");
            System.out.println("1. Remove an existing ingredient");
            System.out.println("2. Add an ingredient");
            System.out.println("3. Create new instructions");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            String choice = keyboard.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
                System.out.print("Please input a valid number: ");
                choice = keyboard.nextLine();
            }
            num = Integer.parseInt(choice);
            switch (num) {
                case 1 -> {
                    for (int i = 0; i < recipe.size(); i++) {
                        System.out.println(i+1 + ".) "+ recipe.getIngredient(i));
                    }
                    System.out.print("Enter the number of the ingredient you would like to remove or enter 0 to cancel: ");
                    // max needs to be the size of the recipe
                    num = validateNumber(recipe.size(), keyboard);
                    recipe.removeIngredient(num - 1);
                    editRecipe(keyboard, recipes);
                }
                case 2 -> {
                    ingredientHandler(keyboard, recipe);
                    editRecipe(keyboard, recipes);
                }
                case 3 -> {
                    recipe.setInstructions(createInstructions(keyboard));
                    editRecipe(keyboard, recipes);
                }
                default -> System.out.println("\t\tSending you back to the menu...\n\n");
            }
        }

    }

    // Displays ingredients for a recipe
    public static void displayIngredients(Recipe<Ingredient> recipe) {
        for (int i = 0; i < recipe.size(); i++) {
            System.out.println(i + 1 + ".) " + recipe.getIngredient(i));
        }
    }

    // Handles flow of program
    public static void runProgram(Scanner keyboard, RecipeBook recipes) {
        int choice = menu(keyboard);
        while (choice != 6) {
            switch (choice) {
                case 1 -> createRecipe(keyboard, recipes);
                case 2 -> viewRecipe(keyboard, recipes);
                case 3 -> {
                    System.out.println("\t\tRecipe List:\n");
                    System.out.println(recipes);
                }
                case 4 -> deleteRecipe(keyboard, recipes);
                default -> editRecipe(keyboard, recipes);
            }
            choice = menu(keyboard);
        }
        System.out.println("\t\t Thank you for using my program!");
    }

    public static void main(String[] args) {
        // RecipeBook automatically instantiates an arrayList of Recipe<Ingredients>
        RecipeBook recipes = new RecipeBook();
        Scanner keyboard = new Scanner(System.in);
        runProgram(keyboard, recipes);
    }
}