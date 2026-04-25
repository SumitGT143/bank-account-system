# Bank Account System

A Java OOP project demonstrating core object-oriented programming concepts using a banking domain.

## Concepts Covered
- **Encapsulation** — private fields with controlled access via getters/setters
- **Inheritance** — SavingAccount and CurrentAccount extend BankAccount
- **Polymorphism** — withdraw() behaves differently in each subclass
- **Abstraction** — AccountManager hides ArrayList complexity from Main

## Classes
| Class | Description |
|---|---|
| `BankAccount` | Base class — holds name, account number, balance |
| `SavingAccount` | Extends BankAccount — adds interest rate and applyInterest() |
| `CurrentAccount` | Extends BankAccount — adds overdraft limit |
| `AccountManager` | Manages collection of accounts — add, find, display, total |

## Sample Output
