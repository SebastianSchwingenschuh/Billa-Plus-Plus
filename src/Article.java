import java.util.Objects;

public abstract class Article implements Comparable<Article> {
    private String name;
    private int barcode;
    private int quantity;

    public Article(String name, int barcode, int quantity) {
        if (quantity < 0) {
            quantity = 0;
        }
        this.name = name;

        if (!isValidBarcode(barcode)) {
            throw new IllegalArgumentException("Invalid barcode");
        }
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public boolean isValidBarcode(int barcode) {
        //Number 4 5 3 4 2 3 2
        //Weight 1 3 1 3 1 3 1
        //Products 4 15 3 12 2 9 2
        //Sum 47
        //Sum mod 10 7
        //Difference to next 10 3
        int lastDigit = barcode % 10;
        int[] barcodeDigits = new int[8];

        for (int i = 7; i >= 0; i--) {
            barcodeDigits[i] = barcode % 10;
            barcode = barcode / 10;
        }

        int queersum = 0;
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 1) {
                queersum += barcodeDigits[i] * 3;
            } else {
                queersum += barcodeDigits[i];
            }
        }

        return 10 - (queersum % 10) == barcodeDigits[7];
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

    @Override
    public String toString() {
        //#12345678 Raeuchertofu (100 in stock)
        return String.format("#%d %s (%d in stock)", barcode, name, quantity);
    }

    public int buy(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (this.quantity < quantity) { //nicht genug im lager
            int quantityBefore = this.quantity;
            //alles aufkaufen
            this.quantity = 0;
            return quantityBefore;
        }
        this.quantity -= quantity;
        return quantity;
    }

    public void restock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity += quantity;
    }

    public static boolean checkBarcode(int barcode) {
        return false;
    }

    public int compareTo(Article article) {
        return this.name.compareTo(article.name);
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
