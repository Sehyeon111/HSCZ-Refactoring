package net.kdigital.web_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BoardEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDTO {
    private Long consultNum;
    private String consultWriter;
    private String consultTitle;
    private String consultContent;
    private LocalDateTime consultDate;
    private String productCategory;
    private String productHscode;

    // 생성자 (페이징을 위한 생성자, boardList에서 사용할 내용으로 추림)
    public BoardDTO(Long consultNum, String consultWriter, String consultTitle, LocalDateTime consultDate,
            String productCategory) {
        super();
        this.consultNum = consultNum;
        this.consultWriter = consultWriter;
        this.consultTitle = consultTitle;
        this.consultDate = consultDate;
        this.productCategory = productCategory;
    }

    // Entity 받아서 --> DTO 반환
    public static BoardDTO toDTO(BoardEntity boardEntity) {
        return BoardDTO.builder()
                .consultNum(boardEntity.getConsultNum())
                .consultWriter(boardEntity.getConsultWriter())
                .consultTitle(boardEntity.getConsultTitle())
                .consultContent(boardEntity.getConsultContent())
                .consultDate(boardEntity.getConsultDate()) // 수정된 부분
                .productCategory(boardEntity.getProductCategory()) // 추가된 부분
                .productHscode(boardEntity.getProductHscode()) // 세율정보페이지에서 연결될때 HScode 가져옴
                .build();
    }
}
