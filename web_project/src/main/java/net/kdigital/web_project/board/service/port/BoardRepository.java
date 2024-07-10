package net.kdigital.web_project.board.service.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.kdigital.web_project.board.domain.Board;
import net.kdigital.web_project.board.infrastructure.BoardEntity;

public interface BoardRepository {

    Page<Board> findAllByProductCategory(String searchItem, String searchWord, PageRequest of);

    void save(Board board);

    // Optional<Board> findById(PageRequest pageRequest);

    void deleteById(Long consultNum);

    List<Board> findAllByConsultWriterOrderByConsultNumDesc(String userName);

    Optional<Board> findById(Long consultNum);

    Page<Board> findAll(PageRequest of);

}
