package net.kdigital.web_project.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.CustomerLikeDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name = "customer_like")
public class CustomerLikeEntity {

    @SequenceGenerator(
        name="customer_like_seq"
		, sequenceName="customer_like_seq"
		, initialValue = 1
		, allocationSize = 1
    )

    @Id
    @GeneratedValue(generator = "customer_like_seq")
    @Column(name = "user_like_id")
    private Long userLikeId;

    @Column(name = "user_id")
    private String userId;

    @CreationTimestamp
    @Column(name = "like_date")
    private LocalDateTime likeDate;

    /* AnswerEntity와의 관계 설정 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_num")
    private AnswerEntity answerEntity;

    public static CustomerLikeEntity toEntity(CustomerLikeDTO customerLikeDTO, AnswerEntity answerEntity) {
        return CustomerLikeEntity.builder()
            .userLikeId(customerLikeDTO.getUserLikeId())
            .userId(customerLikeDTO.getUserId())
            .likeDate(customerLikeDTO.getLikeDate())
            .answerEntity(answerEntity)
            .build();
    }
}

