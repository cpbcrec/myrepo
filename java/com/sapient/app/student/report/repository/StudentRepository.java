package com.sapient.app.student.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sapient.app.student.report.jpa.domain.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{
	@Query("from Student s where s.isProcessed = false")
    List<Student> findAllNotProcessed();

    @Query("from Student s where s.isProcessed = true")
    List<Student> findAllProcessed();

    @Query("select count(s) from Student s where s.isProcessed = true")
    Long countAllProcessed();

    @Query("from Student s left join fetch s.subjects where s.id = :id")
    Student findById(@Param("id") Integer id);
}
