package net.kdigital.web_project.board.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.board.domain.Board;
import net.kdigital.web_project.board.service.port.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    public final BoardJpaRepository boardJpaRepository;

    @Override
    public Page<Board> findAllByProductCategory(String searchItem, String searchWord, PageRequest of) {

        return boardJpaRepository.findAllByProductCategory(searchItem, searchWord, of).map(entity -> entity.toModel());
    }

    @Override
    public void save(Board board) {
        boardJpaRepository.save(BoardEntity.from(board));
    }

    // @Override
    // public Optional<BoardEntity> findById(PageRequest pageRequest) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'findById'");
    // }

    @Override
    public void deleteById(Long consultNum) {
        boardJpaRepository.deleteById(consultNum);
    }

    @Override
    public List<Board> findAllByConsultWriterOrderByConsultNumDesc(String userName) {
        return boardJpaRepository.findAllByConsultWriterOrderByConsultNumDesc(userName)
                .stream().map(entity -> entity.toModel()).collect(Collectors.toList());
    }

    @Override
    public Optional<Board> findById(Long consultNum) {
        return boardJpaRepository.findById(consultNum).map(entity -> entity.toModel());
    }

    @Override
    public Page<Board> findAll(PageRequest of) {
        return boardJpaRepository.findAll(of).map(entity -> entity.toModel());
    }

}
