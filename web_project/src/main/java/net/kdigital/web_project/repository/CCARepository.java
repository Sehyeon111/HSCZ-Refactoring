package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kdigital.web_project.entity.BoardEntity;

public interface CCARepository extends JpaRepository<BoardEntity, Long> {

    @Query(value = "SELECT * FROM consult_cca WHERE product_category LIKE %:searchItem% AND (consult_title LIKE %:searchWord% OR consult_writer LIKE %:searchWord% OR consult_content LIKE %:searchWord%)", nativeQuery = true)
    Page<BoardEntity> findAllByProductCategory(@Param("searchItem") String searchItem,
            @Param("searchWord") String searchWord, PageRequest pageRequest);

    List<BoardEntity> findAllByConsultWriterOrderByConsultNumDesc(String userName);
}
