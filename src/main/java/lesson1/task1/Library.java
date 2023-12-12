package lesson1.task1;



import java.util.ArrayList;
import java.util.List;

public class Library {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();
        books.add(new Book("Преступление и наказание", "Фёдор Достоевский", 1866));
        books.add(new Book("Евгений Онегин", "Александр Пушкин", 1833));
        books.add(new Book("Война и мир", "Лев Толстой", 1869));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));

        // Поиск книг, написанных автором
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books){
            if ("Лев Толстой".equals(book.getAuthor())) {
                authorBooks.add(book);
            }
        }
        System.out.println("Лев Толстой: " + authorBooks);

        // Поиск книг, изданных после 1866
        List<Book> booksAfterYear = new ArrayList<>();
        for (Book book : books){
            if (book.getYear() > 1866) {
                booksAfterYear.add(book);
            }
        }
        System.out.println("Книги после 1866: " + booksAfterYear);

        List<String> uniqueTitles = new ArrayList<>();
        for (Book book: books) {
            if (!uniqueTitles.contains(book.getTitle())){
                uniqueTitles.add(book.getTitle());
            }
        }

        System.out.println("Наименования книг: " + uniqueTitles);

    }

}
