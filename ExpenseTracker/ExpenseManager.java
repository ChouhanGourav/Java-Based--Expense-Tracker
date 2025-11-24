import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private final String fileName;

    public ExpenseManager(String fileName) {
        this.fileName = fileName;
        loadExpenses();
    }

    public void addExpense(Expense e) {
        expenses.add(e);
        saveExpenses();
    }

    // NEW: Delete expense by index
    public boolean deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            saveExpenses();
            return true;
        } else {
            return false;
        }
    }

    public void listExpensesWithIndex() {
        // Show numbered list for delete option
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.println((i + 1) + ". " + e.getDate() + " | " + e.getAmount() + " | " + e.getCategory() + " | " + e.getDescription());
        }
    }

    public void listExpenses() {
        for (Expense e : expenses) {
            System.out.println(e.getDate() + " | " + e.getAmount() + " | " + e.getCategory() + " | " + e.getDescription());
        }
    }

    public void summaryByMonth() {
        Map<String, Double> summary = new HashMap<>();
        for (Expense e : expenses) {
            String key = e.getDate().getYear() + "-" + String.format("%02d", e.getDate().getMonthValue());
            summary.put(key, summary.getOrDefault(key, 0.0) + e.getAmount());
        }
        summary.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public void summaryByCategory() {
        Map<String, Double> summary = new HashMap<>();
        for (Expense e : expenses) {
            summary.put(e.getCategory(), summary.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        summary.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private void loadExpenses() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                expenses.add(Expense.fromString(line));
            }
        } catch (IOException ignored) { }
    }

    private void saveExpenses() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Expense e : expenses) {
                bw.write(e.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }
}