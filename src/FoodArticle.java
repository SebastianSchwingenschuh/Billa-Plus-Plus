import java.util.Arrays;
import java.util.HashSet;

public class FoodArticle extends Article {
    HashSet<AllergenType> allergenTypes;

    public FoodArticle(String name, int barcode, int quantity, AllergenType[] allergenTypes) {
        super(name, barcode, quantity);
        this.allergenTypes = new HashSet<>(Arrays.asList(allergenTypes));
    }

    public boolean addAllergen(AllergenType allergenType) {
        if (allergenTypes.contains(allergenType)) {
            return false;
        }
        this.allergenTypes.add(allergenType);
        return true;
    }

    public boolean containsAnyAllergen(AllergenType[] allergenTypes) {
        for (AllergenType allergenType : allergenTypes) {
            if (this.allergenTypes.contains(allergenType)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeAllergen(AllergenType allergenType) {
        if(!this.allergenTypes.contains(allergenType)) {
            return false;
        }
        this.allergenTypes.remove(allergenType);
        return true;
    }
}
