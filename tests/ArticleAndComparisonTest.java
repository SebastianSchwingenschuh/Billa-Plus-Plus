import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleAndComparisonTest {
    @Test
    void testCheckBarcode() {
        assertEquals(true, Article.checkBarcode(73513535));
        assertEquals(true, Article.checkBarcode(12345678));
        assertEquals(true, Article.checkBarcode(11223342));

        assertEquals(false, Article.checkBarcode(19741997));
        assertEquals(false, Article.checkBarcode(73513536));

        //too long or short:
        assertEquals(false, Article.checkBarcode(12342));
        assertEquals(false, Article.checkBarcode(2321341));
        assertEquals(false, Article.checkBarcode(232134131));
        assertEquals(false, Article.checkBarcode(1234567890));
    }

    @Test
    void testClassIsAbstract() {
        assertEquals(true, Modifier.isAbstract(Article.class.getModifiers()));
    }

    @Test
    void testComparable() {
        TechArticle dyson = new TechArticle("Dyson Hyperduster", 50185731, 15, 30);
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[] {AllergenType.A, AllergenType.O});
        TechArticle nintendo = new TechArticle("Nintendo Switch", 45364033, 10, 18);
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A});
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P});
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);
        TechArticle usbCharger = new TechArticle("USB Charger", 38571246, 21, 12);

        List<Article> articles = new LinkedList<>();
        articles.add(dyson);
        articles.add(pils);
        articles.add(nintendo);
        articles.add(pasta);
        articles.add(peas);
        articles.add(tofuSmoked);
        articles.add(raspberry);
        articles.add(usbCharger);

        Collections.shuffle(articles);

        Collections.sort(articles);

        assertEquals(dyson, articles.get(0));
        assertEquals(pils, articles.get(1));
        assertEquals(nintendo, articles.get(2));
        assertEquals(pasta, articles.get(3));
        assertEquals(peas, articles.get(4));
        assertEquals(tofuSmoked, articles.get(5));
        assertEquals(raspberry, articles.get(6));
        assertEquals(usbCharger, articles.get(7));
    }

    @Test
    void testQuantityComparator() {
        TechArticle nintendo = new TechArticle("Nintendo Switch", 45364033, 10, 18);
        TechArticle dyson = new TechArticle("Dyson Hyperduster", 50185731, 15, 30);
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 16, 24);
        FoodArticle pils = new FoodArticle("Grieskirchner Pils", 47104718, 96, new AllergenType[] {AllergenType.A, AllergenType.O});
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        TechArticle usbCharger = new TechArticle("USB Charger", 38571246, 120, 12);
        FoodArticle peas = new FoodArticle("Peas", 29582143, 150, new AllergenType[] {AllergenType.P});
        FoodArticle pasta = new FoodArticle("Pasta", 19359218, 200, new AllergenType[] {AllergenType.A});

        List<Article> articles = new LinkedList<>();
        articles.add(nintendo);
        articles.add(dyson);
        articles.add(raspberry);
        articles.add(pils);
        articles.add(tofuSmoked);
        articles.add(usbCharger);
        articles.add(peas);
        articles.add(pasta);

        Collections.shuffle(articles);

        Collections.sort(articles, new ArticleQuantityComparator());

        assertEquals(nintendo, articles.get(0));
        assertEquals(dyson, articles.get(1));
        assertEquals(raspberry, articles.get(2));
        assertEquals(pils, articles.get(3));
        assertEquals(tofuSmoked, articles.get(4));
        assertEquals(usbCharger, articles.get(5));
        assertEquals(peas, articles.get(6));
        assertEquals(pasta, articles.get(7));
    }
}