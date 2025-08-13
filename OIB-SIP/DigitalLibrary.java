import java.util.*;

public class DigitalLibrary {
    static Scanner sc = new Scanner(System.in);

    static class Book {
        int id;
        String title;
        String author;
        boolean isIssued = false;

        Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }

        public String toString() {
            return id + ". " + title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
        }
    }

    static Map<Integer, Book> books = new HashMap<>();
    static Set<Integer> issuedBooks = new HashSet<>();
    static Map<String, String> users = new HashMap<>();
    static String currentUser = null;
    static boolean isAdmin = false;

    public static void main(String[] args) {
        // Default admin and users
        users.put("admin", "admin123");
        users.put("user", "user123");

        while (true) {
            System.out.println("\n📚 Welcome to the Digital Library Management System");
            System.out.print("Login as (admin/user): ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUser = username;
                isAdmin = username.equals("admin");
                System.out.println("Login successful as " + currentUser);
                if (isAdmin)
                    adminMenu();
                else
                    userMenu();
            } else {
                System.out.println("❌ Invalid credentials!");
            }
        }
    }

    // Admin Functions
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Update Book");
            System.out.println("4. View All Books");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> updateBook();
                case 4 -> viewBooks();
                case 5 -> logout();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Book Author: ");
        String author = sc.nextLine();
        books.put(id, new Book(id, title, author));
        System.out.println("✅ Book added successfully.");
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (books.containsKey(id)) {
            books.remove(id);
            issuedBooks.remove(id);
            System.out.println("✅ Book deleted.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }

    static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (books.containsKey(id)) {
            System.out.print("Enter new title: ");
            String title = sc.nextLine();
            System.out.print("Enter new author: ");
            String author = sc.nextLine();
            books.put(id, new Book(id, title, author));
            System.out.println("✅ Book updated.");
        } else {
            System.out.println("❌ Book not found.");
        }
    }

    // User Functions
    static void userMenu() {
        while (true) {
            System.out.println("\n--- User Panel ---");
            System.out.println("1. View All Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Contact Admin");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewBooks();
                case 2 -> searchBook();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> contactAdmin();
                case 6 -> logout();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 No books in library.");
        } else {
            books.values().forEach(System.out::println);
        }
    }

    static void searchBook() {
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books.values()) {
            if (book.title.toLowerCase().contains(keyword) || book.author.toLowerCase().contains(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No matching books found.");
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book book = books.get(id);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            issuedBooks.add(id);
            System.out.println("✅ Book issued.");
        } else {
            System.out.println("❌ Book not available.");
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book book = books.get(id);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            issuedBooks.remove(id);
            System.out.println("✅ Book returned.");
        } else {
            System.out.println("❌ This book was not issued.");
        }
    }

    static void contactAdmin() {
        System.out.print("Enter your query for the admin: ");
        String query = sc.nextLine();
        System.out.println("📧 Your query has been sent: \"" + query + "\" (simulated email)");
    }

    static void logout() {
        System.out.println("🔓 Logged out.");
        currentUser = null;
        isAdmin = false;
        main(null); // Return to login
    }
}
