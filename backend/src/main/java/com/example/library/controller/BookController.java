package com.example.library.controller;
import com.example.library.model.Book; import com.example.library.service.BookService; import jakarta.validation.Valid; import org.springframework.data.domain.Page; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/books") public class BookController {private final BookService service;public BookController(BookService s){service=s;}
 @GetMapping public Page<Book> list(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size,@RequestParam(required=false) String q){return service.list(page,size,q);}
 @PostMapping @PreAuthorize("hasRole('ADMIN')") public Book create(@Valid @RequestBody Book b){return service.create(b);}
 @PutMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public Book update(@PathVariable Long id,@Valid @RequestBody Book b){return service.update(id,b);}
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public void delete(@PathVariable Long id){service.delete(id);}
}
