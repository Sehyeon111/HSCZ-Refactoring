package net.kdigital.web_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CustomerLikeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CustomerLikeDTO {
    private Long userLikeId;
    private String userId;
    private Long replyNum;
    private LocalDateTime likeDate;

    public static CustomerLikeDTO toDTO(CustomerLikeEntity customerLikeEntity, Long replyNum) {
        return CustomerLikeDTO.builder()
            .userLikeId(customerLikeEntity.getUserLikeId())
            .userId(customerLikeEntity.getUserId())
            .replyNum(replyNum)
            .likeDate(customerLikeEntity.getLikeDate())
            .build();
    }
}
