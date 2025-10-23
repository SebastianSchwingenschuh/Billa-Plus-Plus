import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechArticleTest {
    @Test
    void testConstructor() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(20, raspberry.getQuantity());
        assertEquals(24, raspberry.getWarrantyMonths());
    }

    @Test
    void testConstructorWithInvalidBarcode() {
        assertThrows(IllegalArgumentException.class, () -> {
            TechArticle raspberry = new TechArticle("Raspberry Pi", 45342322, 20, 24);
        });
    }

    @Test
    void testConstructorNegativeQuantity() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 0, 24);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(0, raspberry.getQuantity());
        assertEquals(24, raspberry.getWarrantyMonths());

        raspberry = new TechArticle("Raspberry Pi", 45342323, -1, 24);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(0, raspberry.getQuantity());
        assertEquals(24, raspberry.getWarrantyMonths());

        raspberry = new TechArticle("Raspberry Pi", 45342323, -1234, 24);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(0, raspberry.getQuantity());
        assertEquals(24, raspberry.getWarrantyMonths());
    }

    @Test
    void testConstructorInvalidWarrantyMonths() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 0);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(20, raspberry.getQuantity());
        assertEquals(6, raspberry.getWarrantyMonths());

        raspberry = new TechArticle("Raspberry Pi", 45342323, 20, -1);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(20, raspberry.getQuantity());
        assertEquals(6, raspberry.getWarrantyMonths());

        raspberry = new TechArticle("Raspberry Pi", 45342323, 20, -70);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(20, raspberry.getQuantity());
        assertEquals(6, raspberry.getWarrantyMonths());

        raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 5);
        assertEquals("Raspberry Pi", raspberry.getName());
        assertEquals(45342323, raspberry.getBarcode());
        assertEquals(20, raspberry.getQuantity());
        assertEquals(6, raspberry.getWarrantyMonths());
    }

    @Test
    void testBuy() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(20, raspberry.getQuantity());

        int quantityBought = raspberry.buy(7);

        assertEquals(7, quantityBought);
        assertEquals(13, raspberry.getQuantity());

        quantityBought = raspberry.buy(8);

        assertEquals(8, quantityBought);
        assertEquals(5, raspberry.getQuantity());

        quantityBought = raspberry.buy(40);

        assertEquals(5, quantityBought);
        assertEquals(0, raspberry.getQuantity());

        quantityBought = raspberry.buy(1);

        assertEquals(0, quantityBought);
        assertEquals(0, raspberry.getQuantity());
    }

    @Test
    void testBuyNegativeQuantity() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(20, raspberry.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {
            raspberry.buy(-1);
        });

        assertEquals(20, raspberry.getQuantity());
    }

    @Test
    void testRestock() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(20, raspberry.getQuantity());

        raspberry.restock(10);

        assertEquals(30, raspberry.getQuantity());

        raspberry.restock(100);

        assertEquals(130, raspberry.getQuantity());
    }

    @Test
    void testRestockNegativeQuantity() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(20, raspberry.getQuantity());

        assertThrows(IllegalArgumentException.class, () -> {
            raspberry.restock(-1);
        });

        assertEquals(20, raspberry.getQuantity());
    }

    @Test
    void testBuyAndRestock() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals(20, raspberry.getQuantity());

        raspberry.restock(13);

        assertEquals(33, raspberry.getQuantity());

        int quantityBought = raspberry.buy(10);

        assertEquals(10, quantityBought);
        assertEquals(23, raspberry.getQuantity());

        quantityBought = raspberry.buy(25);

        assertEquals(23, quantityBought);
        assertEquals(0, raspberry.getQuantity());

        raspberry.restock(15);

        assertEquals(15, raspberry.getQuantity());

        quantityBought = raspberry.buy(1);

        assertEquals(1, quantityBought);
        assertEquals(14, raspberry.getQuantity());

        quantityBought = raspberry.buy(14);

        assertEquals(14, quantityBought);
        assertEquals(0, raspberry.getQuantity());
    }

    @Test
    void testToString() {
        TechArticle raspberry = new TechArticle("Raspberry Pi", 45342323, 20, 24);

        assertEquals("#45342323 Raspberry Pi (20 in stock)", raspberry.toString());

        raspberry.buy(7);

        assertEquals("#45342323 Raspberry Pi (13 in stock)", raspberry.toString());

        raspberry.restock(10);

        assertEquals("#45342323 Raspberry Pi (23 in stock)", raspberry.toString());
    }
}