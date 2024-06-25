package net.kdigital.web_project.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BoardDTO;
import net.kdigital.web_project.entity.BoardEntity;

import net.kdigital.web_project.repository.CCARepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CCAService {
    @Value("${user.board.pageLimit}")
    int pageLimit;

    private final CCARepository ccaRepository;

    public Page<BoardDTO> findAllConsultsbySearch(Pageable pageable, String searchWord, String searchItem) {
        int page = pageable.getPageNumber() - 1;

        Page<BoardEntity> entityList = null;

        entityList = ccaRepository.findAllByProductCategory(
                searchItem,
                searchWord,
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "consult_num")));

        log.info("{}", entityList.get());

        Page<BoardDTO> dtoList = null; // DTO 생성자 추가

        dtoList = entityList.map(board -> new BoardDTO(
                board.getConsultNum(),
                board.getConsultWriter(),
                board.getConsultTitle(),
                board.getConsultDate(),
                board.getProductCategory()));

        return dtoList;
    }

    @Transactional
    public Long insertConsult(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toEntity(boardDTO);

        ccaRepository.save(boardEntity);

        return boardEntity.getConsultNum();

    }

    public BoardDTO selectOneConsult(Long consultNum) {
        Optional<BoardEntity> entity = ccaRepository.findById(consultNum);

        if (entity.isPresent()) {
            BoardEntity boardEntity = entity.get();
            return BoardDTO.toDTO(boardEntity);
        }

        return null;
    }

    @Transactional
    public void deleteOneConsult(Long consultNum) {
        Optional<BoardEntity> entity = ccaRepository.findById(consultNum);

        if (entity.isPresent()) {
            ccaRepository.deleteById(consultNum);
        }
    }

    @Transactional
    public void updateOneConsult(BoardDTO boardDTO) {
        Optional<BoardEntity> entityOptional = ccaRepository.findById(boardDTO.getConsultNum());

        if (entityOptional.isPresent()) {
            BoardEntity boardEntity = entityOptional.get();
            boardEntity.setConsultTitle(boardDTO.getConsultTitle());
            boardEntity.setConsultContent(boardDTO.getConsultContent());
            boardEntity.setConsultDate(LocalDateTime.now());
            boardEntity.setProductCategory(boardDTO.getProductCategory());
            boardEntity.setConsultWriter(boardDTO.getConsultWriter());
            ccaRepository.save(boardEntity); // 수정된 엔터티를 저장
        }
    }

    public Page<BoardDTO> findAllConsults(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;

        Page<BoardEntity> entityList = ccaRepository.findAll(
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "consultNum")));

        Page<BoardDTO> dtoList = entityList.map(board -> new BoardDTO(
                board.getConsultNum(),
                board.getConsultWriter(),
                board.getConsultTitle(),
                board.getConsultDate(),
                board.getProductCategory()));
        return dtoList;
    }

    public List<BoardEntity> findAllConsultsbyuserId(String userName) {
        List<BoardEntity> boardList = ccaRepository.findAllByConsultWriterOrderByConsultNumDesc(userName);

        // List<BoardDTO> dtoList = new ArrayList<>();
        //
        // for(BoardEntity temp : boardList) {
        // dtoList.add(BoardDTO.toDTO(temp));}

        return boardList;
    }

    public BoardDTO findByConsultNum(Long consultNum) {
        Optional<BoardEntity> entity = ccaRepository.findById(consultNum);

        BoardDTO dto = BoardDTO.toDTO(entity.get());
        return dto;
    }

}