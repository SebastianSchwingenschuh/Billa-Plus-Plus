import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryTest {
    @Test
    public void testAddArticle() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("USB Charger", 38571246, 20, 12)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P})));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("Raspberry Pi", 45342323, 20, 24)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A})));

        assertEquals(false, billaPlusPlus.addArticle(new FoodArticle("Mustard", 12345678, 50, new AllergenType[] {AllergenType.M})));
        assertEquals(false, billaPlusPlus.addArticle(new FoodArticle("Burger Buns", 19359218, 25, new AllergenType[] {AllergenType.A})));
        assertEquals(false, billaPlusPlus.addArticle(tofuSmoked));
    }

    @Test
    void testAddArticleWithZeroQuantity() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 0, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(false, billaPlusPlus.addArticle(tofuSmoked));
    }

    @Test
    public void testGetSortedArticles() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[] {AllergenType.A, AllergenType.O});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("USB Charger", 38571246, 20, 12)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P})));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("Raspberry Pi", 45342323, 20, 24)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A})));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        List<Article> articles = billaPlusPlus.getSortedArticles();
        assertEquals(pils, articles.get(0));
        assertEquals("Pasta", articles.get(1).getName());
        assertEquals("Peas", articles.get(2).getName());
        assertEquals(tofuSmoked, articles.get(3));
        assertEquals("Raspberry Pi", articles.get(4).getName());
        assertEquals("USB Charger", articles.get(5).getName());
    }

    @Test
    public void testGetArticleByBarcode() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("USB Charger", 38571246, 20, 12)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P})));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("Raspberry Pi", 45342323, 20, 24)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A})));

        assertEquals("Pasta", billaPlusPlus.getArticleByBarcode(19359218).getName());
        assertEquals("Peas", billaPlusPlus.getArticleByBarcode(29582143).getName());
        assertEquals("Raspberry Pi", billaPlusPlus.getArticleByBarcode(45342323).getName());
        assertEquals(tofuSmoked, billaPlusPlus.getArticleByBarcode(12345678));
        assertEquals("USB Charger", billaPlusPlus.getArticleByBarcode(38571246).getName());

    }

    @Test
    public void testGetArticleByBarcodeWithDuplicates() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("USB Charger", 38571246, 20, 12)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P})));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("Raspberry Pi", 45342323, 20, 24)));
        assertEquals(true, billaPlusPlus.addArticle(new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A})));

        assertEquals(false, billaPlusPlus.addArticle(new FoodArticle("Mustard", 12345678, 50, new AllergenType[] {AllergenType.M})));
        assertEquals(false, billaPlusPlus.addArticle(new FoodArticle("Burger Buns", 19359218, 25, new AllergenType[] {AllergenType.A})));

        assertEquals(tofuSmoked, billaPlusPlus.getArticleByBarcode(12345678));
        assertEquals("Pasta", billaPlusPlus.getArticleByBarcode(19359218).getName());
    }

    @Test
    public void testGetFoodWithoutAllergen() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[] {AllergenType.A, AllergenType.O});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("USB Charger", 38571246, 20, 12)));
        assertEquals(true, billaPlusPlus.addArticle(peas));
        assertEquals(true, billaPlusPlus.addArticle(new TechArticle("Raspberry Pi", 45342323, 20, 24)));
        assertEquals(true, billaPlusPlus.addArticle(pasta));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        List<FoodArticle> foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.A, AllergenType.R});
        assertEquals(1,foodArticles.size());
        assertEquals(peas,foodArticles.get(0));

        foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.O, AllergenType.P});
        assertEquals(2,foodArticles.size());
        assertEquals(true,foodArticles.contains(tofuSmoked));
        assertEquals(true, foodArticles.contains(pasta));

        foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.F, AllergenType.L,  AllergenType.M, AllergenType.N});
        assertEquals(3,foodArticles.size());
        assertEquals(true,foodArticles.contains(pasta));
        assertEquals(true, foodArticles.contains(peas));
        assertEquals(true,foodArticles.contains(pils));
    }

    @Test
    public void testGetProductsWithQuantityBelow() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[] {AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(usbCharger));
        assertEquals(true, billaPlusPlus.addArticle(peas));
        assertEquals(true, billaPlusPlus.addArticle(raspberry));
        assertEquals(true, billaPlusPlus.addArticle(pasta));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        List<Article> articles = billaPlusPlus.getArticlesWithQuantityBelow(300);
        assertEquals(6,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pils, articles.get(2));
        assertEquals(tofuSmoked, articles.get(3));
        assertEquals(peas, articles.get(4));
        assertEquals(pasta, articles.get(5));

        articles = billaPlusPlus.getArticlesWithQuantityBelow(25);
        assertEquals(2,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));

        articles = billaPlusPlus.getArticlesWithQuantityBelow(115);
        assertEquals(4,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pils, articles.get(2));
        assertEquals(tofuSmoked, articles.get(3));

        articles = billaPlusPlus.getArticlesWithQuantityBelow(199);
        assertEquals(5,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pils, articles.get(2));
        assertEquals(tofuSmoked, articles.get(3));
        assertEquals(peas, articles.get(4));
    }

    @Test
    public void testBuyAndRestockArticles() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(usbCharger));
        assertEquals(true, billaPlusPlus.addArticle(peas));
        assertEquals(true, billaPlusPlus.addArticle(raspberry));
        assertEquals(true, billaPlusPlus.addArticle(pasta));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        List<Article> articles = billaPlusPlus.getArticlesWithQuantityBelow(101);
        assertEquals(4,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pils, articles.get(2));
        assertEquals(tofuSmoked, articles.get(3));

        peas.buy(50);
        tofuSmoked.restock(20);

        articles = billaPlusPlus.getArticlesWithQuantityBelow(101);
        assertEquals(4,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pils, articles.get(2));
        assertEquals(peas, articles.get(3));

        raspberry.restock(2);
        pils.restock(20);

        articles = billaPlusPlus.getArticlesWithQuantityBelow(101);
        assertEquals(3,articles.size());
        assertEquals(usbCharger, articles.get(0));
        assertEquals(raspberry, articles.get(1));
        assertEquals(peas, articles.get(2));

        raspberry.buy(3);
        pasta.buy(150);

        articles = billaPlusPlus.getArticlesWithQuantityBelow(101);
        assertEquals(4,articles.size());
        assertEquals(raspberry, articles.get(0));
        assertEquals(usbCharger, articles.get(1));
        assertEquals(pasta, articles.get(2));
        assertEquals(peas, articles.get(3));
    }

    @Test
    void testGetTechArticleWithLongestWarranty() {
        ArticleRepository billaPlusPlus = new ArticleRepository();
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(usbCharger));
        assertEquals(true, billaPlusPlus.addArticle(peas));
        assertEquals(true, billaPlusPlus.addArticle(raspberry));
        assertEquals(true, billaPlusPlus.addArticle(pasta));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        assertEquals(raspberry, billaPlusPlus.getTechArticleWithLongestWarranty());

        TechArticle nintendo = new TechArticle("Nintendo Switch", 45364033, 10, 18);
        billaPlusPlus.addArticle(nintendo);

        assertEquals(raspberry, billaPlusPlus.getTechArticleWithLongestWarranty());

        TechArticle dyson = new TechArticle("Dyson Hyperduster", 50185731, 15, 48);
        billaPlusPlus.addArticle(dyson);

        assertEquals(dyson, billaPlusPlus.getTechArticleWithLongestWarranty());
    }

    @Test
    void testGetTechArticleWithLongestWarrantyNoTechArticles() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        assertEquals(null, billaPlusPlus.getTechArticleWithLongestWarranty());

        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});

        assertEquals(true, billaPlusPlus.addArticle(tofuSmoked));
        assertEquals(true, billaPlusPlus.addArticle(peas));
        assertEquals(true, billaPlusPlus.addArticle(pasta));
        assertEquals(true, billaPlusPlus.addArticle(pils));

        assertEquals(null, billaPlusPlus.getTechArticleWithLongestWarranty());
    }
}