package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.AnswerEntity;
import net.kdigital.web_project.entity.CustomerLikeEntity;

public interface CustomerLikeRepository extends JpaRepository<CustomerLikeEntity, Long>{

    boolean existsByUserIdAndAnswerEntity(String username, AnswerEntity answerEntity);
    
}
