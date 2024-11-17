package venhan_JavainternTask;

package venhan_javaTask;

import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int quantity;

    public Book(String title, String author, String isbn, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.quantity = quantity;
    }

  
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public String getGenre() { return genre; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Genre: " + genre + ", Quantity: " + quantity;
    }
}

class Borrower {
    private String name;
    private String contact;
    private String membershipId;

    public Borrower(String name, String contact, String membershipId) {
        this.name = name;
        this.contact = contact;
        this.membershipId = membershipId;
    }


    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getMembershipId() { return membershipId; }

    @Override
    public String toString() {
        return "Name: " + name + ", Contact: " + contact + ", Membership ID: " + membershipId;
    }
}

class Library {
    private List<Book> books;
    private List<Borrower> borrowers;
    private Map<String, List<Book>> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
        borrowedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(String isbn, String title, String author, int quantity) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setQuantity(quantity);
                // Update other fields if needed
                return;
            }
        }
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public void updateBorrower(String membershipId, String contact) {
        for (Borrower borrower : borrowers) {
            if (borrower.getMembershipId().equals(membershipId)) {
                return;
            }
        }
    }

    public void removeBorrower(String membershipId) {
        borrowers.removeIf(borrower -> borrower.getMembershipId().equals(membershipId));
    }


    public void borrowBook(String membershipId, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                borrowedBooks.computeIfAbsent(membershipId, k -> new ArrayList<>()).add(book);
                System.out.println("Book borrowed: " + book);
                return;
            }
        }
        System.out.println("Book not available or does not exist.");
    }

    public void returnBook(String membershipId, String isbn) {
        List<Book> borrowed = borrowedBooks.get(membershipId);
        if (borrowed != null) {
            for (Book book : borrowed) {
                if (book.getIsbn().equals(isbn)) {
                    borrowed.remove(book);
                    for (Book b : books) {
                        if (b.getIsbn().equals(isbn)) {
                            b.setQuantity(b.getQuantity() + 1);
                            System.out.println("Book returned: " + b);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No record of this book being borrowed.");
    }
    public void searchBooks(String query) {
        for (Book book : books) {
            if (book.getTitle().contains(query) || book.getAuthor ().contains(query) || book.getGenre().contains(query)) {
                System.out.println(book);
            }
        }
    }

    public void showAvailableBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " - Available Copies: " + book.getQuantity());
        }
    }
}

public class LibrarayManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Add Borrower");
            System.out.println("5. Update Borrower");
            System.out.println("6. Remove Borrower");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Search Books");
            System.out.println("10. Show Available Books");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    library.addBook(new Book(title, author, isbn, genre, quantity));
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to update: ");
                    String updateIsbn = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    library.updateBook(updateIsbn, newTitle, newAuthor, newQuantity);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter membership ID: ");
                    String membershipId = scanner.nextLine();
                    library.addBorrower(new Borrower(name, contact, membershipId));
                    break;
                case 5:
                    System.out.print("Enter membership ID of the borrower to update: ");
                    String updateMembershipId = scanner.nextLine();
                    System.out.print("Enter new contact: ");
                    String newContact = scanner.nextLine();
                    library.updateBorrower(updateMembershipId, newContact);
                    break;
                case 6:
                    System.out.print("Enter membership ID of the borrower to remove: ");
                    String removeMembershipId = scanner.nextLine();
                    library.removeBorrower(removeMembershipId);
                    break;
                case 7:
                    System.out.print("Enter membership ID: ");
                    String borrowMembershipId = scanner.nextLine();
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowMembershipId, borrowIsbn);
                    break;
                case 8:
                    System.out.print("Enter membership ID: ");
                    String returnMembershipId = scanner.nextLine();
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnMembershipId, returnIsbn);
                    break;
                case 9:
                    System.out.print("Enter search query (title/author/genre): ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;
                case 10:
                    library.showAvailableBooks();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
        }while(true);
    }
}
