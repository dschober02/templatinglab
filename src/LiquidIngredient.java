public class LiquidIngredient implements Ingredient {
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
