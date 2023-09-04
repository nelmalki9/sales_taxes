import java.util.ArrayList;
import java.util.List;

class Product {
    private int quantity;
    private String name;
    private double price;
    private boolean isExempt;
    private boolean isImported;

    // Konstruktor für die Product-Klasse
    public Product(int quantity, String name, double price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.isExempt = isExemptItem(name);
        this.isImported = name.contains("imported");
    }

    // Überprüfung, ob das Produkt von der Verkaufssteuer befreit ist
    private boolean isExemptItem(String name) {
        String[] exemptKeywords = {"book", "chocolate", "pill"};
        for (String keyword : exemptKeywords) {
            if (name.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    // Berechnung der Verkaufssteuer für das Produkt
    public double calculateSalesTax() {
        double taxRate = 0.10;  // Grundsteuersatz
        if (isExempt) {
            taxRate = 0;  // Produkte, die von der Steuer befreit sind
        }
        if (isImported) {
            taxRate += 0.05;  // Zusätzliche Steuer für importierte Produkte
        }
        // Die Steuer wird auf 0,05 aufgerundet
        return Math.ceil(price * taxRate * 20.0) / 20.0;
    }

    // Berechnung des Gesamtpreises des Produkts
    public double getTotalPrice() {
        return price + calculateSalesTax();
    }

    @Override
    public String toString() {
        return quantity + " " + name + ": " + String.format("%.2f", getTotalPrice());
    }
}

public class SalesTaxCalculator {
    public static void main(String[] args) {
        // Erstellen von Einkaufskörben für die verschiedenen Eingabebeispiele.
        List<Product> basket1 = new ArrayList<>();
        basket1.add(new Product(1, "book", 12.49));
        basket1.add(new Product(1, "music CD", 14.99));
        basket1.add(new Product(1, "chocolate bar", 0.85));

        List<Product> basket2 = new ArrayList<>();
        basket2.add(new Product(1, "imported box of chocolates", 10.00));
        basket2.add(new Product(1, "imported bottle of perfume", 47.50));

        List<Product> basket3 = new ArrayList<>();
        basket3.add(new Product(1, "imported bottle of perfume", 27.99));
        basket3.add(new Product(1, "bottle of perfume", 18.99));
        basket3.add(new Product(1, "packet of headache pills", 9.75));
        basket3.add(new Product(1, "box of imported chocolates", 11.25));

        System.out.println("Output 1:");
        processShoppingBasket(basket1);

        System.out.println("\nOutput 2:");
        processShoppingBasket(basket2);

        System.out.println("\nOutput 3:");
        processShoppingBasket(basket3);
    }

    // Funktion zur Verarbeitung eines Einkaufskorbs und zur Ausgabe der Ergebnisse
    public static void processShoppingBasket(List<Product> basket) {
        double totalSalesTax = 0;
        double totalCost = 0;

        for (Product product : basket) {
            System.out.println(product);
            totalSalesTax += product.calculateSalesTax();
            totalCost += product.getTotalPrice();
        }

        System.out.println("Sales Taxes: " + String.format("%.2f", totalSalesTax));
        System.out.println("Total: " + String.format("%.2f", totalCost));
    }
}
