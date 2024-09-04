package com.LonpacInterviewAssignment.project.project_create_demo.repository;

import com.LonpacInterviewAssignment.project.project_create_demo.model.PostcodeTown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeTownRepository extends JpaRepository<PostcodeTown, String> {
    // You can add custom query methods here if needed
}