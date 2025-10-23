import java.util.Arrays;
import java.util.HashSet;

public class FoodArticle extends Article {
    HashSet<AllergenType> allergenTypes;

    public FoodArticle(String name, int barcode, int quantity, AllergenType[] allergenTypes) {
        super(name, barcode, quantity);
        this.allergenTypes = new HashSet<>(Arrays.asList(allergenTypes));
    }

    public boolean addAllergen(AllergenType allergenType) {
        return false;
    }

    public boolean containsAnyAllergen(AllergenType[]allergenTypes) {
        return false;
    }

    public boolean removeAllergen(AllergenType allergenType) {
        return false;
    }
}
