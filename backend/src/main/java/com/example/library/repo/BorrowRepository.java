package com.example.library.repo;
import com.example.library.model.BorrowRecord; import java.util.List; import org.springframework.data.jpa.repository.JpaRepository;
public interface BorrowRepository extends JpaRepository<BorrowRecord,Long>{ List<BorrowRecord> findByUserIdOrderByBorrowDateDesc(Long userId); List<BorrowRecord> findByReturnDateIsNull(); }
