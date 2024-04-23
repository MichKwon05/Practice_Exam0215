package com.mich.crudPractice.models.history;

import com.mich.crudPractice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Modifying
    @Query(
            value = "UPDATE history SET status = :status WHERE id = :id",
            nativeQuery = true
    )
    int updateStatus(
            @Param("status") Boolean status,
            @Param("id")Long id);

    List<History> findAllByStatus(Boolean status);

    Optional<History> findById(Long id);
    History getById(Long id);

    Optional<History> findByNameHistory(String nameHistory);

}
