import java.util.Objects;

public abstract class Article implements Comparable<Article> {
    private String name;
    private int barcode;
    private int quantity;

    public Article(String name, int barcode, int quantity) {
        this.name = name;
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public int buy (int quantity) {
        return 0;
    }
    
    public void restock(int quantity) {
    }
    
    public static boolean checkBarcode (int barcode) {
        return false;
    }
    
    public int compareTo(Article article) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return barcode == article.barcode && quantity == article.quantity && Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, barcode, quantity);
    }
}
