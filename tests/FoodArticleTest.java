import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodArticleTest {
    @Test
    public void testInheritance() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals(true, tofuSmoked instanceof Article);
    }

    @Test
    public void testConstructor() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals("Raeuchertofu", tofuSmoked.getName());
        assertEquals(12345678, tofuSmoked.getBarcode());
        assertEquals(100, tofuSmoked.getQuantity());
    }

    @Test
    void testConstructorWithInvalidBarcode() {
        assertThrows(IllegalArgumentException.class, () -> {
            FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345670, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        });
    }

    @Test
    void testConstructorNegativeQuantity() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 0, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals("Raeuchertofu", tofuSmoked.getName());
        assertEquals(12345678, tofuSmoked.getBarcode());
        assertEquals(0, tofuSmoked.getQuantity());

        tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, -1, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals("Raeuchertofu", tofuSmoked.getName());
        assertEquals(12345678, tofuSmoked.getBarcode());
        assertEquals(0, tofuSmoked.getQuantity());

        tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, -999, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals("Raeuchertofu", tofuSmoked.getName());
        assertEquals(12345678, tofuSmoked.getBarcode());
        assertEquals(0, tofuSmoked.getQuantity());
    }

    @Test
    public void testAddAllergen() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals(true, tofuSmoked.addAllergen(AllergenType.L));
        assertEquals(false, tofuSmoked.addAllergen(AllergenType.A));
        assertEquals(false, tofuSmoked.addAllergen(AllergenType.L));
    }

    @Test
    public void testRemoveAllergen() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals(false, tofuSmoked.removeAllergen(AllergenType.G));
        assertEquals(true, tofuSmoked.removeAllergen(AllergenType.A));
        assertEquals(false, tofuSmoked.removeAllergen(AllergenType.A));
    }

    @Test
    public void testContainsAnyAllergen() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});
        assertEquals(true,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.F,AllergenType.A}));
        assertEquals(true,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.F}));

        assertEquals(false,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.D}));
        assertEquals(false,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.D, AllergenType.E, AllergenType.M}));

        assertEquals(true,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.D,AllergenType.A}));
        assertEquals(true,tofuSmoked.containsAnyAllergen(new AllergenType[] {AllergenType.D, AllergenType.F, AllergenType.M, AllergenType.N}));
    }

    @Test
    public void testAddRemoveContainsAllergens() {
        FoodArticle tofu = new FoodArticle("Tofu", 32060243, 50, new AllergenType[] {AllergenType.F});

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));

        //smoke it
        tofu.addAllergen(AllergenType.A);

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));

        //marinate it
        tofu.addAllergen(AllergenType.L);

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));

        //desmoke it
        tofu.removeAllergen(AllergenType.A);

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));

        //add sesam
        tofu.addAllergen(AllergenType.N);

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));

        //demarinate
        tofu.removeAllergen(AllergenType.L);

        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.F}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.R, AllergenType.L, AllergenType.O}));
        assertEquals(false, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.A}));
        assertEquals(true, tofu.containsAnyAllergen(new AllergenType[] {AllergenType.L, AllergenType.N }));
    }

    @Test
    void testBuy() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(100, tofuSmoked.getQuantity());

        int quantityBought = tofuSmoked.buy(25);

        assertEquals(25, quantityBought);
        assertEquals(75, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(50);

        assertEquals(50, quantityBought);
        assertEquals(25, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(50);

        assertEquals(25, quantityBought);
        assertEquals(0, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(1);

        assertEquals(0, quantityBought);
        assertEquals(0, tofuSmoked.getQuantity());
    }

    @Test
    void testBuyNegativeQuantity() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(100, tofuSmoked.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {
            tofuSmoked.buy(-25);
        });

        assertEquals(100, tofuSmoked.getQuantity());
    }

    @Test
    void testRestock() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(100, tofuSmoked.getQuantity());

        tofuSmoked.restock(25);

        assertEquals(125, tofuSmoked.getQuantity());

        tofuSmoked.restock(100);

        assertEquals(225, tofuSmoked.getQuantity());
    }

    @Test
    void testRestockNegativeQuantity() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(100, tofuSmoked.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {
            tofuSmoked.restock(-50);
        });

        assertEquals(100, tofuSmoked.getQuantity());
    }

    @Test
    void testBuyAndRestock() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals(100, tofuSmoked.getQuantity());

        tofuSmoked.restock(25);

        assertEquals(125, tofuSmoked.getQuantity());

        int quantityBought = tofuSmoked.buy(65);

        assertEquals(65, quantityBought);
        assertEquals(60, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(150);

        assertEquals(60, quantityBought);
        assertEquals(0, tofuSmoked.getQuantity());

        tofuSmoked.restock(10);

        assertEquals(10, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(7);

        assertEquals(7, quantityBought);
        assertEquals(3, tofuSmoked.getQuantity());

        quantityBought = tofuSmoked.buy(3);

        assertEquals(3, quantityBought);
        assertEquals(0, tofuSmoked.getQuantity());
    }

    @Test
    void testToString() {
        FoodArticle tofuSmoked = new FoodArticle("Raeuchertofu", 12345678, 100, new AllergenType[] {AllergenType.F,AllergenType.A});

        assertEquals("#12345678 Raeuchertofu (100 in stock)", tofuSmoked.toString());

        tofuSmoked.restock(5);

        assertEquals("#12345678 Raeuchertofu (105 in stock)", tofuSmoked.toString());

        tofuSmoked.buy(63);

        assertEquals("#12345678 Raeuchertofu (42 in stock)", tofuSmoked.toString());
    }
}