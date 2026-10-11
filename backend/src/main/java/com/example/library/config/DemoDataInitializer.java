package com.example.library.config;

import com.example.library.model.Book;
import com.example.library.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DemoDataInitializer {

    @Bean
    CommandLineRunner seedBooks(BookRepository books) {
        return args -> {
            if (books.count() > 0) {
                return;
            }

            books.saveAll(List.of(
                    book("The Hobbit", "J. R. R. Tolkien", "9780547928227", "Fantasy", 5),
                    book("Pride and Prejudice", "Jane Austen", "9780141439518", "Classic", 4),
                    book("1984", "George Orwell", "9780451524935", "Science Fiction", 3),
                    book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Classic", 4),
                    book("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Fiction", 3)
            ));
        };
    }

    private Book book(String title, String author, String isbn, String category, int quantity) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setCategory(category);
        book.setQuantity(quantity);
        book.setAvailableQuantity(quantity);
        return book;
    }
}