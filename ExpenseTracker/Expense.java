import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Expense(LocalDate date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() { return date; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return date + "," + amount + "," + category + "," + description;
    }

    public static Expense fromString(String csv) {
        String[] parts = csv.split(",");
        return new Expense(
            LocalDate.parse(parts[0]),
            Double.parseDouble(parts[1]),
            parts[2],
            parts.length > 3 ? parts[3] : ""
        );
    }
}