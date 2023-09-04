import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTaxCalculatorTest {

    private List<Product> basket;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        basket = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Locale.setDefault(Locale.US);
    }

    // Testfall für den ersten Einkaufskorb
    @Test
    public void testBasket1() {
        basket.add(new Product(1, "book", 12.49));
        basket.add(new Product(1, "music CD", 14.99));
        basket.add(new Product(1, "chocolate bar", 0.85));

        String expectedOutput = """
                1 book: 12.49
                1 music CD: 16.49
                1 chocolate bar: 0.85
                Sales Taxes: 1.50
                Total: 29.83
                """.replace("\n", System.lineSeparator());


        SalesTaxCalculator.processShoppingBasket(basket);

        assertEquals(expectedOutput, outputStream.toString());
    }

    // Testfall für den zweiten Einkaufskorb
    @Test
    public void testBasket2() {
        basket.add(new Product(1, "imported box of chocolates", 10.00));
        basket.add(new Product(1, "imported bottle of perfume", 47.50));

        String expectedOutput = """
                1 imported box of chocolates: 10.50
                1 imported bottle of perfume: 54.65
                Sales Taxes: 7.65
                Total: 65.15
                """.replace("\n", System.lineSeparator());


        SalesTaxCalculator.processShoppingBasket(basket);

        assertEquals(expectedOutput, outputStream.toString());
    }

    // Testfall für den dritten Einkaufskorb
    @Test
    public void testBasket3() {
        basket.add(new Product(1, "imported bottle of perfume", 27.99));
        basket.add(new Product(1, "bottle of perfume", 18.99));
        basket.add(new Product(1, "packet of headache pills", 9.75));
        basket.add(new Product(1, "box of imported chocolates", 11.25));

        String expectedOutput = """
                1 imported bottle of perfume: 32.19
                1 bottle of perfume: 20.89
                1 packet of headache pills: 9.75
                1 box of imported chocolates: 11.85
                Sales Taxes: 6.70
                Total: 74.68
                """.replace("\n", System.lineSeparator());


        SalesTaxCalculator.processShoppingBasket(basket);

        assertEquals(expectedOutput, outputStream.toString());
    }
}
