import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class ArticleFactoryTest {
    @Test
    void testInterfaceAndInheritance() {
        assertEquals(true, Modifier.isInterface(ArticleFactory.class.getModifiers()));

        FoodArticleFactory foodArticleFactory = new FoodArticleFactory();
        assertEquals(true, foodArticleFactory instanceof ArticleFactory);

        TechArticleFactory techArticleFactory = new TechArticleFactory();
        assertEquals(true, techArticleFactory instanceof ArticleFactory);
    }

    @Test
    void testTechArticleFactoryValidLine() {
        ArticleFactory articleFactory = new TechArticleFactory();

        Article article = articleFactory.createFromString("32479636;Sony Bravia;3;24");

        assertEquals(true, article instanceof TechArticle);

        TechArticle techArticle = (TechArticle) article;

        assertEquals(32479636, techArticle.getBarcode());
        assertEquals("Sony Bravia", techArticle.getName());
        assertEquals(3, techArticle.getQuantity());
        assertEquals(24, techArticle.getWarrantyMonths());
    }

    @Test
    void testTechArticleFactoryInvalidQuantity() {
        ArticleFactory articleFactory = new TechArticleFactory();

        Article article = articleFactory.createFromString("32479636;Sony Bravia;0;24");
        assertEquals(0, article.getQuantity());

        article = articleFactory.createFromString("32479636;Sony Bravia;-1;24");
        assertEquals(0, article.getQuantity());

        article = articleFactory.createFromString("32479636;Sony Bravia;-99;24");
        assertEquals(0, article.getQuantity());
    }

    @Test
    void testTechArticleFactoryInvalidWarranty() {
        ArticleFactory articleFactory = new TechArticleFactory();

        TechArticle techArticle = (TechArticle) articleFactory.createFromString("32479636;Sony Bravia;3;0");
        assertEquals(6, techArticle.getWarrantyMonths());

        techArticle = (TechArticle) articleFactory.createFromString("32479636;Sony Bravia;3;5");
        assertEquals(6, techArticle.getWarrantyMonths());

        techArticle = (TechArticle) articleFactory.createFromString("32479636;Sony Bravia;3;-1");
        assertEquals(6, techArticle.getWarrantyMonths());

        techArticle = (TechArticle) articleFactory.createFromString("32479636;Sony Bravia;3;-24");
        assertEquals(6, techArticle.getWarrantyMonths());
    }

    @Test
    void testTechArticleFactoryLineTooShort() {
        ArticleFactory articleFactory = new TechArticleFactory();

        assertThrows(ArticleManagementException.class, () -> {
            Article article = articleFactory.createFromString("32479636;Sony Bravia;24");
        });
    }

    @Test
    void testTechArticleFactoryLineTooLong() {
        ArticleFactory articleFactory = new TechArticleFactory();

        assertThrows(ArticleManagementException.class, () -> {
            Article article = articleFactory.createFromString("32479636;Sony;Bravia;3;24");
        });
    }

    @Test
    void testTechArticleFactoryValidLineWithWhitespaces() {
        ArticleFactory articleFactory = new TechArticleFactory();

        Article article = articleFactory.createFromString(" 32479636 ;    Sony Bravia  ;   3     ; 24     ");

        assertEquals(true, article instanceof TechArticle);

        TechArticle techArticle = (TechArticle) article;

        assertEquals(32479636, techArticle.getBarcode());
        assertEquals("Sony Bravia", techArticle.getName());
        assertEquals(3, techArticle.getQuantity());
        assertEquals(24, techArticle.getWarrantyMonths());
    }
    
    @Test
    void testFoodArticleFactoryValidLine() {
        ArticleFactory articleFactory = new FoodArticleFactory();

        Article article = articleFactory.createFromString("22971588;Salzstangerl;140;A,G,N");

        assertEquals(true, article instanceof FoodArticle);

        FoodArticle foodArticle = (FoodArticle) article;
        assertEquals(22971588, foodArticle.getBarcode());
        assertEquals("Salzstangerl", foodArticle.getName());
        assertEquals(140, foodArticle.getQuantity());

        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.A, AllergenType.A}));
        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.A, AllergenType.G}));
        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.A, AllergenType.N}));
        assertEquals(false, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.B, AllergenType.C, AllergenType.H, AllergenType.L}));
    }

    @Test
    void testFoodArticleFactoryValidLineOnlyOneAllergen() {
        ArticleFactory articleFactory = new FoodArticleFactory();

        Article article = articleFactory.createFromString("26353522;Sauerkraut;45;O");

        assertEquals(true, article instanceof FoodArticle);

        FoodArticle foodArticle = (FoodArticle) article;
        assertEquals(26353522, foodArticle.getBarcode());
        assertEquals("Sauerkraut", foodArticle.getName());
        assertEquals(45, foodArticle.getQuantity());

        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.O}));
        assertEquals(false, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.A, AllergenType.L, AllergenType.N, AllergenType.G}));
    }

    @Test
    void testFoodArticleFactoryLineTooShort() {
        ArticleFactory articleFactory = new TechArticleFactory();

        assertThrows(ArticleManagementException.class, () -> {
            Article article = articleFactory.createFromString("22971588;Salzstangerl;A,G,N");
        });
    }

    @Test
    void testFoodArticleFactoryLineTooLong() {
        ArticleFactory articleFactory = new TechArticleFactory();

        assertThrows(ArticleManagementException.class, () -> {
            Article article = articleFactory.createFromString("22971588;Kellys;Salzstangerl;140;A,G,N");
        });
    }

    @Test
    void testFoodArticleFactoryInvalidQuantity() {
        ArticleFactory articleFactory = new FoodArticleFactory();

        Article article = articleFactory.createFromString("22971588;Salzstangerl;0;A,G,N");
        assertEquals(0, article.getQuantity());

        article = articleFactory.createFromString("22971588;Salzstangerl;-1;A,G,N");
        assertEquals(0, article.getQuantity());

        article = articleFactory.createFromString("22971588;Salzstangerl;-13;A,G,N");
        assertEquals(0, article.getQuantity());
    }

    @Test
    void testFoodArticleFactoryValidLineWithWhitespaces() {
        ArticleFactory articleFactory = new FoodArticleFactory();

        Article article = articleFactory.createFromString("  22971588 ; Salzstangerl  ;   140 ;    A,G,N ");

        assertEquals(true, article instanceof FoodArticle);

        FoodArticle foodArticle = (FoodArticle) article;
        assertEquals(22971588, foodArticle.getBarcode());
        assertEquals("Salzstangerl", foodArticle.getName());
        assertEquals(140, foodArticle.getQuantity());

        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.G}));
        assertEquals(true, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.N}));
        assertEquals(false, foodArticle.containsAnyAllergen(new AllergenType[] {AllergenType.B, AllergenType.C, AllergenType.H, AllergenType.L}));
    }
}