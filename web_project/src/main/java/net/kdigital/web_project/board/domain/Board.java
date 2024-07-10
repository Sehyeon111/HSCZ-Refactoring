package net.kdigital.web_project.board.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.kdigital.web_project.common.service.port.LocalDateTimeHolder;

@Getter
@ToString
public class Board {
    private Long consultNum;
    private String consultWriter;
    private String consultTitle;
    private String consultContent;
    private LocalDateTime consultDate;
    private String productCategory;
    private String productHscode;

    @Builder
    public Board(Long consultNum, String consultWriter, String consultTitle, String consultContent,
            LocalDateTime consultDate, String productCategory, String productHscode) {
        super();
        this.consultNum = consultNum;
        this.consultWriter = consultWriter;
        this.consultTitle = consultTitle;
        this.consultContent = consultContent;
        this.consultDate = consultDate;
        this.productCategory = productCategory;
        this.productHscode = productHscode;
    }

    // 생성자 (페이징을 위한 생성자, boardList에서 사용할 내용으로 추림)
    public Board(Long consultNum, String consultWriter, String consultTitle, LocalDateTime consultDate,
            String productCategory) {
        super();
        this.consultNum = consultNum;
        this.consultWriter = consultWriter;
        this.consultTitle = consultTitle;
        this.consultDate = consultDate;
        this.productCategory = productCategory;
    }

    public Board update(Board board, LocalDateTimeHolder localDateTimeHolder) {
        return Board.builder()
                .consultNum(this.getConsultNum())
                .consultWriter(this.getConsultWriter())
                .consultTitle(board.getConsultTitle())
                .consultContent(board.getConsultContent())
                .consultDate(localDateTimeHolder.setDateTime())
                .productCategory(board.getProductCategory())
                .productHscode(board.getProductHscode())
                .build();
    }

    public void setLocalDateTime(LocalDateTimeHolder localDateTimeHolder) {
        this.consultDate = localDateTimeHolder.setDateTime();
    }

}
