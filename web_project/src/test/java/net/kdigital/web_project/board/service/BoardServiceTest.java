package net.kdigital.web_project.board.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.kdigital.web_project.board.domain.Board;
import net.kdigital.web_project.board.service.port.BoardRepository;
import net.kdigital.web_project.mock.FakeBoardRepository;
import net.kdigital.web_project.mock.FakeLocalDateTimeHolder;

public class BoardServiceTest {

    public BoardService boardService;

    @BeforeEach
    void init() {
        BoardRepository fakBoardRepository = new FakeBoardRepository();
        boardService = BoardService.builder()
                .boardRepository(fakBoardRepository)
                .localDateTimeHolder(new FakeLocalDateTimeHolder(LocalDateTime.of(2024, Month.JULY, 1, 15, 30)))
                .build();
    }

    @Test
    public void Board를_저장할_수_있다() {
        // given
        Board board = Board.builder()
                .consultWriter("ssehn9327")
                .consultTitle("BoardTest01")
                .consultContent("BoardTesting01")
                .productCategory("기계류")
                .productHscode("8407")
                .build();
        // when
        boardService.insertConsult(board);
        Board result = boardService.findById(board.getConsultNum().longValue());
        // then
        assertThat(result.getConsultWriter()).isEqualTo("ssehn9327");
        assertThat(result.getConsultTitle()).isEqualTo("BoardTest01");
        assertThat(result.getConsultContent()).isEqualTo("BoardTesting01");
        assertThat(result.getProductCategory()).isEqualTo("기계류");
        assertThat(result.getProductHscode()).isEqualTo("8407");

    }
}
