import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;

    //THIS KEYWORD 
    public Book(String title, String author, double price) {
        this.title = title;      
        this.author = author;     
        this.price = price;      
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    //METHOD OVERRIDIN
    @Override
    public String toString() {
        return title + " by " + author + " - $" + price;
    }
}

class Bookstore {
    private ArrayList<Book> books;

    public Bookstore() {
        books = new ArrayList<>();
        //  English books
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 12.50));
        books.add(new Book("1984", "George Orwell", 8.99));
        
        // Add Urdu novels
        books.add(new Book("Abehayat", "Umera Ahmed", 1800));
        books.add(new Book("Bakht", "Mehrunnisa Shahmeer", 1400));
        books.add(new Book("Peer-e-Kamil", "Umera Ahmed", 2000));
        books.add(new Book("Maala", "Nimra Ahmed ", 3000));
        books.add(new Book("Inteqam", "Areesha Abbasi", 1000));
    }

    public void displayBooks() {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }
}

class Cart {
    private ArrayList<Book> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addBook(Book book) {
        items.add(book);
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (Book book : items) {
                System.out.println("- " + book);
            }
            System.out.println("Total: $" + calculateTotal());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Book book : items) {
            total += book.getPrice();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bookstore bookstore = new Bookstore();
        Cart cart = new Cart();
        String command;

        System.out.println("Welcome to the Online Bookstore!");

        do {
            System.out.println("\nAvailable commands: view, add, cart, checkout, exit");
            System.out.print("Enter a command: ");
            command = scanner.nextLine();

            switch (command) {
                case "view":
                    bookstore.displayBooks();
                    break;
                case "add":
                    System.out.print("Enter the book number to add to cart: ");
                    int bookNumber = scanner.nextInt();
                    scanner.nextLine();
                    Book book = bookstore.getBook(bookNumber - 1);
                    
    //EXCEPTION HANDLING 
                if (book != null) {
                        cart.addBook(book);
                    System.out.println(book.getTitle() + " has been added to your cart.");
                    } else {
                        System.out.println("Invalid book number.");
                    }
                    break;
                case "cart":
                    cart.viewCart();
                    break;
                case "checkout":
                    double total = cart.calculateTotal();
                    System.out.println("Your total is: $" + total);
                    cart.clearCart();
                    System.out.println("Thank you for your purchase!");
                    break;
                case "exit":
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}