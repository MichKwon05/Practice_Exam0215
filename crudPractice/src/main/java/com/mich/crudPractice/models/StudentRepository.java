package com.mich.crudPractice.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Query(
            value = "UPDATE students SET status = :status WHERE id = :id",
            nativeQuery = true
    )
    int updateStatus(
            @Param("status") Boolean status,
            @Param("id")Long id);

    <Optional>Student findById(Long id);
    List<Student> findAllByStatus(Boolean status);
    Student getById(Long id);

}
