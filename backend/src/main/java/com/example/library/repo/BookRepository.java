package com.example.library.repo;
import com.example.library.model.Book; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface BookRepository extends JpaRepository<Book, Long> {
	@Query("""
			select b from Book b
			where lower(b.title) like lower(concat('%', :query, '%'))
			   or lower(b.author) like lower(concat('%', :query, '%'))
			   or lower(b.category) like lower(concat('%', :query, '%'))
			   or lower(b.isbn) like lower(concat('%', :query, '%'))
			""")
	Page<Book> search(@Param("query") String query, Pageable pageable);
}
