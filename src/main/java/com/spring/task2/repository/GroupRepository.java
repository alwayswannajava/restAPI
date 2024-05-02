package com.spring.task2.repository;

import com.spring.task2.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(name = "select * from groups " +
            "where group_name = :groupName", nativeQuery = true)
    boolean existsByGroupName(String groupName);

}
