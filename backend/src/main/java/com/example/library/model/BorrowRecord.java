package com.example.library.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="borrow_records")
public class BorrowRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="user_id") private AppUser user;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="book_id") private Book book;
 private LocalDate borrowDate=LocalDate.now(); private LocalDate dueDate=LocalDate.now().plusDays(14); private LocalDate returnDate;
 public BorrowRecord(){} public Long getId(){return id;} public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;} public Book getBook(){return book;} public void setBook(Book v){book=v;}
 public LocalDate getBorrowDate(){return borrowDate;} public LocalDate getDueDate(){return dueDate;} public LocalDate getReturnDate(){return returnDate;} public void setReturnDate(LocalDate v){returnDate=v;}
}
