Overview
- Expense: immutable-like POJO that holds date, amount, category, description.
- ExpenseManager: in-memory List<Expense> + CSV persistence.
- Main: minimal CLI presenting operations and delegating behavior.

Key decisions & trade-offs
- File-based CSV persistence
  - Pros: simple, no external DB, easy to inspect and submit.
  - Cons: not safe for concurrent access, limited scaling, basic escaping for commas not implemented.
- No external libraries
  - Keeps the submission self-contained and easy to grade.
- Aggregation done in memory
  - Fine for expected small datasets in assignments.

Sources & references
- Java SE documentation (java.time.LocalDate, java.io, java.util): https://docs.oracle.com/javase/8/docs/api/
