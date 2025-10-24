public class TechArticle extends Article {
    private int warrantyMonths;

    public TechArticle(String name, int barcode, int quantity, int warrantyMonths) {
        super(name, barcode, quantity);
        this.warrantyMonths = warrantyMonths;
    }


    public int getWarrantyMonths() {
        if(warrantyMonths <= 6) {
            return 6;
        }
        return warrantyMonths;
    }
}
