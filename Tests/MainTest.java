import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private Recipe<Ingredient> recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe<>("Pancakes", "Mix ingredients and cook on a skillet.");
    }

    @Test
    void testAddSolidIngredient() {
        SolidIngredient flour = new SolidIngredient("Flour", 200);
        recipe.addIngredient(flour);

        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Flour", ingredients.get(0).getName());
        assertEquals(200, ingredients.get(0).getQuantity());
    }

    @Test
    void testAddLiquidIngredient() {
        Ingredient milk = new LiquidIngredient("Milk", 150);
        recipe.addIngredient(milk);

        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Milk", ingredients.get(0).getName());
        assertEquals(150, ingredients.get(0).getQuantity());
    }
}