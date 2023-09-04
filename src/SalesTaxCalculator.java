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
