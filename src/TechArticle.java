public class TechArticle extends Article {
    private int warrantyMonths;

    public TechArticle(String name, int barcode, int quantity, int warrantyMonths) {
        super(name, barcode, quantity);
        this.warrantyMonths = warrantyMonths;
    }


    public int getWarrantyMonths() {
        return Math.max(warrantyMonths, 6);
    }
}
