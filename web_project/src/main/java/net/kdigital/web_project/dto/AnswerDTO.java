package net.kdigital.web_project.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.AnswerEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AnswerDTO {
    private Long replyNum;
    private Long consultNum;
    private String replyWriter;
    private String replyContent;
    private LocalDateTime replyDate;
    private int likeCount;

   
    

    // Entity 받아서  --> DTO 반환 
    public static AnswerDTO toDTO(AnswerEntity answerEntity, Long consultNum) {
		return AnswerDTO.builder()
				.replyNum(answerEntity.getReplyNum())
				.replyWriter(answerEntity.getReplyWriter())
				.replyContent(answerEntity.getReplyContent())
				.likeCount(answerEntity.getLikeCount())
				.consultNum(consultNum)
			    .replyDate(answerEntity.getReplyDate())
				.build();
}
}