import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager("expenses.csv");

        while (true) {
            System.out.println("\n1. Add Expense\n2. List Expenses\n3. Delete Expense\n4. Monthly Summary\n5. Category Summary\n6. Exit");
            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());

            if (option == 1) {
                System.out.print("Date (YYYY-MM-DD): ");
                LocalDate date = LocalDate.parse(scanner.nextLine());
                System.out.print("Amount: ");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.print("Category: ");
                String category = scanner.nextLine();
                System.out.print("Description: ");
                String desc = scanner.nextLine();

                manager.addExpense(new Expense(date, amount, category, desc));
                System.out.println("Expense Added.");
            } else if (option == 2) {
                manager.listExpenses();
            } else if (option == 3) {
                // DELETE OPTION
                System.out.println("Select expense to delete:");
                manager.listExpensesWithIndex();
                System.out.print("Enter number to delete (0 to cancel): ");
                int idx = Integer.parseInt(scanner.nextLine());
                if (idx > 0) {
                    boolean success = manager.deleteExpense(idx - 1);
                    if (success) {
                        System.out.println("Expense deleted.");
                    } else {
                        System.out.println("Invalid index!");
                    }
                } else {
                    System.out.println("Delete canceled.");
                }
            } else if (option == 4) {
                manager.summaryByMonth();
            } else if (option == 5) {
                manager.summaryByCategory();
            } else if (option == 6) {
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }
}