import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    @DisplayName("Testing SolidIngredient Constructor and setter")
    public void testSolidIngredientConstructor() {
        // assemble
        SolidIngredient si = new SolidIngredient("Banana", 5);
        // act
        si.setQuantity(2);
        // assert
        assertEquals("Banana", si.getName());
        assertNotEquals(5, si.getQuantity());
        assertEquals(2, si.getQuantity());
    }
    @Test
    @DisplayName("Testing SolidIngredient toString")
    public void testSolidIngredientToString() {
        // assemble and act
        SolidIngredient si = new SolidIngredient("Banana", 5);
        String expected = "Banana: 5.0 grams";
        // assert
        assertEquals(expected, si.toString());
    }
    @Test
    @DisplayName("Testing SolidIngredient Constructor")
    public void testLiquidIngredientConstructor() {
        // assemble
        LiquidIngredient li = new LiquidIngredient("Milk", 5);
        // act
        li.setQuantity(8);
        // assert
        assertEquals("Milk", li.getName());
        assertNotEquals(5, li.getQuantity());
        assertEquals(8, li.getQuantity());
    }


}