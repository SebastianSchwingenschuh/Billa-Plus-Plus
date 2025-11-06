import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRepositoryFileTest {
    @Test
    void testGetSorted() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        billaPlusPlus.addArticle(tofuSmoked);
        billaPlusPlus.addArticle(peas);
        billaPlusPlus.addArticle(pasta);
        billaPlusPlus.addArticle(pils);
        billaPlusPlus.addArticle(usbCharger);
        billaPlusPlus.addArticle(raspberry);

        billaPlusPlus.addArticlesFromFile("data/food.csv", new FoodArticleFactory());

        List<Article> articles = billaPlusPlus.getSortedArticles();

        assertEquals(11, articles.size());
        assertEquals(pils, articles.get(0));
        assertEquals("#37534196 Kartoffelchips (80 in stock)", articles.get(1).toString());
        assertEquals("#61045703 Muesliriegel (220 in stock)", articles.get(2).toString());
        assertEquals(pasta, articles.get(3));
        assertEquals(peas, articles.get(4));
        assertEquals(tofuSmoked, articles.get(5));
        assertEquals(raspberry, articles.get(6));
        assertEquals("#22971588 Salzstangerl (140 in stock)", articles.get(7).toString());
        assertEquals("#26353522 Sauerkraut (45 in stock)", articles.get(8).toString());
        assertEquals("#47346891 Thunfischsalat (40 in stock)", articles.get(9).toString());
        assertEquals(usbCharger, articles.get(10));

        billaPlusPlus.addArticlesFromFile("data/tech.csv", new TechArticleFactory());

        articles = billaPlusPlus.getSortedArticles();

        assertEquals(14, articles.size());
        assertEquals(pils, articles.get(0));
        assertEquals("#37534196 Kartoffelchips (80 in stock)", articles.get(1).toString());
        assertEquals("#61045703 Muesliriegel (220 in stock)", articles.get(2).toString());
        assertEquals(pasta, articles.get(3));
        assertEquals(peas, articles.get(4));
        assertEquals(tofuSmoked, articles.get(5));
        assertEquals(raspberry, articles.get(6));
        assertEquals("#97462626 Router (5 in stock)", articles.get(7).toString());
        assertEquals("#22971588 Salzstangerl (140 in stock)", articles.get(8).toString());
        assertEquals("#26353522 Sauerkraut (45 in stock)", articles.get(9).toString());
        assertEquals("#32479636 Sony Bravia (3 in stock)", articles.get(10).toString());
        assertEquals("#89143452 Thinkpad (10 in stock)", articles.get(11).toString());
        assertEquals("#47346891 Thunfischsalat (40 in stock)", articles.get(12).toString());
        assertEquals(usbCharger, articles.get(13));
    }

    @Test
    void testAddArticlesFromNonExistingFile() {
        ArticleManagementException ex = assertThrows(ArticleManagementException.class, ()->{
            ArticleRepository billaPlusPlus = new ArticleRepository();
            billaPlusPlus.addArticlesFromFile("file/haxi.csv", new TechArticleFactory());
        });

        assertEquals("Exception occured while loading file!", ex.getMessage());
        assertEquals(true, ex.getCause() instanceof IOException);
    }

    @Test
    void testGetByBarcode() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        billaPlusPlus.addArticlesFromFile("data/tech.csv", new TechArticleFactory());
        billaPlusPlus.addArticlesFromFile("data/food.csv", new FoodArticleFactory());

        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        billaPlusPlus.addArticle(tofuSmoked);
        billaPlusPlus.addArticle(peas);
        billaPlusPlus.addArticle(pasta);
        billaPlusPlus.addArticle(pils);
        billaPlusPlus.addArticle(usbCharger);
        billaPlusPlus.addArticle(raspberry);

        assertEquals(tofuSmoked, billaPlusPlus.getArticleByBarcode(12345678));
        assertEquals(pasta, billaPlusPlus.getArticleByBarcode(19359218));
        assertEquals("#22971588 Salzstangerl (140 in stock)", billaPlusPlus.getArticleByBarcode(22971588).toString());
        assertEquals("#26353522 Sauerkraut (45 in stock)", billaPlusPlus.getArticleByBarcode(26353522).toString());
        assertEquals(peas, billaPlusPlus.getArticleByBarcode(29582143));
        assertEquals("#32479636 Sony Bravia (3 in stock)", billaPlusPlus.getArticleByBarcode(32479636).toString());
        assertEquals("#37534196 Kartoffelchips (80 in stock)", billaPlusPlus.getArticleByBarcode(37534196).toString());
        assertEquals(usbCharger, billaPlusPlus.getArticleByBarcode(38571246));
        assertEquals(raspberry, billaPlusPlus.getArticleByBarcode(45342323));
        assertEquals("#47346891 Thunfischsalat (40 in stock)", billaPlusPlus.getArticleByBarcode(47346891).toString());
        assertEquals("#89143452 Thinkpad (10 in stock)", billaPlusPlus.getArticleByBarcode(89143452).toString());
        assertEquals(pils, billaPlusPlus.getArticleByBarcode(47104718));
        assertEquals("#61045703 Muesliriegel (220 in stock)", billaPlusPlus.getArticleByBarcode(61045703).toString());
        assertEquals("#97462626 Router (5 in stock)", billaPlusPlus.getArticleByBarcode(97462626).toString());

        //quantity in .csv was 0:
        assertEquals(null, billaPlusPlus.getArticleByBarcode(17522698));
        assertEquals(null, billaPlusPlus.getArticleByBarcode(42406161));
    }

    @Test
    void testAddingDuplicateBarcodesDoesNotOverwrite() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        //barcodes identical to ones found in the .csv files:
        FoodArticle maerzen = new FoodArticle("Grieskirchner Maerzen", 89143452, 12, new AllergenType[]{AllergenType.A, AllergenType.O});
        TechArticle speaker = new TechArticle("Bluetooth Speaker", 47346891, 10, 9);

        billaPlusPlus.addArticle(maerzen);
        billaPlusPlus.addArticle(speaker);

        billaPlusPlus.addArticlesFromFile("data/tech.csv", new TechArticleFactory());
        billaPlusPlus.addArticlesFromFile("data/food.csv", new FoodArticleFactory());

        List<Article> articles = billaPlusPlus.getSortedArticles();
        assertEquals(8, articles.size());

        assertEquals(maerzen, billaPlusPlus.getArticleByBarcode(89143452));
        assertEquals(speaker, billaPlusPlus.getArticleByBarcode(47346891));

        TechArticle batteries = new TechArticle("Batterien", 97462626, 16, 6);
        FoodArticle tofu = new FoodArticle("Tofu", 26353522, 50, new AllergenType[] {AllergenType.F});

        assertEquals(false, billaPlusPlus.addArticle(batteries));
        assertEquals(false, billaPlusPlus.addArticle(tofu));

        articles = billaPlusPlus.getSortedArticles();
        assertEquals(8, articles.size());

        assertEquals("#97462626 Router (5 in stock)", billaPlusPlus.getArticleByBarcode(97462626).toString());
        assertEquals("#26353522 Sauerkraut (45 in stock)", billaPlusPlus.getArticleByBarcode(26353522).toString());
    }

    @Test
    public void testGetFoodWithoutAllergen() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        billaPlusPlus.addArticlesFromFile("data/tech.csv", new TechArticleFactory());

        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[]{AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[]{AllergenType.A});
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[]{AllergenType.A, AllergenType.O});
        Article usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);
        Article raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        billaPlusPlus.addArticle(tofuSmoked);
        billaPlusPlus.addArticle(peas);
        billaPlusPlus.addArticle(pasta);
        billaPlusPlus.addArticle(pils);
        billaPlusPlus.addArticle(usbCharger);
        billaPlusPlus.addArticle(raspberry);

        billaPlusPlus.addArticlesFromFile("data/food.csv", new FoodArticleFactory());

        List<FoodArticle> foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.A, AllergenType.L});
        assertEquals(3,foodArticles.size());
        assertEquals(peas,foodArticles.get(0));
        assertEquals("#26353522 Sauerkraut (45 in stock)",foodArticles.get(1).toString());
        assertEquals("#47346891 Thunfischsalat (40 in stock)",foodArticles.get(2).toString());

        foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.G, AllergenType.F});
        assertEquals(5,foodArticles.size());
        assertEquals("#47104718 Grieskirchner Pils (96 in stock)",foodArticles.get(0).toString());
        assertEquals("#61045703 Muesliriegel (220 in stock)",foodArticles.get(1).toString());
        assertEquals("#19359218 Pasta (200 in stock)",foodArticles.get(2).toString());
        assertEquals(peas,foodArticles.get(3));
        assertEquals("#26353522 Sauerkraut (45 in stock)",foodArticles.get(4).toString());

        billaPlusPlus.addArticle(new FoodArticle("Gruener Veltliner", 31546144, 18, new AllergenType[] {AllergenType.O}));

        foodArticles = billaPlusPlus.getFoodWithoutAllergens(new AllergenType[] {AllergenType.A, AllergenType.G, AllergenType.F, AllergenType.P});
        assertEquals(2,foodArticles.size());
        assertEquals("#31546144 Gruener Veltliner (18 in stock)",foodArticles.get(0).toString());
        assertEquals("#26353522 Sauerkraut (45 in stock)",foodArticles.get(1).toString());
    }

    @Test
    void testGetTechArticleWithLongestWarranty() {
        ArticleRepository billaPlusPlus = new ArticleRepository();

        billaPlusPlus.addArticlesFromFile("data/tech.csv", new TechArticleFactory());

        assertEquals("#89143452 Thinkpad (10 in stock)", billaPlusPlus.getTechArticleWithLongestWarranty().toString());

        TechArticle dyson = new TechArticle("Dyson Hyperduster", 50185731, 15, 48);
        billaPlusPlus.addArticle(dyson);

        assertEquals(dyson, billaPlusPlus.getTechArticleWithLongestWarranty());
    }
}