package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.BoardEntity;
import net.kdigital.web_project.entity.AnswerEntity; 

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

	List<AnswerEntity> findAllByBoardEntityOrderByReplyNumDesc(BoardEntity entity);
	
	List<AnswerEntity> findAllByReplyWriterOrderByReplyNumDesc(String username);
	
	

    List<AnswerEntity> findAllByBoardEntityOrderByLikeCountDesc(BoardEntity entity);

}
