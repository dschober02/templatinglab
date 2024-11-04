public class SolidIngredient implements Ingredient {
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
        return name + ": " + grams + " grams";
    }
}