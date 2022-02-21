package com.example.testjpa.repository;

import com.example.testjpa.model.JpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/1/30 9:43
 */
@Repository
public interface JpaTestRepository extends JpaRepository<JpaTest, Long> {

    @Query(value = "select * from jpatest where ",nativeQuery = true)
    List<JpaTest> findByInstructedAndTreeid(Date date, long treeId);

    List<JpaTest> findByTreeid(long treeId);

    @Query(value = "SELECT *\n" +
            "FROM jpatest\n" +
            "WHERE TO_DAYS(instructed) = TO_DAYS(?1)",nativeQuery = true)
    List<JpaTest> findByInstructed(Date date);

    @Query("select t from JpaTest t where t.instructed = ?1")
    List<JpaTest> findByInstructed1(String time);

    @Query(value = "SELECT *\n" +
            "FROM jpatest\n" +
            "WHERE TO_DAYS(instructed) = TO_DAYS(DATE(?1))",nativeQuery = true)
    List<JpaTest> findByInstructed2(String date);

    @Query(value = "SELECT *\n" +
            "FROM jpatest\n" +
            "WHERE TO_DAYS(instructed) = TO_DAYS(?1)",nativeQuery = true)
    List<JpaTest> findByInstructed3(String time);

    @Query(value = "select * from jpatest where treeid = ?1",nativeQuery = true)
    List<JpaTest> findByTreeid1(long treeId);
}
