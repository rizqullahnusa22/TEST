import java.util.ArrayList;
import java.util.Scanner;

// Main class untuk menjalankan aplikasi
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Daftar buku yang tersedia
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Java Programming", "James Gosling", 50.0));
        bookList.add(new Book("Clean Code", "Robert C. Martin", 40.0));
        bookList.add(new Book("Effective Java", "Joshua Bloch", 45.0));

        // Informasi pengguna
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(username, email);

        // Keranjang belanja
        Cart cart = new Cart();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to Second-hand Book E-commerce, " + user.getUsername());
            System.out.println("1. View Books");
            System.out.println("2. Add Book to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Books:");
                    for (int i = 0; i < bookList.size(); i++) {
                        System.out.println((i + 1) + ". " + bookList.get(i));
                    }
                    break;

                case 2:
                    System.out.print("Enter book number to add to cart: ");
                    int bookNumber = scanner.nextInt();
                    if (bookNumber > 0 && bookNumber <= bookList.size()) {
                        cart.addBook(bookList.get(bookNumber - 1));
                    } else {
                        System.out.println("Invalid book number.");
                    }
                    break;

                case 3:
                    cart.viewCart();
                    break;

                case 4:
                    double total = cart.checkout();
                    System.out.println("Total payment: $" + total);
                    System.out.println("Thank you for your purchase, " + user.getUsername() + "!");
                    break;

                case 5:
                    exit = true;
                    System.out.println("Exiting the application. Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }


// Class Book untuk menyimpan informasi buku
class Book {
    private String title;
    private String author;
    private double price;

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

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Price: $" + price;
    }
}

// Class User untuk menyimpan informasi pengguna
class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

// Class Cart untuk menyimpan buku yang dibeli oleh pengguna
class Cart {
    private ArrayList<Book> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addBook(Book book) {
        cartItems.add(book);
        System.out.println(book.getTitle() + " added to cart.");
    }

    public void viewCart() {
        System.out.println("Your Cart:");
        for (Book book : cartItems) {
            System.out.println(book);
        }
    }

    public double checkout() {
        double total = 0;
        for (Book book : cartItems) {
            total += book.getPrice();
        }
        cartItems.clear();
        return total;
    }
}

}