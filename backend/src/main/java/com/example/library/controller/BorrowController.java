package com.example.library.controller;
import com.example.library.model.BorrowRecord;import com.example.library.service.BorrowService;import java.security.Principal;import java.util.List;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/borrow") public class BorrowController {private final BorrowService service;public BorrowController(BorrowService s){service=s;}
 @PostMapping("/{bookId}") public BorrowRecord borrow(@PathVariable Long bookId,Principal p){return service.borrow(bookId,p.getName());}
 @GetMapping("/mine") public List<BorrowRecord> mine(Principal p){return service.mine(p.getName());}
 @GetMapping @PreAuthorize("hasRole('ADMIN')") public List<BorrowRecord> all(){return service.all();}
 @PostMapping("/return/{recordId}") public BorrowRecord returnBook(@PathVariable Long recordId,Principal p,org.springframework.security.core.Authentication a){boolean admin=a.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));return service.returnBook(recordId,p.getName(),admin);}
}
