package net.kdigital.web_project.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import net.kdigital.web_project.common.domain.exception.ResouceNotFoundException;
import net.kdigital.web_project.common.service.port.LocalDateTimeHolder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import net.kdigital.web_project.board.domain.Board;
import net.kdigital.web_project.board.infrastructure.BoardEntity;
import net.kdigital.web_project.board.service.port.BoardRepository;

@Service
@Builder
@RequiredArgsConstructor
public class BoardService {
    @Value("${user.board.pageLimit}")
    public final int pageLimit;

    public final BoardRepository boardRepository;
    public final LocalDateTimeHolder localDateTimeHolder;

    public Page<Board> findAllConsultsbySearch(Pageable pageable, String searchWord, String searchItem) {
        int page = pageable.getPageNumber() - 1;

        Page<Board> boardList = boardRepository.findAllByProductCategory(
                searchItem,
                searchWord,
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "consult_num")));

        return boardList;
    }

    @Transactional
    public Long insertConsult(Board board) {
        board.setLocalDateTime(localDateTimeHolder);
        boardRepository.save(board);
        return board.getConsultNum();
    }

    public Board findById(Long consultNum) {
        return boardRepository.findById(consultNum)
                .orElseThrow(() -> new ResouceNotFoundException("Board", Long.toString(consultNum)));
    }

    @Transactional
    public void deleteOneConsult(Long consultNum) {
        Board board = findById(consultNum);
        boardRepository.deleteById(board.getConsultNum());
    }

    @Transactional
    public void updateOneConsult(Board board) {
        Board originalBoard = findById(board.getConsultNum());
        originalBoard.update(board, localDateTimeHolder);
    }

    public Page<Board> findAllConsults(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;

        Page<Board> boardList = boardRepository.findAll(
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "consultNum")));
        return boardList;
    }

    public List<Board> findAllConsultsbyuserId(String userName) {
        List<Board> boardList = boardRepository.findAllByConsultWriterOrderByConsultNumDesc(userName);
        return boardList;
    }

}