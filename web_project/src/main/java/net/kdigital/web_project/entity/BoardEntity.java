package net.kdigital.web_project.entity;

import java.time.LocalDateTime;



import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.BoardDTO;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="CONSULT_CCA") 
public class BoardEntity {
	@SequenceGenerator(
		name="consult_seq"
		, sequenceName = "consult_seq"
		, initialValue = 1
		, allocationSize = 1
	)
	
	@Id
	@GeneratedValue(generator = "consult_seq")
	@Column(name="consult_num")
	private Long consultNum;
	
	@Column(name="consult_writer", nullable=false)
	private String consultWriter;
	
	@Column(name="consult_title")
	private String consultTitle;
	
	@Column(name="consult_content")
	private String consultContent;
	
	@Column(name="consult_date")
	@CreationTimestamp		// 게시글이 처음 생성될 때 자동으로 날짜 세팅
	private LocalDateTime consultDate;
	
	@Column(name="product_category")
	private String productCategory;
	
	@Column(name="product_hscode")
	private String productHscode;
	/*
	 * 댓글과의 관계설정
	 * mappedBy: one에 해당하는 테이블 엔티티
	 * CascadeType.REMOVE 이 값으로 on delete cascade를 설정
	 * fetch : LAZY는 지연호출, EAGER: 즉시 호출
	 */
	@OneToMany(mappedBy = "boardEntity", 
			cascade = CascadeType.REMOVE,
			orphanRemoval = true,
			fetch = FetchType.LAZY
			)
	@OrderBy("reply_num asc")
	
	
	// DTO를 전달받아 Entity 로 반환
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.consultNum(boardDTO.getConsultNum())
				.consultWriter(boardDTO.getConsultWriter())
				.consultTitle(boardDTO.getConsultTitle())
				.consultContent(boardDTO.getConsultContent())
				.consultDate(boardDTO.getConsultDate())
				.productCategory(boardDTO.getProductCategory())
				.productHscode(boardDTO.getProductHscode())
				.build();
	}
}